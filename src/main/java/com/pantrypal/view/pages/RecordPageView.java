package com.pantrypal.view.pages;

import com.pantrypal.controller.RecordPageController;
import com.pantrypal.model.LiveRecorder;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RecordPageView extends Page {
    private PaneHeader paneHeader;
    private PaneFooter paneFooter;
    private VBox center;
    private Button recordButton;

    private Button back;

    // public LiveRecorder liveRecorder;
    // Whisper whisper = Whisper.getInstance();
    private boolean isRecording;
    private String mealType;
    RecordPageController recordPageController;
    public RecordPageView(int width, int height) {
        super(width, height);
    }

    public void setMealType(String mealType)
    {
        this.mealType = mealType;
    }
    public String getMealType()
    {
        return this.mealType;
    }


    public boolean getIsRecording() {
        return isRecording;
    }

    public void setIsrecording(boolean b) {
        this.isRecording = b;
    }

    @Override
    protected void createView() {
        {

            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.paneHeader = new PaneHeader();
            paneHeader.setTitleInMiddle(new Text("Say your "+mealType+" Ingredients..."));
            this.center = mainContent;
            this.paneFooter = new PaneFooter();
            this.borderPane.setTop(this.paneHeader);
            this.borderPane.setCenter(this.center);
            this.borderPane.setBottom(this.paneFooter);

            this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                    "-fx-border-color: #DEB887; " +
                    "-fx-border-width: 10; " +
                    "-fx-border-radius: 15; " +
                    "-fx-background-radius: 15;");

            this.recordButton = paneFooter.creatButton("MICRPHONE", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            back = paneFooter.creatButton("Back", "-fx-background-color: #FFEBD7; " +

                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");

            this.paneFooter.setButton(recordButton);
            paneFooter.setButton(back);
            isRecording = false;
            // this.liveRecorder = new LiveRecorder();
            
            recordPageController = new RecordPageController(this);

        }
    }
    public void setRecordButtonAction(EventHandler<ActionEvent> eventHandler) {
        recordButton.setOnAction(eventHandler);
    }

    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        back.setOnAction(eventHandler);
    }


    public Button getRecordButton() {
        return recordButton;
    }

    // public LiveRecorder getLiveRecorder() {
    //     return liveRecorder;
    // }
}
