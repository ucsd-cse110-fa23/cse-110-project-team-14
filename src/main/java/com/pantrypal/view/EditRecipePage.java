package com.pantrypal.view;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.controller.EditRecipeController;
import com.pantrypal.model.DatabaseOPS;
import com.pantrypal.model.Recipe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

// EDIT RECIPE PAGE now extends the abstract Page class
public class EditRecipePage extends Page {

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button saveButton;

    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    public TextArea detailLable;
    public TextArea ingredientLabel;
    private Text titleText;
    public Recipe r;
    public DatabaseOPS db;
    public RecipeTitleView recipeTitleView;
    // private SeeRecipeFromRecording SRPR;
    public SeeRecipePage SRP;
    private EditRecipeController erc;

    final private String COLLECTION_NAME = Globals.username;

    public boolean fromRecording = false;

    public EditRecipePage(int width, int height, ISeeRecipePage SRP) {
        super(width, height);
        // prevent for access database for multiple time
        this.db = new DatabaseOPS(COLLECTION_NAME);
        if (SRP instanceof SeeRecipeFromRecording) {
            fromRecording = true;
            // this.SRPR = (SeeRecipeFromRescording) SRPR;
            this.SRP = new SeeRecipePage(constants.width, constants.height);
            this.SRP.setRecipe(((SeeRecipeFromRecording) SRP).getRecipe());
        }
        if (SRP instanceof SeeRecipePage) {
            this.SRP = (SeeRecipePage) SRP;
        }
    }

    public void setRecipe(Recipe r) {
        // set recipe data
        this.r = r;
        this.recipeTitleView = new RecipeTitleView(r);
        this.title = r.getRecipeTitle();
        this.detail = r.getRecipeInstructions();
        this.ingredients = r.getRecipeIngredients();
        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }

    // set button
    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.Footer.getButton("Discard Changes").setOnAction(eventHandler);
    }

    // set button
    public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.Footer.getButton("Save Recipe").setOnAction(eventHandler);
    }

    // Implementation of the createView method from Page
    @Override

    protected void createView() {
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.TOP_LEFT);

        detailLable = new TextArea(detail);
        detailLable.setWrapText(true);

        // changing font size so itll fit
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        ingredientLabel = new TextArea(ingredients);
        detailLable.setWrapText(true);

        // changing font size so itll fit
        ingredientLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        // add components to the page
        mainContent.getChildren().addAll(ingredientLabel, detailLable);
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        titleText = new Text(title);
        Header.setTitleInMiddle(titleText);
        // set button style and create button
        this.back = Footer.creatButton("Discard Changes", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");

        this.saveButton = Footer.creatButton("Save Recipe", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");

        Footer.getChildren().addAll(back, saveButton);
        this.borderPane.setTop(Header);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.Footer);

        this.borderPane.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                        "-fx-border-color: #DEB887; " +
                        "-fx-border-width: 10; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15;");
        // connect controller to the page view
        erc = new EditRecipeController(this);
    }
}
