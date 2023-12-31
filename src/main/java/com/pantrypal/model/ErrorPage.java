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

    // Disply a blank Error page
    // Constant, no buttons, no pinging
    @Override
    protected void createView() {
        // Main vertical box to hold all content in the page
        VBox mainContent = new VBox();
        mainContent.setSpacing(15); // Set spacing between elements
        mainContent.setAlignment(Pos.CENTER); // Center align the content

        // Initialize the header pane
        this.paneHeader = new paneHeader();

        // Initialize and configure the error message label
        errorMsg = new Label();
        errorMsg.setTextFill(Color.web("#8B4513")); // Set text color
        errorMsg.setStyle("-fx-font-weight: bold; -fx-font-size: 18;"); // Set font style
        errorMsg.setText("Error Server Unexpectedly Crashed."); // Set error message text

        // Add the error message label to the main content vertical box
        mainContent.getChildren().add(errorMsg);

        // Set the main content to the center of the page
        this.center = mainContent;

        // Initialize the footer pane
        this.paneFooter = new paneFooter();

        // Set the header, center, and footer panes to the respective positions in the border pane
        this.borderPane.setTop(this.paneHeader);
        this.borderPane.setCenter(this.center);
        this.borderPane.setBottom(this.paneFooter);

        // Set the style for the border pane (background color, border color, etc.)
        this.borderPane.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");
    }
}
