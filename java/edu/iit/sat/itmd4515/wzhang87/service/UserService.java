/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.security.Group;
import edu.iit.sat.itmd4515.wzhang87.domain.security.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * User service class with methods necessary for CRUD operation
 * @author mrslo
 */
@Stateless
public class UserService extends AbstractService {

    @EJB
    CustomerService customerSvc;

    /**
     *
     */
    public UserService() {
        super(User.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", entityClass).getResultList();
    }

    // this method provide log in service tot the users

    /**
     *
     * @param userName
     * @param password
     */
    public void register(String userName, String password) {
        Query query = em.createNamedQuery("User.CheckExistingUser", User.class);
        query.setParameter("currentName", userName);
        List<User> results = query.getResultList();
        if (results.size() > 1) {
            // Exception.... Duplicate entry in the databse...
            System.out.println("Duplicate entries already in the databse. Need to recheck");
        } else if (results.size() == 1) {
            // Exception.... Username already exist, try with different name
            System.out.println(userName + " is already registered.");
        }
        // create a new user and persist it to the underlying relational database
        User user = new User(userName, password, true);
        Group userGroup = new Group("USER_GROUP", "This is the group for users!");
        user.addGroup(userGroup);
        Customer customer = new Customer();

        customer.setUser(user);
        System.out.println("Persisting user " + userName);
        em.persist(user);
        customerSvc.create(customer);

    }

}
