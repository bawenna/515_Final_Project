/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
  * Generate the VintageGame table, which contains general information about the games
 * @author mrslo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQuery(name = "VintageGame.findAll", query = "select g from VintageGame g")
@NamedQuery(name = "VintageGame.findByName", query = "select g from VintageGame g where g.name = :name")
@NamedQuery(name = "VintageGame.findById", query = "select g from VintageGame g where g.id = :id")
public class VintageGame extends AbstractIdentifiedEntity {
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private GameGenre genre;

    /**
     *
     */
    @PositiveOrZero
    public double price;
    @PastOrPresent
    private LocalDate dateOfRelease;
    @ManyToMany(mappedBy = "games")
    private List<OrderInfo> orders = new ArrayList<>();
    private String mark;

    /**
     *
     */
    public VintageGame() {
    }

    /**
     *
     * @param name
     * @param genre
     * @param price
     * @param dateOfRelease
     * @param mark
     */
    public VintageGame(String name, GameGenre genre, double price, LocalDate dateOfRelease, String mark) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.dateOfRelease = dateOfRelease;
        this.mark = mark;
    }

    /**
     *
     * @param id
     * @param name
     * @param genre
     * @param price
     * @param dateOfRelease
     * @param mark
     */
    public VintageGame(Long id, String name, GameGenre genre, double price, LocalDate dateOfRelease, String mark) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.dateOfRelease = dateOfRelease;
        this.mark = mark;
    }
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of string
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public GameGenre getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(GameGenre genre) {
        this.genre = genre;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    /**
     *
     * @param dateOfRelease
     */
    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    @Override
    public String toString() {
        return "vintageGame{" + "name=" + name + ", genre=" + genre + ", dateOfRelease=" + dateOfRelease + '}';
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public List<OrderInfo> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

    /**
     *
     * @return
     */
    public String getMark() {
        return mark;
    }

    /**
     *
     * @param mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

}