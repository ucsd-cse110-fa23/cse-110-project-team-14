package com.pantrypal.model;

import org.bson.Document;

import com.pantrypal.Globals;
import com.pantrypal.view.StageController;

import java.util.prefs.*;

public class Login {
    DatabaseOPS db;
    String collectionName = "users";
    //TODO
     /**
    * Creates a new account with the given username and password.
    * 
    * @param username The username for the new account.
    * @param password The password for the new account.
    * @return True if the account is successfully created, false otherwise.
    */
    public boolean checkCredentials(String username, String password) {
        db = new DatabaseOPS(collectionName);
        // Check if username already exists
        Document userObject = db.findUno(username);
        if (userObject != null) {
            // Throw error stating username already exists
            if(userObject.get("password").toString().equals(password)){
                return true;
            }
            return false;
        }
        // username didn't exist
        return false;
    }

    public boolean setAutomaticLogin(String username, String password){
        Preferences pref = Preferences.userRoot();
        pref.put("username", username);
        pref.put("password", password);
        return true;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void checkAutomaticLogin(){
        Preferences pref = Preferences.userRoot();
        String username = pref.get("username", null);
        String password = pref.get("password", null);
        if(username != null && password != null){
            if(checkCredentials(username, password)){
                Globals.username = username;
                StageController stg = StageController.getInstance();
                stg.changeTo("mainPage");
            }
        }
    }

    public void unsetAutomaticLogin(){
        Preferences pref = Preferences.userRoot();
        pref.put("username", "");
        pref.put("password", "");
    }

}
