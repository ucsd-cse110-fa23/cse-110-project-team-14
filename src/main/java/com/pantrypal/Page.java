package com.pantrypal;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

// Each Display Card is a Page
public abstract class Page {
    protected Scene scene; // scene element associated with it
    protected BorderPane borderPane; // using BorderPane
    protected int width;
    protected int height;
    protected String style;

    public Page(int width, int height) {
        this.width = width;
        this.height = height;
        this.borderPane = new BorderPane();
        this.scene = new Scene(borderPane, width, height);
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
