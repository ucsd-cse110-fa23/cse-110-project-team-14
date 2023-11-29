package com.pantrypal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class mainPage extends Page {


    paneHeader paneHeader;
    paneFooter paneFooter;
    private Button addButton;
    //private arraylist<recipe> recipes; which has all the recipes and we use it to sort 
    public mainPage(int width, int height) {
        super(width, height);
        // IntializeRecipeList.uploadRecipes();
        DatabaseOPS db = new DatabaseOPS("recipes");
        Globals.recipes = db.initializeRecipesToList(); //<-- this will return the arraylist
        if(Globals.sortingState == Globals.SortingState.NEWTOOLD) {
            Globals.recipes = Sort.newToOldSort(Globals.recipes);
        } else if(Globals.sortingState == Globals.SortingState.OLDTONEW) {
            Globals.recipes = Sort.oldToNewSort(Globals.recipes);
        } else if(Globals.sortingState == Globals.SortingState.ATOZ) {
            Globals.recipes = Sort.aToZSort(Globals.recipes);
        } else if(Globals.sortingState == Globals.SortingState.ZTOA) {
            Globals.recipes = Sort.zToASort(Globals.recipes);
        }
        for (int i = Globals.recipes.size() - 1; i >= 0; i--) {
            Recipe recipe = Globals.recipes.get(i);
            RecipeTitleView recipeTitleView = new RecipeTitleView(recipe); //<-- This should be UI
            SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height); //<-- This should be UI
            SRP.setRecipe(recipe);
            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(recipe.getRecipeTitle(), SRP);
                stg.changeTo(recipe.getRecipeTitle());
            });
            RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
        }

        // RecipeTitleView recipeTitleView = new RecipeTitleView(recipe); //<-- This should be UI
        //         SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height); //<-- This should be UI
        //         SRP.setRecipe(recipe);
        //         recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
        //             StageController stg = StageController.getInstance();
        //             stg.registerPage(recipe.getRecipeTitle(), SRP);
        //             stg.changeTo(recipe.getRecipeTitle());
        //         });
        //         RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
    }
    // pass garbage boolean
    // prevents initializing recipe list multiple times
     public mainPage(int width, int height, boolean initialized) {
        super(width, height);
        
    }

    //Change to use a helper method
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

        ComboBox<String> comboBox = new ComboBox<>();

        // Add items to the ComboBox
        ObservableList<String> items = FXCollections.observableArrayList(
                "Newest to Oldest",
                "Oldest to Newest",
                "A to Z",
                "Z to A"
        );

        comboBox.setItems(items);

        comboBox.setOnAction(e -> {
                // TODO:  Put into a helper method for refactoring purposes
                
                // Get the selected item
                String selectedOption = comboBox.getValue();
                // do a switch case to check between "Newest to Oldest", "Oldest to Newest", "A to Z", "Z to A"
                switch(selectedOption) {
                    case "Newest to Oldest":
                        // sort by newest to oldest
                        //NEWTOOLD
                        Globals.sortingState = Globals.SortingState.NEWTOOLD;
                        update();
                        break;
                    case "Oldest to Newest":
                        // sort by oldest to newest
                        // OLDTONEW
                        Globals.sortingState = Globals.SortingState.OLDTONEW;
                        update();
                        break;
                    case "A to Z":
                        // sort by A to Z
                        // ATOZ
                        Globals.sortingState = Globals.SortingState.ATOZ;
                        update();
                        break;
                    case "Z to A":
                        // sort by Z to A
                        // ZTOA
                        Globals.sortingState = Globals.SortingState.ZTOA;
                        update();
                        break;
                }
                // Add your functionality here
                System.out.println("Selected: " + selectedOption);
        });

        VBox vBox = new VBox(comboBox);
        
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
        mainContent.getChildren().add(vBox); // may have to redo
        mainContent.getChildren().add(scroll);
        
        this.borderPane.setTop(paneHeader);
        this.borderPane.setCenter(mainContent);
        this.borderPane.setBottom(paneFooter);

        this.addButton = paneFooter.creatButton("Create Recipe", "-fx-background-color: #FFEBD7; " +
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

    public void update(){
        RecipeTitleListView.getInstance().getChildren().clear();
        DatabaseOPS db = new DatabaseOPS("recipes");
        Globals.recipes = db.initializeRecipesToList(); //<-- this will return the arraylist
        if(Globals.sortingState == Globals.SortingState.NEWTOOLD) {
            Globals.recipes = Sort.newToOldSort(Globals.recipes);
        } else if(Globals.sortingState == Globals.SortingState.OLDTONEW) {
            Globals.recipes = Sort.oldToNewSort(Globals.recipes);
        } else if(Globals.sortingState == Globals.SortingState.ATOZ) {
            Globals.recipes = Sort.aToZSort(Globals.recipes);
        } else if(Globals.sortingState == Globals.SortingState.ZTOA) {
            Globals.recipes = Sort.zToASort(Globals.recipes);
        }
        RecipeTitleListView.getInstance().getChildren().clear();
        for (int i = Globals.recipes.size() - 1; i >= 0; i--) {
            Recipe recipe = Globals.recipes.get(i);
            RecipeTitleView recipeTitleView = new RecipeTitleView(recipe); //<-- This should be UI
            SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height); //<-- This should be UI
            SRP.setRecipe(recipe);
            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(recipe.getRecipeTitle(), SRP);
                stg.changeTo(recipe.getRecipeTitle());
            });
            RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
        }
    }
}