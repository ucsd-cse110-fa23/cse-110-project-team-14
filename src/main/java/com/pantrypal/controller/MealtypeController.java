package com.pantrypal.controller;
import com.pantrypal.view.pages.MealTypePageView;
import com.pantrypal.model.MealTypeModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealtypeController extends Controller{

    MealTypePageView mealTypePage;
    MealTypeModel model;
    MealtypeController(MealTypePageView mealTypePage, MealTypeModel model)
    {
        this.model = model;
        this.mealTypePage = mealTypePage;
    }


    private void handleRecordButton(ActionEvent event) {
        mealTypePage.setIsrecording(!mealTypePage.getIsRecording());
        if(mealTypePage.getIsRecording())
        {
            //start recording
            mealTypePage.getRecordButton().setText("RECORDING...?");
        }else {
            //stop recording
            mealTypePage.getRecordButton().setText("GOT VOICE");
        }
        stg.changeTo("RecordingPage");
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
//            }
//            //model.RecogMealType()
//            );
//
//    }
}
