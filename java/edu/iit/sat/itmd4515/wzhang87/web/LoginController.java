/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.web;

import edu.iit.sat.itmd4515.wzhang87.domain.security.User;
import edu.iit.sat.itmd4515.wzhang87.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author mrslo
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @NotBlank(message = "You must enter a username!")
    private String username;
        private String username1;
    @NotBlank(message = "You must enter a password!")
    private String password;
        private String password1;
    @Inject
    private SecurityContext securityContext;
    @Inject
    private FacesContext facesContext;
    @EJB
    private UserService userSvc;

    /**
     *
     */
    public LoginController() {

    }

    //seccurity methods
    // authenticated username

    /**
     * Return the current user
     * @return
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    /**
     * Return the current role
     * @return
     */
    public String getRoles(){
        return securityContext.toString();
    }

    /**
     * Check if the user is the admin
     * @return
     */
    public boolean isAdmin() {
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    /**
     * Check if the user is a normal user
     * @return
     */
    public boolean isCustomer() {
        return securityContext.isCallerInRole("USER_ROLE");
    }

    //action

    /**
     * Login action
     * @return
     */
    public String doLogin() {
        LOG.info("Inside doLogin");
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest) facesContext.getExternalContext().getRequest(),
                (HttpServletResponse) facesContext.getExternalContext().getResponse(),
                AuthenticationParameters.withParams().credential(credential)
        );
        LOG.info("Authentication status" + status.toString());

        switch (status) {
            case SEND_CONTINUE:
                LOG.info("SEND_CONTINUE in login");
                break;
            case SEND_FAILURE:
                LOG.info("SEND_FAILURE in login");
                return "/error.xhtml";
            case SUCCESS:
                LOG.info("SUCCCESS in login");
                break;
            case NOT_DONE:
                LOG.info("NOT_DONE in login");
                return "/error.xhtml";

        }

        return "/welcome.xhtml?faces-redirect=true";
    }

    /**
     * Logout action
     * @return
     */
    public String doLogout() {
        try {
            HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return "/error.xhtml";
        }
        LOG.info("Log out successfully!");
        return "/login.xhtml?faces-redirect=true";

    }
    
    /**
     * Register for a new account
     * @return
     */
    public String register(){
        userSvc.register(username1, password1);
        return "/login.xhtml";
    }
    
    public String toRegister(){
        return "/signUp.xhtml";
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUsername1() {
        return username1;
    }

    /**
     *
     * @param username1
     */
    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    /**
     *
     * @return
     */
    public String getPassword1() {
        return password1;
    }

    /**
     *
     * @param password1
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

}
