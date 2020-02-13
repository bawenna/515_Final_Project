/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package edu.iit.sat.itmd4515.wzhang87.domain;

import java.time.LocalDate;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mrslo

public class GameTest extends AbstractJPATest {

    private static final Logger LOG = Logger.getLogger(GameTest.class.getName());

    //testing my existing game
    @Test
    public void testFindExistingGame() {
        //if you knew the PK, easy as em.find
        //this lets us use named parameters and queries
        VintageGame seed = em.createNamedQuery("VintageGame.findByName", VintageGame.class)
                .setParameter("name", "seed")
                .getSingleResult();
        assertTrue("Name should match!", "seed".equals(seed.getName()));

    }

    @Test
    public void testCreateNewValidGame() {

        VintageGame game = new VintageGame("Super Mario 3", GameGenre.Action, 59.99, LocalDate.of(1996, 2, 4), "NDSA");
        tx.begin();
        //do some work here!
        assertNull("ID should be null before em.persist and commit", game.getId());
        em.persist(game);
        assertNull("How about now? ID should be null after em.persist and before commit", game.getId());
        tx.commit();
        assertNotNull("ID should now be populated!", game.getId());
        LOG.info(game.toString());
        assertTrue("ID should be greater than 0", game.getId() > 0);
        tx.begin();
        em.remove(em.merge(game));
        tx.commit();

    }

    @Test
    public void testOrderGameManyToManyBiDirectionalRelationship() {
        VintageGame game = new VintageGame("Super Mario 2", GameGenre.Action, 59.99, LocalDate.of(1996, 2, 4), "SADAS");
        OrderInfo order = new OrderInfo(30.00, LocalDate.of(2019, 2, 4), "Completed");
        //set inverse side and observer
        //only setting the inverse side of relationship, fk not written to db
        //now setting owning side of relationship
        order.addGame(game);
        tx.begin();
        em.persist(game);
        em.persist(order);
        tx.commit();
        //find the owing side
        OrderInfo findOrder = em.find(OrderInfo.class, 1l);
        System.out.println("The order total is: " + findOrder.getOrderTotal());
        assertEquals(order.getOrderTotal(), findOrder.getOrderTotal(), 0.00);
        System.out.println("Ordered Games: " + findOrder.getGames().toString());
        assertEquals(order.getGames().get(0).getName(), findOrder.getGames().get(0).getName());

        //find inverse side
        VintageGame findGame = em.createNamedQuery("VintageGame.findByName", VintageGame.class)
                .setParameter("name", "Super Mario 2")
                .getSingleResult();
        System.out.println("The ordered game are: " + findGame.getName());
        assertEquals(game.getName(), findGame.getName());
        System.out.println("Order information: " + findGame.getOrders().toString());
        assertEquals(game.getOrders().get(0).getCustomer(), findGame.getOrders().get(0).getCustomer());
        //we need to clean up after thes test
        tx.begin();
        order.removeGame(game);
        em.remove(em.merge(game));
        tx.commit();

        //not pulling directly from dbs, we are pulling the cache
    }

    @Test
    public void testOrderCustomerManyToOneBiDirectionalRelationship() {
        Customer c = new Customer("Zhang", "Wenzhao", "201 S 4th", "Chicago", "IL", "60632", "3123727323", "example@cjj.com");
        Customer c2 = new Customer("Liu", "Aa", "201 S 4th", "Chicago", "IL", "60632", "3123727323", "example@cjj.com");

        OrderInfo order = new OrderInfo(30.00, LocalDate.of(2019, 2, 4), "Completed");
        order.setCustomer(c);
        tx.begin();
        em.persist(c);
        em.persist(c2);
        em.persist(order);
        tx.commit();
        OrderInfo findOrder = em.find(OrderInfo.class, 2l);
        System.out.println("The order belongs to customer: " + findOrder.getCustomer().getCustomerFirstName());
        assertEquals(order.getCustomer(), findOrder.getCustomer());

    }

    //test a removal by creating a new pet then removing
    //assert that you can no longer find it ffrom database
    @Test
    public void testRemoveGame() {
        VintageGame game = new VintageGame("Super Mario 2", GameGenre.Action, 59.99, LocalDate.of(1996, 2, 4), "ASD");
        tx.begin();
        em.persist(game);
        em.remove(em.merge(game));
        assertNull("Object deleted, ID should be null!", game.getId());
        tx.commit();
    }

    //test an update to seed data
    //first, find the seed data and assert it is what you expect from before fixture
    //make update and commit
    //em.merge OR call set methods on a managed entity inside the transaction
    //read it back form dbn
    //assert the update is what you expect
    @Test
    public void testUpdateGame() {
        VintageGame game = new VintageGame("Super Mario 2", GameGenre.Action, 59.99, LocalDate.of(1996, 2, 4), "SADAS");
        tx.begin();
        em.persist(game);
        assertEquals(GameGenre.Action, game.getGenre());
        game.setGenre(GameGenre.RPG);
        em.merge(game);
        assertEquals(GameGenre.RPG, game.getGenre());
        em.remove(em.merge(game));
        tx.commit();
    }

    @Test(expected = RollbackException.class)
    public void testCreateInvalidGameDatabaseFail() {
        VintageGame game = new VintageGame(null, GameGenre.Action, 59.99, LocalDate.of(1996, 2, 4), "sdad");
        tx.begin();
        //do some work here!
        assertNull("ID should be null before em.persist and commit", game.getId());
        em.persist(game);
        assertNull("How about now? ID should be null after em.persist and before commit", game.getId());
        tx.commit();
        assertNotNull("ID should now be populated!", game.getId());
        LOG.info(game.toString());
        assertTrue("ID should be greater than 0", game.getId() > 0);
    }

}
*/