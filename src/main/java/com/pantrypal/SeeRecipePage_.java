package com.pantrypal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.net.http.WebSocket;

// SeeRecipePage now extends the abstract Page class
public class SeeRecipePage_ extends Page {

    private String title = "Spaghetti Carbonara";
    private String detail = "Instructions:\n";
    // Recipe instructions
    // ...
    private Button back;
    private paneHeader Header;
    private VBox center;
    private paneFooter Footer;

    public SeeRecipePage_(int width, int height) {
        super(width, height);
    }

    public void addListeners() {
        back.setOnAction(e -> {
            // go back
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(new mainPage(width, height).getScene());
            System.out.println("SWITCHED TO See PAGE");
        });
        // add buttons that don't change page
    }

    // Implementation of the createView method from Page
    @Override
    protected void createView() {
        // Create header
//        Text titleText = new Text(title);
//        titleText.setFill(Color.web("#8B4513"));
//        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
//        VBox headerBox = new VBox(titleText);
//        headerBox.setAlignment(Pos.CENTER);
//        headerBox.setPadding(new Insets(10));
//        headerBox.setStyle("-fx-background-color: #F5DEB3; " +
//                "-fx-border-radius: 15 15 0 0; " +
//                "-fx-background-radius: 15 15 0 0;");
//
//        // Create footer if needed
//
//        // Create center content
//        Label detailLabel = new Label(detail);
//        detailLabel.setTextFill(Color.web("#8B4513"));
//        detailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
//        VBox centerBox = new VBox(detailLabel);
//        centerBox.setSpacing(15);
//        centerBox.setAlignment(Pos.CENTER);
//
//        // Assign sections to the BorderPane
//        this.borderPane.setTop(headerBox);
//        this.borderPane.setCenter(centerBox);
//        // Set footer if created
//
//        // Apply overall styling
//        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
//                "-fx-border-color: #DEB887; " +
//                "-fx-border-width: 10; " +
//                "-fx-border-radius: 15; " +
//                "-fx-background-radius: 15;");


        ///////
        back = new Button("back");
        VBox mainContent = new VBox();
        // mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        Label detailLable = new Label(detail);
        detailLable.setTextFill(Color.web("#8B4513"));
        detailLable.setWrapText(true);
        //changing font size so itll fit
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");

        Label ingredientLabel = new Label(this.detail);
        ingredientLabel.setTextFill(Color.web("#8B4513"));
        ingredientLabel.setWrapText(true);
        //changing font size so itll fit
        ingredientLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");

        mainContent.getChildren().addAll(ingredientLabel, detailLable);//TODO: we need to fill in what chatgpt said
        paneHeader a = new paneHeader();
        Header = new paneHeader();
        this.center = mainContent;
        Footer = new paneFooter();
        Header.setTitleInMiddle(new Text(this.title));
        Footer.setButton(back);
        this.borderPane.setTop(Header);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.Footer);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

        addListeners();

    }
}
