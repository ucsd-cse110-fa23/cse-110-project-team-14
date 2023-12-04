package com.pantrypal.view.pages;

import com.pantrypal.model.Recipe;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RecipeTitleView extends HBox {

    private Recipe recipe;
    private Button recipeName;

    public RecipeTitleView(Recipe recipe){
        this.recipe = recipe;

        // Edit to correct width to fill in up to borders
        // Change colors of text and box
        this.recipeName = new Button(recipe.getRecipeTitle()); // create recipeTitle name recipeTitle field
        recipeName.setPrefSize(1000, 40); // set size of the recipeTitle button
        recipeName.setStyle("-fx-background-color: #FFEBD7; ; -fx-text-fill: #8B4513; -fx-font-size: 30; -fx-border-width: 0;"); // set background color of recipefield
             
        // center the recipeName
        recipeName.setMinHeight(60);
        recipeName.setAlignment(Pos.CENTER);
        this.getChildren().add(recipeName); // add recipelabel to recipe

    }
    
    public Recipe getRecipe() {
        return this.recipe;
    }

    public Button getRecipeTitleButton() {
        return this.recipeName; // return the recipeTitle button
    }
}

