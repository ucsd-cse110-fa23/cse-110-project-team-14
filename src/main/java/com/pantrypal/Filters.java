package com.pantrypal;

import java.util.ArrayList;

import com.pantrypal.model.Recipe;


public class Filters {
    public static ArrayList<Recipe> filterByBreakfast(ArrayList<Recipe> recipes) {
        String mealType = "Breakfast";
        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
        for (Recipe recipe : recipes) {
            if (recipe.getMealType().equals(mealType)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public static ArrayList<Recipe> filterByLunch(ArrayList<Recipe> recipes) {
        String mealType = "Lunch";
        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
        for (Recipe recipe : recipes) {
            if (recipe.getMealType().equals(mealType)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public static ArrayList<Recipe> filterByDinner(ArrayList<Recipe> recipes) {
        String mealType = "Dinner";
        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
        for (Recipe recipe : recipes) {
            if (recipe.getMealType().equals(mealType)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }
}
