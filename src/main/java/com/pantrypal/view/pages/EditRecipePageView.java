package com.pantrypal.view.pages;

import com.pantrypal.constants.Constants;
import com.pantrypal.controller.EditRecipePageController;
import com.pantrypal.model.Recipe;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// EDIT RECIPE PAGE now extends the abstract Page class
public class EditRecipePageView extends Page {

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button saveButton;
    
    private VBox center;
    private TextArea detailLable;
    private TextArea ingredientLabel;
    private Text titleText;
    private Recipe r;
    //private DatabaseOPS db;
    //RecipeTitleView recipeTitleView;
    // private SeeRecipeFromRecording SRPR;
    private SeeRecipePageView SRP;
    EditRecipePageController editRecipePageController;

    final private String COLLECTION_NAME = "recipes";
            

    private boolean fromRecording = false;
    public EditRecipePageView(int width, int height, ISeeRecipePage SRP) {
        super(width, height);
        //TODO: Database things
        //this.db = new DatabaseOPS(COLLECTION_NAME);
        //TODO: Database things
        if(SRP instanceof SeeRecipeFromRecordingView){
            fromRecording = true;
            // this.SRPR = (SeeRecipeFromRescording) SRPR;
            this.SRP = new SeeRecipePageView(Constants.WIDTH, Constants.HEIGHT);
            this.SRP.setRecipe( ((SeeRecipeFromRecordingView)SRP).getRecipe());
        }
        if(SRP instanceof SeeRecipePageView){
            this.SRP = (SeeRecipePageView) SRP;
        }
    }

    public void setRecipe(Recipe r) {
        this.r = r;
        //TODO: Title things
        //this.recipeTitleView = new RecipeTitleView(r);
        //TODO: Title things
        this.title = r.getRecipeTitle();
        this.detail = r.getRecipeInstructions();
        this.ingredients = r.getRecipeIngredients();
        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }
    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler){
        this.paneFooter.getButton("Discard Changes").setOnAction(eventHandler);
    }
    public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler){
        this.paneFooter.getButton("Save Recipe").setOnAction(eventHandler);
    }
    public Recipe getRecipe()
    {
        return this.r;
    }
    public TextArea getIngredientLabel(){
        return this.ingredientLabel;
    }
    public TextArea getDetailLabel(){
        return this.detailLable;
    }
    public SeeRecipePageView getSRP() {
        return SRP;
    }


    // Implementation of the createView method from Page
    @Override
    protected void createView() {  
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.TOP_LEFT);

        detailLable = new TextArea(detail);
        detailLable.setWrapText(true);

        //changing font size so itll fit
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        ingredientLabel = new TextArea(ingredients);
        detailLable.setWrapText(true);



        //changing font size so itll fit
        ingredientLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        mainContent.getChildren().addAll(ingredientLabel, detailLable);
        paneHeader = new PaneHeader();
        this.center = mainContent;
        paneFooter = new PaneFooter();
        titleText = new Text(title);
        paneHeader.setTitleInMiddle(titleText);
        this.back = paneFooter.creatButton("Discard Changes", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");

        this.saveButton = paneFooter.creatButton("Save Recipe", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");

        paneFooter.getChildren().addAll(back, saveButton);
        this.borderPane.setTop(paneHeader);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.paneFooter);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

         editRecipePageController = new EditRecipePageController(this);

    }
}
