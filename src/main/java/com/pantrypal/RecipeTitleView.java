package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RecipeTitleView extends HBox {

    private Recipe recipe;
    private Label index;
    private Button recipeName;

    RecipeTitleView(Recipe recipe){

        //TODO set style and size
        this.recipe = recipe;


        // index = new Label();
        // index.setText(""); // create index label
        // index.setPrefSize(40, 20); // set size of Index label
        // index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
        // this.getChildren().add(index); // add index label to recipe

        // Edit to correct width to fill in up to borders
        // Change colors of text and box
        this.recipeName = new Button(recipe.getRecipeTitle()); // create recipeTitle name recipeTitle field
        recipeName.setPrefSize(1000, 40); // set size of the recipeTitle button
        recipeName.setStyle("-fx-background-color: #FFEBD7; ; -fx-text-fill: #8B4513; -fx-font-size: 30; -fx-border-width: 0;"); // set background color of recipefield
                // "-fx-text-fill: #8B4513; " +
                // "-fx-border-color: #8B4513; " +
                // "-fx-border-radius: 20; " +
                // "-fx-background-radius: 20; " +
                // "-fx-padding: 5 15 5 15;");
        // center the recipeName
        recipeName.setMinHeight(60);
        recipeName.setAlignment(Pos.CENTER);
        //index.setTextAlignment(TextAlignment.CENTER); // set alignment of recipe field
        this.getChildren().add(recipeName); // add recipelabel to recipe

    }
    
    public Recipe getRecipe() {
        return this.recipe;
    }

    // public void setRecipeTitleIndex(int num) {
    //     this.index.setText(num + ""); // num to String
    // }

    public Button getRecipeTitleButton() {
        return this.recipeName; // return the recipeTitle button
    }

    // Toggles the editable state by calling setEditable()
    // public void toggleDetails(){
    //     this.detailsButton.toggleDetails();
    //     this.contactName.setEditable(this.detailsButton.editable);
    //     this.contactPhoneNumber.setEditable(this.detailsButton.editable);
    //     this.contactEmail.setEditable(this.detailsButton.editable);
    // }
}

