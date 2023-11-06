package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

/*
 * Processor class will do the following:
 * - It will take the transcribed file and send it to ChatGPT
 * - Grab response from ChatGpt and parse it
 * - Send the parsed response to the Recipe (with getters and setters)
 */
public class MockTextToRecipe {
    private String ingredients; //Grabbed from second whisper
    private String mealType; //Grabbed from first whisper
    private Recipe recipe;
    private MockChatGPT chatGPT;
    // String generatedText = responseJson.getString("text"); --> use this to grab the user's speech as text

    MockTextToRecipe(String ingredients, String mealType, Recipe recipe) {
        this.ingredients = ingredients;
        this.mealType = mealType;
        this.recipe = recipe;
        this.chatGPT = new MockChatGPT();
    }

    void createRecipeObj() throws IOException, 
        InterruptedException, URISyntaxException{
        String prompt = "Make me a meal using only these ingredients: " + this.ingredients;
        chatGPT.generatedRecipe(300, prompt);

        //Fill Recipe title
        recipe.setRecipeTitle(chatGPT.parseTitle());
        
        //Fill recipe ingredients
        recipe.setRecipeIngredients(chatGPT.parseRecipeIngredients());

        //Fill recipe instructions
        recipe.setRecipeInstructions(chatGPT.parseRecipeInstructions());
        
    }

    Recipe getRecipe() {
        return this.recipe;
    }


}
