package com.pantrypal.controller;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.view.EditRecipePage;
import com.pantrypal.view.RecipeTitleListView;
import com.pantrypal.view.SeeRecipeFromRecording;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

/*
* Controller for handling interactions with the EditRecipepage
*/
public class EditRecipeController {
    private EditRecipePage erp;

    public EditRecipeController(EditRecipePage erp) {
        this.erp = erp;
        this.erp.setBackButtonAction(this::handleBackButton);
        this.erp.setSaveButtonAction(this::handleSaveButton);
    }

    /**
     * Handles the action when the back button is clicked.
     * Navigates back to the main page or the recipe view page
     */
    public void handleBackButton(ActionEvent event) {
        // go back to main page
        Stage stage = (Stage) erp.getScene().getWindow();
        if (erp.fromRecording) {
            SeeRecipeFromRecording SRPR = new SeeRecipeFromRecording(constants.width, constants.height);
            SRPR.setRecipe(erp.r);
            stage.setScene(SRPR.getScene());
        } else {
            stage.setScene(erp.SRP.getScene());
        }
    }

    /**
     * Save Button
     * Updates the recipe details and saves to the database.
     */
    public void handleSaveButton(ActionEvent event) {
        int index = Globals.recipes.indexOf(erp.r);
        erp.r.setRecipeIngredients(erp.ingredientLabel.getText());
        erp.r.setRecipeInstructions(erp.detailLable.getText());
        erp.SRP.setRecipe(erp.r);
        // Save recipe to database

        // ADD TO DATABASE
        if (erp.fromRecording) {
            RecipeTitleListView.getInstance().getChildren().add(erp.recipeTitleView);
            erp.db.insert(erp.r);
            Globals.recipes.add(erp.r);
        } else {
            erp.db.update(erp.r);
            if (index != -1) {
                Globals.recipes.set(index, erp.r);
            } else {
                System.out.println("ERROR: Recipe not found in Globals.recipes");
            }
        }

        erp.recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
            StageController stg = StageController.getInstance();
            stg.registerPage(erp.r.getRecipeTitle(), erp.SRP);
            stg.changeTo(erp.r.getRecipeTitle());
        });

        Stage stage = (Stage) erp.getScene().getWindow();
        stage.setScene(erp.SRP.getScene());
    }

}
