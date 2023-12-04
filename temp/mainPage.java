package temp;

import com.pantrypal.Globals;
import com.pantrypal.constants.Constants;
import com.pantrypal.controller.StageController;
import com.pantrypal.model.DatabaseOPS;
import com.pantrypal.model.Recipe;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import com.pantrypal.view.pages.Page;
import com.pantrypal.view.pages.RecipeTitleListView;
import com.pantrypal.view.pages.RecipeTitleView;
import com.pantrypal.view.pages.SeeRecipePageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class mainPage extends Page {
    private PaneHeader paneHeader;
    private PaneFooter paneFooter;
    private Button addButton;
    //private arraylist<recipe> recipes; which has all the recipes and we use it to sort 
    public mainPage(int width, int height) {
        super(width, height);
        // IntializeRecipeList.uploadRecipes();
        DatabaseOPS db = new DatabaseOPS("recipes");
        Globals.recipes = db.initializeRecipesToList(); //<-- this will return the arraylist
        for (int i = Globals.recipes.size() - 1; i >= 0; i--) {
            Recipe recipe = Globals.recipes.get(i);
            RecipeTitleView recipeTitleView = new RecipeTitleView(recipe); //<-- This should be UI
            SeeRecipePageView SRP = new SeeRecipePageView(Constants.WIDTH, Constants.HEIGHT); //<-- This should be UI
            SRP.setRecipe(recipe);
            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                StageController stg = StageController.getInstance();
                stg.registerPage(recipe.getRecipeTitle(), SRP);
                stg.changeTo(recipe.getRecipeTitle());
            });
            RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
        }
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

        paneHeader = new PaneHeader();
        paneHeader.setTitleInMiddle(new Text("PantryPal: The best Recipe manager"));
        
        ScrollPane scroll = new ScrollPane(RecipeTitleListView.getInstance());
        scroll.setPrefSize(1000, 1000);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        paneFooter = new PaneFooter();
        
        VBox mainContent = new VBox();
        mainContent.minHeight(1000);
        mainContent.minWidth(1000);
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
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
}