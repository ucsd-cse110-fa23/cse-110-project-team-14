package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UserStoryMS2_9Testing {
    @Test
    public void testfilterByBreakfast(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        char title = 65;
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle(title + "TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(i);
            title++;
            recipes.add(recipe_obj);
        }
        recipes = Sort.oldToNewSort(recipes);
        int j = 9;
        for (int i = 0; i < 10; i++) {
            assertEquals("TInstr" + (j), recipes.get(i).getRecipeInstructions());
            j--;
        }
    }
}