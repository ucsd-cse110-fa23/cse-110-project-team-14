package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Toolkit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

// SeeRecipePage now extends the abstract Page class 
//SeeRecipePage uses an Interface to allow it to work with SeeRecipeFromRecording
public class SeeRecipePage extends Page implements ISeeRecipePage{

    private String title;
    private String mealType;
    private String detail;
    private String ingredients;
    private String imageURL;
    private Button back;
    private Button editButton;
    private Button deleteButton; 
    private Button shareButton;
    
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
        this.imageURL = r.getRecipeImageURL();
        detailLable.setText(detail);
        ingredientLabel.setText(ingredients);
        titleText.setText(title);

    }

    public void addListeners() {
        back.setOnAction(e -> {
            // go back to main page
            StageController stageController = StageController.getInstance();
            stageController.changeTo("mainPage");
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
                stage.setScene(new mainPage(width, height, false).getScene());
            });

            noButton.setOnAction(e1 -> {
                confirmDelete.hide();
            });
        });

        this.shareButton.setOnAction(e -> {
            shareButton.setText("Copied To Clipboard");
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            // Create a StringSelection object containing the text to be copied
            String toCopy = "http://localhost:8100/share/" + Globals.username + "/" + this.title;
            StringSelection selection = new StringSelection(toCopy);

            // Set the contents of the clipboard to the StringSelection
            toolkit.getSystemClipboard().setContents(selection, null);

            // Display message for 5 seconds
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(5),
                    event -> shareButton.setText("Share button"))); // Set the original text here
            timeline.setCycleCount(1);
            timeline.play();
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
        recipeBox.setStyle("-fx-background-color: #FFEBD7;");
        boolean isImageUrlAcc = false;
        if(imageURL == null){
            recipeBox.getChildren().addAll(ingredientLabel, detailLable);
        }
        else{
            ImageCreation imageCreation = new ImageCreation();
            if(imageCreation.isValidURL(imageURL)) {
                // Access the Recipe image from the URL
                ImageView imageView = new ImageView();

                // Load an image from a URL
                String imageUrl = imageURL;
                Image image = new Image(imageUrl);

                // Set the image in the ImageView
                imageView.setImage(image);
                recipeBox.getChildren().addAll(ingredientLabel, detailLable, imageView);
            } else {
                String newImageURL = imageCreation.regenerateImageURL(title, r);
                if(newImageURL != null) {
                    imageURL = newImageURL;
                    ImageView imageView = new ImageView();

                    // Load an image from a URL
                    String imageUrl = imageURL;
                    Image image = new Image(imageUrl);

                    // Set the image in the ImageView
                    imageView.setImage(image);
                    recipeBox.getChildren().addAll(ingredientLabel, detailLable, imageView);
                } else {
                    recipeBox.getChildren().addAll(ingredientLabel, detailLable);
                }


            }
        }
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
        
        this.shareButton = Footer.creatButton("Share Button", "-fx-background-color: #FFEBD7; " +
            "-fx-text-fill: #8B4513; " +
            "-fx-border-color: #8B4513; " +
            "-fx-border-radius: 20; " +
            "-fx-background-radius: 20; " +
            "-fx-padding: 5 15 5 15;");    
                Footer.getChildren().add(shareButton);
        
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
