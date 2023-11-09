package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.net.URISyntaxException;
public class UserStory2Testing {
    @Test
    public void testUserStory()  {
        MockWhisper whisper = new MockWhisper();
        MockTextToRecipe textToRecipe = new MockTextToRecipe(whisper.getResponse(0), "dinner", new Recipe());
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Chicken and Rice Casserole", textToRecipe.getRecipe().getRecipeTitle());
            assertEquals("Ingredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\n", textToRecipe.getRecipe().getRecipeIngredients());
            assertEquals("Instructions:\n\n1. Preheat oven to 375°F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish", textToRecipe.getRecipe().getRecipeInstructions());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        
    }
}
