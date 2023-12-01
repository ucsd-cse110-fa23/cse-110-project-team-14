package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.view.pages.MealTypePageView;
import com.pantrypal.view.pages.RecordPageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealtypeController extends Controller {

    MealTypePageView mealTypePage;

    public MealtypeController(MealTypePageView mealTypePage) {
        this.mealTypePage = mealTypePage;
//        System.out.println("********IN MealtypeController");
        this.mealTypePage.setRecordButtonAction(this::handleRecordButton);
//        System.out.println(this.mealTypePage.getRecordButton().getText()+", ID:" + System.identityHashCode(this.mealTypePage.getRecordButton()));
        this.mealTypePage.setBackButtonAction(this::handleBackButton);
//        System.out.println(this.mealTypePage.getRecordButton().getText()+", ID:" + System.identityHashCode(this.mealTypePage.getBackButton()));
//        System.out.println("********OUT MealtypeController");
    }

    private void handleRecordButton(ActionEvent event) {
//        System.out.println("handleRecordButton");
        mealTypePage.setIsrecording(!mealTypePage.getIsRecording());
        if (mealTypePage.getIsRecording()) {
            //start recording
            mealTypePage.getRecordButton().setText("RECORDING...?");
            mealTypePage.getLiveRecorder().startRecording();
        } else {
            //stop recording
            mealTypePage.getRecordButton().setText("GOT VOICE");
            mealTypePage.getLiveRecorder().stopRecording();

            try {
                // make recipe
                mealTypePage.setMealType(mealTypePage.getWhisper().getResponse());

                String mealType = mealTypePage.getMealType();
                mealType = mealType.toLowerCase();
                System.out.println("mealType: " + mealType);
                Pattern pattern = Pattern.compile("lunch|dinner|breakfast");
                Matcher matcher = pattern.matcher(mealType);
                if (matcher.find()) {
                    mealType = matcher.group();//we only want mealType be a single world.
                } else {
                    mealType = "";//defaut meal type
                }
                if (mealType == "") {
                    mealTypePage.getCenter().getChildren().clear();
                    Label errorLabel = new Label("Can't recognize meal type,\n please say breakfast, lunch, or dinner. ");
                    errorLabel.setTextFill(Color.web("#8B4513"));
                    errorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 22;");
                    mealTypePage.getCenter().getChildren().add(errorLabel);
                    //mealTypePage.update();
                } else {
                    RecordPageView recordPage;
                    recordPage = (RecordPageView) stg.getPage(Constants.RECORDPAGE_NAME);
                    recordPage.setMealType(mealType);
                    stg.changeTo(Constants.RECORDPAGE_NAME);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void handleBackButton(ActionEvent event) {
        stg.changeTo(Constants.MAINPAGE_NAME);
    }


}


