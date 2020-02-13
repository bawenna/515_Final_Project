/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import edu.iit.sat.itmd4515.wzhang87.domain.VintageGame;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * OrderInfo service class with methods necessary for CRUD operation
 *
 * @author mrslo
 */
@Stateless
public class OrderInfoService extends AbstractService<OrderInfo> {

    /**
     *
     */
    public OrderInfoService() {
        super(OrderInfo.class);
    }

    /**
     * CRUD function Inherit from constructor
     *
     * @param o
     */
    public void create(OrderInfo o) {
        em.persist(o);
    }

    /**
     * CRUD function Inherit from constructor
     *
     * @param o
     * @return
     */
    public OrderInfo createOrder(OrderInfo o) {
        em.persist(o);
        //in middle of the EJB method, if we need a commit, we need to manage that with EM
        em.flush();
        return o;
        //at the end of EJB method, we expect the persistence context to be flushed, written in the database
    }

    /**
     * CRUD function Inherit from constructor
     *
     * @param o
     */
    public void update(OrderInfo o) {
        em.merge(o);
    }

    /**
     * CRUD function Inherit from constructor
     *
     * @param o
     */
    public void remove(OrderInfo o) {
        em.remove(em.merge(o));
    }

    /**
     * Find all the orders from the table
     *
     * @return
     */
    @Override
    public List findAll() {
        return em.createNamedQuery("OrderInfo.findAll", entityClass).getResultList();
    }

    /**
     * Find a particular order by its orderID
     *
     * @param id
     * @return
     */
    public List findById(Long id) {
        return em.createNamedQuery("OrderInfo.findById", entityClass).getResultList();
    }

    /**
     * Find a particular order for a customer based on the userID
     *
     * @param id
     * @return
     */
    public List findByUserId(Long id) {
        return em.createNamedQuery("OrderInfo.findByUserId", OrderInfo.class).getResultList();
    }

    /**
     *
     * @param game
     * @param quantity
     */
    public void addToCart(VintageGame game, int quantity) {
        OrderInfo order = new OrderInfo();
        order.addGame(game);
        em.persist(order);
        System.out.println("An order has been persisted");
    }

    /**
     *
     * @param order
     * @param customer
     */
    public void createAndAddCustomer(OrderInfo order, Customer customer) {
        customer = em.getReference(Customer.class, customer.getId());
        super.create(order);
        em.flush();
        customer.getOrders().add(order);
        em.merge(customer);

    }

    /**
     *
     * @return
     */
    public Class<OrderInfo> getEntityClass() {
        return entityClass;
    }

    /**
     *
     * @param entityClass
     */
    public void setEntityClass(Class<OrderInfo> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Edit the order information once a customer submitted an order
     *
     * @param orderFromAdminForm
     * @param orderFromForm
     */
    public void editOrderFromAdminForm(OrderInfo orderFromAdminForm) {
        OrderInfo orderFromDatabase = em.getReference(OrderInfo.class, orderFromAdminForm.getId());
        orderFromDatabase.setOrderStatus(orderFromAdminForm.getOrderStatus());
        em.merge(orderFromDatabase);
    }

    /*
public void updateOrder(OrderInfo orderFromForm) {
        // first I might get a reference to the order thats currenly in the db
        OrderInfo orderFromDatabase = em.getReference(OrderInfo.class, orderFromForm.getId());
        orderFromDatabase.setOrderStatus(orderFromForm.getOrderStatus());
        orderFromDatabase.addCustomer(orderFromForm.getCustomer());
        orderFromDatabase.setPayment(orderFromForm.getPayment());
        orderFromDatabase.setCreatedTimeStamp(orderFromForm.getCreatedTimeStamp());
        List<VintageGame> gamesFromForm = new ArrayList<>(orderFromForm.getGames());
        gamesFromForm.forEach((VintageGame g) -> {
            if (!orderFromDatabase.getGames().contains(g)) {
                g.getOrders().add(orderFromDatabase);
            }

            em.merge(g);
        });

        List<VintageGame> gamesFromDb = new ArrayList<>(orderFromDatabase.getGames());
        gamesFromDb.forEach((VintageGame g) -> {
            if (!orderFromForm.getGames().contains(g)) {
                g.getOrders().remove(gamesFromDb);
            }

            em.merge(g);
        });

        em.merge(orderFromDatabase);
    }
     */
}
