package com.pantrypal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SeeRecipePage extends BorderPane {

    private SeeRecipePage.Header header;
    private SeeRecipePage.Footer footer;
    private VBox center;
    private Button recordButton;
    private String title = "Spaghetti Carbonara";
    private String detail= "Instructions:\n" +
            "\n" +
            "Cook spaghetti in salted boiling water until al dente.\n" +
            "Fry pancetta/bacon until crisp.\n" +
            "Beat eggs with grated Parmesan and pepper.\n" +
            "Drain pasta, reserving some cooking water.\n" +
            "Mix hot spaghetti with egg mixture and pancetta.\n" +
            "Add pasta water if needed to make it creamy.\n" +
            "Serve with extra cheese and pepper.";
    class Header extends HBox {
        Header() {
            this.setPrefSize(1000, 60);
            this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 15 15 0 0; -fx-background-radius: 15 15 0 0;");

            Text titleText = new Text(title);
           //TODO: need to be filled in from what gpt said
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


        Footer() {
            this.setPrefSize(1000, 60);
            this.setStyle("-fx-background-color: #F5DEB3; -fx-border-radius: 0 0 15 15; -fx-background-radius: 0 0 15 15;");


            this.setAlignment(Pos.CENTER);

            // Add shadow for depth
            DropShadow ds = new DropShadow();
            ds.setOffsetY(-3.0);
            ds.setColor(Color.color(0.4, 0.4, 0.4));
            this.setEffect(ds);
        }


    }

    SeeRecipePage()
    {
        VBox mainContent = new VBox();
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        Label detailLable = new Label(detail);
        detailLable.setTextFill(Color.web("#8B4513"));
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
        mainContent.getChildren().add(detailLable);//TODO: we need to fill in what chatgpt said

        this.header = new SeeRecipePage.Header();
        this.center = mainContent;
        this.footer = new SeeRecipePage.Footer();

        this.setTop(this.header);
        this.setCenter(this.center);
        this.setBottom(this.footer);

        this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");





        //addListeners();
    }
}
