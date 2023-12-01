package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*; // Used for equality, greater than or equal to, etc. for find operation
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class UserStoryMS2_3Testing {

    //Tests if creation works for a new user
    @Test
    public void testCreateAccount() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS("users_test");
        db.deleteAllUsers();
        CreateAccount ca = new CreateAccount();
        ca.setCollectionName("users_test");
        boolean result = ca.createAccount("test", "test");
        assertEquals(true, result);

        result = ca.createAccount("test", "test");
        assertEquals(false, result);
    }

    //Tests if creation works for an existing user
    @Test
    public void testCreateAccount2() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS("users_test");
        db.deleteAllUsers();
        CreateAccount ca = new CreateAccount();
        ca.setCollectionName("users_test");
        boolean result = ca.createAccount("test", "test");
        assertEquals(true, result);

        result = ca.createAccount("test", "test");
        assertEquals(false, result);
    }
}
