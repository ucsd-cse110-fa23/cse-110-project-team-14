package com.pantrypal;

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
    private Button deleteButton;
    
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;
    private TextArea detailLable;
    private TextArea ingredientLabel;
    private Text titleText;
    private Recipe r;
    private DatabaseOPS db;
    RecipeTitleView recipeTitleView; 
    // private SeeRecipeFromRecording SRPR;
    private SeeRecipePage SRP;
    

    final private String COLLECTION_NAME = "recipes";
            

    private boolean fromRecording = false;
    public EditRecipePage(int width, int height, ISeeRecipePage SRP) {
        super(width, height);
        this.db = new DatabaseOPS(COLLECTION_NAME);
        if(SRP instanceof SeeRecipeFromRecording){
            fromRecording = true;
            // this.SRPR = (SeeRecipeFromRescording) SRPR;
            this.SRP = new SeeRecipePage(600, 600);
            this.SRP.setRecipe( ((SeeRecipeFromRecording)SRP).getRecipe());
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
            if(fromRecording){
                
                stage.setScene(new mainPage(width, height, false).getScene());
            }
            else{
                stage.setScene(this.SRP.getScene());
            }
        });

        //save button action
        saveButton.setOnAction(e -> {

            r.setRecipeIngredients(ingredientLabel.getText());
            r.setRecipeInstructions(detailLable.getText());
            SRP.setRecipe(r);
            // Save recipe to database
            db.update(r);

           
            // ADD TO DATABASE 
            if(fromRecording){
                RecipeTitleListView.getInstance().getChildren().add(recipeTitleView); 
            }
            
            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(r.getRecipeTitle(), SRP);
                stg.changeTo(r.getRecipeTitle());
            });

            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(SRP.getScene());

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
                for(Object e2 : RecipeTitleListView.getInstance().getChildren()){
                    if(e2 instanceof RecipeTitleView){
                        if( ((RecipeTitleView)e2).getRecipe().equals(this.r)){
                            RecipeTitleListView.getInstance().getChildren().remove(e2);
                            break;
                        }
                    }
                }

                confirmDelete.hide();
                stage.setScene(new mainPage(width, height, false).getScene());
            });

            noButton.setOnAction(e1 -> {
                confirmDelete.hide();
            });
        });


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
