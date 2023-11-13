package com.pantrypal;

// implemented by SeeRecipePage 
// implemented by SeeRecipePageFromRecording
interface ISeeRecipePage {
    public void setRecipe(Recipe r);
    public void addListeners();
    public void createView();
}
