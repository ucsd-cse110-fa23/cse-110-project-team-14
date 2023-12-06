package com.pantrypal.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pantrypal.view.MealTypePage;
import com.pantrypal.view.RecordPage;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MealTypePageController {
    private MealTypePage mtp;
    public MealTypePageController(MealTypePage mtp){
        this.mtp = mtp;
        this.mtp.setRecordButtonAction(this::handleRecordButton);
        this.mtp.setBackButtonAction(this::handleBackButton);
    }

    public void handleRecordButton(ActionEvent event)
    {
            // isRecording = !isRecording; // TOGGLES
            mtp.setIsrecording(!mtp.getIsRecording()); // this works?

            if (mtp.getIsRecording()) {
                mtp.recordButton.setText("RECORDING...?");
                mtp.liveRecorder.startRecording();

            }
            if (!mtp.getIsRecording()) {
                // HERE WE WOULD OPEN THE NEW WINDOW
                mtp.liveRecorder.stopRecording();
                mtp.recordButton.setText("GOT VOICE");

                try {
                    // make recipe
                    mtp.mealType = mtp.whisper.getResponse();
                    mtp.mealType = mtp.mealType.toLowerCase();

                    Pattern pattern = Pattern.compile("lunch|dinner|breakfast");
                    Matcher matcher = pattern.matcher(mtp.mealType);
                    if (matcher.find()) {
                        mtp.mealType = matcher.group();//we only want mealType be a single world.
                    } else {
                        mtp.mealType = "";//defaut meal type is lunch
                    }
                    if(mtp.mealType=="")
                    {
                        mtp.center.getChildren().clear();
                        Label errorLabel  =new Label("Can't recognize meal type,\n please say breakfast, lunch, or dinner. ");
                        errorLabel.setTextFill(Color.web("#8B4513"));
                        errorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 22;");
                        mtp.center.getChildren().add(errorLabel);
                        //this.update();
                    }
                    else
                    {
                        StageController stg = StageController.getInstance();
                        RecordPage recordPage;
                        recordPage = (RecordPage) stg.getPage("RecordPage");
                        recordPage.setMealType(mtp.mealType);
                        stg.changeTo("RecordPage");
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

    }

    public void handleBackButton(ActionEvent event)
    {
        StageController stg =  StageController.getInstance();
        stg.changeTo("mainPage");
    }

}