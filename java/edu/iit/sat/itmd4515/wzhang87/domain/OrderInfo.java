/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generate the OrderInfo table, which contains general information about the order
 * @author mrslo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQuery(name = "OrderInfo.findAll", query = "select o from OrderInfo o")
@NamedQuery(name = "OrderInfo.findById", query = "select o from OrderInfo o where o.id = :id")
@NamedQuery(name = "OrderInfo.findByUserId", query = "select o from OrderInfo o where o.customer.id = :id")

public class OrderInfo extends AbstractIdentifiedEntity {

    private double orderTotal;
    private LocalDate orderDate;
    private String orderStatus;

    //this is the owning side
    //The owning side will get the foreign key in the db and controls the db updates
    @OneToOne
    private Payment payment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    /**
     *
     */
    @ManyToMany
    //@JoinTable(name = "ORDER_GAMES", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "GAME_ID"))
    @JoinTable(name = "ORDER_GAMES", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "GAME_ID"))
    public List<VintageGame> games = new ArrayList<>();

    /**
     *
     */
    public OrderInfo() {
    }

    /**
     * addGame is a helper method to manage both side of the bi-directional mtm
     * relationship
     *
     * @param game
     */
    public void addGame(VintageGame game) {
        if (!this.games.contains(game)) {
            this.games.add(game);
        }
        if (!game.getOrders().contains(this)) {
            game.getOrders().add(this);
        }

    }

    /**
     *
     * @param game
     */
    public void removeGame(VintageGame game) {
        if (this.games.contains(game)) {
            this.games.remove(game);
        }
        if (game.getOrders().contains(this)) {
            game.getOrders().remove(this);
        }
    }

    /**
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {
        this.setCustomer(customer);
    }

    /**
     *
     * @param orderTotal
     * @param orderDate
     * @param orderStatus
     */
    public OrderInfo(double orderTotal, LocalDate orderDate, String orderStatus) {
        this.orderTotal = orderTotal;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param orderDate
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return "OrderInfo{" + "orderTotal=" + orderTotal + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + '}';
    }

}
