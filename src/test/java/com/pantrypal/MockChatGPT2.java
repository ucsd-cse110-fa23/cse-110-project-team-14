package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockChatGPT2 extends ChatGPT {
    private String prompt;
    private int maxTokens;
    private String response;
    
    void generatedRecipe(int maxTokens, String prompt) {
        // TEST: 1
        // implemnet file reader? 
        //TODO: PROMPT CHANGED?
        String prompt1 = "Make me a dinner recipe using only these ingredients: chicken, rice. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        

        if(temp == 1){
            response = "Chicken and Rice Casserole\n\nIngredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken" +
            "\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder" +
            "\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil" +
            "\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice" +
            "\n\nInstructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften." +
            "\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two." +
            "\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish";
        }
        else if(temp == 2){
            response ="Crispy Chicken and Rice\nIngredients:\n-1 Chicken Breast, cut into cubes\n-1/2 cup of White Rice\n-1/4 cup Soy Sauce\n-2 cloves of Garlic, minced\n-1 tsp of Chopped Ginger\n-2 tbsp of Olive Oil\n-Salt and Pepper, to taste\nInstructions:\n1. Preheat the oven to 350°F.\n2. In a medium sized bowl, stir together the soy sauce, garlic, ginger, olive oil, salt, and pepper.\n3. Add the chicken cubes to the marinade and let it sit for 10 minutes.\n4. In a small pot, bring 1 cup of water to a boil.\n5. Add the white rice to the boiling water and cook for about 15 minutes or according to package instructions.\n6. In a large baking dish, spread out the marinated chicken in an even layer.\n7. Bake in the preheated oven for about 20 minutes, or until the chicken is cooked through.\n8. Once the chicken has cooked, add the cooked rice to the baking dish and stir to combine.\n9. Return the dish to the oven and continue to bake for an additional 10 minutes.\n10. Serve warm and enjoy!";
        }
        


    }

    String parseTitle() {
        int indexFirstCharacter = 0;
        while(this.response.charAt(indexFirstCharacter) == '\n'){
            indexFirstCharacter++;
        }
        int indexOfNewLine = this.response.indexOf("\n", indexFirstCharacter);

        return response.substring(indexFirstCharacter, indexOfNewLine);

    }

    /**
     * Parses the response from the API call to get the ingredients of the recipe
     */
    String parseRecipeIngredients() {
        int indexOfIngredients = this.response.indexOf("Ingredients:");

        //Check if the ChatGPT Ingredients did not had semicolons
        if(indexOfIngredients == -1){
            indexOfIngredients = this.response.indexOf("Ingredients");
        }

        int indexOfInstructions = this.response.indexOf("Instructions:");

        //Check if the ChatGPT Instructions did not had semicolons
        if(indexOfInstructions == -1){
            indexOfInstructions = this.response.indexOf("Instructions");
        }

        return this.response.substring(indexOfIngredients, indexOfInstructions);
    }


    /**
     * Parses the response from the API call to get the instructions of the recipe
     */
    String parseRecipeInstructions() {
        int indexOfInstructions = this.response.indexOf("Instructions:");

        //Check if the ChatGPT Instructions did not had semicolons
        if(indexOfInstructions == -1){
            indexOfInstructions = this.response.indexOf("Instructions");
        }
        return this.response.substring(indexOfInstructions);
    }
    
}

/* 
Prompt: "Make me a recipe using only: chicken, rice"
Chicken and Rice Casserole

Ingredients:

- 2 cups cooked rice
- 2 cups cooked and diced chicken
- 1 onion, diced
- 1 bell pepper, diced
- 1 teaspoon garlic powder
- 1 teaspoon smoked paprika
- 1/2 teaspoon ground cumin
- 2 tablespoons olive oil
- 1 can of diced tomatoes
- 1 cup shredded cheese of your choice

Instructions:

1. Preheat oven to 375°F.

2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.

3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.        

4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.

5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish
 */