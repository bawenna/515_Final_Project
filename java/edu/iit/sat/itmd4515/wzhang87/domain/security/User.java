/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.domain.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Generate the user table
 * @author mrslo
 */
@Entity
@EntityListeners(UserListener.class)
@Table(name = "sec_user")
@NamedQuery(name="User.findAll", query= "select u from User u")
@NamedQuery(name = "User.CheckExistingUser", query = "SELECT u FROM User u WHERE u.userName LIKE :currentName")
public class User {

    @Id
    private String userName;
    private String passWord;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "sec_user_groups", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param g
     */
    public void addGroup(Group g) {
        if (!this.groups.contains(g)) {
            this.groups.add(g);
        }
        if (g.getUsers().contains(this)) {
            g.getUsers().add(this);
        }

    }

    /**
     *
     * @param userName
     * @param passWord
     * @param enabled
     */
    public User(String userName, String passWord, Boolean enabled) {
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     *
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     *
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return
     */
    public List getGroups() {
        return groups;
    }

    /**
     *
     * @param groups
     */
    public void setGroups(List groups) {
        this.groups = groups;
    }

}
