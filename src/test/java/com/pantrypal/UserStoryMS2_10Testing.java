package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
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
        recipes = Sort.newToOldSort(recipes);
        int j = 9;
        for (int i = 0; i < 10; i++) {
            assertEquals("TInstr" + (j), recipes.get(i).getRecipeInstructions());
            j--;
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
        recipes = Sort.oldToNewSort(recipes);
        int j = 9;
        for (int i = 0; i < 10; i++) {
            assertEquals("TInstr" + (j), recipes.get(i).getRecipeInstructions());
            j--;
        }
    }
    /**
     * Test case to verify the sorting of recipes in descending order by title (Z to A).
     */
    @Test
    public void sortingZtoA(){
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
        recipes = Sort.aToZSort(recipes);
        title = 90;
        for (int i = 0; i < 10; i++) {
            assertEquals(title + "TR" + i, recipes.get(i).getRecipeTitle());
            title--;
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
        recipes = Sort.zToASort(recipes);
        title = 65;
        for (int i = 0; i < 10; i++) {
            assertEquals(title + "TR" + i, recipes.get(i).getRecipeTitle());
            title++;
        }
    }

    /*
     * Tests that integrates this with ChatGPT and Whisper
     */
    @Test
    public void sortingAtoZAndGPT(){
        MockWhisper whisper = new MockWhisper();
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(6), whisper.getResponse(2), new Recipe(), new MockChatGPT(), new MockImageCreation());
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Egg Tortilla Breakfast Sandwich", textToRecipe.getRecipe().getRecipeTitle());
            recipes.add(textToRecipe.getRecipe());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        MockWhisper whisper2 = new MockWhisper();
        TextToRecipe textToRecipe2 = new TextToRecipe(whisper2.getResponse(1), "dinner", new Recipe(), new MockChatGPT(), new MockImageCreation());
        try {
            textToRecipe2.createRecipeObj();
            assertEquals("Baked Cinnamon Apple Crisp", textToRecipe2.getRecipe().getRecipeTitle());
            recipes.add(textToRecipe2.getRecipe());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        TextToRecipe textToRecipe3 = new TextToRecipe(whisper.getResponse(0), "dinner", new Recipe(), new MockChatGPT(), new MockImageCreation());
        try {
            textToRecipe3.createRecipeObj();
            assertEquals("Chicken and Rice Casserole", textToRecipe3.getRecipe().getRecipeTitle());
            recipes.add(textToRecipe3.getRecipe());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        recipes = Sort.aToZSort(recipes);
        assertEquals("Baked Cinnamon Apple Crisp", recipes.get(2).getRecipeTitle());
        assertEquals("Chicken and Rice Casserole", recipes.get(1).getRecipeTitle());
        assertEquals("Egg Tortilla Breakfast Sandwich", recipes.get(0).getRecipeTitle());
        
    }
}


