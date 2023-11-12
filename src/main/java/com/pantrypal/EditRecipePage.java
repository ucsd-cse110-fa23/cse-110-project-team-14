package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// EDIT RECIPE PAGE now extends the abstract Page class
public class EditRecipePage extends Page {

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button saveButton;
    private Button deleteButton;
    
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    private TextField detailLable;
    private TextField ingredientLabel;
    private Text titleText;
    private Recipe r;
    private DatabaseOPS db;
    RecipeTitleView recipeTitleView; 
    private SeeRecipeFromRecording SRPR;
    private SeeRecipePage SRP;
    

    final private String COLLECTION_NAME = "recipes";
            


    public EditRecipePage(int width, int height, ISeeRecipePage SRP) {
        super(width, height);
        this.db = new DatabaseOPS(COLLECTION_NAME);
        if(SRP instanceof SeeRecipeFromRecording){
            this.SRPR = (SeeRecipeFromRecording) SRPR;
        }
        if(SRP instanceof SeeRecipePage){
            this.SRP = (SeeRecipePage) SRP;
        }
    }

    public void setRecipe(Recipe r) {
        this.r = r;
        this.recipeTitleView = new RecipeTitleView(r);
        this.title = r.getRecipeTitle();
        this.detail = r.getRecipeInstructions();
        this.ingredients = r.getRecipeIngredients();
        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }

    public void addListeners() {
        back.setOnAction(e -> {
            // go back to main page
            Stage stage = (Stage) this.getScene().getWindow();
            if(SRP != null){
                this.SRPR = (SeeRecipeFromRecording) SRPR;
            }
            if(SRPR != null){
                this.SRP = (SeeRecipePage) SRP;
            }
            stage.setScene(SRP.getScene());
        });

        //save button action
        saveButton.setOnAction(e -> {
            // ADDs DUPLICATE AT THE BOTTOM???
            // WE'LL ASK
            // Change the page to SeeRecipePageRecording
            // SeeRecipePage SRP = new SeeRecipePage(600, 600);
            r.setRecipeIngredients(ingredientLabel.getText());
            r.setRecipeInstructions(detailLable.getText());
            SRP.setRecipe(r);
            // Save recipe to database
            // db.insert(r);

            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(r.getRecipeTitle(), SRP);
                stg.changeTo(r.getRecipeTitle());
            });

            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(SRP.getScene());
            // System.out.println("SWITCHED TO See PAGE");
        });

        deleteButton.setOnAction(e -> {
            // Delete recipe from database
            // TODO: COMMENTED DB STUFF FOR TESTING
            // db.deleteUno(r);

            // System.out.println("DELETING RECIPE: " + RecipeTitleListView.getInstance().getChildren().remove(recipeTitleView));
            for(Object e2 : RecipeTitleListView.getInstance().getChildren()){
                if(e2 instanceof RecipeTitleView){
                    if( ((RecipeTitleView)e2).getRecipe().equals(this.r)){
                        System.out.println(RecipeTitleListView.getInstance().getChildren().remove(e2));
                        break;
                    }
                }
            }
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
            // System.out.println("SWITCHED TO See PAGE");
        });


    }

    // Implementation of the createView method from Page
    @Override


    protected void createView() {  
        VBox mainContent = new VBox();
        // mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.TOP_LEFT);

        detailLable = new TextField(detail);
        // detailLable.setTe(Color.web("#8B4513"));
        // detailLable.setWrapText(true);
        //changing font size so itll fit
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        ingredientLabel = new TextField(ingredients);
        // ingredientLabel.setTextFill(Color.web("#8B4513"));
        // ingredientLabel.setWrapText(true);
        //changing font size so itll fit
        ingredientLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        mainContent.getChildren().addAll(ingredientLabel, detailLable);//TODO: we need to fill in what chatgpt said
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        titleText = new Text(title);
        Header.setTitleInMiddle(titleText);
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

        this.deleteButton = Footer.creatButton("Delete Recipe", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        
        
        
        // Footer.setButton(back);
        Footer.getChildren().addAll(back, saveButton, deleteButton);
        this.borderPane.setTop(Header);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.Footer);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

        addListeners();

    }
}
