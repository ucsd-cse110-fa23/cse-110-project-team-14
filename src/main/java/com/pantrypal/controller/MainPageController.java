package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.model.RecipeModel;
import com.pantrypal.view.pages.MainPageView;
import com.pantrypal.view.pages.MealTypePageView;
import javafx.event.ActionEvent;

public class MainPageController extends Controller{
    private MainPageView mainPageView;
    private RecipeModel model; 

    public MainPageController(MainPageView mainPageView)
    {
        this.mainPageView = mainPageView;
        model = new RecipeModel(this);
        this.mainPageView.setAddButtonAction(this::handleAddButton);
    }

    private void handleAddButton(ActionEvent event) {
        stg.changeTo(Constants.MEALTYPE_PAGE_NAME);
    }

    public void initRecipes(){
        // call model and uhh request the list of recipes kinda
        // update view so that the VBOX of recipes is shown
        model.handleInitRecipes();
    }
    public MainPageView getMainPageView()
    {
        return mainPageView;
    }

    public void updateView(){
        
    }

}
