package com.pantrypal.view.pages;

import com.pantrypal.controller.MealtypeController;
import com.pantrypal.model.WhisperModel;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import com.pantrypal.model.LiveRecorder;
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
import java.util.EventListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealTypePageView extends Page {
    private VBox center;

    private LiveRecorder liveRecorder;
    private WhisperModel whisper = new WhisperModel();
    private boolean isRecording;
    private String mealType = "lunch";//defaut is Lunch
    private MealtypeController mealtypeController;

    public MealTypePageView(int width, int height) {
        super(width, height);
        paneHeader = new PaneHeader();
        paneFooter = new PaneFooter();

    }


    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler){
//        System.out.println("--------IN setBackButtonAction");
//        System.out.println(System.identityHashCode(this.paneFooter.getButton("Back")));
        this.paneFooter.getButton("Back").setOnAction(eventHandler);
//        System.out.println("--------out setBackButtonAction");
    }

    public void setRecordButtonAction(EventHandler<ActionEvent> eventHandler)
    {
//        System.out.println("-------IN setRecordButtonAction");
//        System.out.println("setRecordButtonAction");
//        System.out.println(System.identityHashCode(this.paneFooter.getButton("MICRPHONE")));
        this.paneFooter.getButton("MICRPHONE").setOnAction(eventHandler);
//        System.out.println("-------out setRecordButtonAction");
    }

    public boolean getIsRecording() {
        return isRecording;
    }
    public Button getRecordButton()
    {
        return paneFooter.getButton("MICRPHONE");
    }
    public Button getBackButton()
    {
        return paneFooter.getButton("Back");
    }
    public void setIsrecording(boolean b) {
        this.isRecording = b;
    }
    public void setMealType(String mealType)
    {
        this.mealType = mealType;
    }
    public WhisperModel getWhisper()
    {
        return this.whisper;
    }
    public LiveRecorder getLiveRecorder()
    {
        return this.liveRecorder;
    }
    @Override
    protected void createView() {
            paneFooter = new PaneFooter();
            paneHeader = new PaneHeader();
            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            paneHeader.setTitleInMiddle(new Text("Do you want a Breakfast, Lunch, or Dinner.\n                        Say your Meal Type..."));
            this.center = mainContent;
            this.borderPane.setTop(this.paneHeader);
            this.borderPane.setCenter(this.center);
            this.borderPane.setBottom(this.paneFooter);
            this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                    "-fx-border-color: #DEB887; " +
                    "-fx-border-width: 10; " +
                    "-fx-border-radius: 15; " +
                    "-fx-background-radius: 15;");

            Button recordButton = paneFooter.creatButton("MICRPHONE", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            Button backButton = paneFooter.creatButton("Back", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            this.paneFooter.setButton("MICRPHONE");
            this.paneFooter.setButton("Back");
            isRecording = false;
            this.mealtypeController = new MealtypeController(this);
            this.liveRecorder = new LiveRecorder();

    }

    public String getMealType() {
        return mealType;
    }
    public VBox getCenter()
    {
        return this.center;
    }
}
