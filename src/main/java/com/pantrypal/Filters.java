package com.pantrypal;

import java.util.ArrayList;

import com.pantrypal.model.Recipe;

public class Filters {

    // Method to filter recipes by Breakfast meal type
    public static ArrayList<Recipe> filterByBreakfast(ArrayList<Recipe> recipes) {
        // Define the target meal type for filtering
        String mealType = "Breakfast";
        // List to store filtered recipes
        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
        // Iterate through each recipe in the input list
        for (Recipe recipe : recipes) {
            // Check if the current recipe has the specified meal type
            if (recipe.getMealType().equals(mealType)) {
                // If true, add the recipe to the filtered list
                filteredRecipes.add(recipe);
            }
        }
        // Return the filtered list of recipes
        return filteredRecipes;
    }

    // Method to filter recipes by Lunch meal type
    public static ArrayList<Recipe> filterByLunch(ArrayList<Recipe> recipes) {
        // Define the target meal type for filtering
        String mealType = "Lunch";
        // List to store filtered recipes
        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
        // Iterate through each recipe in the input list
        for (Recipe recipe : recipes) {
            // Check if the current recipe has the specified meal type
            if (recipe.getMealType().equals(mealType)) {
                // If true, add the recipe to the filtered list
                filteredRecipes.add(recipe);
            }
        }
        // Return the filtered list of recipes
        return filteredRecipes;
    }

    // Method to filter recipes by Dinner meal type
    public static ArrayList<Recipe> filterByDinner(ArrayList<Recipe> recipes) {
        // Define the target meal type for filtering
        String mealType = "Dinner";
        // List to store filtered recipes
        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
        // Iterate through each recipe in the input list
        for (Recipe recipe : recipes) {
            // Check if the current recipe has the specified meal type
            if (recipe.getMealType().equals(mealType)) {
                // If true, add the recipe to the filtered list
                filteredRecipes.add(recipe);
            }
        }
        // Return the filtered list of recipes
        return filteredRecipes;
    }
}
