package com.pantrypal.controller;

import com.pantrypal.view.pages.MainPageView;
import javafx.event.ActionEvent;

public class MainPageController extends Controller{
    private MainPageView mainPageView;

    public MainPageController(MainPageView mainPageView)
    {
        this.mainPageView = mainPageView;
        this.mainPageView.setAddButtonAction(this::handleAddButton);
    }


    private void handleAddButton(ActionEvent event) {
        stg.changeTo("MealTypePage");
    }
}
