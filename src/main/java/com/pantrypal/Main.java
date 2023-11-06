package com.pantrypal;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;





class Appframe extends BorderPane {

     paneHeader paneHeader = new paneHeader();
     paneFooter paneFooter = new paneFooter();
    private Button addButton;
    private Button SeeRecipeDetail;
    Appframe() {
//        header = new Header();
//        footer = new Footer();

        VBox mainContent = new VBox();
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        paneHeader.setTitleInMiddle(new Text("PantryPal: The best Recipe manager"));

        this.setTop(paneHeader);
        this.setCenter(mainContent);
        this.setBottom(paneFooter);

        this.addButton = paneFooter.creatButton("addButton","-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        this.SeeRecipeDetail = paneFooter.creatButton("SeeRecipeDetail","-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");
        paneFooter.setButton(this.addButton);
        paneFooter.setButton(this.SeeRecipeDetail);

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


    public Button getSeeRecipeDetail() {
        return this.SeeRecipeDetail;
    }

}


public class Main extends Application {
    Appframe root = new Appframe();
    RecordPage rp = new RecordPage();
    SeeRecipePage SRP = new SeeRecipePage();
    private Button addButton = root.getAddButton();
    private Button SeeRecipeDetail = root.getSeeRecipeDetail();
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

        SeeRecipeDetail.setOnAction(e2 -> {
            primaryStage.setScene(new Scene(SRP, width, height));

        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}