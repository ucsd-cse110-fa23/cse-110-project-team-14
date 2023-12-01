package com.pantrypal.controller;

import com.pantrypal.view.pages.Page;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageController {
    private static final StageController instance = new StageController();

    private Stage primaryStage;
    private final Map<String, Page> pages = new HashMap<>();
    public boolean fetchDB = true;
    private StageController() {

    }

    public static StageController getInstance() {
        return instance;
    }

    public void init(Stage stage) {
        this.primaryStage = stage;
    }

    public void registerPage(String key, Page page) {
        pages.put(key, page);
    }

    public void changeTo(String key) {
        Page page = pages.get(key);
        if (page == null) {
            System.out.println("Error: page " + key + "not found");
            return;
        }
        page.update();
        System.out.println("Changing to page " + key);
        this.primaryStage.setScene(page.getScene());
        this.primaryStage.show();
    }
    public void storePrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }
    public Page getPage(String key) {
        return pages.get(key);
    }

}