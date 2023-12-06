package com.pantrypal.controller;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.model.RecipeModel;
import com.pantrypal.view.StageController;
import com.pantrypal.view.mainPage;

import javafx.event.ActionEvent;

public class MainPageController {
    private mainPage mainPageView;
    private RecipeModel model; 
    StageController stg;

    public MainPageController(mainPage mainPageView)
    {
        this.mainPageView = mainPageView;
        model = new RecipeModel(this);
        this.mainPageView.setAddButtonAction(this::handleAddButton);
        this.mainPageView.setSignOutAction(this::handleSignOut);
        stg = StageController.getInstance();
    }

    private void handleAddButton(ActionEvent event) {
         // swap to record page
         StageController stgController = StageController.getInstance();

         stgController.changeTo("MealTypePage");
    }

    private void handleSignOut(ActionEvent event) {
         // swap to record page
        StageController stgController = StageController.getInstance();
        Globals.username = "recipes";
        mainPageView.login.unsetAutomaticLogin();

        stgController.changeTo("LoginPage");
    }

    public void initRecipes(){
        // call model and uhh request the list of recipes kinda
        // update view so that the VBOX of recipes is shown
        model.handleInitRecipes();
    }


    public mainPage getMainPageView()
    {
        return mainPageView;
    }

    public void updateView(){
        
    }

}