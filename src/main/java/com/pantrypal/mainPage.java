package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class mainPage extends Page {


    paneHeader paneHeader;
    paneFooter paneFooter;
    private Button addButton;

    public mainPage(int width, int height) {
        super(width, height);
        // IntializeRecipeList.uploadRecipes();
        DatabaseOPS db = new DatabaseOPS("recipes");
        db.initializeRecipesToList();
    }
    // pass garbage boolean
    // prevents initializing recipe list multiple times
     public mainPage(int width, int height, boolean initialized) {
        super(width, height);
        
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            // swap to record page
            StageController stgController = StageController.getInstance();

            stgController.changeTo("MealTypePage");

        });
    }

    // gets the addButton 
    public Button getAddButton() {
        return this.addButton;
    }

    // SET UP MAIN PAGE VIEW
    @Override
    protected void createView() {

        paneHeader = new paneHeader();
        paneHeader.setTitleInMiddle(new Text("PantryPal: The best Recipe manager"));
        
        ScrollPane scroll = new ScrollPane(RecipeTitleListView.getInstance());
        scroll.setPrefSize(1000, 1000);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        paneFooter = new paneFooter();
        
        VBox mainContent = new VBox();
        mainContent.minHeight(1000);
        mainContent.minWidth(1000);
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.getChildren().add(scroll);
        
        this.borderPane.setTop(paneHeader);
        this.borderPane.setCenter(mainContent);
        this.borderPane.setBottom(paneFooter);

        this.addButton = paneFooter.creatButton("addButton", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");

        paneFooter.setButton(this.addButton);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");
        addListeners();
    }
}