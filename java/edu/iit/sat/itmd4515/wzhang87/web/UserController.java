/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.web;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import edu.iit.sat.itmd4515.wzhang87.service.CustomerService;
import edu.iit.sat.itmd4515.wzhang87.service.OrderInfoService;
import java.io.Serializable;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mrslo
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    @EJB
    private CustomerService customerSvc;
    @EJB
    private OrderInfoService orderInfoSvc;
    private Customer customer;
    @Inject
    LoginController loginController;
    private List<OrderInfo> ordersList;
    private OrderInfo order;

    /**
     *
     */
    public UserController() {
    }

    /**
     * Grab orders from customers with matching customerID
     *
     * @return
     * @throws Exception
     */
    public List<OrderInfo> getOrders()
            throws Exception {
        Query orderQuery = em.createNamedQuery("OrderInfo.findByUserId", OrderInfo.class);
        customer = customerSvc.findByUsername(loginController.getRemoteUser());
        orderQuery.setParameter("id", customer.id);
        ordersList = orderQuery.getResultList();
        return ordersList;
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("PostConstruct() is fired by CustomerController");
        customer = customerSvc.findByUsername(loginController.getRemoteUser());
        System.out.println("Customer Name" + customer.getOrders().size());
    }

    //prepare method
    /**
     *
     * @param o
     * @return
     */
    public String prepareViewOrders(OrderInfo o) {
        LOG.info("Inside prepareViewPet with pet " + o.toString());
        this.order = o;
        return "/welcome.xhtml";
    }

    //action methods
    /**
     *
     * @param order
     * @return
     */
    public String doViewOrders(OrderInfo order) {
        LOG.info("Inside DoView Method: " + order.toString());
        return "/welcome.xhtml";
    }

    /**
     * Go back to the home screen for user
     * @return
     */
    public String doGoBack() {
        return "/user/home.xhtml";
    }

    /**
     * Go back to all the orders to user
     * @return
     */
    public String doGoBackToOrders() {
        return "/user/welcome.xhtml";
    }

    /**
     * Go back to admin's panel for all the orders
     * @return
     */
    public String doGoBackToOrdersAdmin() {
        return "/admin/allOrders.xhtml";
    }

    /**
     *
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
