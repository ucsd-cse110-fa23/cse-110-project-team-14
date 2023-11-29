package com.pantrypal.view.pages;

import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
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
    private PaneHeader paneHeader;
    private PaneFooter paneFooter;
    private VBox center;
    private Button recordButton;
    private Button back;
   // public LiveRecorder liveRecorder;
    //public Whisper whisper = new Whisper();
    private boolean isRecording;
    private String mealType = "lunch";//defaut is Lunch

    public MealTypePageView(int width, int height) {
        super(width, height);
    }

//    public void addListeners() {
//        recordButton.setOnAction(e -> {
//            // isRecording = !isRecording; // TOGGLES
//            this.setIsrecording(!this.getIsRecording()); // this works?
//
//            if (this.getIsRecording()) {
//                recordButton.setText("RECORDING...?");
//                //this.liveRecorder.startRecording();
//
//            }
//            if (!this.getIsRecording()) {
//                // HERE WE WOULD OPEN THE NEW WINDOW
//                this.liveRecorder.stopRecording();
//                recordButton.setText("GOT VOICE");
//
//                try {
//                    // make recipe
//                    mealType = this.whisper.getResponse();
//                    mealType = mealType.toLowerCase();
//
//                    Pattern pattern = Pattern.compile("lunch|dinner|breakfast");
//                    Matcher matcher = pattern.matcher(mealType);
//                    if (matcher.find()) {
//                        mealType = matcher.group();//we only want mealType be a single world.
//                    } else {
//                        mealType = "";//defaut meal type is lunch
//                    }
//                    if(mealType=="")
//                    {
//                        this.center.getChildren().clear();
//                        Label errorLabel  =new Label("Can't recognize meal type,\n please say breakfast, lunch, or dinner. ");
//                        errorLabel.setTextFill(Color.web("#8B4513"));
//                        errorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 22;");
//                        this.center.getChildren().add(errorLabel);
//                        //this.update();
//                    }
//                    else
//                    {
//                        StageController stg = StageController.getInstance();
//                        RecordPage recordPage;
//                        recordPage = (RecordPage) stg.getPage("RecordPage");
//                        recordPage.setMealType(mealType);
//                        stg.changeTo("RecordPage");
//                    }
//
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                } catch (URISyntaxException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//
//        back.setOnAction(e->
//        {
//            StageController stg =  StageController.getInstance();
//            stg.changeTo("mainPage");
//        });
//    }

    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler){
        back.setOnAction(eventHandler);
    }

    public void setRecordButtonAction(EventHandler<ActionEvent> eventHandler)
    {
        recordButton.setOnAction(eventHandler);
    }

    public boolean getIsRecording() {
        return isRecording;
    }
    public Button getRecordButton()
    {
        return recordButton;
    }
    public void setIsrecording(boolean b) {
        this.isRecording = b;
    }

    @Override
    protected void createView() {


            VBox mainContent = new VBox();
            mainContent.setSpacing(15);
            mainContent.setAlignment(Pos.CENTER);
            this.paneHeader = new PaneHeader();
            paneHeader.setTitleInMiddle(new Text("Do you want a Breakfast, Lunch, or Dinner.\n                        Say your Meal Type..."));
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
            this.back = paneFooter.creatButton("Back", "-fx-background-color: #FFEBD7; " +
                    "-fx-text-fill: #8B4513; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 5 15 5 15;");
            this.paneFooter.setButton(recordButton);
            this.paneFooter.setButton(back);
            isRecording = false;
            //this.liveRecorder = new LiveRecorder();

    }
}
