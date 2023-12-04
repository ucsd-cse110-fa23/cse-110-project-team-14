package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserStoryMS2_7Testing {
    /**
     * Test case for the recipe tags functionality.
     * It verifies that the meal type of a recipe is correctly set and retrieved.
     */
    @Test
    public void testRecipeTags(){
        Recipe recipe = new Recipe();
        assertEquals(null, recipe.getMealType());
        recipe.setMealType("breakfast");
        assertEquals("Breakfast", recipe.getMealType());
        recipe.setMealType("lunch");
        assertEquals("Lunch", recipe.getMealType());
        recipe.setMealType("dinner");
        assertEquals("Dinner", recipe.getMealType());
    }

    /**
     * Test case for User Story MS2_7 using BDD approach.
     * It tests the functionality of creating a recipe object from text input and checking that the tags are correctly set.
     */
    @Test
    public void testUserStoryMS2_7BDD(){
        MockWhisper whisper = new MockWhisper();
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(6), whisper.getResponse(2), new Recipe(), new MockChatGPT(), new MockImageCreation());
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Breakfast", textToRecipe.getRecipe().getMealType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
}
