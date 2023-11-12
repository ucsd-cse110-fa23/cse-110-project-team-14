package com.pantrypal;
import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application {
    mainPage root = new mainPage(600, 600);
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(false);
        primaryStage.setScene(root.getScene());
        StageController stgControl = StageController.getInstance();
        stgControl.init(primaryStage);
        stgControl.registerPage("mainPage", root);
        stgControl.registerPage("RecordPage", new RecordPage(600, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}