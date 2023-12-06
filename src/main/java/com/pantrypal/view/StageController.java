package com.pantrypal.view;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageController {
    private static final StageController instance = new StageController();

    private Stage primaryStage;
    private final Map<String, Page> pages = new HashMap<>();
    private String currentPage;

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
        primaryStage.setScene(page.getScene());
        primaryStage.show();
        currentPage = key;
    }

    public Page getPage(String key) {
        return pages.get(key);
    }

    public String getCurrentPage(){
        return currentPage;
    }

}