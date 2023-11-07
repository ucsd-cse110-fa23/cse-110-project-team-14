package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockChatGPT {
    private String prompt;
    private int maxTokens;
    private String response;
    
    void generatedRecipe(int maxTokens, String prompt) {
        // TEST: 1
        // implemnet file reader? 
        if(prompt.equals("Make me a meal using only these ingredients: chicken, rice")){
            response = "Chicken and Rice Casserole\n\nIngredients:\n\n- 2 cups cooked rice\n- 2 cups cooked and diced chicken\n- 1 onion, diced\n- 1 bell pepper, diced\n- 1 teaspoon garlic powder\n- 1 teaspoon smoked paprika\n- 1/2 teaspoon ground cumin\n- 2 tablespoons olive oil\n- 1 can of diced tomatoes\n- 1 cup shredded cheese of your choice\n\nInstructions:\n\n1. Preheat oven to 375°F.\n\n2. Heat olive oil in a large skillet over medium heat. Add the onion and bell pepper. Saute until they start to soften.\n\n3. Add garlic powder, smoked paprika, and cumin. Cook for another minute or two.\n\n4. Add the diced tomatoes and cooked chicken. Simmer for 5-7 minutes.\n\n5. Meanwhile, spread the cooked rice in the bottom of a greased 9x13 inch baking dish";
        }
        else if(prompt.equals("Make me a meal using only these ingredients: apple, cinammon, sugar and flour")){
            response = "Baked Cinnamon Apple Crisp\nIngredients\n\n? 4 large apples, peeled and sliced\n? 2 tsp. ground cinnammon\n? 1/2 cup white sugar\n? 1/4 cup all-purpose flour\n? 1/4 cup butter\n\nInstructions\n\n1. Preheat oven to 350 degrees F (175 degrees C).\n\n2. Grease an 8-inch square baking dish.\n\n3. In a medium bowl, combine the apples, cinnamon, sugar, and flour. Toss to coat the apples.\n\n4. Pour the apple mixture into the prepared baking dish.\n\n5. Dot the top with the butter.\n\n6. Bake for 30 minutes, or until the apples are tender and the topping is lightly browned.\n\n7. Serve the warm apple crisp with a scoop of your favorite ice cream. Enjoy!";
        }

    }

    String parseTitle() {
        int indexFirstCharacter = 0;
        while(this.response.charAt(indexFirstCharacter) == '\n'){
            indexFirstCharacter++;
        }
        int indexOfNewLine = this.response.indexOf("\n", indexFirstCharacter);

        System.out.println("CAT GPTSAYS" + this.response);
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