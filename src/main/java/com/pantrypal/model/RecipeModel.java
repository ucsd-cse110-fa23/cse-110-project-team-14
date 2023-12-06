package com.pantrypal.model;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.controller.MainPageController;
import com.pantrypal.controller.RecordPageController;
import com.pantrypal.view.RecipeTitleListView;
import com.pantrypal.view.RecipeTitleView;
import com.pantrypal.view.RecordPage;
import com.pantrypal.view.SeeRecipeFromRecording;
import com.pantrypal.view.SeeRecipePage;
import com.pantrypal.view.StageController;
import com.pantrypal.view.mainPage;

import javafx.scene.control.ScrollPane;

public class RecipeModel {
    private ChatGPT chatGPT = new ChatGPT();
    private Whisper whisper = Whisper.getInstance();
    StageController stg = StageController.getInstance();
    
    private RecordPageController recordPageController;
    private RecordPage rpv; 

    private MainPageController mainPageController;
    private mainPage mpv;
    
    
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
        SeeRecipeFromRecording SRV;
        try {
            String transcribedText = whisper.getResponse();
            Whisper.getInstance().setText(transcribedText);
            
            t2R = new TextToRecipe(transcribedText, recordPageController.getRecordPageView().getMealType(), new Recipe(), new ChatGPT(), new ImageCreation());
            t2R.createRecipeObj();

            SRV = new SeeRecipeFromRecording(constants.width, constants.height);
            SRV.setRecipe(t2R.getRecipe());

              // make a listener for recipeTitleView
              RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
              recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                  StageController stg = StageController.getInstance();
                  stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRV);
                  stg.changeTo(t2R.getRecipe().getRecipeTitle());
              });

            recordPageController.updateView(t2R, SRV);
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
            SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height); //<-- This should be UI
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