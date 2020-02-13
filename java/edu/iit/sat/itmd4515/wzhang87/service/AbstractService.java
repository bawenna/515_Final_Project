/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Abstract class with methods necessary for CRUD operation
 * @author mrslo
 * @param <T>
 */
public abstract class AbstractService<T> {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    //introduce a construstor for our subclasses, our subclasses can provide the value of the class to be used in the entity

    /**
     *
     */
    protected Class<T> entityClass;
    //pass parameter as constructor

    /**
     *
     * @param entityClass
     */
    public AbstractService(Class entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @param entity
     */
    public void create(T entity) {
        em.persist(entity);
    }

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     *
     * @param entity
     */
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    //find an entity, not limiting to orderinfo now

    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        return em.find(entityClass, id);
    }
    //abstract method since owner.findall is hardcoded

    /**
     *
     * @return
     */
    public abstract List<T> findAll();

}
