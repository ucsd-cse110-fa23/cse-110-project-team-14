package com.pantrypal.view.pages;

import com.pantrypal.controller.ConfirmDeleteController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ConfirmDeleteView
{
    Button yesButton;
    Button noButton;
    HBox buttonBox;
    Label label;
    VBox confirmDeleteContent;
    Popup confirmDelete;
    public ConfirmDeleteView(SeeRecipePageView seeRecipePageView)
    {
        confirmDelete = new Popup();
        confirmDeleteContent = new VBox();
        confirmDeleteContent.styleProperty().set("-fx-background-color: #FFEBD7; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 20 20 20 20;");
         label = new Label("Do you want to delete this recipe?");
         yesButton = new Button("Yes");
         noButton = new Button("No");
         buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(yesButton);
        buttonBox.getChildren().add(noButton);
        confirmDeleteContent.getChildren().add(label);
        confirmDeleteContent.getChildren().add(buttonBox);
        // add the label
        confirmDelete.getContent().add(confirmDeleteContent);
        Stage stage = (Stage) seeRecipePageView.getScene().getWindow();
        confirmDelete.show(stage);
        confirmDelete.setAutoHide(true);
        ConfirmDeleteController confirmDeleteController = new ConfirmDeleteController(this);
    }
    public void setYesButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.yesButton.setOnAction(eventHandler);
    }
    public void setNoButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.noButton.setOnAction(eventHandler);
    }
    public Popup getConfirmDelete()
    {
        return confirmDelete;
    }


}
