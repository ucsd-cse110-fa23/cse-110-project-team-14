package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.view.pages.MainPageView;
import com.pantrypal.view.pages.MealTypePageView;
import javafx.event.ActionEvent;

public class MainPageController extends Controller{
    private MainPageView mainPageView;

    public MainPageController(MainPageView mainPageView)
    {
        this.mainPageView = mainPageView;
        this.mainPageView.setAddButtonAction(this::handleAddButton);
    }
    private void handleAddButton(ActionEvent event) {
        stg.changeTo(Constants.MEALTYPE_PAGE_NAME);
    }
}
