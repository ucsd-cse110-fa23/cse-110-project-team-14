package com.pantrypal.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.util.List;

import com.pantrypal.constants.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ChatGPT {
    private static final String API_ENDPOINT = Constants.CHATGPT_API_ENDPOINT;
    private static final String API_KEY = Constants.API_KEY;
    private static final String MODEL = Constants.CHAT_MODEL;
    private String prompt;
    private int maxTokens;
    private String response;



    void generatedRecipe(int maxTokens, String prompt) throws IOException, 
        InterruptedException, URISyntaxException {
        // Set request parameters
        this.maxTokens = maxTokens;
        this.prompt = prompt;

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", this.prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        // Create the HTTP Client
        HttpClient client = HttpClient.newHttpClient();


        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(API_ENDPOINT))
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(JSONObject.valueToString(requestBody)))
        .build();


        // Send the request and receive the response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );
        /////test things
        System.out.println("ChatGPT response: " + response);

        // Process the response
        String responseBody = response.body();
        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");


        this.response = generatedText;

        //TODO: Remove this print statement
        System.out.println("CHATGPT says: " + generatedText);
    }

    /**
     * Parses the response from the API call to get the title of the recipe
     */
    String parseTitle() {
        int indexFirstCharacter = 0;
        while(this.response.charAt(indexFirstCharacter) == '\n'){
            indexFirstCharacter++;
        }
        //If statement for a bug where chatGPT returns "Recipe:"
        if(this.response.contains("Recipe:")){
            indexFirstCharacter += 8;
        }
        else if (this.response.contains("Recipe")){
            indexFirstCharacter += 7;
        }
        else if (this.response.contains("Recipe Title:")){
            indexFirstCharacter += 14;
        }
        else if (this.response.contains("Recipe Title")){
            indexFirstCharacter += 13;
        }
        else if (this.response.contains("Title:")){
            indexFirstCharacter += 7;
        }
        else if (this.response.contains("Title")){
            indexFirstCharacter += 6;
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
            indexOfInstructions = this.response.indexOf("Directions");
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
