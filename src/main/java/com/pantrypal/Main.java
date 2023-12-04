package com.pantrypal;

import com.pantrypal.constants.Constants;
import com.pantrypal.controller.StageController;
import com.pantrypal.view.pages.MainPageView;
import com.pantrypal.view.pages.RecordPageView;
import javafx.application.Application;
import javafx.stage.Stage;
import com.pantrypal.view.pages.MealTypePageView;
public class Main extends Application {
    MainPageView root = new MainPageView(Constants.WIDTH, Constants.HEIGHT);
    MealTypePageView mealTypePageView = new MealTypePageView(Constants.WIDTH, Constants.HEIGHT);
    RecordPageView recordPageView = new RecordPageView(Constants.WIDTH, Constants.HEIGHT);

    public void start(Stage primaryStage) throws Exception {
        StageController stgControl = StageController.getInstance();
        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(false);
        primaryStage.setScene(root.getScene());
        stgControl.init(primaryStage);
        stgControl.registerPage(Constants.MAINPAGE_NAME, root);
        stgControl.registerPage(Constants.MEALTYPE_PAGE_NAME, mealTypePageView);
        stgControl.registerPage(Constants.RECORDPAGE_NAME, recordPageView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
