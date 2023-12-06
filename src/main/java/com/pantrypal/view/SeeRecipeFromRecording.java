package com.pantrypal.view;

import java.io.IOException;
import java.net.URISyntaxException;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.controller.SeeRecipeRecordingController;
import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.DatabaseOPS;
import com.pantrypal.model.ImageCreation;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.model.Whisper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

// SeeRecipeFromRcording now extends the abstract Page class and implements interface
public class SeeRecipeFromRecording extends Page implements ISeeRecipePage {

    private String title;
    private String mealType;
    private String detail;
    private String ingredients;
    private String imageURL;
    private Button back;
    private Button saveButton;
    private Button editButton;
    private Button regenButton;
    
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    public Label detailLable;
    public Label ingredientLabel;
    private Text titleText;
    public Recipe r;
    public DatabaseOPS db;
    public RecipeTitleView recipeTitleView; 
    private SeeRecipeRecordingController src;

    final private String COLLECTION_NAME = Globals.username;
            

    public SeeRecipeFromRecording(int width, int height) {
        super(width, height);
        this.db = new DatabaseOPS(COLLECTION_NAME);
    }

   
    public void setRecipe(Recipe r) {
        this.r = r;
        this.recipeTitleView = new RecipeTitleView(r);
        this.title = r.getRecipeTitle();
        this.mealType = r.getMealType();
        this.detail = r.getRecipeInstructions();
        this.ingredients = r.getRecipeIngredients();
        this.imageURL = r.getRecipeImageURL();

        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }

    public Recipe getRecipe(){
        return this.r;
    }

    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler){
        back.setOnAction(eventHandler);
    }

    public void setEditButtonAction(EventHandler<ActionEvent> eventHandler){
        editButton.setOnAction(eventHandler);
    }

    public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler){
        saveButton.setOnAction(eventHandler);
    }

    public void setRegenButtonAction(EventHandler<ActionEvent> eventHandler){
        regenButton.setOnAction(eventHandler);
    }


    // Implementation of the createView method from Page
    @Override
    public void createView() {

        VBox mainContent = new VBox();
        // mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.TOP_LEFT);

        detailLable = new Label(detail);
        detailLable.setTextFill(Color.web("#8B4513"));
        detailLable.setWrapText(true);
        //changing font size so itll fit
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        ingredientLabel = new Label(ingredients);
        ingredientLabel.setTextFill(Color.web("#8B4513"));
        ingredientLabel.setWrapText(true);
        //changing font size so itll fit
        ingredientLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        VBox recipeBox = new VBox();
        recipeBox.setAlignment(Pos.TOP_LEFT);
        recipeBox.setStyle("-fx-background-color: #FFEBD7;");

        if(imageURL == null){
            recipeBox.getChildren().addAll(ingredientLabel, detailLable);
        }
        else{
            // Access the Recipe image from the URL
            ImageView imageView = new ImageView();

            // Load an image from a URL
            String imageUrl = imageURL;
            Image image = new Image(imageUrl);

            // Set the image in the ImageView
            imageView.setImage(image);
            recipeBox.getChildren().addAll(ingredientLabel, detailLable, imageView);
        }

        ScrollPane recipePageScroller = new ScrollPane(recipeBox);
        recipePageScroller.setPrefSize(1000, 1000);
        recipePageScroller.setFitToWidth(true);
        mainContent.minHeight(1000);
        mainContent.minWidth(1000);
        mainContent.getChildren().add(recipePageScroller);
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        titleText = new Text(mealType + ": " + title);
        Header.setTitleInMiddle(titleText);
        this.back = Footer.creatButton("Cancel", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        Footer.getChildren().add(this.back);

       
        this.saveButton = Footer.creatButton("Save Recipe", "-fx-background-color: #FFEBD7; " +
            "-fx-text-fill: #8B4513; " +
            "-fx-border-color: #8B4513; " +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 5 15 5 15;");  
            Footer.getChildren().add(saveButton);
        
        this.editButton = Footer.creatButton("Edit", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");    
                Footer.getChildren().add(editButton);
        
        this.regenButton = Footer.creatButton("Regenerate", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");    
                Footer.getChildren().add(regenButton);
        
        this.borderPane.setTop(Header);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.Footer);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");
        src = new SeeRecipeRecordingController(this);

    }

}
