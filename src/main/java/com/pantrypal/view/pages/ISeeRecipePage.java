package com.pantrypal.view.pages;

import com.pantrypal.model.Recipe;

// implemented by SeeRecipePageView
// implemented by SeeRecipePageFromRecording
interface ISeeRecipePage {
    public void setRecipe(Recipe r);
    public void createView();
}
