package com.pantrypal;
/*
 * Recipe class to store recipe information
 * This is what is shown once you clicked on the recipe list
 */
public class Recipe {
    private String recipeTitle;
    private String recipeInstructions; 
    private String recipeIngredients;
    private String mealType;
    private int recipeIndex;  //Used in order to sort recipes by time created

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

    @SuppressWarnings("unused")
    void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @SuppressWarnings("unused")
    String getMealType() {
        return mealType;
    }

    void setRecipeIndex(int recipeIndex) {
        this.recipeIndex = recipeIndex;
    }

    int getRecipeIndex() {
        return recipeIndex;
    }

}
