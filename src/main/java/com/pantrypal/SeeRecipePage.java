package com.pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SeeRecipePage extends BorderPane {

    private paneHeader paneHeader;
    private paneFooter paneFooter;
    private VBox center;
    private Button recordButton;
    private String title;
    private String detail;

    SeeRecipePage(Recipe r)
    {
        this.title = r.getRecipeTitle();
        this.detail = r.getRecipeInstructions();
        
        VBox mainContent = new VBox();
        // mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        Label detailLable = new Label(detail);
        detailLable.setTextFill(Color.web("#8B4513"));
        detailLable.setWrapText(true);
        //changing font size so itll fit
        detailLable.setStyle("-fx-font-weight: bold; -fx-font-size: 16;"); 
        mainContent.getChildren().add(detailLable);//TODO: we need to fill in what chatgpt said
        paneHeader a =new paneHeader();
        this.paneHeader = new paneHeader();
        this.center = mainContent;
        this.paneFooter = new paneFooter();
        this.paneHeader.setTitleInMiddle(new Text(this.title));   
        this.setTop(paneHeader);
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
