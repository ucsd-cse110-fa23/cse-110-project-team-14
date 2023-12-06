package com.pantrypal.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pantrypal.controller.MealTypePageController;
import com.pantrypal.model.LiveRecorder;
import com.pantrypal.model.Whisper;

public class MealTypePage extends Page {
    private paneHeader paneHeader;
    private paneFooter paneFooter;
    public VBox center;
    public Button recordButton;
    private Button back;
    public LiveRecorder liveRecorder;
    public Whisper whisper = new Whisper();
    private boolean isRecording;
    public String mealType = "lunch";//defaut is Lunch
    private MealTypePageController mtpc; 

    public MealTypePage(int width, int height) {
        super(width, height);
    }

    public void setRecordButtonAction(EventHandler<ActionEvent> eventHandler){
        recordButton.setOnAction(eventHandler);
    }

    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler){
        back.setOnAction(eventHandler);
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
            this.paneHeader = new paneHeader();
            paneHeader.setTitleInMiddle(new Text("Do you want a Breakfast, Lunch, or Dinner.\n                        Say your Meal Type..."));
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

            this.recordButton = paneFooter.creatButton("MICRPHONE", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            this.back = paneFooter.creatButton("Back", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            this.paneFooter.setButton(recordButton);
            this.paneFooter.setButton(back);
            isRecording = false;
            this.liveRecorder = new LiveRecorder();
            mtpc = new MealTypePageController(this);
        }
    }
}
