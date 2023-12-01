package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.view.pages.ConfirmDeleteView;
import com.pantrypal.view.pages.EditRecipePageView;
import com.pantrypal.view.pages.SeeRecipePageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class SeeRecipePageController extends Controller{
    SeeRecipePageView seeRecipePageView;
    public SeeRecipePageController(SeeRecipePageView seeRecipePageView)
    {

        this.seeRecipePageView = seeRecipePageView;
        this.seeRecipePageView.setBackButtonAction(this::handleBackButton);
        this.seeRecipePageView.setEditButtonAction(this::handleEditButton);
        this.seeRecipePageView.setDeleteButtonAction(this::handleDeleteButton);
    }

    public void handleBackButton(ActionEvent event) {
        stg.changeTo(Constants.MAINPAGE_NAME);
    }
    public void handleEditButton(ActionEvent event) {
        EditRecipePageView ERP = new EditRecipePageView(Constants.WIDTH, Constants.HEIGHT, this.seeRecipePageView);
        ERP.setRecipe(seeRecipePageView.getRecipe());
        stg.registerPage(seeRecipePageView.getRecipe().getRecipeTitle(), ERP);
        stg.changeTo(seeRecipePageView.getRecipe().getRecipeTitle());
    }
    public void handleDeleteButton(ActionEvent event) {
        ConfirmDeleteView confirmDeleteView = new ConfirmDeleteView(seeRecipePageView);

    }
}
