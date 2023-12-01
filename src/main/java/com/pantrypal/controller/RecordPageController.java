package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.model.WhisperModel;
import com.pantrypal.view.pages.RecordPageView;
import com.pantrypal.view.pages.SeeRecipeFromRecordingView;
import javafx.event.ActionEvent;

public class RecordPageController extends Controller {


    private RecordPageView recordPageView;

    public RecordPageController(RecordPageView recordPageView) {
        this.recordPageView = recordPageView;
        this.recordPageView.setRecordButtonAction(this::handRecordButton);
        this.recordPageView.setBackButtonAction(this::handleBackButton);
    }

    public void handRecordButton(ActionEvent event) {
        recordPageView.setIsrecording(!recordPageView.getIsRecording());
        if (recordPageView.getIsRecording()) {
            //start recording
            recordPageView.getRecordButton().setText("RECORDING...?");
            recordPageView.getLiveRecorder().startRecording();
        } else {
            //stop recording
            recordPageView.getRecordButton().setText("GOT VOICE");
            recordPageView.getLiveRecorder().stopRecording();

            try {

                String transcribedText = WhisperModel.getInstance().getResponse();
                TextToRecipe t2R = new TextToRecipe(transcribedText, recordPageView.getMealType(), new Recipe(), new ChatGPT());
                WhisperModel.getInstance().setText(transcribedText);
                t2R.createRecipeObj();
                System.out.println("succes");
                SeeRecipeFromRecordingView SRV = new SeeRecipeFromRecordingView(Constants.WIDTH, Constants.HEIGHT);
                SRV.setRecipe(t2R.getRecipe());
//TODO: add to database
                // make a listener for recipeTitleView
                // RecipeTitleView recipeTitleView = new RecipeTitleView(t2R.getRecipe());
//                    recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
//                        StageController stg = StageController.getInstance();
//                        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRP);
//                        stg.changeTo(t2R.getRecipe().getRecipeTitle());
//                    });
// TODO: add to database
                stg = StageController.getInstance();
                stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRV);
                stg.changeTo(t2R.getRecipe().getRecipeTitle());
//


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleBackButton(ActionEvent event) {
        stg.changeTo(Constants.MEALTYPE_PAGE_NAME);
    }
}
