package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

// SeeRecipePage now extends the abstract Page class 
//SeeRecipePage uses an Interface to allow it to work with SeeRecipeFromRecording
public class SeeRecipePage extends Page implements ISeeRecipePage{

    private String title;
    private String mealType;
    private String detail;
    private String ingredients;
    private Button back;
    private Button editButton;
    private Button deleteButton; 
    
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
            
    public SeeRecipePage(int width, int height) {
        super(width, height);
        this.db = new DatabaseOPS(COLLECTION_NAME);
    }

    public SeeRecipePage(int width, int height, Recipe r) {
        super(width, height);
        // setRecipe(r);
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

    public void addListeners() {
        back.setOnAction(e -> {
            // go back to main page
            //Stage stage = (Stage) this.getScene().getWindow();
            //stage.setScene(new mainPage(width, height, false).getScene());
            StageController stageController = StageController.getInstance();
            stageController.changeTo("mainPage");
            System.out.println("SWITCHED TO MAIN PAGE");
        });

         
        editButton.setOnAction(e -> {
            //OPEN EDIT PAGE
            EditRecipePage ERP = new EditRecipePage(constants.width, constants.height, this);
            ERP.setRecipe(r);
            StageController stg = StageController.getInstance();
            stg.registerPage(r.getRecipeTitle(), ERP);
            stg.changeTo(r.getRecipeTitle());       
            });

         deleteButton.setOnAction(e -> {
            // Delete recipe from database
            Popup confirmDelete = new Popup();
            VBox confirmDeleteContent = new VBox();
            confirmDeleteContent.styleProperty().set("-fx-background-color: #FFEBD7; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 20 20 20 20;");
            Label label = new Label("Do you want to delete this recipe?");
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");
            HBox buttonBox = new HBox();
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.getChildren().add(yesButton);
            buttonBox.getChildren().add(noButton);
            confirmDeleteContent.getChildren().add(label);
            confirmDeleteContent.getChildren().add(buttonBox);

            // add the label 
            confirmDelete.getContent().add(confirmDeleteContent);

            Stage stage = (Stage) this.getScene().getWindow();
            confirmDelete.show(stage);
            confirmDelete.setAutoHide(true);

            yesButton.setOnAction(e1 -> {
                db.deleteUno(r);
                Globals.recipes.remove(r);
                for(Object e2 : RecipeTitleListView.getInstance().getChildren()){
                    if(e2 instanceof RecipeTitleView){
                        if( ((RecipeTitleView)e2).getRecipe().equals(this.r)){
                            RecipeTitleListView.getInstance().getChildren().remove(e2);
                            break;
                        }
                    }
                }

                confirmDelete.hide();
                //Stage stage = (Stage) this.getScene().getWindow();
                //stage.setScene(new mainPage(width, height, false).getScene());
                StageController stageController = StageController.getInstance();
                stageController.changeTo("mainPage");
            });

            noButton.setOnAction(e1 -> {
                confirmDelete.hide();
            });
        });
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
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        titleText = new Text(mealType + ": " + title);
        Header.setTitleInMiddle(titleText);
        this.back = Footer.creatButton("Back to Home", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        Footer.getChildren().add(this.back);
      
        this.editButton = Footer.creatButton("Edit Button", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");    
                Footer.getChildren().add(editButton);

        this.deleteButton = Footer.creatButton("Delete Button", "-fx-background-color: #FFEBD7; " +
            "-fx-text-fill: #8B4513; " +
            "-fx-border-color: #8B4513; " +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 5 15 5 15;");    
                Footer.getChildren().add(deleteButton);
        
        
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
