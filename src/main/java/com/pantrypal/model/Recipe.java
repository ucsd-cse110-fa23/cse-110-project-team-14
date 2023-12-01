package com.pantrypal.model;
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

    public Recipe() {
    }
    
    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }
    
    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    @SuppressWarnings("unused")
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @SuppressWarnings("unused")
    public String getMealType() {
        return mealType;
    }

    void setRecipeIndex(int recipeIndex) {
        this.recipeIndex = recipeIndex;
    }

    int getRecipeIndex() {
        return recipeIndex;
    }

}
