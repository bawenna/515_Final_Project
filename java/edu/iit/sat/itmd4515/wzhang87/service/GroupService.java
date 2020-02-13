/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.service;

import edu.iit.sat.itmd4515.wzhang87.domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Group service class with methods necessary for CRUD operation
 * @author mrslo
 */
@Stateless
public class GroupService extends AbstractService {

    /**
     *
     */
    public GroupService() {
        super(Group.class);
    }

    /**
     * Find all groups in the table
     * @return
     */
    @Override
    public List<Group> findAll() {
        return em.createNamedQuery("Group.findAll", entityClass).getResultList();
    }
    
}
