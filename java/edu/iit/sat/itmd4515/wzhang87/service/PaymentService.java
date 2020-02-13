/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.Payment;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Payment service class with methods necessary for CRUD operation
 * @author mrslo
 */
@Stateless
public class PaymentService extends AbstractService {

    /**
     *
     */
    public PaymentService() {
        super(Payment.class);
    }

    /**
     * CRUD function Inherit from constructor
     * @param p
     */
    public void create(Payment p) {
        em.persist(p);
    }

    /**
     * CRUD function Inherit from constructor
     * @param p
     * @return
     */
    public Payment createPayment(Payment p) {
        em.persist(p);
        //in middle of the EJB method, if we need a commit, we need to manage that with EM
        em.flush();
        return p;
        //at the end of EJB method, we expect the persistence context to be flushed, written in the database
    }

    /**
     * CRUD function Inherit from constructor
     * @param p
     */
    public void update(Payment p) {
        em.merge(p);
    }

    /**
     * CRUD function Inherit from constructor
     * @param p
     */
    public void remove(Payment p) {
        em.remove(em.merge(p));
    }

    /**
     * Find all payment stored in the database
     * @return
     */
    @Override
    public List findAll() {
        return em.createNamedQuery("Payment.findAll", entityClass).getResultList();
    }

    /**
     * Find a particular payment based on the paymentID
     * @return
     */
    public List findById() {
        return em.createNamedQuery("Payment.findById", entityClass).getResultList();
    }

}
