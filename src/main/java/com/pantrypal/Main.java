package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    mainPage root = new mainPage(600, 600);


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(false);
        primaryStage.setScene(root.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}