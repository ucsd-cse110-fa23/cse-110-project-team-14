package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// SeeRecipePage now extends the abstract Page class
public class SeeRecipeFromRecording extends Page implements ISeeRecipePage {

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button saveButton;
    private Button editButton;
    
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    private Label detailLable;
    private Label ingredientLabel;
    private Text titleText;
    private Recipe r;
    private DatabaseOPS db;
    RecipeTitleView recipeTitleView; 

    final private String COLLECTION_NAME = "recipes";
            

    public SeeRecipeFromRecording(int width, int height) {
        super(width, height);
        this.db = new DatabaseOPS(COLLECTION_NAME);
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
            stage.setScene(new mainPage(width, height, false).getScene());
            System.out.println("SWITCHED TO MAIN PAGE");
        });
        
     
        saveButton.setOnAction(e -> {
            // Change the page to SeeRecipePageRecording
            SeeRecipePage SRP = new SeeRecipePage(600, 600);
            SRP.setRecipe(r);
            // Save recipe to database
            // db.insert(r);

            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(r.getRecipeTitle(), SRP);
                stg.changeTo(r.getRecipeTitle());
            });
    
            RecipeTitleListView.getInstance().getChildren().add(recipeTitleView); 
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
            // System.out.println("SWITCHED TO See PAGE");
        });
        
        editButton.setOnAction(e -> {
            //OPEN EDIT PAGE
            EditRecipePage ERP = new EditRecipePage(600, 600, this);
            ERP.setRecipe(r);
            StageController stg = StageController.getInstance();
            stg.registerPage(r.getRecipeTitle(), ERP);
            stg.changeTo(r.getRecipeTitle());       
            });
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

        mainContent.getChildren().addAll(ingredientLabel, detailLable);
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        titleText = new Text(title);
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
        
        this.editButton = Footer.creatButton("Edit Button", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");    
                Footer.getChildren().add(editButton);
        
        
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
