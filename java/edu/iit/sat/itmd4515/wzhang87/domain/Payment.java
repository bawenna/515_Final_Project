/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generate the Payment table, which contains general information about the payment
 * @author mrslo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQuery(name = "Payment.findAll", query = "select p from Payment p")
@NamedQuery(name = "Payment.findById", query = "select p from VintageGame p where p.id =:id")
public class Payment extends AbstractIdentifiedEntity {
    
    private String paymentType;
    private LocalDate paymentDate;
    private String paymentStatus;
    
    /**
     *
     */
    public Payment() {
    }

    /**
     *
     * @param paymentType
     * @param paymentDate
     * @param paymentStatus
     */
    public Payment(String paymentType, LocalDate paymentDate, String paymentStatus) {
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Get the value of paymentType
     *
     * @return the value of paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Set the value of paymentType
     *
     * @param paymentType new value of paymentType
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     *
     * @return
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     *
     * @param paymentDate
     */
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     *
     * @return
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     *
     * @param paymentStatus
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    
}
