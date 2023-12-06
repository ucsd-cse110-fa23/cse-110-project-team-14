package com.pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.pantrypal.model.Client;
import com.pantrypal.model.Server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserStoryMS2_8Testing {
    // Preprocess the server to turn on or off
    @BeforeAll
    public static void setUp() {
        // Start the server in a separate thread
        new Thread(() -> {
            try {
                Server.main(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Wait for some time to allow the server to start (adjust as needed)
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void serverIsUp() {
        assertEquals(isServerRunning(), true); // if server is running
    }

    @Test
    public void checkRecipeTitle() {
        Client c = new Client("ivan", "title: Wine and Cheese Platter");
        try {
            c.sendRequest();
            assertEquals(c.getRecipeTitle(), "title: Wine and Cheese Platter"); // if server is running
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            fail("Exception occurred: " + e.getMessage());
        }
    }
    // Existing code for isServerRunning method
    private boolean isServerRunning() {
        String SERVER_HOSTNAME = "localhost";
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
}