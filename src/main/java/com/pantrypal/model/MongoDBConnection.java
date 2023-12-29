package com.pantrypal.model;

import com.mongodb.client.MongoClients;
import com.pantrypal.Globals;
import com.mongodb.client.MongoClient;

public class MongoDBConnection {
    private static MongoClient mongoClient = null;
    /**
     * Provides a singleton instance of MongoClient.
     * Ensures that only one MongoClient instance is used throughout the application,
     * following the singleton design pattern.
     *
     * @return A MongoClient instance connected to the specified MongoDB database.
     */
    public static MongoClient getInstance() {
        // Check if the MongoClient instance is not already created
        if (mongoClient == null) {
            
            // URI string containing the MongoDB connection details
            // Note: Including credentials directly in the code is a security risk. 
            // Consider using environment variables or a configuration file.
            String uri = Globals.URI_MONGODB; // TEAM 14 Database managed by Aidan

            // Create a new MongoClient instance with the given URI
            mongoClient = MongoClients.create(uri);
        }
        return mongoClient;
    }
}
