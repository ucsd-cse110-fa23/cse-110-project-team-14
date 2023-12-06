package com.pantrypal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.pantrypal.model.Recipe;

public class Sort {
    // Method to sort recipes from new to old based on their recipe index
    public static ArrayList<Recipe> newToOldSort(ArrayList<Recipe> recipes) {
        // Comparator to compare recipes based on their recipe index in ascending order
        Comparator<Recipe> comparator = new Comparator<Recipe>() {
            @Override
            public int compare(Recipe r1, Recipe r2) {
                if (r1.getRecipeIndex() > (r2.getRecipeIndex())) {
                    return 1;
                } else if (r1.getRecipeIndex() < r2.getRecipeIndex()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(recipes, comparator);
        return recipes;
    }

    // Method to sort recipes from old to new based on their recipe index
    public static ArrayList<Recipe> oldToNewSort(ArrayList<Recipe> recipes) {
        // Comparator to compare recipes based on their recipe index in descending order
        Comparator<Recipe> comparator = new Comparator<Recipe>() {
            @Override
            public int compare(Recipe r1, Recipe r2) {
                if (r1.getRecipeIndex() > (r2.getRecipeIndex())) {
                    return -1;
                } else if (r1.getRecipeIndex() < r2.getRecipeIndex()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(recipes, comparator);
        return recipes;
    }

    // Method to sort recipes from A to Z based on their recipe title
    public static ArrayList<Recipe> aToZSort(ArrayList<Recipe> recipes) {
        Comparator<Recipe> comparator = new Comparator<Recipe>() {
            @Override
            public int compare(Recipe r1, Recipe r2) {
                String recipeName1 = r1.getRecipeTitle();
                String recipeName2 = r2.getRecipeTitle();
                recipeName1 = recipeName1.toLowerCase();
                recipeName2 = recipeName2.toLowerCase();
                return recipeName2.compareTo(recipeName1);
            }
        };
        // Sorting the recipes using the defined comparator
        Collections.sort(recipes, comparator);
        return recipes;
    }

    // Method to sort recipes from Z to A based on their recipe title
    public static ArrayList<Recipe> zToASort(ArrayList<Recipe> recipes) {
        Comparator<Recipe> comparator = new Comparator<Recipe>() {
            @Override
            public int compare(Recipe r1, Recipe r2) {
                String recipeName1 = r1.getRecipeTitle();
                String recipeName2 = r2.getRecipeTitle();
                recipeName1 = recipeName1.toLowerCase();
                recipeName2 = recipeName2.toLowerCase();
                return recipeName1.compareTo(recipeName2);
            }
        };
        // Sorting the recipes using the defined comparator
        Collections.sort(recipes, comparator);
        return recipes;
    }

}
