package com.pantrypal.model;

public class CreateUserCollection {
    DatabaseOPS db;

    public boolean createUserCollection(String username) {
        // Creating the collection of user
        db = new DatabaseOPS(username);

        // Create new collection with the name of the username
        db.createCollection();
        
        return true;
    }

}
