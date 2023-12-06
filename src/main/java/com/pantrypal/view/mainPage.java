package com.pantrypal.view;

import javafx.scene.control.Label;

import com.pantrypal.Filters;
import com.pantrypal.Globals;
import com.pantrypal.Sort;
import com.pantrypal.constants;
import com.pantrypal.Globals.FilterType;
import com.pantrypal.Globals.SortingState;
import com.pantrypal.controller.MainPageController;
import com.pantrypal.model.DatabaseOPS;
import com.pantrypal.model.Login;
import com.pantrypal.model.Recipe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class mainPage extends Page {


    paneHeader paneHeader;
    paneFooter paneFooter;
    private Button addButton;
    private Button signOutButton;
    public Login login =  new Login();
    private MainPageController mpc;
    //private arraylist<recipe> recipes; which has all the recipes and we use it to sort 
    public mainPage(int width, int height) {
        super(width, height);
        // IntializeRecipeList.uploadRecipes();
        DatabaseOPS db = new DatabaseOPS(Globals.username);
        Globals.recipes = db.initializeRecipesToList(); //<-- this will return the arraylist
        //Add the recipes to the filter list
        if(Globals.filterType == Globals.FilterType.BREAKFAST){
            Globals.recipes = Filters.filterByBreakfast(Globals.recipes);
        } else if(Globals.filterType == Globals.FilterType.LUNCH){
            Globals.recipes = Filters.filterByLunch(Globals.recipes);
        } else if(Globals.filterType == Globals.FilterType.DINNER){
            Globals.recipes = Filters.filterByDinner(Globals.recipes);
        }


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

    public void setAddButtonAction(EventHandler<ActionEvent> eventHandler){
        addButton.setOnAction(eventHandler);
    }

    public void setSignOutAction(EventHandler<ActionEvent> eventHandler){
        signOutButton.setOnAction(eventHandler);
    }

    // gets the addButton 
    public Button getAddButton() {
        return this.addButton;
    }

    public Button getSignOutButton() {
        return this.signOutButton;
    }

    public void sortBoxClick(String selectedOption){

        // Get the selected item
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
    }

    public void filterMealTypeBoxClick(String selectedOption){
        // do a switch case to check between firs
        switch(selectedOption) {
            case "None":
                //Filter by none
                Globals.filterType = Globals.FilterType.NONE;
                update();
                break;
            case "Breakfast":
                // Filter by breakfast
                Globals.filterType = Globals.FilterType.BREAKFAST;
                update();
                break;
            case "Lunch":
                // Filter by lunch
                Globals.filterType = Globals.FilterType.LUNCH;
                update();
                break;
            case "Dinner":
                // Filter by dinner
                Globals.filterType = Globals.FilterType.DINNER;
                update();
                break;
        }
        // Add your functionality here
        System.out.println("Selected: " + selectedOption);
    }

    // SET UP MAIN PAGE VIEW
    @Override
    protected void createView() {

        paneHeader = new paneHeader();
        paneHeader.setTitleInMiddle(new Text("PantryPal: The best Recipe manager"));

        ComboBox<String> sortBox = new ComboBox<>();
        ComboBox<String> filterMealTypeBox = new ComboBox<>();

        // Add sort to the ComboBox
        ObservableList<String> sort = FXCollections.observableArrayList(
                "Newest to Oldest",
                "Oldest to Newest",
                "A to Z",
                "Z to A"
        );

        // Add filter to the ComboBox
        ObservableList<String> filterMealType = FXCollections.observableArrayList(
                "None",
                "Breakfast",
                "Lunch",
                "Dinner"
        );

        
        sortBox.setItems(sort);
        filterMealTypeBox.setItems(filterMealType);

        sortBox.setOnAction(e -> {
            sortBoxClick(sortBox.getValue());
                // TODO:  Put into a helper method for refactoring purposes
                
                // // Get the selected item
                // String selectedOption = sortBox.getValue();
                // // do a switch case to check between "Newest to Oldest", "Oldest to Newest", "A to Z", "Z to A"
                // switch(selectedOption) {
                //     case "Newest to Oldest":
                //         // sort by newest to oldest
                //         //NEWTOOLD
                //         Globals.sortingState = Globals.SortingState.NEWTOOLD;
                //         update();
                //         break;
                //     case "Oldest to Newest":
                //         // sort by oldest to newest
                //         // OLDTONEW
                //         Globals.sortingState = Globals.SortingState.OLDTONEW;
                //         update();
                //         break;
                //     case "A to Z":
                //         // sort by A to Z
                //         // ATOZ
                //         Globals.sortingState = Globals.SortingState.ATOZ;
                //         update();
                //         break;
                //     case "Z to A":
                //         // sort by Z to A
                //         // ZTOA
                //         Globals.sortingState = Globals.SortingState.ZTOA;
                //         update();
                //         break;
                // }
                // // Add your functionality here
                // System.out.println("Selected: " + selectedOption);
        });

        filterMealTypeBox.setOnAction(e -> {
            filterMealTypeBoxClick(filterMealTypeBox.getValue());
                // // TODO:  Put into a helper method for refactoring purposes
                
                // // Get the selected item
                // String selectedOption = filterMealTypeBox.getValue();
                // // do a switch case to check between firs
                // switch(selectedOption) {
                //     case "None":
                //         //Filter by none
                //         Globals.filterType = Globals.FilterType.NONE;
                //         update();
                //         break;
                //     case "Breakfast":
                //         // Filter by breakfast
                //         Globals.filterType = Globals.FilterType.BREAKFAST;
                //         update();
                //         break;
                //     case "Lunch":
                //         // Filter by lunch
                //         Globals.filterType = Globals.FilterType.LUNCH;
                //         update();
                //         break;
                //     case "Dinner":
                //         // Filter by dinner
                //         Globals.filterType = Globals.FilterType.DINNER;
                //         update();
                //         break;
                // }
                // // Add your functionality here
                // System.out.println("Selected: " + selectedOption);
        });


        /* Create the pageSettings subheader for adding the two drop downs and the signout option */
        HBox pageSettings = new HBox();
        this.signOutButton = new Button("Sign Out");
        this.signOutButton.setStyle("-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        
        pageSettings.setPadding(new Insets(10)); // Add padding for better visibility

        // Set the spacing between buttons
        pageSettings.setSpacing(5);

        // Set the left button to grow horizontally to the left
        //HBox.setHgrow(sortBox, javafx.scene.layout.Priority.ALWAYS);
        //HBox.setHgrow(filterMealTypeBox, javafx.scene.layout.Priority.ALWAYS);

        // Add the dropdowns and the signout button to the HBox pageSettings subheader
        
        Label filterLabel = new Label("Filter:");
        Label sortLabel = new Label("Sort:");
        sortLabel.setStyle("-fx-text-fill: #8B4513; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold;");
        filterLabel.setStyle("-fx-text-fill: #8B4513; " + 
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold;");
        pageSettings.getChildren().addAll(sortLabel, sortBox, filterLabel, filterMealTypeBox, signOutButton);

        // Set alignment for the buttons
        // pageSettings.setMargin(signOutButton, new Insets(0, 0, 0, 10)); // Add margin to the right button
        pageSettings.setAlignment(javafx.geometry.Pos.CENTER); // Align buttons to the right


        //pageSettings.getChildren().add(comboBox);
        // Add the spacing between this comboBox and the signOut button
        //pageSettings.getChildren().add(signOutButton);
        
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
        mainContent.getChildren().add(pageSettings); // may have to redo
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

        mpc = new MainPageController(this);
    }

    public void update(){
        RecipeTitleListView.getInstance().getChildren().clear();
        DatabaseOPS db = new DatabaseOPS(Globals.username);
        Globals.recipes = db.initializeRecipesToList(); //<-- this will return the arraylist

        // Add the recipes to the filter list
        if(Globals.filterType == Globals.FilterType.BREAKFAST){
            Globals.recipes = Filters.filterByBreakfast(Globals.recipes);
        } else if(Globals.filterType == Globals.FilterType.LUNCH){
            Globals.recipes = Filters.filterByLunch(Globals.recipes);
        } else if(Globals.filterType == Globals.FilterType.DINNER){
            Globals.recipes = Filters.filterByDinner(Globals.recipes);
            System.out.println("DINNER");
        }

        // Sort the recipes
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