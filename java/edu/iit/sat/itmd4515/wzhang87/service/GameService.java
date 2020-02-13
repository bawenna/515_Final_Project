/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.Customer;
import edu.iit.sat.itmd4515.wzhang87.domain.GameGenre;
import edu.iit.sat.itmd4515.wzhang87.domain.VintageGame;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author mrslo
 */
@Stateful
@Named
public class GameService extends AbstractService<VintageGame> {

    /**
     *
     */
    public long id;

    /**
     *
     */
    public double newprice;

    /**
     *
     */
    public GameService() {
        super(VintageGame.class);
    }

    /**
     * CRUD function Inherit from constructor
     * @param g
     */
    public void update(VintageGame g) {
        em.merge(g);
    }

    /**
     * CRUD function Inherit from constructor
     * @param g
     */
    public void remove(VintageGame g) {
        em.remove(em.merge(g));
    }

    /** 
     * Find all the game entries in the database
     * @return
     */
    @Override
    public List findAll() {
        return em.createNamedQuery("VintageGame.findAll", entityClass).getResultList();
    }

    /**
     * Find the particular game based on the gameID
     * @return
     */
    public List findById() {
        return em.createNamedQuery("VintageGame.findById", entityClass).getResultList();
    }

    /**
     * return the game entity based on the mark
     * @param query
     * @return
     */
    public VintageGame returnGame(String query) {
        VintageGame game = (VintageGame) em.createQuery("SELECT g FROM VintageGame g WHERE g.mark= :mark").setParameter("mark", query).getSingleResult();
        return game;
    }


    /**
     * Update 
     * @param id
     * @param price
     */
    public void updateExistingGame(long id, double price) {
        VintageGame existingGame = em.find(VintageGame.class, id);
        System.out.println("Infor" + existingGame.toString());
        existingGame.setPrice(price);
        System.out.println("Existing product updated.");
    }

    /**
     *
     * @param gameFromAdminForm
     */
    public void editGameFromAdminForm(VintageGame gameFromAdminForm) {
        //This is the reference to the game in db
        VintageGame gameFromDB = em.getReference(VintageGame.class, gameFromAdminForm.getId());
        gameFromDB.setName(gameFromAdminForm.getName());
        gameFromDB.setDateOfRelease(gameFromAdminForm.getDateOfRelease());
        gameFromDB.setGenre(gameFromAdminForm.getGenre());
        gameFromDB.setPrice(gameFromAdminForm.getPrice());
        em.merge(gameFromDB);
    }

}
