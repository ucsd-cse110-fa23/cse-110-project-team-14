package com.pantrypal.model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

import com.pantrypal.view.Page;
import com.pantrypal.view.paneFooter;
import com.pantrypal.view.paneHeader;

import javafx.scene.control.Label;

public class ErrorPage extends Page {
    private paneHeader paneHeader;
    private paneFooter paneFooter;
    private VBox center;
    private Label errorMsg;
    
    public ErrorPage(int width, int height) {
        super(width, height);
    }

    @Override
    protected void createView() {
        {

            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.paneHeader = new paneHeader();
            
            errorMsg = new Label();
            errorMsg.setTextFill(Color.web("#8B4513"));
            errorMsg.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
            
            errorMsg.setText("Error Server Unexpectadly Crashed.");
            mainContent.getChildren().add(errorMsg);

            this.center = mainContent;
            this.paneFooter = new paneFooter();
            this.borderPane.setTop(this.paneHeader);
            this.borderPane.setCenter(this.center);
            this.borderPane.setBottom(this.paneFooter);

            this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                    "-fx-border-color: #DEB887; " +
                    "-fx-border-width: 10; " +
                    "-fx-border-radius: 15; " +
                    "-fx-background-radius: 15;");

        }
    }


}
