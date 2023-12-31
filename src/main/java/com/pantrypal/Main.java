package com.pantrypal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.pantrypal.model.ErrorPage;
import com.pantrypal.view.LoginPage;
import com.pantrypal.view.MealTypePage;
import com.pantrypal.view.RecordPage;
import com.pantrypal.view.RegisterPage;
import com.pantrypal.view.StageController;
import com.pantrypal.view.mainPage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
    LoginPage root = new LoginPage(constants.width, constants.height);
    // mainPage root = new mainPage(constants.width, constants.height);
    public static StageController stgControl = StageController.getInstance();
    public static boolean wasError;
    private static String lastSuccessfulPage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PantryPal");
        primaryStage.setResizable(true);
        // Get the current appWidth of the app to set the width of the recipes
        Globals.appWidth = primaryStage.getWidth();

        primaryStage.setScene(root.getScene());
        stgControl.init(primaryStage);
        stgControl.registerPage("LoginPage", root);
        stgControl.registerPage("RegisterPage", new RegisterPage(constants.width, constants.height));
        stgControl.registerPage("mainPage", new mainPage(constants.width, constants.height));
        stgControl.registerPage("MealTypePage", new MealTypePage(constants.width, constants.height));
        stgControl.registerPage("ErrorPage", new ErrorPage(constants.width, constants.height));

        stgControl.registerPage("RecordPage", new RecordPage(constants.width, constants.height));
        primaryStage.show();

        root.checkAutomaticLogin();
        // instead making each page a listener, schedule a ping to the server every 5
        // seconds
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            if (wasError) {
                // if last ping was an error (server shutdown)
                // we want to return to lastSuccessfulPage
                Platform.runLater(() -> stgControl.changeTo(lastSuccessfulPage));
            }
            if (!isServerRunning()) {
                // if we ping server shutdowngo to error page
                Platform.runLater(() -> stgControl.changeTo("ErrorPage"));
                wasError = true;
            } else {
                // if server is active we should return to current page
                lastSuccessfulPage = stgControl.getCurrentPage();
                wasError = false;
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    // temporary client to ping server
    // checks for response code 200.
    // handled by server localhost:8100/status/ (returns 200 if server status UP)
    private static boolean isServerRunning() {
        String SERVER_HOSTNAME = Globals.IPADRESS;
        String SERVER_PORT = "8100";

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(
                    "http://" + SERVER_HOSTNAME + ":" + SERVER_PORT + "/status").openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Exception occurred: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}