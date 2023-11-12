package com.pantrypal;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class RecipeTitleListView extends VBox {
    // Create the recipeTitle button
    //private Button recipeTitleButton = .toggleRecipeTitleButton();
    private static final RecipeTitleListView instance = new RecipeTitleListView();

    RecipeTitleListView() {
        // Set margin to the top and bottom of the RecipeTitleList
        this.setPadding(new Insets(5,0,5,0));
        this.setSpacing(5); // sets spacing between recipes
        this.setStyle("-fx-background-color: #8B4513;");
        this.minWidth(1000);
        this.minHeight(1000);
    }

    public void updateRecipeIndices() {
        int index = 1;
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof RecipeTitleView) {
                //((RecipeTitle) this.getChildren().get(i)).setRecipeTitleIndex(index);
                index++;
            }
        }
    }

    public static RecipeTitleListView getInstance() {
        return instance;
    }
}