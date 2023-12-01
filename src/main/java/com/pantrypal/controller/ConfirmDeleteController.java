package com.pantrypal.controller;

import com.pantrypal.constants.Constants;
import com.pantrypal.view.pages.ConfirmDeleteView;
import javafx.event.ActionEvent;

import javax.swing.*;

public class ConfirmDeleteController extends Controller{
    ConfirmDeleteView confirmDeleteView;
    public ConfirmDeleteController(ConfirmDeleteView confirmDeleteView)
    {
        this.confirmDeleteView = confirmDeleteView;
        this.confirmDeleteView.setYesButtonAction(this::yesButtonHandler);
        this.confirmDeleteView.setNoButtonAction(this::noButtonHandler);
    }

    public void yesButtonHandler(ActionEvent event)
    {
        //TODO: handle database things
//        db.deleteUno(r);
//        for(Object e2 : RecipeTitleListView.getInstance().getChildren()){
//            if(e2 instanceof RecipeTitleView){
//                if( ((RecipeTitleView)e2).getRecipe().equals(this.r)){
//                    RecipeTitleListView.getInstance().getChildren().remove(e2);
//                    break;
//                }
//            }
//        }
        //TODO: handle database things
        confirmDeleteView.getConfirmDelete().hide();
        stg.fetchDB = true;
        stg.changeTo(Constants.MAINPAGE_NAME);
    }
    public void noButtonHandler(ActionEvent event)
    {
        confirmDeleteView.getConfirmDelete().hide();
    }
}
