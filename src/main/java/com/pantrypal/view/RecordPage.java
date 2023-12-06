package com.pantrypal.view;

import com.pantrypal.controller.RecordPageController;
import com.pantrypal.model.LiveRecorder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RecordPage extends Page {

    // Components for the RecordPage
    private paneHeader paneHeader;
    private paneFooter paneFooter;
    private VBox center;
    private Button recordButton;
    private Button back;

    // Variables for recording state and meal type
    private boolean isRecording;
    private String mealType;

    // Controller for handling RecordPage actions
    RecordPageController recordPageController;

    // Constructor to initialize the RecordPage with width and height
    public RecordPage(int width, int height) {
        super(width, height);
    }

    // Setter for setting the meal type associated with the RecordPage
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    // Getter for retrieving the meal type associated with the RecordPage
    public String getMealType() {
        return this.mealType;
    }

    // Getter for retrieving the recording state of the RecordPage
    public boolean getIsRecording() {
        return isRecording;
    }

    // Setter for updating the recording state of the RecordPage
    public void setIsRecording(boolean b) {
        this.isRecording = b;
    }

    // Override method to create the view of the RecordPage
    @Override
    protected void createView() {
        {
            // Create main content VBox
            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);

            // Initialize components
            this.paneHeader = new paneHeader();
            paneHeader.setTitleInMiddle(new Text("Say your " + mealType + " Ingredients..."));
            this.center = mainContent;
            this.paneFooter = new paneFooter();
            this.borderPane.setTop(this.paneHeader);
            this.borderPane.setCenter(this.center);
            this.borderPane.setBottom(this.paneFooter);

            // Set styles for the RecordPage
            this.borderPane.setStyle(
                    "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); "
                            + "-fx-border-color: #DEB887; " +
                            "-fx-border-width: 10; " +
                            "-fx-border-radius: 15; " +
                            "-fx-background-radius: 15;");

            // Create and configure RecordButton
            this.recordButton = paneFooter.creatButton("mic", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            // Create and configure BackButton
            back = paneFooter.creatButton("Back", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            // Set buttons in the footer
            this.paneFooter.setButton(recordButton);
            paneFooter.setButton(back);
            isRecording = false;

            // Initialize RecordPageController for handling actions
            recordPageController = new RecordPageController(this);
        }
    }

    // Setter for attaching an action event to the RecordButton
    public void setRecordButtonAction(EventHandler<ActionEvent> eventHandler) {
        recordButton.setOnAction(eventHandler);
    }

    // Setter for attaching an action event to the BackButton
    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        back.setOnAction(eventHandler);
    }

    // Getter for retrieving the RecordButton
    public Button getRecordButton() {
        return recordButton;
    }

}
