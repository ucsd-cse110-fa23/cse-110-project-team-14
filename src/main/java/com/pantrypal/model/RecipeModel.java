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
            
            System.out.println(transcribedText);
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

    /**
     * Handles the initialization of recipes, loading data from the database and updating the UI.
     * This method performs the following steps:
     * 1. Initializes a connection to the "recipes" database using DatabaseOPS.
     * 2. Retrieves a list of recipes from the database.
     * 3. Populates the Globals.recipes ArrayList with the retrieved data.
     * 4. Iterates through the list of recipes in reverse order.
     * 5. For each recipe, creates a RecipeTitleView UI element and a SeeRecipePage UI element.
     * 6. Associates the UI elements with the corresponding recipe data.
     * 7. Sets up an action on the RecipeTitleButton to navigate to the detailed view of the recipe.
     * 8. Registers the SeeRecipePage with the StageController and adds the RecipeTitleView to the UI.
     *
     * Note: The loaded recipes are assumed to be displayed in a vertical list using RecipeTitleListView.
     */
    public void handleInitRecipes(){
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