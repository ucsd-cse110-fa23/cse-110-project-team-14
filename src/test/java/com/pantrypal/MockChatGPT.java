package com.pantrypal;

import com.pantrypal.model.ChatGPT;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockChatGPT extends ChatGPT {
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

        String prompt2 = "Make me a dinner recipe using only these ingredients: apple, cinammon, sugar and flour. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";

        String prompt3 = "Make me a breakfast recipe using only these ingredients: bread, egg, tortilla. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";

        String prompt4 = "Make me a lunch recipe using only these ingredients: pasta, chicken, tomatoes. \n" +
        "Please give me the recipe following this format:\n" +
        "Recipe title\n" +
        "Ingredients:\n" +
        "Instructions:\n";

        if(prompt.equals(prompt1)){
            response = "Chicken and Rice Casserole\n\nIngredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken" +
            "\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder" +
            "\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil" +
            "\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice" +
            "\n\nInstructions:\n\n1. Preheat oven to 375F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften." +
            "\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two." +
            "\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish";
        }
        else if(prompt.equals(prompt2)){
            response = "Baked Cinnamon Apple Crisp\nIngredients\n\n? 4 large apples, peeled and sliced" +
            "\n? 2 tsp. ground cinnammon\n? 1/2 cup white sugar\n? 1/4 cup all-purpose flour\n? 1/4 cup butter" +
            "\n\nInstructions\n\n1. Preheat oven to 350 degrees F (175 degrees C).\n\n2. Grease an 8-inch square baking dish." +
            "\n\n3. In a medium bowl, combine the apples, cinnamon, sugar, and flour. Toss to coat the apples.\n\n4. Pour the apple mixture into the prepared baking dish." +
            "\n\n5. Dot the top with the butter.\n\n6. Bake for 30 minutes, or until the apples are tender and the topping is lightly browned." +
            "\n\n7. Serve the warm apple crisp with a scoop of your favorite ice cream. Enjoy!";
        }
        else if(prompt.equals(prompt3)){
            response = "\n\nEgg Tortilla Breakfast Sandwich\nIngredients:" +
            "\n- 2 slices of bread\n- 1 egg\n- 1 flour tortilla" +
            "\nInstructions:\n1. Toast the bread slices and set aside.\n2. Heat a small nonstick skillet over medium heat." +
            "\n3. Add the egg and cook until done, about 3 minutes.\n4. Remove egg from the skillet and set aside." +
            "\n5. Place tortilla in the skillet and warm for about 30 seconds.\n6. Place one of the slices of bread in the skillet and top with the egg." + 
            "\n7. Top the egg with the tortilla and the other slice of bread.\n8. Heat until bread";
        }
        else if(prompt.equals(prompt4)){
            response = "Stovetop Baked Chicken Tomato Pasta\nIngredients:\n- 2 chicken breasts, cubed\n- 1 cup pasta" +
            "\n- 1/2 cup diced tomatoes" + 
            "\n- 1/4 cup Parmesan cheese" +
            "\n- 2 cloves garlic, minced" +
            "\n- 2-3 tbsp olive oil" +
            "\n- Salt and pepper" +
            "\nInstructions:" +
            "\n1. In a large skillet, heat 2 tablespoons of olive oil over medium-high heat." +
            "\n2. Add the cubed chicken and season with salt and pepper. Cook for 4-5 minutes or until chicken is cooked through" +
            "\n3. Add the garlic and cook for one minute, stirring often" +
            "\n4. Add the tomatoes and cook for 2-3 minutes" +
            "\n5. Add the pasta and 1 cup of water. Bring to a boil, reduce to a simmer and cook for 8-10 minutes or until pasta is cooked through." + 
            "\n6. Add the Parmesan cheese and remaining tablespoon of olive oil, stirring to combine." +
            "\n7. Serve warm and enjoy!";
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