/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain;

import edu.iit.sat.itmd4515.wzhang87.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Generates the Customer table
 * @author mrslo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
@NamedQuery(name = "Customer.findByName", query = "select c from Customer c where c.customerFirstName = :customerFirstName")
@NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.user.userName = :username")
@Entity
public class Customer extends AbstractIdentifiedEntity {

    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerZip;
    private String customerPhone;
    private String customerEmail;
    //this is the inverse side

    @OneToMany(mappedBy = "customer")
    @XmlTransient
    @JsonbTransient
    private List<OrderInfo> orders = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Customer() {

    }

    /**
     *
     * @param customerFirstName
     */
    public Customer(String customerFirstName) {
        this.customerFirstName = customerFirstName;

    }

    /**
     *
     * @param customerFirstName
     * @param customerLastName
     * @param customerAddress
     * @param customerCity
     * @param customerState
     * @param customerZip
     * @param customerPhone
     * @param customerEmail
     */
    public Customer(String customerFirstName, String customerLastName, String customerAddress, String customerCity, String customerState, String customerZip, String customerPhone, String customerEmail) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    /**
     * Get the value of customerFirstName
     *
     * @return the value of customerFirstName
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * Set the value of customerFirstName
     *
     * @param customerFirstName new value of customerFirstName
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     *
     * @return
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     *
     * @param customerLastName
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    /**
     *
     * @return
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *
     * @return
     */
    public String getCustomerCity() {
        return customerCity;
    }

    /**
     *
     * @param customerCity
     */
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    /**
     *
     * @return
     */
    public String getCustomerState() {
        return customerState;
    }

    /**
     *
     * @param customerState
     */
    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    /**
     *
     * @return
     */
    public String getCustomerZip() {
        return customerZip;
    }

    /**
     *
     * @param customerZip
     */
    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    /**
     *
     * @return
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     *
     * @param customerPhone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     *
     * @return
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     *
     * @param customerEmail
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     *
     * @return
     */
    public List<OrderInfo> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
