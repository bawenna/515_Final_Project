/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.wzhang87.web;

import edu.iit.sat.itmd4515.wzhang87.domain.GameGenre;
import edu.iit.sat.itmd4515.wzhang87.domain.OrderInfo;
import edu.iit.sat.itmd4515.wzhang87.domain.VintageGame;
import edu.iit.sat.itmd4515.wzhang87.service.GameService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Methods relates to the game operations
 *
 * @author mrslo
 */
@Named
@RequestScoped
public class GameController {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    private static final Logger LOG = Logger.getLogger(GameController.class.getName());
    @EJB
    private GameService gameSvc;
    private VintageGame game;
    @EJB
    private GameService gameService;
    private long selectedGame;
    private double newPrice;

    /**
     *
     */
    public GameController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside GameController.postConstruct()");
        game = new VintageGame();
    }

    /**
     *
     * @return
     */
    public GameGenre[] getGameGenre() {
        return GameGenre.values();
    }

    /**
     * Retrieve the orders that contain the specific game
     *
     * @param g
     * @return
     */
    public String formatOrdersAsString(VintageGame g) {
        List<String> orderIds = new ArrayList<>();
        for (OrderInfo o : g.getOrders()) {
            orderIds.add(Long.toString(o.getId()));
        }
        return String.join(",", orderIds);
    }

    //prepare action method
    /**
     * Prepare action method for editing a game
     *
     * @param game
     * @return
     */
    public String prepareEditGame(VintageGame game) {
        this.game = game;
        return "/admin/edit.xhtml";
    }

    /**
     * Prepare action method for creating a game
     *
     * @return
     */
    public String prepareCreateGame() {
        LOG.info("Inside GameController.executeSaveGame" + game.toString());
        this.game = new VintageGame();
        return "/admin/edit.xhtml";
    }

    /**
     * Prepare action method for viewing a game
     * @param game
     * @return
     */
    public String prepareViewGame(VintageGame game) {
        LOG.info("Inside GameController.executeSaveGame" + game.toString());
        this.game = game;
        return "/admin/viewGame.xhtml";
    }

    /**
     * Save changes to an existing game OR create a new one based on the gameID
     * @return
     */
    public String doSaveGame() {
        LOG.info("Inside GameController.executeSaveGame" + game.toString());

        if (this.game.getId() != null) {
            LOG.info("Call and update in the service layer with" + this.game.toString());
            gameSvc.editGameFromAdminForm(game);
        } else {
            LOG.info("Call and create in the service layer with" + this.game.toString());
            gameSvc.create(game);

        }
        return "/admin/gameok.xhtml";
    }

    /**
     * Delete a game
     * @param game
     * @return
     */
    public String doDeleteGame(VintageGame game) {
        gameSvc.remove(game);
        return "/welcome.xhtml";
    }

    /**
     * 
     * @return
     */
    public String executeSaveGame() {
        LOG.info("Inside GameController.executeSaveGame" + game.toString());
        gameSvc.create(game);
        return "/admin/gameok.xhtml";
    }

    /**
     *
     */
    public void updateExistingGame() {
        System.out.println("Updating Existing product... ");

        System.out.println("Selected game ID: " + game.id);
        gameService.updateExistingGame(game.id, game.price);
        System.out.println("Updated successfully, the new price is now" + game.price);
    }

    /**
     *
     * @return
     */
    public String getQuery() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("query");
    }

    /**
     *
     * @return
     */
    public VintageGame getGameResult() {
        return gameService.returnGame(getQuery());

    }

    /**
     *
     * @return
     */
    public VintageGame getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(VintageGame game) {
        this.game = game;
    }

    // edit the amount of a existing product
    /**
     * Get the value of game
     *
     * @return the value of game
     */
    /**
     * Set the value of game
     *
     * @param game new value of game
     */
}
