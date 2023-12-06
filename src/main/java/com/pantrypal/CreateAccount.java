package com.pantrypal;


import javafx.scene.chart.PieChart.Data;

/*
 * This class should use the following DatabaseOPS methods:
 * 
 * 
 * This class should create a collection with the name of the user's username using the DatabaseOPS class.
 * 
 * Methods for this class:
 * - public boolean createAccount(String username, String password) - returns true if account was created successfully, false if not
 * 
 * Idea on how would this work:
 * There is a collection (We can store this in another DataBase) called "users" that contains all the usernames and passwords of the users. 
 * And everytime a new user is created, a new collection is created with the name of the username.
 * During the account creation we check if the username already exists in the "users" collection, if it does, we return false and we do not create the collection.
 * 
 * 
 * FOR MS2-US 4 (automatic login): We can create a local file with the login information (so it is stored locally as required by the professor) and
 * we just grab the information from that file and send it to this method to verify it
 * 
 * 
 * -->Database 
 * ------>Collection 1: Credientals/login
 * --------Document: user/password
 * --------Document: user/password
 * ------->Collection 2:Frankie
 * ----------->recipe 1
 * ----------->recipe 2
 * ------->Collection 3: Ivan
 * ----------->recipe 1
 * ----------->recipe 2
 */
public class CreateAccount {
    DatabaseOPS db;
    CreateUserCollection userCollection = new CreateUserCollection();
    String collectionName = "users";

    /**
    * Creates a new account with the given username and password.
    * 
    * @param username The username for the new account.
    * @param password The password for the new account.
    * @return True if the account is successfully created, false otherwise.
    */
    public boolean createAccount(String username, String password) {
        db = new DatabaseOPS(collectionName);
        // Check if username already exists
        if (db.findUno(username) != null) {
            // Throw error stating username already exists
            return false;
        }
        // Create new collection with the name of the username
        db.insert(username, password);
        //userCollection.createUserCollection(username);
        return true;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

}
