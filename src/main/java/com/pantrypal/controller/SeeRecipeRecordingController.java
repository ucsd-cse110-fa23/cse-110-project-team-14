package com.pantrypal.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.ImageCreation;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.model.Whisper;
import com.pantrypal.view.EditRecipePage;
import com.pantrypal.view.RecipeTitleListView;
import com.pantrypal.view.RecipeTitleView;
import com.pantrypal.view.SeeRecipeFromRecording;
import com.pantrypal.view.SeeRecipePage;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;
import com.pantrypal.view.StageController;

public class SeeRecipeRecordingController {
    private SeeRecipeFromRecording srf;
    StageController stg;

    public SeeRecipeRecordingController(SeeRecipeFromRecording srf){
        this.srf = srf;
        stg = StageController.getInstance();
        this.srf.setRegenButtonAction(this::handleRegenButton);
        this.srf.setEditButtonAction(this::handleEditButton);
        this.srf.setSaveButtonAction(this::handleSaveButton);
        this.srf.setBackButtonAction(this::handleBackButton);
        
    }

    public void handleEditButton(ActionEvent event) {
        //OPEN EDIT PAGE
            EditRecipePage ERP = new EditRecipePage(constants.width, constants.height, srf);
            ERP.setRecipe(srf.r);
            StageController stg = StageController.getInstance();
            stg.registerPage(srf.r.getRecipeTitle(), ERP);
            stg.changeTo(srf.r.getRecipeTitle());      

        
    }

    public void handleRegenButton(ActionEvent event) {
          remakeRecipe();

        
    }

    public void handleSaveButton(ActionEvent event) {
        SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height);
        SRP.setRecipe(srf.r);
        // Save recipe to database
        srf.db.insert(srf.r);

        srf.recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
            StageController stg = StageController.getInstance();
            stg.registerPage(srf.r.getRecipeTitle(), SRP);
            stg.changeTo(srf.r.getRecipeTitle());
        });

        //Update the global counters
        srf.r.setRecipeIndex(Globals.recipeIndex);
        Globals.recipeIndex++;

        //Add recipe to the global recipe ArrayList
        Globals.recipes.add(srf.r);

        // Add recipe to the recipe list 
        //Add it to the top of the list
        RecipeTitleListView.getInstance().getChildren().add(0, srf.recipeTitleView);
        StageController stageController = StageController.getInstance();
        stageController.changeTo("mainPage");
        
    }

    public void handleBackButton(ActionEvent event) {
        // go back to main page
        StageController stageController = StageController.getInstance();
        stageController.changeTo("mainPage");
    }


//creates a new page which will hold the regenerated recipe
    public void addRecipeToScreen(TextToRecipe t2R, SeeRecipeFromRecording SRP){
        RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
        recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                    StageController stg = StageController.getInstance();
                    stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
                    stg.changeTo(t2R.getRecipe().getRecipeTitle());
                });

        StageController stg = StageController.getInstance();
        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
        stg.changeTo(t2R.getRecipe().getRecipeTitle());
    }
    
    // creates the newly generated recipe using the same instance of whisper that was originally said
    public void remakeRecipe(){
            try {
                // from whisper gets previous text (avoids reading from recording.wav again)
                TextToRecipe t2R = new TextToRecipe(Whisper.getInstance().getText(), srf.getRecipe().getMealType(), new Recipe(), new ChatGPT(), new ImageCreation());
                t2R.createRecipeObj();
                SeeRecipeFromRecording SRP = new SeeRecipeFromRecording(constants.width, constants.height);
                SRP.setRecipe(t2R.getRecipe());
                // call helper method to set up page and titleView button
                addRecipeToScreen(t2R, SRP);

            } catch (IOException | InterruptedException | URISyntaxException e1) {
                e1.printStackTrace();
            }  

    }
    
}