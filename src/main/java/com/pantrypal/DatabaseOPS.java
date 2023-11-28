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

import java.util.ArrayList;
import java.util.Iterator;


public class DatabaseOPS {
    
    private String uri = "mongodb+srv://team14:team14onTop@cluster0.pqup4sj.mongodb.net/?retryWrites=true&w=majority"; // TEAM 14 Database managed by Aidan
    // INSERT BACK --> "mongodb+srv://jarredondo:Lab6Connection@cluster0.qpdv67p.mongodb.net/?retryWrites=true&w=majority";
    final private String collectionName;

    final private MongoClient mongoClient = MongoClients.create(uri);

    DatabaseOPS(String collectionName) {
        this.collectionName = collectionName;


    }
    
    // Add Recipe to database
    public void insert(Recipe recipe_obj){
        
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            // No need to worry about duplicate recipes as per the project requirements
            Document recipe = new Document("Title", recipe_obj.getRecipeTitle());
            recipe.append("Ingredients", recipe_obj.getRecipeIngredients())
                   .append("Instructions", recipe_obj.getRecipeInstructions())
                   .append("Meal Type", recipe_obj.getMealType()); // Instructions list
        
            recipesCollection.insertOne(recipe);
            // Get size of collection
            System.out.println("Size of collection: " + recipesCollection.countDocuments());
        }
    }

    // Read/Find Recipe from database and return the one you search for
    public Document findUno(Recipe recipe_obj){
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            Document recipe = recipesCollection.find(eq("Title", recipe_obj.getRecipeTitle())).first();
            return recipe;
        }
    }

    // Update Recipe in database
    public void update(Recipe recipe_obj){
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            recipesCollection.updateOne(eq("Title", recipe_obj.getRecipeTitle()), new Document("$set", new Document("Ingredients", recipe_obj.getRecipeIngredients())));
            recipesCollection.updateOne(eq("Title", recipe_obj.getRecipeTitle()), new Document("$set", new Document("Instructions", recipe_obj.getRecipeInstructions())));
        }
    }
    
    // Delete one Recipe in database (If you deleteOne, may override recipesCollection)
    public void deleteUno(Recipe recipe_obj){
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            recipesCollection.deleteOne(eq("Title", recipe_obj.getRecipeTitle()));
        }
    }

    // Delete all Recipes in database
    public void deleteAll() {
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            recipesCollection.deleteMany(new Document());
        }
    }


    public long getCollectionSize() {
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            System.out.println("Size of collection: " + recipesCollection.countDocuments());
            return recipesCollection.countDocuments();
        } catch (Exception e) {
            return -1;
        }
    }


    // Remove this and add it to the controller
    public ArrayList<Recipe> initializeRecipesToList() {
        try (mongoClient) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
            MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
            
            // access all elements in the collection
            Iterator<Document> cursor = recipesCollection.find().iterator();
            // while (cursor.hasNext()) {
            //     Document doc = cursor.next();
            //     String title = doc.getString("Title");
            //     String ingredients = doc.getString("Ingredients");
            //     String instructions = doc.getString("Instructions");
            //     Recipe recipe = new Recipe();
            //     recipe.setRecipeTitle(title);
            //     recipe.setRecipeIngredients(ingredients);
            //     recipe.setRecipeInstructions(instructions);
            //     RecipeTitleView recipeTitleView = new RecipeTitleView(recipe); //<-- This should be UI
            //     SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height); //<-- This should be UI
            //     SRP.setRecipe(recipe);
            //     recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
            //         StageController stg = StageController.getInstance();
            //         stg.registerPage(recipe.getRecipeTitle(), SRP);
            //         stg.changeTo(recipe.getRecipeTitle());
            //     });
            //     RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);

            // }
            ArrayList<Recipe> recipes = new ArrayList<Recipe>();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String title = doc.getString("Title");
                String ingredients = doc.getString("Ingredients");
                String instructions = doc.getString("Instructions");
                String mealType = doc.getString("Meal Type");
                Recipe recipe = new Recipe();
                recipe.setRecipeTitle(title);
                recipe.setMealType(mealType);
                recipe.setRecipeIngredients(ingredients);
                recipe.setRecipeInstructions(instructions);
                recipe.setRecipeIndex(Globals.recipeIndex); //Set the order when the recipe was created
                Globals.recipeIndex++; //Add one more to the counter to keep track of the order
                recipes.add(recipe);

            }
            return recipes;
        }
    }
}
