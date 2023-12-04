package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button back;
    private Button saveButton;
    private Button editButton;
    private Button regenButton;
    
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    private Label detailLable;
    private Label ingredientLabel;
    private Text titleText;
    private Recipe r;
    private DatabaseOPS db;
    RecipeTitleView recipeTitleView; 

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

        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }

    public Recipe getRecipe(){
        return this.r;
    }

    public void addListeners() {
        back.setOnAction(e -> {
            // go back to main page
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
        });
        
     
        saveButton.setOnAction(e -> {
            // Change the page to SeeRecipePageRecording
            SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height);
            SRP.setRecipe(r);
            // Save recipe to database
            db.insert(r);

            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(r.getRecipeTitle(), SRP);
                stg.changeTo(r.getRecipeTitle());
            });
    
            

            //Update the global counters
            r.setRecipeIndex(Globals.recipeIndex);
            Globals.recipeIndex++;

            //Add recipe to the global recipe ArrayList
            Globals.recipes.add(r);

            // Add recipe to the recipe list 
            //Add it to the top of the list
            RecipeTitleListView.getInstance().getChildren().add(0, recipeTitleView);
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
        });
        
        editButton.setOnAction(e -> {
            //OPEN EDIT PAGE
            EditRecipePage ERP = new EditRecipePage(constants.width, constants.height, this);
            ERP.setRecipe(r);
            StageController stg = StageController.getInstance();
            stg.registerPage(r.getRecipeTitle(), ERP);
            stg.changeTo(r.getRecipeTitle());       
            });

        regenButton.setOnAction(e -> {
            remakeRecipe();
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
        
        // this.deleteButton = Footer.creatButton("Delete Button", "-fx-background-color: #FFEBD7; " +
        //         "-fx-text-fill: #8B4513; " +
        //         "-fx-border-color: #8B4513; " +
        //         "-fx-border-radius: 20; " +
        //         "-fx-background-radius: 20; " +
        //         "-fx-padding: 5 15 5 15;");    
        //         Footer.getChildren().add(deleteButton);
        
        
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

    //creates a new page which will hold the regenerated recipe
    public void addRecipeToScreen(TextToRecipe t2R, SeeRecipeFromRecording SRP){
        RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
        recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                    StageController stg = StageController.getInstance();
                    stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
                    stg.changeTo(t2R.getRecipe().getRecipeTitle());
                });

        StageController stg = StageController.getInstance();
        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
        stg.changeTo(t2R.getRecipe().getRecipeTitle());
    }
    
    // creates the newly generated recipe using the same instance of whisper that was originally said
    public void remakeRecipe(){
            try {
                // from whisper gets previous text (avoids reading from recording.wav again)
                TextToRecipe t2R = new TextToRecipe(Whisper.getInstance().getText(), this.getRecipe().getMealType(), new Recipe(), new ChatGPT());
                t2R.createRecipeObj();
                SeeRecipeFromRecording SRP = new SeeRecipeFromRecording(constants.width, constants.height);
                SRP.setRecipe(t2R.getRecipe());
                // call helper method to set up page and titleView button
                addRecipeToScreen(t2R, SRP);

            } catch (IOException | InterruptedException | URISyntaxException e1) {
                e1.printStackTrace();
            }  

    }


}
