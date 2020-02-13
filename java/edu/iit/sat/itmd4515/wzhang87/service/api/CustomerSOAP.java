/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service.api;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.service.CustomerService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author mrslo
 */
@WebService
public class CustomerSOAP {

    @EJB
    CustomerService customerService;

    /**
     *
     */
    public CustomerSOAP() {
    }

    /**
     *
     * @param name
     * @return
     */
    @WebMethod
    public Customer createNewCustomer(String name) {
        return customerService.createCustomer(new Customer(name));
    }

}
