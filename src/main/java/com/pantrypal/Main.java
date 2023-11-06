package com.pantrypal;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class Header extends HBox {
    Header() {
        this.setPrefSize(1000, 60);
        this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 15 15 0 0; -fx-background-radius: 15 15 0 0;");
        
        Text titleText = new Text("PantryPal: The best Recipe manager");
        titleText.setFill(Color.web("#8B4513"));  // Saddle Brown
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER);
        
        // Add shadow for depth
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        this.setEffect(ds);
    }
}

class Footer extends HBox {
    private Button addButton;

    Footer() {
        this.setPrefSize(1000, 60);
        this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 0 0 15 15; -fx-background-radius: 0 0 15 15;");

        addButton = new Button("Create Recipe");
        addButton.setStyle("-fx-background-color: #FFEBD7; -fx-text-fill: #8B4513; -fx-border-color: #8B4513; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5 15 5 15;");

        this.getChildren().addAll(addButton);
        this.setAlignment(Pos.CENTER);

        // Add shadow for depth
        DropShadow ds = new DropShadow();
        ds.setOffsetY(-3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        this.setEffect(ds);
    }


    public Button getAddButton() {
        return addButton;
    }
}


class Appframe extends BorderPane {
    private Header header;
    private Footer footer;
    private Button addButton;

    Appframe() {
        header = new Header();
        footer = new Footer();

        VBox mainContent = new VBox();
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);

        this.setTop(header);
        this.setCenter(mainContent);
        this.setBottom(footer);

        this.addButton = footer.getAddButton();

        this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                      "-fx-border-color: #DEB887; " +
                      "-fx-border-width: 10; " +
                      "-fx-border-radius: 15; " +
                      "-fx-background-radius: 15;");
        addListeners();
    }
     public void addListeners() {
        // add buttons that don't change page
    }

    public Button getAddButton() {
        return this.addButton;
    }
}


public class Main extends Application {
    Appframe root = new Appframe();
    RecordPage rp = new RecordPage();
    private Button addButton = root.getAddButton();

    @Override
    public void start(Stage primaryStage) throws Exception {
       
        //CHANGE later for resolution
        int width = 600;
        int height = 600; //back to 900? 

        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(false);
        
        primaryStage.setScene(new Scene(root, width, height));

        // main handles all listeners for now?
        addButton.setOnAction(e -> {
            // SET TO RECORDING STAGE
            primaryStage.setScene(new Scene(rp, width, height));
            System.out.println("SWITCHED TO RECORD PAGE");
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
