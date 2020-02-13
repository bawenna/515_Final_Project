/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.web;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.GameGenre;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import edu.iit.sat.itmd4515.wzhang87.domain.VintageGame;
import edu.iit.sat.itmd4515.wzhang87.service.GameService;
import edu.iit.sat.itmd4515.wzhang87.service.OrderInfoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mrslo
 */
@Named
@RequestScoped
public class OrderController {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    private static final Logger LOG = Logger.getLogger(OrderController.class.getName());
    @EJB
    private GameService gameSvc;
    private VintageGame game;
    private OrderInfo order;
    @EJB
    private GameService gameService;
    @EJB
    private OrderInfoService orderSvc;
    private long selectedGame;
    private double newPrice;
    private List<OrderInfo> ordersList;
    private String orderStatus;
    private Customer customer;

    /**
     *
     */
    public OrderController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside OrderController.postConstruct()");
        order = new OrderInfo();
    }

    //prepare action method
    /**
     * Prepare action for editing an order
     *
     * @param order
     * @return
     */
    public String prepareEditOrder(OrderInfo order) {
        this.order = order;
        return "/admin/editOrder.xhtml";
    }

    /**
     *
     * @return @throws Exception
     */
    public List<OrderInfo> getOrders()
            throws Exception {
        Query orderQuery = em.createNamedQuery("OrderInfo.findAll", OrderInfo.class);
        ordersList = orderQuery.getResultList();
        return ordersList;
    }

    /**
     * Prepare action for viewing an order
     *
     * @param Order
     * @return
     */
    public String prepareViewOrder(OrderInfo Order) {
        LOG.info("Inside GameController.prepareViewOrder" + order.toString());
        this.order = order;
        return "/admin/viewOrder.xhtml";
    }

    /**
     * Prepare action for viewing all orders
     * @param 
     * @return
     */
    public String prepareViewAllOrders() {
        return "/admin/allOrders.xhtml";
    }

    /**
     *
     * @param order
     * @return
     */
    public String prepareViewOrderForUser(OrderInfo order) {
        LOG.info("Inside GameController.prepareViewOrderForUser" + order.toString());
        this.order = order;
        return "/user/viewOrder.xhtml";
    }

    /**
     * Save the editted order or create a new one based on the ID
     * @return
     */
    public String doSaveOrder() {
        LOG.info("Inside GameController.doSaveOrder" + order.toString());

        if (this.order.getId() != null) {
            LOG.info("Call and update in the service layer with" + this.order.toString());
            orderSvc.editOrderFromAdminForm(order);
        } else {
            LOG.info("Call and create in the service layer with" + this.game.toString());
            orderSvc.create(order);

        }
        return "/admin/allOrders.xhtml";
    }

    public String formatOrdersAsString(OrderInfo o) {
        List<String> orderIds = new ArrayList<>();
        for (VintageGame g : o.getGames()) {
            orderIds.add(g.getName());
        }
        return String.join(",", orderIds);
    }

    /**
     *
     * @return
     */
    public String updateOrder() {
        LOG.info("Inside GameController.updateOrder" + order.toString());
        orderSvc.editOrderFromAdminForm(order);
        return "/admin/allOrders.xhtml";
    }

    /**
     *
     * @return
     */
    public String executeSaveOrder() {
        LOG.info("Inside GameController.executeSaveOrder" + order.toString());
        orderSvc.create(order);
        return "/admin/orderok.xhtml";
    }

    public String prepareCreateGame() {
        LOG.info("Inside GameController.prepareCreateGame" + game.toString());
        this.game = new VintageGame();
        return "/admin/edit.xhtml";
    }

    /**
     *
     * @return
     */
    public String getQuery() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
    }

    /**
     *
     * @return
     */
    public VintageGame getGameResult() {
        return gameService.returnGame(getQuery());
    }

    /**
     *
     * @return
     */
    public VintageGame getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(VintageGame game) {
        this.game = game;
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
    public OrderInfoService getOrderSvc() {
        return orderSvc;
    }

    /**
     *
     * @param orderSvc
     */
    public void setOrderSvc(OrderInfoService orderSvc) {
        this.orderSvc = orderSvc;
    }

    /**
     *
     * @return
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     *
     * @param orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // edit the amount of a existing product
    /**
     * Get the value of game
     *
     * @return the value of game
     */
    /**
     * Set the value of game
     *
     * @param game new value of game
     */
}
