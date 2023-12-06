package com.pantrypal;


import org.junit.jupiter.api.Test;

import com.pantrypal.model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserStory9Testing {
    
    @Test
    public void testRecipeEdit(){
        Recipe recipe = new Recipe();
        assertEquals(null, recipe.getMealType());
        assertEquals(null, recipe.getRecipeIngredients());
        assertEquals(null, recipe.getRecipeInstructions());
        assertEquals(null, recipe.getRecipeTitle());
        recipe.setMealType("breakfast");
        recipe.setRecipeIngredients("Ingredients:\n\n- 2 slices of bread\n- 1 egg\n- 1 flour tortilla\n");
        recipe.setRecipeInstructions("Instructions:\n1. Toast the bread slices and set aside.\n2. Heat a small nonstick skillet over medium heat.");
        recipe.setRecipeTitle("Egg Tortilla Breakfast Sandwich");
        assertEquals("Breakfast", recipe.getMealType());
        assertEquals("Ingredients:\n\n- 2 slices of bread\n- 1 egg\n- 1 flour tortilla\n", recipe.getRecipeIngredients());
        assertEquals("Instructions:\n1. Toast the bread slices and set aside.\n2. Heat a small nonstick skillet over medium heat.", recipe.getRecipeInstructions());
        assertEquals("Egg Tortilla Breakfast Sandwich", recipe.getRecipeTitle());
        
    }
}
