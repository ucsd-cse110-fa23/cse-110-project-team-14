package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.LiveRecorder;
import com.pantrypal.model.RecipeModel;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.model.WhisperModel;
import com.pantrypal.view.pages.RecordPageView;
import com.pantrypal.view.pages.SeeRecipeFromRecordingView;
import javafx.event.ActionEvent;

public class RecordPageController extends Controller {
    private RecipeModel model;
    private RecordPageView recordPageView;
    private LiveRecorder liveRecorder = new LiveRecorder(); // model

    public RecordPageController(RecordPageView recordPageView) {
        this.recordPageView = recordPageView;
        model = new RecipeModel(this); // model takes a controller
        this.recordPageView.setRecordButtonAction(this::handRecordButton);
        this.recordPageView.setBackButtonAction(this::handleBackButton);
    }

    public void handRecordButton(ActionEvent event) {
        recordPageView.setIsrecording(!recordPageView.getIsRecording());
        if (recordPageView.getIsRecording()) {
            //start recording
            recordPageView.getRecordButton().setText("RECORDING...?");
            liveRecorder.startRecording(); 
            // call to model method called startRecording
        } else {
            //stop recording
            recordPageView.getRecordButton().setText("GOT VOICE");
            liveRecorder.stopRecording(); 

            model.handleCreateRecipe();
        }
    }

    public void handleBackButton(ActionEvent event) {
        stg.changeTo(Constants.MEALTYPE_PAGE_NAME);
    }

    public RecordPageView getRecordPageView(){
        return recordPageView;
    }

    public void updateView(TextToRecipe t2R, SeeRecipeFromRecordingView SRV){
        stg = StageController.getInstance();
        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRV);
        stg.changeTo(t2R.getRecipe().getRecipeTitle());
    }
}
