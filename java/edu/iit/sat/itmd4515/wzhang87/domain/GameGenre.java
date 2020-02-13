/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain;

import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generate the GameGenre Column values for the VintageGame Table
 * @author mrslo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "GameGenre.findAll", query = "select g from GameGenre g")
@NamedQuery(name = "GameGenre.findByName", query = "select g from GameGenre g where g.label :=label ")
public enum GameGenre {

    /**
     *
     */
    Action("Action"),

    /**
     *
     */
    Shooter("Shooter"),

    /**
     *
     */
    RPG("RPG"),

    /**
     *
     */
    Racing("Racing"),

    /**
     *
     */
    Sport("Sport"),

    /**
     *
     */
    Adventure("Adventure"),

    /**
     *
     */
    Horror("Horror"),

    /**
     *
     */
    Technical("Technical"),

    /**
     *
     */
    Interactive("Interactive");
    

    private String label;

    private GameGenre(String label) {
        this.label = label;
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

}
