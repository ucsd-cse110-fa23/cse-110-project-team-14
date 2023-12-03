package com.pantrypal;
import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application {
    RegisterPageView root = new RegisterPageView(constants.width, constants.height);
    // mainPage root = new mainPage(constants.width, constants.height);
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(false);
        primaryStage.setScene(root.getScene());
        StageController stgControl = StageController.getInstance();
        stgControl.init(primaryStage);
        stgControl.registerPage("RegisterPage", root);
        stgControl.registerPage("mainPage", new mainPage(constants.width,constants.height));
        stgControl.registerPage("MealTypePage",new MealTypePage(constants.width,constants.height));
        //TODO: we need load the all saved recipe from database

        stgControl.registerPage("RecordPage", new RecordPage(constants.width, constants.height));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}