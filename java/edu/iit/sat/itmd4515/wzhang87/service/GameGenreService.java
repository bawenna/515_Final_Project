/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.GameGenre;
import java.util.List;
import javax.ejb.Stateless;

/**
 * GameGenre service class with methods necessary for CRUD operation
 * @author mrslo
 */
@Stateless
public class GameGenreService extends AbstractService<GameGenre> {

    /**
     *
     */
    public GameGenreService() {
        super(GameGenre.class);
    }

    /**
     * Find all the game genre listed in the table 
     * @return
     */
    @Override
    public List findAll() {
        return em.createNamedQuery("GameGenre.findAll", entityClass).getResultList();
    }

}
