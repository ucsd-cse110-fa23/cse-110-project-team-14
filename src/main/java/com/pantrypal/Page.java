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
        this.borderPane = new BorderPane(); // 使用BorderPane
        this.scene = new Scene(borderPane, width, height);
        createView();
    }

    protected abstract void createView(); // 用于初始化页面视图的抽象方法

    public Scene getScene() {
        return scene;
    }

    // 可以添加其他通用方法，例如添加导航栏，公共样式设置等
}