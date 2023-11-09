package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.net.URISyntaxException;

public class UserStory3Testing {
    // This user story is similar to user story 2, but with different ingredients since we only were implementing the UI in the story
    // We were also not able to have unit tests since we were just getting the details from the previous user story and displaying them

    @Test
    public void testUserStory3(){
        MockWhisper whisper = new MockWhisper();
        MockTextToRecipe textToRecipe = new MockTextToRecipe(whisper.getResponse(1), "dinner", new Recipe());
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Baked Cinnamon Apple Crisp", textToRecipe.getRecipe().getRecipeTitle());
            assertEquals("Ingredients\n\n? 4 large apples, peeled and sliced\n? 2 tsp. ground cinnammon\n? 1/2 cup white sugar\n? 1/4 cup all-purpose flour\n? 1/4 cup butter\n\n", textToRecipe.getRecipe().getRecipeIngredients());
            assertEquals("Instructions\n\n1. Preheat oven to 350 degrees F (175 degrees C).\n\n2. Grease an 8-inch square baking dish.\n\n3. In a medium bowl, combine the apples, cinnamon, sugar, and flour. Toss to coat the apples.\n\n4. Pour the apple mixture into the prepared baking dish.\n\n5. Dot the top with the butter.\n\n6. Bake for 30 minutes, or until the apples are tender and the topping is lightly browned.\n\n7. Serve the warm apple crisp with a scoop of your favorite ice cream. Enjoy!", textToRecipe.getRecipe().getRecipeInstructions());
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
