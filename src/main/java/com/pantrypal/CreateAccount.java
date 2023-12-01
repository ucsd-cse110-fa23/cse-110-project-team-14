package com.pantrypal;

/*
 * This class should use the following DatabaseOPS methods:
 * - boolean collectionExists(String collectionName) --> Not implemented yet
 * - boolean createCollection(String collectionName) --> Not implemented yet
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
 */
public class CreateAccount {
    
}
