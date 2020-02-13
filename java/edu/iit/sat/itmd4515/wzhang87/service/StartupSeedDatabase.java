/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.GameGenre;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import edu.iit.sat.itmd4515.wzhang87.domain.VintageGame;
import edu.iit.sat.itmd4515.wzhang87.domain.security.Group;
import edu.iit.sat.itmd4515.wzhang87.domain.security.User;
import edu.iit.sat.itmd4515.wzhang87.web.GameController;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is a class for preseeding the database with sample contents
 *
 * @author mrslo
 */
@Startup
@Singleton
public class StartupSeedDatabase {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    GameController gc;

    /**
     *
     */
    @EJB
    protected OrderInfoService orderSvc;

    /**
     *
     */
    @EJB
    protected GameService gameSvc;

    /**
     *
     */
    @EJB
    protected CustomerService customerSvc;
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;

    /**
     *
     */
    public StartupSeedDatabase() {
    }

    @PostConstruct
    private void seedDatabase() {
        //seeding the database with pre-defined records
        //create admin user and groups
        User adminUser = new User("admin", "admin", true);
        Group adminGroup = new Group("ADMIN_GROUP", "This is the group for admins!");
        adminUser.addGroup(adminGroup);
        groupSvc.create(adminGroup);
        userSvc.create(adminUser);

        Group userGroup = new Group("USER_GROUP", "This is the group for users!");

        User customer1 = new User("customer1", "customer1", true);
        User customer2 = new User("customer2", "customer2", true);
        customer1.addGroup(userGroup);
        customer2.addGroup(userGroup);

        groupSvc.create(userGroup);
        userSvc.create(customer1);
        userSvc.create(customer2);

        //associate security domain to business domain
        VintageGame v1 = new VintageGame("Super Mario 2", GameGenre.Action, 20.99, LocalDate.of(1992, 3, 23), "NJJ");
        VintageGame v2 = new VintageGame("Legend of Heroes", GameGenre.RPG, 24.99, LocalDate.of(1998, 3, 12), "LOH");
        VintageGame v3 = new VintageGame("Doom", GameGenre.Action, 18.99, LocalDate.of(1996, 2, 13), "BAD");
        VintageGame v4 = new VintageGame("Devil May Cry", GameGenre.Action, 41.99, LocalDate.of(2001, 5, 13), "DMC");
        VintageGame v5 = new VintageGame("Yakuza", GameGenre.Action, 49.99, LocalDate.of(2005, 2, 13), "BAD");
        VintageGame v6 = new VintageGame("Grand Theft Auto 2", GameGenre.Action, 59.99, LocalDate.of(2002, 3, 21), "GTA");
        VintageGame v7 = new VintageGame("Initial D Extreme Stage", GameGenre.Racing, 15.99, LocalDate.of(2004, 7, 21), "IDD");
        VintageGame v8 = new VintageGame("Grooove Coaster Heaven Festival", GameGenre.Technical, 49.99, LocalDate.of(2015, 8, 12), "GCC");
        VintageGame v9 = new VintageGame("Splinter Cell Double Agent", GameGenre.Action, 34.99, LocalDate.of(2007, 2, 13), "SCP");
        VintageGame v10 = new VintageGame("Dragon's Crown", GameGenre.Action, 19.99, LocalDate.of(2014, 7, 13), "DCC");
        VintageGame v11 = new VintageGame("NBA 2K 10", GameGenre.Sport, 18.99, LocalDate.of(2010, 10, 13), "NBA");

        Customer c1 = new Customer("Wenzhao", "Zhang", "2955 S Benson St", "Chicago", "IL", "60616", "3125659881", "wzhang87@hawk.iit.edu");
        Customer c2 = new Customer("Da", "Ze", "2955 S Benson St", "Chicago", "IL", "60616", "3125659881", "wzhang87@hawk.iit.edu");

        OrderInfo o1 = new OrderInfo(20.99, LocalDate.of(2019, 3, 6), "Processing");
        OrderInfo o2 = new OrderInfo(19.99, LocalDate.of(2019, 3, 6), "Processing");
        OrderInfo o3 = new OrderInfo(19.99, LocalDate.of(2019, 3, 6), "Processing");
        o1.addGame(v1);
        o2.addGame(v2);
        o1.addGame(v2);
        o3.addGame(v2);
        c1.setUser(customer1);
        c2.setUser(customer2);
        o1.addCustomer(c1);
        o2.addCustomer(c2);
        o3.addCustomer(c1);
        c1.getOrders().add(o1);
        c1.getOrders().add(o3);
        c2.getOrders().add(o2);

        //CRUD - Creaate / Read
        gameSvc.create(v1);
        gameSvc.create(v2);
        gameSvc.create(v3);
        gameSvc.create(v4);
        gameSvc.create(v5);
        gameSvc.create(v6);
        gameSvc.create(v7);
        gameSvc.create(v8);
        gameSvc.create(v9);
        gameSvc.create(v10);
        gameSvc.create(v11);

        orderSvc.create(o1);
        orderSvc.create(o2);
        orderSvc.create(o3);
        customerSvc.create(c1);
        customerSvc.create(c2);
        em.flush();
        System.out.println("Testing:" + c1.getOrders().size());

        //CRUD - Update
        //CRUD - Delete
    }
}
