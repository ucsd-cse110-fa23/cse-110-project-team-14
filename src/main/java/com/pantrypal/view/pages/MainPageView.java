package com.pantrypal.view.pages;

import com.pantrypal.controller.MainPageController;
import com.pantrypal.controller.StageController;
import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPageView extends Page{
    Button addButton;
    private MainPageController mainPageController; 

    public MainPageView(int width, int height) {
        super(width, height);
        mainPageController = new MainPageController(this);
        mainPageController.initRecipes();
    }

    public Button getAddButton() {
        return this.addButton;
    }

    @Override
    protected void createView() {
        paneHeader = new PaneHeader();
        paneFooter = new PaneFooter();
        paneHeader.setTitleInMiddle(new Text("PantryPal: The best Recipe manager"));
//TODO: title things

       ScrollPane scroll = new ScrollPane(RecipeTitleListView.getInstance());
       scroll.setPrefSize(1000, 1000);
       scroll.setFitToWidth(true);
       scroll.setFitToHeight(true);
//TODO: title things
        VBox mainContent = new VBox();
        mainContent.minHeight(1000);
        mainContent.minWidth(1000);
        mainContent.setSpacing(15);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.getChildren().add(scroll);

        this.borderPane.setTop(paneHeader);
        this.borderPane.setCenter(mainContent);
        this.borderPane.setBottom(paneFooter);

        this.addButton = paneFooter.creatButton("Create Recipe", "-fx-background-color: #FFEBD7; " +
                "-fx-text-fill: #8B4513; " +
                "-fx-border-color: #8B4513; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 5 15 5 15;");

        paneFooter.setButton(this.addButton);

        this.borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #FFE4B5, #FFDEAD, #FFE4B5, #FFDEAD); " +
                "-fx-border-color: #DEB887; " +
                "-fx-border-width: 10; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15;");

        
    }
    public void setAddButtonAction(EventHandler<ActionEvent> eventHandler) {
        addButton.setOnAction(eventHandler);
    }
}
