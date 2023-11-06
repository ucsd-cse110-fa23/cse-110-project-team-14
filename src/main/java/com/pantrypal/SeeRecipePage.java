package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SeeRecipePage extends BorderPane {

    private paneHeader paneHeader;
    private paneFooter paneFooter;
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

    SeeRecipePage()
    {
        VBox mainContent = new VBox();
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        Label detailLable = new Label(detail);
        detailLable.setTextFill(Color.web("#8B4513"));
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
        mainContent.getChildren().add(detailLable);//TODO: we need to fill in what chatgpt said
        paneHeader a =new paneHeader();
        this.paneHeader = new paneHeader();
        this.center = mainContent;
        this.paneFooter = new paneFooter();
        paneHeader.setTitleInMiddle(new Text(title));
        this.setTop(this.paneHeader);
        this.setCenter(this.center);
        this.setBottom(this.paneFooter);

        this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");





        //addListeners();
    }
}
