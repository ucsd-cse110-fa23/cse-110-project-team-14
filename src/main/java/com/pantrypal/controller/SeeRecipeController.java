package com.pantrypal.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import com.pantrypal.Globals;
import com.pantrypal.constants;
import com.pantrypal.view.EditRecipePage;
import com.pantrypal.view.RecipeTitleListView;
import com.pantrypal.view.RecipeTitleView;
import com.pantrypal.view.SeeRecipePage;
import com.pantrypal.view.StageController;
import com.pantrypal.view.mainPage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SeeRecipeController {
    private SeeRecipePage srp;
    StageController stg;
    public SeeRecipeController(SeeRecipePage srp){
        this.srp = srp;
        stg = StageController.getInstance();
        this.srp.setBackButtonAction(this::handleBackButton);
        this.srp.setEditButtonAction(this::handleEdit);
        this.srp.setDeleteButtonAction(this::handleDelete);
        this.srp.setShareButtonAction(this::handleShare);
    }

    public void handleBackButton(ActionEvent event)
    {
         // go back to main page
            StageController stageController = StageController.getInstance();
            stageController.changeTo("mainPage");
    }

    public void handleEdit(ActionEvent event)
    {
        //OPEN EDIT PAGE
        EditRecipePage ERP = new EditRecipePage(constants.width, constants.height, srp);
        ERP.setRecipe(srp.r);
        stg.registerPage(srp.r.getRecipeTitle(), ERP);
        stg.changeTo(srp.r.getRecipeTitle());   
    }

    public void handleDelete(ActionEvent event)
    {
        // Delete recipe from database
            Popup confirmDelete = new Popup();
            VBox confirmDeleteContent = new VBox();
            confirmDeleteContent.styleProperty().set("-fx-background-color: #FFEBD7; " +
                    "-fx-border-color: #8B4513; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-padding: 20 20 20 20;");
            Label label = new Label("Do you want to delete this recipe?");
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");
            HBox buttonBox = new HBox();
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.getChildren().add(yesButton);
            buttonBox.getChildren().add(noButton);
            confirmDeleteContent.getChildren().add(label);
            confirmDeleteContent.getChildren().add(buttonBox);

            // add the label 
            confirmDelete.getContent().add(confirmDeleteContent);

            Stage stage = (Stage) srp.getScene().getWindow();
            confirmDelete.show(stage);
            confirmDelete.setAutoHide(true);

            yesButton.setOnAction(e1 -> {
                srp.db.deleteUno(srp.r);
                Globals.recipes.remove(srp.r); 
                for(Object e2 : RecipeTitleListView.getInstance().getChildren()){
                    if(e2 instanceof RecipeTitleView){
                        if( ((RecipeTitleView)e2).getRecipe().equals(srp.r)){
                            RecipeTitleListView.getInstance().getChildren().remove(e2);
                            break;
                        }
                    }
                }

                confirmDelete.hide();
                stg.changeTo("mainPage"); 
            });

            noButton.setOnAction(e1 -> {
                confirmDelete.hide();
            });
    }

    public void handleShare(ActionEvent event)
    {
         srp.shareButton.setText("Copied To Clipboard");
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            // Create a StringSelection object containing the text to be copied
            String toCopy = "http://localhost:8100/share/" + Globals.username + "/" + srp.title;
            StringSelection selection = new StringSelection(toCopy);

            // Set the contents of the clipboard to the StringSelection
            toolkit.getSystemClipboard().setContents(selection, null);

            // Display message for 5 seconds
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(5),
                    event1 -> srp.shareButton.setText("Share button"))); // Set the original text here
            timeline.setCycleCount(1);
            timeline.play();
    }



}
