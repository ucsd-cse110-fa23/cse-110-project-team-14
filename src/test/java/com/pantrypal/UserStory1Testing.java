package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.net.URISyntaxException;


public class UserStory1Testing {
    /**
     * Test case to verify the functionality of the parseTitle method in the MockChatGPT class.
     * The test generates a recipe using only chicken and rice and verifies that the parseTitle method
     * returns the expected title "Chicken and Rice Casserole".
     */
    @Test
    public void testParseTitle() {
        MockChatGPT mockChatGPT = new MockChatGPT();
        String prompt = "Make me a dinner recipe using only these ingredients: chicken, rice. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";
        mockChatGPT.generatedRecipe(300, prompt);
        String title = mockChatGPT.parseTitle();
        assertEquals("Chicken and Rice Casserole", title);
    }

    /**
     * Tests the parseRecipeIngredients method of the MockChatGPT class.
     * It generates a recipe using only chicken and rice, and then checks if the parsed ingredients match the expected result.
     */
    @Test
    public void testParseRecipeIngredients() {
        MockChatGPT mockChatGPT = new MockChatGPT();
        String prompt = "Make me a dinner recipe using only these ingredients: chicken, rice. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";
        mockChatGPT.generatedRecipe(300, prompt);
        String ingredients = mockChatGPT.parseRecipeIngredients();
        assertEquals("Ingredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\n", ingredients);
    }

    /**
     * Tests the parseRecipeInstructions method of the MockChatGPT class.
     * Verifies that the instructions generated by the parseRecipeInstructions method match the expected instructions.
     */
    @Test
    public void testParseRecipeInstructions() {
        MockChatGPT mockChatGPT = new MockChatGPT();
        String prompt = "Make me a dinner recipe using only these ingredients: chicken, rice. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";
        mockChatGPT.generatedRecipe(300, prompt);
        String instructions = mockChatGPT.parseRecipeInstructions();
        assertEquals("Instructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish", instructions);
    }

    @Test
    void testCreateRecipeObj() throws IOException, InterruptedException, URISyntaxException {
        TextToRecipe textToRecipe = new TextToRecipe("pasta, chicken, tomatoes", "lunch", new Recipe(), new MockChatGPT());
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Stovetop Baked Chicken Tomato Pasta", textToRecipe.getRecipe().getRecipeTitle());

            String ingredients = "Ingredients:\n- 2 chicken breasts, cubed\n- 1 cup pasta" +
            "\n- 1/2 cup diced tomatoes" + 
            "\n- 1/4 cup Parmesan cheese" +
            "\n- 2 cloves garlic, minced" +
            "\n- 2-3 tbsp olive oil" +
            "\n- Salt and pepper\n";
            assertEquals(ingredients, textToRecipe.getRecipe().getRecipeIngredients());

            String instructions = "Instructions:" +
            "\n1. In a large skillet, heat 2 tablespoons of olive oil over medium-high heat." +
            "\n2. Add the cubed chicken and season with salt and pepper. Cook for 4-5 minutes or until chicken is cooked through" +
            "\n3. Add the garlic and cook for one minute, stirring often" +
            "\n4. Add the tomatoes and cook for 2-3 minutes" +
            "\n5. Add the pasta and 1 cup of water. Bring to a boil, reduce to a simmer and cook for 8-10 minutes or until pasta is cooked through." + 
            "\n6. Add the Parmesan cheese and remaining tablespoon of olive oil, stirring to combine." +
            "\n7. Serve warm and enjoy!";
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
