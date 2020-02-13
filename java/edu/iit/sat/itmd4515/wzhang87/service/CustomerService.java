/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Customer service class with methods necessary for CRUD operation
 * @author mrslo
 */
@Stateless
public class CustomerService extends AbstractService<Customer> {

    /**
     *
     */
    public CustomerService() {
        super(Customer.class);
    }

    /**
     * Inherit from constructor
     * @param c
     */
    public void create(Customer c) {
        em.persist(c);
    }

    /**
     * Inherit from constructor
     * @param c
     * @return
     */
    public Customer createCustomer(Customer c) {
        em.persist(c);
        //in middle of the EJB method, if we need a commit, we need to manage that with EM
        em.flush();
        return c;
        //at the end of EJB method, we expect the persistence context to be flushed, written in the database
    }

    /**
     * CRUD function Inherit from constructor
     * @param c
     */
    public void update(Customer c) {
        em.merge(c);
    }

    /**
     * CRUD function Inherit from constructor
     * @param c
     */
    public void remove(Customer c) {
        em.remove(em.merge(c));
    }

    //now we can adjust the findall

    /**
     * Find all the customers listed in the database
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    /**
     * Find a particular customer based on his or her customerID
     * @param id
     * @return
     */
    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }

    /**
     * Find a customer based on their first name
     * @param name
     * @return
     */
    public Customer findByName(String name) {
        return em.createNamedQuery("Customer.findByName", Customer.class).setParameter("customerFirstName", name).getSingleResult();
    }

    /**
     * Find a customer based on their username
     * @param username
     * @return
     */
    public Customer findByUsername(String username) {
        return em.createNamedQuery("Customer.findByUsername", Customer.class).setParameter("username", username).getSingleResult();
    }

    /**
     * update customer information for the edit address function
     * @param customerFromForm
     */
    public void editCustomerFromForm(Customer customerFromForm) {
        //This is the reference to the game in db
        Customer customerFromDB = em.getReference(Customer.class, customerFromForm.getId());
        customerFromDB.setCustomerAddress(customerFromForm.getCustomerAddress());
        customerFromDB.setCustomerCity(customerFromForm.getCustomerCity());
        customerFromDB.setCustomerState(customerFromForm.getCustomerState());
        customerFromDB.setCustomerZip(customerFromForm.getCustomerZip());
        customerFromDB.setCustomerPhone(customerFromForm.getCustomerPhone());
        em.merge(customerFromDB);
    }

    /**
     * update customer information with orders persisted
     * @param customerFromForm
     * @param e
     */
    public void editCustomerFromFormWithOrders(Customer customerFromForm, OrderInfo e) {
        //This is the reference to the game in db
        Customer customerFromDB = em.getReference(Customer.class, customerFromForm.getId());
        customerFromDB.setCustomerAddress(customerFromForm.getCustomerAddress());
        customerFromDB.setCustomerCity(customerFromForm.getCustomerCity());
        customerFromDB.setCustomerState(customerFromForm.getCustomerState());
        customerFromDB.setCustomerZip(customerFromForm.getCustomerZip());
        customerFromDB.setCustomerPhone(customerFromForm.getCustomerPhone());
        customerFromDB.getOrders().add(e);
        em.merge(customerFromDB);
    }

}
