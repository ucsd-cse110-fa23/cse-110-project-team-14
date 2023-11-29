package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class UserStoryMS2_10Testing {
    /*
     * Testing the sorting method for recipe objects
     */
    @Test
    public void sortingNewtoOld(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        char title = 65;
        int count = 20;
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle(title + "TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(count);
            title++;
            count--;
            recipes.add(recipe_obj);
        }
        Sort sort = new Sort();
        recipes = sort.newToOldSort(recipes);

        for (int i = 0; i < 10; i++) {
            assertEquals("TInstr" + (i), recipes.get(i).getRecipeInstructions());
        }
    }

    /**
     * Test case to verify the functionality of sorting recipes from old to new.
     */
    @Test
    public void sortingOldtoNew(){
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
        Sort sort = new Sort();
        recipes = sort.oldToNewSort(recipes);

        for (int i = 0; i < 10; i++) {
            assertEquals("TInstr" + (i), recipes.get(i).getRecipeInstructions());
        }
    }

    /**
     * Test case to verify the functionality of sorting recipes in alphabetical order.
     * It creates a list of recipes with titles starting from 'Z' and ending at 'R',
     * and then sorts them in ascending order using the aToZSort() method from the Sort class.
     * Finally, it asserts that the sorted list is in the expected order.
     */
    @Test
    public void sortingAtoZ(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        char title = 90;
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle(title + "TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(i);
            title--;
            recipes.add(recipe_obj);
        }
        Sort sort = new Sort();
        recipes = sort.aToZSort(recipes);
        title++;
        int counter = 9;
        for (int i = 0; i < 10; i++) {
            assertEquals(title + "TR" + counter, recipes.get(i).getRecipeTitle());
            counter--;
            title++;
        }
    }

    /**
     * Test case to verify the sorting of recipes in descending order by title (Z to A).
     */
    @Test
    public void sortingZtoA(){
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
        Sort sort = new Sort();
        recipes = sort.zToASort(recipes);
        title--;
        int counter = 9;
        for (int i = 0; i < 10; i++) {
            assertEquals(title + "TR" + counter, recipes.get(i).getRecipeTitle());
            counter--;
            title--;
        }
    }
}
