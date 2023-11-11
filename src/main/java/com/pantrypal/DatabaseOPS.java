package com.pantrypal;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;


// import java.util.function.Consumer;


import static com.mongodb.client.model.Filters.*; // Used for equality, greater than or equal to, etc. for find operation
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;


import static java.util.Arrays.asList;


public class DatabaseOPS {
    
    private String uri = "mongodb+srv://team14:team14onTop@cluster0.pqup4sj.mongodb.net/?retryWrites=true&w=majority"; // TEAM 14 Database managed by Aidan
    // INSERT BACK --> "mongodb+srv://jarredondo:Lab6Connection@cluster0.qpdv67p.mongodb.net/?retryWrites=true&w=majority";


    final private MongoClient mongoClient = MongoClients.create(uri);;
    
    // Add Recipe to database
    public void insert(Recipe recipe_obj){
        
        try (mongoClient) {
            MongoCollection<Document> recipesCollection = getCollection();
            // No need to worry about duplicate recipes as per the project requirements
            Document recipe = new Document("Title", recipe_obj.getRecipeTitle());
            recipe.append("Ingredients", recipe_obj.getRecipeIngredients())
                   .append("Instructions", recipe_obj.getRecipeInstructions()); // Instructions list

            recipesCollection.insertOne(recipe);
        }
    }

    // Read/Find Recipe from database and return the one you search for
    public Document findUno(Recipe recipe_obj){
        try (mongoClient) {
            MongoCollection<Document> recipesCollection = getCollection();
            Document recipe = recipesCollection.find(eq("Title", recipe_obj.getRecipeTitle())).first();
            return recipe;
        }
    }

    // Update Recipe in database
    public void update(Recipe recipe_obj){
        try (mongoClient) {
            MongoCollection<Document> recipesCollection = getCollection();
            recipesCollection.updateOne(eq("Title", recipe_obj.getRecipeTitle()), new Document("$set", new Document("Ingredients", recipe_obj.getRecipeIngredients())));
        }
    }
    
    // Delete one Recipe in database (If you deleteOne, may override recipesCollection)
    public void deleteUno(Recipe recipe_obj){
        try (mongoClient) {
            MongoCollection<Document> recipesCollection = getCollection();
            recipesCollection.deleteOne(eq("Title", recipe_obj.getRecipeTitle()));
        }
    }

    // Delete all Recipes in database
    public void deleteAll() {
        try (mongoClient) {
            MongoCollection<Document> recipesCollection = getCollection();
            recipesCollection.deleteMany(new Document());
        }
    }

    // Helper function to get the collection
    private MongoCollection<Document> getCollection(){
        try (mongoClient) {
        MongoDatabase projectsRecipesDB = mongoClient.getDatabase("project-team-14");
        MongoCollection<Document> recipesCollection = projectsRecipesDB.getCollection("recipes");
        return recipesCollection;
        }
    }
}
