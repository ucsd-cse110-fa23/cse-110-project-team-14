package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.view.pages.EditRecipePageView;

import javafx.event.ActionEvent;

public class EditRecipePageController extends Controller
{

    private EditRecipePageView editRecipePageView;
    public EditRecipePageController(EditRecipePageView view)
    {
        this.editRecipePageView = view;
        this.editRecipePageView.setBackButtonAction(this::handleBackButton);
        this.editRecipePageView.setSaveButtonAction(this::handleSaveButton);
    }
    public void handleBackButton(ActionEvent event)
    {
        stg.changeTo(Constants.MAINPAGE_NAME);
    }
    public void handleSaveButton(ActionEvent event) {

            editRecipePageView.getRecipe().setRecipeIngredients(editRecipePageView.getIngredientLabel().getText());
            editRecipePageView.getRecipe().setRecipeInstructions(editRecipePageView.getDetailLabel().getText());
            editRecipePageView.getSRP().setRecipe(editRecipePageView.getRecipe());
            // Save recipe to database
//TODO: add to database
            // ADD TO DATABASE
//            if(fromRecording){
//                RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
//                db.insert(r);
//            }
//            else{
//                db.update(r);
//            }
//
//            recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
//                StageController stg = StageController.getInstance();
//                stg.registerPage(r.getRecipeTitle(), SRP);
//                stg.changeTo(r.getRecipeTitle());
//            });
// TODO: add to database
            stg.fetchDB = true;
            stg.changeTo(Constants.MAINPAGE_NAME);

        }
    }