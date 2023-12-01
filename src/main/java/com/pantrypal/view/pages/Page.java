package com.pantrypal.view.pages;

import com.pantrypal.view.components.PaneFooter;
import com.pantrypal.view.components.PaneHeader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public abstract class Page {
    protected Scene scene; // scene element associated with it
    protected BorderPane borderPane; // using BorderPane
    protected int width;
    protected int height;
    PaneFooter paneFooter;
    PaneHeader paneHeader;
    protected String style;

    public Page(int width, int height) {
        this.width = width;
        this.height = height;
        this.borderPane = new BorderPane();
        this.scene = new Scene(borderPane, width, height);
        paneFooter = new PaneFooter();
        paneHeader = new PaneHeader();
        createView();
    }

    protected abstract void createView();

    public Scene getScene() {
        return scene;
    }

    public void update()
    {
        createView();
    }
    public BorderPane getBorderPane() {
        return borderPane;
    }
}
