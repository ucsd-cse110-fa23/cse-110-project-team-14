package com.pantrypal;


import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserStory8Testing {
    @Test
    public void testWhisper() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("breakfast", whisper.getResponse(2));
    }

    @Test
    public void testWhisper2() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("lunch", whisper.getResponse(3));
    }

    @Test
    public void testWhisper3() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("dinner", whisper.getResponse(4));
    }

    @Test
    public void testUserStory8(){
        MockWhisper whisper = new MockWhisper();
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(6), whisper.getResponse(2), new Recipe(), new MockChatGPT());
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Egg Tortilla Breakfast Sandwich", textToRecipe.getRecipe().getRecipeTitle());
            String ingredients = "Ingredients:" +
            "\n- 2 slices of bread\n- 1 egg\n- 1 flour tortilla\n";
            assertEquals(ingredients, textToRecipe.getRecipe().getRecipeIngredients());
            String instructions = "Instructions:\n1. Toast the bread slices and set aside.\n2. Heat a small nonstick skillet over medium heat." +
            "\n3. Add the egg and cook until done, about 3 minutes.\n4. Remove egg from the skillet and set aside." +
            "\n5. Place tortilla in the skillet and warm for about 30 seconds.\n6. Place one of the slices of bread in the skillet and top with the egg." + 
            "\n7. Top the egg with the tortilla and the other slice of bread.\n8. Heat until bread";
            assertEquals(instructions, textToRecipe.getRecipe().getRecipeInstructions()); 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
    }
}
