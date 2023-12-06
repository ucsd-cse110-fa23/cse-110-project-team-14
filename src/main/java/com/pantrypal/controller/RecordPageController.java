package com.pantrypal.controller;

import com.pantrypal.constants;
import com.pantrypal.model.ChatGPT;
import com.pantrypal.model.LiveRecorder;
import com.pantrypal.model.RecipeModel;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;
import com.pantrypal.view.RecordPage;
import com.pantrypal.view.SeeRecipeFromRecording;
import com.pantrypal.view.StageController;

import javafx.event.ActionEvent;

/**
 * The RecordPageController class acts as a controller managing interactions and events for the RecordPage view.
 * It coordinates between the RecordPage view, the RecipeModel, and other necessary components to handle
 * recording functionalities, navigation, and updating views.
 */
public class RecordPageController {
    private RecipeModel model;
    private RecordPage recordPageView;
    private LiveRecorder liveRecorder = new LiveRecorder(); // model
    StageController stg;

    public RecordPageController(RecordPage recordPageView) {
        this.recordPageView = recordPageView;
        stg = StageController.getInstance();
        model = new RecipeModel(this); // model takes a controller
        this.recordPageView.setRecordButtonAction(this::handRecordButton);
        this.recordPageView.setBackButtonAction(this::handleBackButton);
    }

    public void handRecordButton(ActionEvent event) {
        recordPageView.setIsRecording(!recordPageView.getIsRecording());
        if (recordPageView.getIsRecording()) {
            // start recording
            recordPageView.getRecordButton().setText("RECORDING");
            liveRecorder.startRecording();
            // call to model method called startRecording
        } else {
            // stop recording
            recordPageView.getRecordButton().setText("mic");
            liveRecorder.stopRecording();

            model.handleCreateRecipe();
        }
    }

    public void handleBackButton(ActionEvent event) {
        stg.changeTo(constants.MEALTYPE_PAGE_NAME);
    }

    public RecordPage getRecordPageView() {
        return this.recordPageView;
    }

    public void updateView(TextToRecipe t2R, SeeRecipeFromRecording SRV) {
        stg = StageController.getInstance();
        stg.registerPage(t2R.getRecipe().getRecipeTitle(), SRV);
        stg.changeTo(t2R.getRecipe().getRecipeTitle());
    }
}