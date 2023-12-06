package com.pantrypal.model;

import org.bson.Document;
import com.pantrypal.Globals;
import com.pantrypal.view.StageController;
import java.util.prefs.*;

public class Login {
    // Instance variable for database operations
    DatabaseOPS db;
    // Collection name in the database for user data
    String collectionName = "users";

    /**
     * Checks the provided credentials against the database.
     *
     * @param username The username for the account.
     * @param password The password for the account.
     * @return True if credentials are valid, false otherwise.
     */
    public boolean checkCredentials(String username, String password) {
        // Initialize database operations with the specified collection name
        db = new DatabaseOPS(collectionName);
        // Find the document (user data) based on the username
        Document userObject = db.findUno(username);
        // If a user object is found, check the password
        if (userObject != null) {
            // Compare the provided password with the stored password
            if (userObject.get("password").toString().equals(password)) {
                return true; // Correct credentials
            }
            return false; // Incorrect password
        }
        // Return false if username is not found in the database
        return false;
    }

    /**
     * Sets the credentials for automatic login in the user's preferences.
     *
     * @param username The username to be stored.
     * @param password The password to be stored.
     * @return Always returns true.
     */
    public boolean setAutomaticLogin(String username, String password) {
        // Access the user's preference node
        Preferences pref = Preferences.userRoot();
        // Store username and password in preferences
        pref.put("username", username);
        pref.put("password", password);
        return true;
    }

    // Setter for the collection name
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * Checks for stored credentials and logs in automatically if valid.
     */
    public void checkAutomaticLogin() {
        // Access the user's preference node
        Preferences pref = Preferences.userRoot();
        // Retrieve stored username and password
        String username = pref.get("username", null);
        String password = pref.get("password", null);
        // Check if both username and password are stored
        if (username != null && password != null) {
            // Verify credentials and proceed with automatic login
            if (checkCredentials(username, password)) {
                Globals.username = username;
                // Change to main application page upon successful login
                StageController stg = StageController.getInstance();
                stg.changeTo("mainPage");
            }
        }
    }

    /**
     * Clears stored credentials for automatic login.
     */
    public void unsetAutomaticLogin() {
        // Access the user's preference node
        Preferences pref = Preferences.userRoot();
        // Clear stored username and password
        pref.put("username", "");
        pref.put("password", "");
    }
}