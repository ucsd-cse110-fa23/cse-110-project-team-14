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
    private String collectionName;
    private final MongoClient mongoClient = MongoClients.create(uri);
    

    DatabaseOPS(String collectionName) {
        this.collectionName = collectionName;
        
    }
    
    /* Recipe Database Methods */

    // Add Recipe to database
    public void insert(Recipe recipe_obj){
        MongoClient mongoClient = MongoDBConnection.getInstance();
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

    // Read/Find Recipe from database and return the one you search for
    public Document findUno(Recipe recipe_obj){
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
        Document recipe = recipesCollection.find(eq("Title", recipe_obj.getRecipeTitle())).first();
        return recipe;
    }

    // Update Recipe in database
    public void update(Recipe recipe_obj){
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
        recipesCollection.updateOne(eq("Title", recipe_obj.getRecipeTitle()), new Document("$set", new Document("Ingredients", recipe_obj.getRecipeIngredients())));
        recipesCollection.updateOne(eq("Title", recipe_obj.getRecipeTitle()), new Document("$set", new Document("Instructions", recipe_obj.getRecipeInstructions())));

    }

    
    // Delete one Recipe in database (If you deleteOne, may override recipesCollection)
    public void deleteUno(Recipe recipe_obj){
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
        recipesCollection.deleteOne(eq("Title", recipe_obj.getRecipeTitle()));
    
    }


    // Delete all Recipes in database
    public void deleteAll() {
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
        recipesCollection.deleteMany(new Document());
    
    }


    /* User Database Methods */

    //Add username for the list
    // public void insert(String username, String password){
    //     try (mongoClient) {
    //         MongoDatabase PantryPal = mongoClient.getDatabase("PantryPal");
    //         MongoCollection<Document> usersCollection = PantryPal.getCollection(collectionName);
    //         Document user = new Document("username", username);
    //         user.append("password", password);
    //         usersCollection.insertOne(user);
    //         // Get size of collection
    //         System.out.println("Size of collection: " + usersCollection.countDocuments());
    //     }
    // }
    public void insert(String username, String password){
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase PantryPal = mongoClient.getDatabase("PantryPal");
        MongoCollection<Document> usersCollection = PantryPal.getCollection(collectionName);
        Document user = new Document("username", username);
        user.append("password", password);
        usersCollection.insertOne(user);
        // Get size of collection
        System.out.println("Size of collection: " + usersCollection.countDocuments());
        
    }

    // Read/Find user from database and return the one you search for
    // public Document findUno(String username){
    //     try (mongoClient) {
    //         MongoDatabase PantryPal = mongoClient.getDatabase("PantryPal");
    //         MongoCollection<Document> usersCollection = PantryPal.getCollection(collectionName);
    //         Document recipe = usersCollection.find(eq("username", username)).first();
    //         return recipe;
    //     }
    // }

    public Document findUno(String username){
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase PantryPal = mongoClient.getDatabase("PantryPal");
        MongoCollection<Document> usersCollection = PantryPal.getCollection(collectionName);
        Document user = usersCollection.find(eq("username", username)).first();
        return user;

    }


    // Remove this and add it to the controller
    public ArrayList<Recipe> initializeRecipesToList() {
        MongoClient mongoClient = MongoDBConnection.getInstance();
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


    /* MongoDB Collection Helper Methods */ // --> seems like none of these helper methods are used

    // Get collection size
    public long getCollectionSize() {
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase recipeDB = mongoClient.getDatabase("recipeDB");
        MongoCollection<Document> recipesCollection = recipeDB.getCollection(collectionName);
        System.out.println("Size of collection: " + recipesCollection.countDocuments());
        return recipesCollection.countDocuments();
    
    }

    // Get collection name
    public String getCollectionName() {
        return collectionName;
    }

    // Set Collection name
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    //Delete all usernames
    public void deleteAllUsers() {
        MongoClient mongoClient = MongoDBConnection.getInstance();
        MongoDatabase PantryPal = mongoClient.getDatabase("PantryPal");
        MongoCollection<Document> usersCollection = PantryPal.getCollection(collectionName);
        usersCollection.deleteMany(new Document());
    }
}
