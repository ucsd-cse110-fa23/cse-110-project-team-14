package com.pantrypal;

import org.junit.jupiter.api.Test;

import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UserStoryMS2_9Testing {
    /**
     * Test case to verify the functionality of filtering recipes by breakfast meal type.
     * It creates a list of recipes and sets different meal types for each recipe.
     * Then it calls the filterByBreakfast method from the Filters class to filter the recipes by breakfast meal type.
     * Finally, it asserts that all filtered recipes have the meal type "Breakfast".
     */
    @Test
    public void testFilterByBreakfast(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle("TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(i);
            if(i%3 == 0){
                recipe_obj.setMealType("Breakfast");
            }
            else if(i%3 == 1){
                recipe_obj.setMealType("Lunch");
            }
            else{
                recipe_obj.setMealType("Dinner");
            }
            recipes.add(recipe_obj);
        }

        ArrayList<Recipe> filtered_recipes = Filters.filterByBreakfast(recipes);
        for (int i = 0; i < filtered_recipes.size(); i++) {
            assertEquals("Breakfast", filtered_recipes.get(i).getMealType());
        }
    }

    /**
     * Test case to verify the functionality of filtering recipes by lunch meal type.
     * It creates a list of recipes with different meal types (breakfast, lunch, dinner),
     * and then filters the recipes to only include those with lunch as the meal type.
     * Finally, it asserts that all the filtered recipes have lunch as the meal type.
     */
    @Test
    public void testFilterByLunch(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle("TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(i);
            if(i%3 == 0){
                recipe_obj.setMealType("Breakfast");
            }
            else if(i%3 == 1){
                recipe_obj.setMealType("Lunch");
            }
            else{
                recipe_obj.setMealType("Dinner");
            }
            recipes.add(recipe_obj);
        }

        ArrayList<Recipe> filtered_recipes = Filters.filterByLunch(recipes);
        for (int i = 0; i < filtered_recipes.size(); i++) {
            assertEquals("Lunch", filtered_recipes.get(i).getMealType());
        }
    }

    /**
     * Test case to verify the functionality of filtering recipes by dinner meal type.
     * It creates a list of recipes with different meal types (breakfast, lunch, dinner),
     * and then filters the recipes to only include those with dinner as the meal type.
     * Finally, it asserts that all the filtered recipes have "Dinner" as the meal type.
     */
    @Test
    public void testFilterByDinner(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle("TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(i);
            if(i%3 == 0){
                recipe_obj.setMealType("Breakfast");
            }
            else if(i%3 == 1){
                recipe_obj.setMealType("Lunch");
            }
            else{
                recipe_obj.setMealType("Dinner");
            }
            recipes.add(recipe_obj);
        }

        ArrayList<Recipe> filtered_recipes = Filters.filterByDinner(recipes);
        for (int i = 0; i < filtered_recipes.size(); i++) {
            assertEquals("Dinner", filtered_recipes.get(i).getMealType());
        }
    }

    /*
     * Test with sort and filter
     */
    @Test
    public void testFilterByBreakfastAndSortByTitle(){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        char title = 65;
        for (int i = 0; i < 10; i++) {
            Recipe recipe_obj = new Recipe();
            recipe_obj.setRecipeTitle(title + "TR" + i);
            recipe_obj.setRecipeIngredients("TIngred" + i);
            recipe_obj.setRecipeInstructions("TInstr" + i);
            recipe_obj.setRecipeIndex(i);
            title++;
            if(i%3 == 0){
                recipe_obj.setMealType("Breakfast");
            }
            else if(i%3 == 1){
                recipe_obj.setMealType("Lunch");
            }
            else{
                recipe_obj.setMealType("Dinner");
            }
            recipes.add(recipe_obj);
        }

        ArrayList<Recipe> filtered_recipes = Filters.filterByBreakfast(recipes);
        filtered_recipes = Sort.aToZSort(filtered_recipes);
        title = 65;
        for (int i = 0; i < filtered_recipes.size(); i++) {
            assertEquals("Breakfast", filtered_recipes.get(i).getMealType());
            assertEquals(title + "TR" + i, recipes.get(i).getRecipeTitle());
            title++;
        }
    }

    @Test
    public void testMS2_9BDD(){
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

        assertEquals(3, recipes.size());
        recipes = Filters.filterByDinner(recipes);
        assertEquals(2, recipes.size());

        for(int i = 0; i < recipes.size(); i++){
            assertEquals("Dinner", recipes.get(i).getMealType());
        }
    }

    @Test
    public void testMS2_9BDD2(){
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

        assertEquals(3, recipes.size());
        recipes = Filters.filterByLunch(recipes);
        assertEquals(0, recipes.size());
    }
}