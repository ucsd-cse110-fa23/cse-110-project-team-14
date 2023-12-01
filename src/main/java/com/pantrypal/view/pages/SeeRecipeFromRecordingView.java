package com.pantrypal.view.pages;

import com.pantrypal.constants.Constants;
import com.pantrypal.controller.SeeRecipeFromRecordingController;
import com.pantrypal.controller.StageController;
import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.model.WhisperModel;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;


public class SeeRecipeFromRecordingView extends Page implements ISeeRecipePage{

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button saveButton;
    private Button editButton;
    private Button regenButton;

    private VBox center;
    private Label detailLable;
    private Label ingredientLabel;
    private Text titleText;
    private Recipe r;
    SeeRecipeFromRecordingController seeRecipeFromRecordingController;
    public SeeRecipeFromRecordingView(int width, int height) {
        super(width, height);
    }
    @Override
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
    public Recipe getRecipe(){
        return this.r;
    }
    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.back.setOnAction(eventHandler);
    }
    public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.saveButton.setOnAction(eventHandler);
    }
    public void setEditButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.editButton.setOnAction(eventHandler);
    }
    public void setRegenButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.regenButton.setOnAction(eventHandler);
    }
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

        mainContent.getChildren().addAll(ingredientLabel, detailLable);
        paneHeader = new PaneHeader();
        this.center = mainContent;
        paneFooter = new PaneFooter();
        titleText = new Text(title);
        paneHeader.setTitleInMiddle(titleText);
        this.back = paneFooter.creatButton("Cancel", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(this.back);


        this.saveButton = paneFooter.creatButton("Save Recipe", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(saveButton);

        this.editButton = paneFooter.creatButton("Edit", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(editButton);

        this.regenButton = paneFooter.creatButton("Regenerate", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.getChildren().add(regenButton);

        // this.deleteButton = Footer.creatButton("Delete Button", "-fx-background-color: #FFEBD7; " +
        //         "-fx-text-fill: #8B4513; " +
        //         "-fx-border-color: #8B4513; " +
        //         "-fx-border-radius: 20; " +
        //         "-fx-background-radius: 20; " +
        //         "-fx-padding: 5 15 5 15;");
        //         Footer.getChildren().add(deleteButton);


        this.borderPane.setTop(paneHeader);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.paneFooter);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

        seeRecipeFromRecordingController = new SeeRecipeFromRecordingController(this);

    }


}
