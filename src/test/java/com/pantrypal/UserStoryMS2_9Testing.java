package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}