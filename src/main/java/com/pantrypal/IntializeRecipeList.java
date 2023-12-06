package com.pantrypal;

import java.io.*;
import java.util.Scanner;

import com.pantrypal.model.Recipe;
import com.pantrypal.view.RecipeTitleListView;
import com.pantrypal.view.RecipeTitleView;
import com.pantrypal.view.SeeRecipePage;
import com.pantrypal.view.StageController;

/**
 * The IntializeRecipeList class is responsible for handling recipe data
 * initialization, loading recipes from a file,
 * and saving recipes to a file.
 */
public class IntializeRecipeList {

    /**
     * Method to read recipes from a file and initialize RecipeTitleView instances
     * for each recipe.
     * Each RecipeTitleView represents a recipe title displayed in the UI.
     */
    public static void uploadRecipes() {
        try {
            // Opening the recipes file
            File recipesFile = new File("recipes.txt");
            Scanner fr = new Scanner(recipesFile);

            // Reading each line from the file and creating Recipe objects
            while (fr.hasNextLine()) {
                String line = fr.nextLine();
                String[] parts = line.split(";");
                String name = parts[0];
                String ingredients = parts[1];
                String instructions = parts[2];

                // Creating a Recipe object and setting its attributes
                Recipe recipe = new Recipe();
                recipe.setRecipeTitle(name);
                recipe.setRecipeIngredients(ingredients);
                recipe.setRecipeInstructions(instructions);

                // Creating a RecipeTitleView for the Recipe
                RecipeTitleView recipeTitleView = new RecipeTitleView(recipe);

                // Creating a SeeRecipePage to display the detailed recipe information
                SeeRecipePage seeRecipePage = new SeeRecipePage(constants.width, constants.height);
                seeRecipePage.setRecipe(recipe);

                // Handling button action to display detailed recipe information
                recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                    StageController stageController = StageController.getInstance();
                    stageController.registerPage(recipe.getRecipeTitle(), seeRecipePage);
                    stageController.changeTo(recipe.getRecipeTitle());
                });

                // Adding the RecipeTitleView to the RecipeTitleListView
                RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("Could not open recipes file");
        }
    }

    /**
     * Method to save a Recipe to a file.
     * 
     * @param recipe The Recipe object to be saved
     */
    public static void saveRecipe(Recipe recipe) {
        try {
            // Opening the recipes file for writing
            File recipeFile = new File("recipes.txt");
            FileWriter fw = new FileWriter(recipeFile, true); // Appending to the existing file

            // Writing the Recipe details to the file
            fw.write(recipe.getRecipeTitle() + ";");
            fw.write(recipe.getRecipeIngredients() + ";");
            fw.write(recipe.getRecipeInstructions() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save recipe");
        }
    }
}
