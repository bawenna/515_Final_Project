/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package edu.iit.sat.itmd4515.wzhang87.domain;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author mrslo

public class AbstractJPATest {

    private static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;

    @BeforeClass
    public static void beforeClassTestFixtureRunsOncePerClass() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    @AfterClass
    public static void afterClassTestFixtureRunsOncePerClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @Before
    public void beforeEachTestFixture() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        VintageGame seed = new VintageGame("seed", GameGenre.Action, 0.00, LocalDate.of(1996, 2, 4), "NICE");
        tx.begin();
        em.persist(seed);
        tx.commit();
    }

    @After
    public void afterEachTestFixture() {
        //we have to clean up test data after each test
        VintageGame seed = em.createNamedQuery("VintageGame.findByName", VintageGame.class)
                .setParameter("name", "seed")
                .getSingleResult();
        tx.begin();
        em.remove(em.merge(seed));
        tx.commit();
        if (em != null) {
            em.close();
        }
    }
}
*/