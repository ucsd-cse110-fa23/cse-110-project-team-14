package com.pantrypal.view;

import com.pantrypal.model.Recipe;

// implemented by SeeRecipePage 
// implemented by SeeRecipePageFromRecording
interface ISeeRecipePage {
    public void setRecipe(Recipe r);
    public void createView();
}
