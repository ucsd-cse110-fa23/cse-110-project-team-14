package com.pantrypal;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;


public class MongoDBConnection {
    private static MongoClient mongoClient = null;

    public static MongoClient getInstance() {
        if (mongoClient == null) {
            String uri = "mongodb+srv://team14:team14onTop@cluster0.pqup4sj.mongodb.net/?retryWrites=true&w=majority"; // TEAM 14 Database managed by Aidan
            mongoClient = MongoClients.create(uri);
        }
        return mongoClient;
    }
}
