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

    // initializes a stage (must be done to create a stage)
    public void init(Stage stage) {
        this.primaryStage = stage;
    }

    // register a page with its name as a key
    public void registerPage(String key, Page page) {
        pages.put(key, page);
    }

    // switched to a page defined by the key passed in param
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

    // gets the any page passed through with its name (key)
    public Page getPage(String key) {
        return pages.get(key);
    }

    // gets the page that the app is currently on
    public String getCurrentPage() {
        return currentPage;
    }

}