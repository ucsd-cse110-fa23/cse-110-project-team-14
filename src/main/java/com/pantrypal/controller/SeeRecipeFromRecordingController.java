package com.pantrypal.controller;

import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.model.WhisperModel;
import com.pantrypal.view.pages.EditRecipePageView;
import com.pantrypal.view.pages.SeeRecipeFromRecordingView;
import com.pantrypal.view.pages.SeeRecipePageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import com.pantrypal.constants.Constants;

import java.io.IOException;
import java.net.URISyntaxException;

public class SeeRecipeFromRecordingController extends Controller {
    SeeRecipeFromRecordingView seeRecipeFromRecordingView;
    public SeeRecipeFromRecordingController(SeeRecipeFromRecordingView seeRecipeFromRecordingView)
    {
        this.seeRecipeFromRecordingView = seeRecipeFromRecordingView;
        this.seeRecipeFromRecordingView.setBackButtonAction(this::handleBackButton);
        this.seeRecipeFromRecordingView.setSaveButtonAction(this::handleSaveButton);
        this.seeRecipeFromRecordingView.setEditButtonAction(this::handleEditButton);
        this.seeRecipeFromRecordingView.setRegenButtonAction(this::handleRegenButton);
    }

    public void handleBackButton(ActionEvent event) {
        stg.changeTo(Constants.MAINPAGE_NAME);
    }
    public void handleSaveButton(ActionEvent event) {
        // Change the page to SeeRecipePageRecording
        SeeRecipePageView SRP = new SeeRecipePageView(Constants.WIDTH, Constants.HEIGHT);
        SRP.setRecipe(seeRecipeFromRecordingView.getRecipe());

        //TODO: add to database
//        db.insert(r);
//        recipeTitleView.getRecipeTitleButton().setOnAction(e1 ->
//        {
//            StageController stg = StageController.getInstance();
//            stg.registerPage(r.getRecipeTitle(), SRP);
//            stg.changeTo(r.getRecipeTitle());
//        });
//        //Update the global counters
//        r.setRecipeIndex(Globals.recipeIndex);
//        Globals.recipeIndex++;
//        //Add recipe to the global recipe ArrayList
//        Globals.recipes.add(r);

        // Add recipe to the recipe list
        //Add it to the top of the list
        // RecipeTitleListView.getInstance().getChildren().add(0, recipeTitleView);
        //TODO: add to database
        stg.fetchDB = true;
        stg.changeTo(Constants.MAINPAGE_NAME);

    }
    public void handleEditButton(ActionEvent event) {
        EditRecipePageView ERP = new EditRecipePageView(Constants.WIDTH, Constants.HEIGHT, this.seeRecipeFromRecordingView);
        ERP.setRecipe(seeRecipeFromRecordingView.getRecipe());
        stg.registerPage(seeRecipeFromRecordingView.getRecipe().getRecipeTitle(), ERP);

        System.out.println(seeRecipeFromRecordingView.getRecipe().getRecipeTitle());

        stg.changeTo(seeRecipeFromRecordingView.getRecipe().getRecipeTitle());
    }
    public void handleRegenButton(ActionEvent event) {
         remakeRecipe();
    }
    public void remakeRecipe(){
        try {
            // from whisper gets previous text (avoids reading from recording.wav again)
            TextToRecipe t2R = new TextToRecipe(WhisperModel.getInstance().getText(), seeRecipeFromRecordingView.getRecipe().getMealType(), new Recipe(), new ChatGPT());
            t2R.createRecipeObj();
            SeeRecipeFromRecordingView SRP = new SeeRecipeFromRecordingView(Constants.WIDTH, Constants.HEIGHT);
            SRP.setRecipe(t2R.getRecipe());
            // call helper method to set up page and titleView button
            addRecipeToScreen(t2R, SRP);

        } catch (IOException | InterruptedException | URISyntaxException e1) {
            e1.printStackTrace();
        }

    }
    public void addRecipeToScreen(TextToRecipe t2R, SeeRecipeFromRecordingView SRP){
        //TODO: add to Mainpage
//        RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
//        recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
//            StageController stg = StageController.getInstance();
//            stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
//            stg.changeTo(t2R.getRecipe().getRecipeTitle());
//        });
        //TODO: add to Mainpage
        StageController stg = StageController.getInstance();
        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
        stg.changeTo(t2R.getRecipe().getRecipeTitle());
    }
}
