package com.pantrypal.controller;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.model.RecipeModel;
import com.pantrypal.view.StageController;
import com.pantrypal.view.mainPage;

import javafx.event.ActionEvent;

/**
 * Will handle all interactions with the the Main Page
 */
public class MainPageController {
    private mainPage mainPageView;
    private RecipeModel model;
    StageController stg;

    public MainPageController(mainPage mainPageView) {
        this.mainPageView = mainPageView;
        model = new RecipeModel(this);
        this.mainPageView.setAddButtonAction(this::handleAddButton);
        this.mainPageView.setSignOutAction(this::handleSignOut);
        stg = StageController.getInstance();
    }

    /**
     * Handles the action when the add button is clicked.
     * Changes to the MealTypePage for recording a new recipe.
     */
    private void handleAddButton(ActionEvent event) {
        // swap to record page
        StageController stgController = StageController.getInstance();

        stgController.changeTo("MealTypePage");
    }

    /**
     * Handles action of sign out button
     * Changes back to the log in page
     */
    private void handleSignOut(ActionEvent event) {
        // swap to record page
        StageController stgController = StageController.getInstance();
        Globals.username = "recipes";
        mainPageView.login.unsetAutomaticLogin();

        stgController.changeTo("LoginPage");
    }

    public void initRecipes() {
        // call model and uhh request the list of recipes kinda
        // update view so that the VBOX of recipes is shown
        model.handleInitRecipes();
    }

    public mainPage getMainPageView() {
        return mainPageView;
    }

    public void updateView() {

    }

}