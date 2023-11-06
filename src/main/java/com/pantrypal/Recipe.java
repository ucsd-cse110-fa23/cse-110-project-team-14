package com.pantrypal;
/*
 * Recipe class to store recipe information
 * This is what is shown once you clicked on the recipe list
 */
public class Recipe {
    private String recipeTitle;
    private String recipeInstructions; // Take the JSON response object from ChatGPT and put instructions into list (TODO Later)
    private String recipeIngredients;
    private String mealType;

    Recipe() {
    }
    
    void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    String getRecipeTitle() {
        return recipeTitle;
    }
    
    void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    String getRecipeIngredients() {
        return recipeIngredients;
    }

    void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    String getRecipeInstructions() {
        return recipeInstructions;
    }

    void setMealType(String mealType) {
        this.mealType = mealType;
    }

    String getMealType() {
        return mealType;
    }

    //TODO: Once we have the JSON response object from ChatGPT, we can parse it and put the instructions into a list
}
