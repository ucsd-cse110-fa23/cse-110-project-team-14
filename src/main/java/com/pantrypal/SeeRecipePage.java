package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// SeeRecipePage now extends the abstract Page class
public class SeeRecipePage extends Page {

    private String title;
    private String detail;
    private String ingredients;
    private Button back;
    private Button saveButton;
    private Button deleteButton;
    
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    private Label detailLable;
    private Label ingredientLabel;
    private Text titleText;
    private Recipe r;
    RecipeTitleView recipeTitleView; 
            


    public SeeRecipePage(int width, int height) {
        super(width, height);
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
            // go back
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
            System.out.println("SWITCHED TO See PAGE");
        });

        //save button action
        saveButton.setOnAction(e -> {
            // ADDs DUPLICATE AT THE BOTTOM???
            // WE'LL ASK
            SeeRecipePage SRP = new SeeRecipePage(600, 600);
            SRP.setRecipe(r);
            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(r.getRecipeTitle(), SRP);
                stg.changeTo(r.getRecipeTitle());
            });

            boolean addingTwice = false;
            for(Object e2 : RecipeTitleListView.getInstance().getChildren()){
                if(e2 instanceof RecipeTitleView){
                    if( ((RecipeTitleView)e2).getRecipe().equals(this.r)){
                        addingTwice = true;
                        break;
                    }
                }
            }
            if(!addingTwice){
                RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
            }
            
            // RecipeTitleListView.getInstance().updateRecipeIndices();
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height, false).getScene());
            System.out.println("SWITCHED TO See PAGE");
        });

        deleteButton.setOnAction(e -> {
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
            System.out.println("SWITCHED TO See PAGE");
        });


    }

    // Implementation of the createView method from Page
    @Override


    protected void createView() {

        ///////
        back = new Button("Back to Home");
        saveButton = new Button("Save Recipe");
        deleteButton = new Button("Delete Recipe");
        

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

        mainContent.getChildren().addAll(ingredientLabel, detailLable);//TODO: we need to fill in what chatgpt said
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        titleText = new Text(title);
        Header.setTitleInMiddle(titleText);
        this.back = Footer.creatButton("Back to Home", "-fx-background-color: #FFEBD7; " +
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
