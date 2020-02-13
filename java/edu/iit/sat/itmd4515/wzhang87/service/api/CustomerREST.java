/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service.api;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mrslo
 */
@Path("/v1/customers")
public class CustomerREST {

    @EJB
    CustomerService customerService;

    /**
     *
     * @return
     */
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String pingMe() {
        return "You have been pinged form /v1/customers";

    }

    /**
     *
     * @param name
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Customer> getCustomers(@QueryParam("customerFirstName") String name) {

        List<Customer> results = new ArrayList<>();
        if (null == name) {
            results = customerService.findAll();
        } else {
            Customer c = customerService.findByName(name);
        }
        return results;
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") Long id) {
        return customerService.find(id);
    }

    /**
     *
     * @param c
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer createNewCustomer(Customer c){
        return customerService.createCustomer(c);
        
    }

    //@GET
    //@Path("/{name}")
    //public Customer getCustomerByName(@PathParam("name") String name)
    //{
    //    return customerService.findByName(name);
    //}
}
