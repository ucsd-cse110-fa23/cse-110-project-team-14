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
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(1), "dinner", new Recipe(), new MockChatGPT());
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

    @Test
    public void testUserStory3_2()  {
        MockWhisper whisper = new MockWhisper();

        String prompt = "Make me a dinner recipe using only these ingredients: chicken, rice. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(0), "dinner", new Recipe(), new MockChatGPT());
        try {
            textToRecipe.createRecipeObj();
            assertEquals("Chicken and Rice Casserole", textToRecipe.getRecipe().getRecipeTitle());
            assertEquals("Ingredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\n", textToRecipe.getRecipe().getRecipeIngredients());
            assertEquals("Instructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish", textToRecipe.getRecipe().getRecipeInstructions());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        
    }
}
