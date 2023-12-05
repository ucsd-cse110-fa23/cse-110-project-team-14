package com.pantrypal;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        stgControl.registerPage("RegisterPage", new RegisterPage(constants.width,constants.height));
        stgControl.registerPage("mainPage", new mainPage(constants.width,constants.height));
        stgControl.registerPage("MealTypePage",new MealTypePage(constants.width,constants.height));
        stgControl.registerPage("ErrorPage",new ErrorPage(constants.width,constants.height));

        stgControl.registerPage("RecordPage", new RecordPage(constants.width, constants.height));
        primaryStage.show();
      
        root.checkAutomaticLogin();
        // for testing
        // returns some 404 error when recipe not found.
        Client c = new Client("ivan", "Crispy Chicken Rice");
        c.sendRequest();

        // Schedule a task to check the server status every 5 seconds
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            if(wasError){
                Platform.runLater(() -> stgControl.changeTo(lastSuccessfulPage));
            }
            if (!isServerRunning()) {
                Platform.runLater(() -> stgControl.changeTo("ErrorPage"));
                wasError = true;
            } 
            else{
                lastSuccessfulPage = stgControl.getCurrentPage();
                wasError = false;
            }
        }, 0, 5, TimeUnit.SECONDS); 
    }

    private static boolean isServerRunning() {
        String SERVER_HOSTNAME = "localhost";
        String SERVER_PORT = "8100";
    
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://" + SERVER_HOSTNAME + ":" + SERVER_PORT + "/status").openConnection();
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