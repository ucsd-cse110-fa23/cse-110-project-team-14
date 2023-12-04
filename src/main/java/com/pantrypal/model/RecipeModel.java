package com.pantrypal.model;

import com.pantrypal.Globals;
import com.pantrypal.constants.Constants;
import com.pantrypal.controller.MainPageController;
import com.pantrypal.controller.RecordPageController;
import com.pantrypal.controller.StageController;
import com.pantrypal.view.pages.MainPageView;
import com.pantrypal.view.pages.RecipeTitleListView;
import com.pantrypal.view.pages.RecipeTitleView;
import com.pantrypal.view.pages.RecordPageView;
import com.pantrypal.view.pages.SeeRecipeFromRecordingView;
import com.pantrypal.view.pages.SeeRecipePageView;

import javafx.scene.control.ScrollPane;

public class RecipeModel {
    private ChatGPT chatGPT = new ChatGPT();
    private WhisperModel whisper = WhisperModel.getInstance();
    StageController stg = StageController.getInstance();
    
    private RecordPageController recordPageController;
    private RecordPageView rpv; 

    private MainPageController mainPageController;
    private MainPageView mpv;
    
    
    public RecipeModel(RecordPageController rpc){
        this.recordPageController = rpc;
        rpv = recordPageController.getRecordPageView(); 
    }

    public RecipeModel(MainPageController mpc){
        this.mainPageController = mpc;
        mpv = mainPageController.getMainPageView();
    }

    public void handleCreateRecipe(){
        // chatpgt + whisper calls
        // formulate the new recipe to return
        // have controller update the view
        TextToRecipe t2R;
        SeeRecipeFromRecordingView SRV;
        try {
            String transcribedText = whisper.getResponse();
            WhisperModel.getInstance().setText(transcribedText);
            
            t2R = new TextToRecipe(transcribedText, recordPageController.getRecordPageView().getMealType(), new Recipe(), new ChatGPT());
            t2R.createRecipeObj();

            SRV = new SeeRecipeFromRecordingView(Constants.WIDTH, Constants.HEIGHT);
            SRV.setRecipe(t2R.getRecipe());

              // make a listener for recipeTitleView
              RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
              recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                  StageController stg = StageController.getInstance();
                  stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRV);
                  stg.changeTo(t2R.getRecipe().getRecipeTitle());
              });

            recordPageController.updateView(t2R, SRV);
//


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleInitRecipes(){
        System.out.println("hi");
        // perform database operations to load recieps
        DatabaseOPS db = new DatabaseOPS("recipes");
        Globals.recipes = db.initializeRecipesToList(); //<-- this will return the arraylist
        for (int i = Globals.recipes.size() - 1; i >= 0; i--) {
            Recipe recipe = Globals.recipes.get(i);
            // System.out.println(recipe.getRecipeTitle().toString());
            RecipeTitleView recipeTitleView = new RecipeTitleView(recipe); //<-- This should be UI
            SeeRecipePageView SRP = new SeeRecipePageView(Constants.WIDTH, Constants.HEIGHT); //<-- This should be UI
            SRP.setRecipe(recipe);
            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(recipe.getRecipeTitle(), SRP);
                stg.changeTo(recipe.getRecipeTitle());
            });
            RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
        }

    }
}
