/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.web;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.GameGenre;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import edu.iit.sat.itmd4515.wzhang87.domain.Payment;
import edu.iit.sat.itmd4515.wzhang87.domain.VintageGame;
import edu.iit.sat.itmd4515.wzhang87.service.CustomerService;
import edu.iit.sat.itmd4515.wzhang87.service.GameService;
import edu.iit.sat.itmd4515.wzhang87.service.OrderInfoService;
import edu.iit.sat.itmd4515.wzhang87.service.PaymentService;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Methods relates to the cart operations
 * @author mrslo
 */
@Named
@SessionScoped
@ManagedBean
public class CartController implements Serializable {

    private List<VintageGame> games = new ArrayList<>();
    @EJB
    OrderInfoService orderInfoSvc;
    private OrderInfo order = new OrderInfo();
    @EJB
    private PaymentService paymentSvc;
    private Payment payment;
    @EJB
    CustomerService customerSvc;
    double orderTotal;
    double formattedOrderTotal;
    @Inject
    LoginController loginController;
    private Customer customer;
    private int quantity;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    ;
    @PostConstruct
    private void postConstruct() {
        customer = customerSvc.findByUsername(loginController.getRemoteUser());
        payment = new Payment();
    }
    @EJB
    GameService gameSvc;

    /**
     *
     */
    public CartController() {
        System.out.println("Cart Created");
    }

    /**
     * Add the selected game entity to the order entity
     *
     * @param g
     * @throws Exception
     */
    public void addToOrder(VintageGame g) throws Exception {
        order = this.order;
        order.addGame(g);
        orderTotal = orderTotal + g.price;
        formattedOrderTotal = Double.parseDouble(df2.format(orderTotal));
        order.addCustomer(customer);
        customerSvc.update(customer);
        System.out.println(order.toString());
        System.out.println("Total:" + formattedOrderTotal);
    }

    /**
     * remove the game entity from the order entity
     *
     * @param g
     * @return
     */
    public String deleteFromCart(VintageGame g) {
        order = this.order;
        order.removeGame(g);
        orderTotal = orderTotal - g.price;
        formattedOrderTotal = Double.parseDouble(df2.format(orderTotal));
        System.out.println("Total:" + order.getOrderTotal());
        customerSvc.update(customer);
        return "/user/cart.xhtml?faces-redirect=true";
    }

    /**
     * Go to the cart
     *
     * @return
     */
    public String toCart() {
        return "/user/cart.xhtml";
    }

    /**
     * Proceed to checkout
     *
     * @return
     */
    public String toCheckOut() {
        System.out.println("Total:" + order.getOrderTotal());
        System.out.println("Customer address:" + customer.getCustomerAddress());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("Unpaid");
        return "/user/checkout.xhtml";
    }

    /**
     * Proceed to enter shipping address
     *
     * @return
     */
    public String toShipping() {
        System.out.println("Customer address:" + customer.getCustomerAddress());
        customerSvc.update(customer);
        return "/user/shipping.xhtml";

    }

    /**
     * Proceed to place an order, set the orderTotal, PaymentID, and
     * OrderStatus, the Order entity will be created here
     *
     * @return
     */
    public String placeOrder() {
        order = this.order;
        payment.setPaymentStatus("Processed");
        payment.setPaymentDate(LocalDate.now());
        paymentSvc.create(payment);
        order.setPayment(payment);
        order.setOrderStatus("Paid");
        order.setOrderTotal(formattedOrderTotal);
        orderInfoSvc.create(order);
        customer.getOrders().add(order);
        customerSvc.update(customer);
        this.payment = payment;
        order = new OrderInfo();
        orderTotal = 0;
        customer = customerSvc.findByUsername(loginController.getRemoteUser());
        return "/user/checkoutSuccess.xhtml";
    }

    /**
     * Return to the homePage
     *
     * @return
     */
    public String returnHome() {
        customer = customerSvc.findByUsername(loginController.getRemoteUser());
        return "/user/welcome?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public List<VintageGame> getGames() {
        return games;
    }

    /**
     *
     * @param games
     */
    public void setGames(List<VintageGame> games) {
        this.games = games;
    }

    /**
     *
     * @return
     */
    public OrderInfoService getOrderInfoSvc() {
        return orderInfoSvc;
    }

    /**
     *
     * @param orderInfoSvc
     */
    public void setOrderInfoSvc(OrderInfoService orderInfoSvc) {
        this.orderInfoSvc = orderInfoSvc;
    }

    /**
     *
     * @return
     */
    public OrderInfo getOrder() {
        return order;
    }

    /**
     *
     * @param order
     */
    public void setOrder(OrderInfo order) {
        this.order = order;
    }

    /**
     *
     * @return
     */
    public CustomerService getCustomerSvc() {
        return customerSvc;
    }

    /**
     *
     * @param customerSvc
     */
    public void setCustomerSvc(CustomerService customerSvc) {
        this.customerSvc = customerSvc;
    }

    /**
     *
     * @return
     */
    public double getOrderTotal() {
        return orderTotal;
    }

    /**
     *
     * @param orderTotal
     */
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     *
     * @return
     */
    public LoginController getLoginController() {
        return loginController;
    }

    /**
     *
     * @param loginController
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
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

    /**
     *
     * @return
     */
    public GameService getGameSvc() {
        return gameSvc;
    }

    /**
     *
     * @param gameSvc
     */
    public void setGameSvc(GameService gameSvc) {
        this.gameSvc = gameSvc;
    }

    /**
     *
     * @return
     */
    public PaymentService getPaymentSvc() {
        return paymentSvc;
    }

    /**
     *
     * @param paymentSvc
     */
    public void setPaymentSvc(PaymentService paymentSvc) {
        this.paymentSvc = paymentSvc;
    }

    /**
     *
     * @return
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     *
     * @param payment
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFormattedOrderTotal() {
        return formattedOrderTotal;
    }

    public void setFormattedOrderTotal(double formattedOrderTotal) {
        this.formattedOrderTotal = formattedOrderTotal;
    }

}
