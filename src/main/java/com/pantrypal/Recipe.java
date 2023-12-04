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
    private String recipeImageURL;
    private int recipeIndex;  //Used in order to sort recipes by time created

    Recipe() {
    }
    
    /**
     * Sets the title of the recipe.
     *
     * @param recipeTitle the title of the recipe
     */
    void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    /**
     * Returns the title of the recipe.
     *
     * @return the title of the recipe
     */
    String getRecipeTitle() {
        return recipeTitle;
    }
    
    /**
     * Sets the ingredients of the recipe.
     *
     * @param recipeIngredients the ingredients of the recipe
     */
    void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * Returns the ingredients of the recipe.
     *
     * @return the ingredients of the recipe
     */
    String getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * Sets the instructions of the recipe.
     *
     * @param recipeInstructions the instructions of the recipe
     */
    void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    /**
     * Returns the instructions of the recipe.
     *
     * @return the instructions of the recipe
     */
    String getRecipeInstructions() {
        return recipeInstructions;
    }

    /**
     * Sets the image URL of the recipe.
     *
     * @param recipeImageURL the image URL of the recipe
     */
    void setRecipeImageURL(String recipeImageURL) {
        this.recipeImageURL = recipeImageURL;
    }

    /**
     * Returns the image URL of the recipe.
     *
     * @return the image URL of the recipe
     */
    String getRecipeImageURL() {
        return recipeImageURL;
    }

    /**
     * Sets the meal type of the recipe.
     *
     * @param mealType the meal type of the recipe
     */
    void setMealType(String mealType) {
        //Capitalize the first letter of the mealtype
        if(mealType != null){
            this.mealType = mealType.substring(0, 1).toUpperCase() + mealType.substring(1);
        }
        else{
            this.mealType = mealType;
        }
    }

    /**
     * Returns the meal type of the recipe.
     *
     * @return the meal type of the recipe
     */
    String getMealType() {
        return mealType;
    }

    /**
     * Sets the recipe index of the recipe.
     *
     * @param recipeIndex the recipe index of the recipe
     */
    void setRecipeIndex(int recipeIndex) {
        this.recipeIndex = recipeIndex;
    }

    /**
     * Returns the recipe index of the recipe.
     *
     * @return the recipe index of the recipe
     */
    int getRecipeIndex() {
        return recipeIndex;
    }

}
