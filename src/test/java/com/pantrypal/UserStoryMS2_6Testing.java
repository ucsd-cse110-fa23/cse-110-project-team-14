package com.pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

public class UserStoryMS2_6Testing {
    /*
     * This test tests the functionality of the regen recipe by creating a new recipe based on the same prompt using our mock chatgpt
     */
    @Test
    public void userStoryMS2_6Testing(){
        ChatGPT chatgpt = new MockChatGPT2();
        MockWhisper whisper = new MockWhisper();
        int temp = (Math.random() <= 0.5) ? 7 : 8; //random generator
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(temp), whisper.getResponse(4), new Recipe(), chatgpt, new MockImageCreation());
        try {
            textToRecipe.createRecipeObj();
            if (temp == 7){
                assertEquals("Ingredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\n", textToRecipe.getRecipe().getRecipeIngredients());
                assertEquals("Instructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish", textToRecipe.getRecipe().getRecipeInstructions());
            } else if (temp == 8){
                assertEquals("Ingredients:\n-1 Chicken Breast, cut into cubes\n-1/2 cup of White Rice\n-1/4 cup Soy Sauce\n-2 cloves of Garlic, minced\n-1 tsp of Chopped Ginger\n-2 tbsp of Olive Oil\n-Salt and Pepper, to taste\n", textToRecipe.getRecipe().getRecipeIngredients());
                assertEquals("Instructions:\n1. Preheat the oven to 350°F.\n2. In a medium sized bowl, stir together the soy sauce, garlic, ginger, olive oil, salt, and pepper.\n3. Add the chicken cubes to the marinade and let it sit for 10 minutes.\n4. In a small pot, bring 1 cup of water to a boil.\n5. Add the white rice to the boiling water and cook for about 15 minutes or according to package instructions.\n6. In a large baking dish, spread out the marinated chicken in an even layer.\n7. Bake in the preheated oven for about 20 minutes, or until the chicken is cooked through.\n8. Once the chicken has cooked, add the cooked rice to the baking dish and stir to combine.\n9. Return the dish to the oven and continue to bake for an additional 10 minutes.\n10. Serve warm and enjoy!", textToRecipe.getRecipe().getRecipeInstructions());  
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            textToRecipe.createRecipeObj();
            if (temp == 7){
                assertEquals("Ingredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\n", textToRecipe.getRecipe().getRecipeIngredients());
                assertEquals("Instructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish", textToRecipe.getRecipe().getRecipeInstructions());
            } else if (temp == 8){
                assertEquals("Ingredients:\n-1 Chicken Breast, cut into cubes\n-1/2 cup of White Rice\n-1/4 cup Soy Sauce\n-2 cloves of Garlic, minced\n-1 tsp of Chopped Ginger\n-2 tbsp of Olive Oil\n-Salt and Pepper, to taste\n", textToRecipe.getRecipe().getRecipeIngredients());
                assertEquals("Instructions:\n1. Preheat the oven to 350°F.\n2. In a medium sized bowl, stir together the soy sauce, garlic, ginger, olive oil, salt, and pepper.\n3. Add the chicken cubes to the marinade and let it sit for 10 minutes.\n4. In a small pot, bring 1 cup of water to a boil.\n5. Add the white rice to the boiling water and cook for about 15 minutes or according to package instructions.\n6. In a large baking dish, spread out the marinated chicken in an even layer.\n7. Bake in the preheated oven for about 20 minutes, or until the chicken is cooked through.\n8. Once the chicken has cooked, add the cooked rice to the baking dish and stir to combine.\n9. Return the dish to the oven and continue to bake for an additional 10 minutes.\n10. Serve warm and enjoy!", textToRecipe.getRecipe().getRecipeInstructions());  
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            textToRecipe.createRecipeObj();
            if (temp == 7){
                assertEquals("Ingredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\n", textToRecipe.getRecipe().getRecipeIngredients());
                assertEquals("Instructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish", textToRecipe.getRecipe().getRecipeInstructions());
            } else if (temp == 8){
                assertEquals("Ingredients:\n-1 Chicken Breast, cut into cubes\n-1/2 cup of White Rice\n-1/4 cup Soy Sauce\n-2 cloves of Garlic, minced\n-1 tsp of Chopped Ginger\n-2 tbsp of Olive Oil\n-Salt and Pepper, to taste\n", textToRecipe.getRecipe().getRecipeIngredients());
                assertEquals("Instructions:\n1. Preheat the oven to 350°F.\n2. In a medium sized bowl, stir together the soy sauce, garlic, ginger, olive oil, salt, and pepper.\n3. Add the chicken cubes to the marinade and let it sit for 10 minutes.\n4. In a small pot, bring 1 cup of water to a boil.\n5. Add the white rice to the boiling water and cook for about 15 minutes or according to package instructions.\n6. In a large baking dish, spread out the marinated chicken in an even layer.\n7. Bake in the preheated oven for about 20 minutes, or until the chicken is cooked through.\n8. Once the chicken has cooked, add the cooked rice to the baking dish and stir to combine.\n9. Return the dish to the oven and continue to bake for an additional 10 minutes.\n10. Serve warm and enjoy!", textToRecipe.getRecipe().getRecipeInstructions());  
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
