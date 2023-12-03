package com.pantrypal;
import org.bson.Document;

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

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
