package com.pantrypal;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Page {
    protected Scene scene;
    protected BorderPane borderPane; // 使用BorderPane作为根布局
    protected int width;
    protected int height;
    protected String style;

    public Page(int width, int height) {
        this.width = width;
        this.height = height;
        this.borderPane = new BorderPane(); // use BorderPane
        this.scene = new Scene(borderPane, width, height);
        createView();
    }

    protected abstract void createView(); //

    public Scene getScene() {
        return scene;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }


}
