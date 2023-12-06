package com.pantrypal;

import com.pantrypal.model.DatabaseOPS;
import com.pantrypal.model.Recipe;

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

public class UserStory4Testing {
    private String uri = "mongodb+srv://team14:team14onTop@cluster0.pqup4sj.mongodb.net/?retryWrites=true&w=majority";
    final private MongoClient mongoClient = MongoClients.create(uri);
    final private String COLLECTION_NAME = "collection_specifically_designed_for_testing_with_a_long_name";

    @Test
    public void testDeleteAll() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS(COLLECTION_NAME);
        MongoDatabase recipeDB = mongoClient.getDatabase("PantryPal");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(COLLECTION_NAME);
        db.deleteAll();
        long size = recipesCollection.countDocuments();
        assertEquals(0, size);
    }

    @Test
    public void testInsert() throws IOException, URISyntaxException {
        
        DatabaseOPS db = new DatabaseOPS(COLLECTION_NAME);
        MongoDatabase recipeDB = mongoClient.getDatabase("PantryPal");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(COLLECTION_NAME);
        db.deleteAll();
        // Insert recipe_obj into recipesCollection using for loop and check the size everytime 
        for (int i = 0; i < 10; i++) {
            DatabaseOPS db2 = new DatabaseOPS(COLLECTION_NAME);
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle("TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);

            db2.insert(recipe_obj);
            db2 = new DatabaseOPS(COLLECTION_NAME);
            Document recipe = db2.findUno(recipe_obj);
            assertEquals(recipe_obj.getRecipeTitle(), recipe.get("Title"));
            assertEquals(recipe_obj.getRecipeIngredients(), recipe.get("Ingredients"));
            assertEquals(recipe_obj.getRecipeInstructions(), recipe.get("Instructions"));

            long size = recipesCollection.countDocuments();
            assertEquals(i + 1, size);
        }

    }

    //Testing that the last one added is at the top of the list
    @Test
    public void testNewest(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        DatabaseOPS db = new DatabaseOPS(COLLECTION_NAME);
        MongoDatabase recipeDB = mongoClient.getDatabase("PantryPal");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(COLLECTION_NAME);
        db.deleteAll();
        // Insert recipe_obj into recipesCollection using for loop and check the size everytime 
        for (int i = 0; i < 10; i++) {
            DatabaseOPS db2 = new DatabaseOPS(COLLECTION_NAME);
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle("TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);

            db2.insert(recipe_obj);
        }

        DatabaseOPS db3 = new DatabaseOPS(COLLECTION_NAME);
        recipes = db3.initializeRecipesToList();


        for(int i = 0; i < recipes.size(); i++){
            Recipe recipe = recipes.get(i);
            assertEquals(recipe.getRecipeIndex(), i);
        }
    }


    


}


