package com.pantrypal.model;

import java.io.IOException;
import java.net.URISyntaxException;

/*
 * Processor class will do the following:
 * - It will take the transcribed file and send it to ChatGPT
 * - Grab response from ChatGpt and parse it
 * - Send the parsed response to the Recipe (with getters and setters)
 */
public class TextToRecipe {
    private String ingredients; //Grabbed from second whisper
    private String mealType; //Grabbed from first whisper
    private Recipe recipe;
    private ChatGPT chatGPT;
    private ImageCreation imageCreation;
    // String generatedText = responseJson.getString("text"); --> use this to grab the user's speech as text

    public TextToRecipe(String ingredients, String mealType, Recipe recipe, ChatGPT chatGPT, ImageCreation imageCreation) {
        this.ingredients = ingredients;
        this.mealType = mealType;
        this.recipe = recipe;
        this.chatGPT = chatGPT;
        this.imageCreation = imageCreation;
    }

    public void createRecipeObj() throws IOException,
            InterruptedException, URISyntaxException {
        //Fill Recipe Type        
        recipe.setMealType(mealType);

        //String oldPrompt = "Make me a "+mealType+ " recipe using only these ingredients: " + this.ingredients;
        String prompt = "Make me a "+mealType+ " recipe using only these ingredients: "
                + this.ingredients+". \n" +
                "Please give me the recipe following this format:\n" +
                "Recipe title\n" +
                "Ingredients:\n" +
                "Instructions:\n";

        chatGPT.generatedRecipe(300, prompt);

        //Fill Recipe title
        recipe.setRecipeTitle(chatGPT.parseTitle());


        //Fill recipe ingredients
        recipe.setRecipeIngredients(chatGPT.parseRecipeIngredients());

        //Fill recipe instructions
        recipe.setRecipeInstructions(chatGPT.parseRecipeInstructions());
        
        // Use recipe title to generate image url and set the recipe url
        String imageURL = imageCreation.generateImageURL(recipe.getRecipeTitle());
        recipe.setRecipeImageURL(imageURL);
    }

    public Recipe getRecipe() {
        return this.recipe;
    }


}
