package com.pantrypal.view;

import com.pantrypal.constants.Constants;
import com.pantrypal.controller.MainPageController;
import com.pantrypal.controller.StageController;
import com.pantrypal.model.MealTypeModel;
import com.pantrypal.view.pages.MainPageView;
import javafx.application.Application;
import javafx.stage.Stage;
import com.pantrypal.view.pages.MealTypePageView;
//import com.pantrypal.view.pages.RecordPageView;

import static javafx.application.Application.launch;

public class Main extends Application {
    MainPageView root = new MainPageView(Constants.width, Constants.height);

    public void start(Stage primaryStage) throws Exception {

        MainPageController mainPageController = new MainPageController(root);
        MealTypeModel mealTypeModel = new MealTypeModel();
        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(false);
        primaryStage.setScene(root.getScene());
        StageController stgControl = StageController.getInstance();
        stgControl.init(primaryStage);
        stgControl.registerPage(Constants.mainPagename, root);

        stgControl.registerPage(Constants.mealTypePagename,new MealTypePageView(Constants.width,Constants.height));
        //TODO: we need load the all saved recipe from database

        //stgControl.registerPage(Constants.recordPagename, new RecordPageView(Constants.width, Constants.height));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
