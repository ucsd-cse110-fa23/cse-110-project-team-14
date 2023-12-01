package com.pantrypal.view.pages;

import com.pantrypal.constants.Constants;
import com.pantrypal.controller.SeeRecipePageController;
import com.pantrypal.model.Recipe;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

// SeeRecipePageView now extends the abstract Page class
//SeeRecipePageView uses an Interface to allow it to work with SeeRecipeFromRecording
public class SeeRecipePageView extends Page implements ISeeRecipePage{

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button editButton;
    private Button deleteButton; 
    
    private VBox center;
    private Label detailLable;
    private Label ingredientLabel;
    private Text titleText;
    private Recipe r;
    //private DatabaseOPS db;
    //RecipeTitleView recipeTitleView;
    SeeRecipePageController seeRecipePageController;
    final private String COLLECTION_NAME = Constants.COLLECTION_NAME;
            

    public SeeRecipePageView(int width, int height) {
        super(width, height);
        //this.db = new DatabaseOPS(COLLECTION_NAME);
    }

    public void setRecipe(Recipe r) {
        this.r = r;
        //this.recipeTitleView = new RecipeTitleView(r);
        this.title = r.getRecipeTitle();
        this.detail = r.getRecipeInstructions();
        this.ingredients = r.getRecipeIngredients();
        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }

    // Implementation of the createView method from Page
    @Override


    public void createView() {

        VBox recipeBox = new VBox();
        recipeBox.setAlignment(Pos.TOP_LEFT);

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

        recipeBox.getChildren().addAll(ingredientLabel, detailLable);
        recipeBox.setStyle("-fx-background-color: #FFEBD7;");
        ScrollPane recipePageScroller = new ScrollPane(recipeBox);
        recipePageScroller.setPrefSize(1000, 1000);
        recipePageScroller.setFitToWidth(true);
        VBox mainContent = new VBox();
        mainContent.minHeight(1000);
        mainContent.minWidth(1000);
        mainContent.getChildren().add(recipePageScroller);
        paneHeader = new PaneHeader();
        this.center = mainContent;
        paneFooter = new PaneFooter();
        titleText = new Text(title);
        paneHeader.setTitleInMiddle(titleText);
        this.back = paneFooter.creatButton("Back to Home", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(this.back);
      
        this.editButton = paneFooter.creatButton("Edit Button", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(editButton);

        this.deleteButton = paneFooter.creatButton("Delete Button", "-fx-background-color: #FFEBD7; " +
            "-fx-text-fill: #8B4513; " +
            "-fx-border-color: #8B4513; " +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(deleteButton);
        
        
        this.borderPane.setTop(paneHeader);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(paneFooter);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

        seeRecipePageController = new SeeRecipePageController(this);
    }

    public Recipe getRecipe() {
        return r;
    }

    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        back.setOnAction(eventHandler);
    }

    public void setEditButtonAction( EventHandler<ActionEvent> eventHandler) {
        editButton.setOnAction(eventHandler);
    }
    public void setDeleteButtonAction( EventHandler<ActionEvent> eventHandler) {
        deleteButton.setOnAction(eventHandler);
    }

}
