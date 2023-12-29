package com.pantrypal.model;

import com.pantrypal.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ChatGPT {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = Globals.APIKEY;
    private static final String MODEL = "text-davinci-003";
    private String prompt;
    private int maxTokens;
    private String response;

    /**
     * Generates a recipe using the ChatGPT API.
     *
     * @param maxTokens The maximum number of tokens to generate.
     * @param prompt    The prompt to use for generating the recipe.
     * @throws IOException          If an I/O error occurs while sending the request
     *                              or receiving the response.
     * @throws InterruptedException If the thread is interrupted during the HTTP
     *                              request.
     * @throws URISyntaxException   If the URI syntax is incorrect.
     */
    protected void generatedRecipe(int maxTokens, String prompt) throws IOException,
            InterruptedException, URISyntaxException {
        // Set request parameters
        this.maxTokens = maxTokens;
        this.prompt = prompt;

        // Create a request body which you will pass into the request object
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
                HttpResponse.BodyHandlers.ofString());

        // Process the response
        String responseBody = response.body();

        // Parse the response JSON
        JSONObject responseJson = new JSONObject(responseBody);

        // Extract the generated text from the response
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");

        // Set the generated text as the response
        this.response = generatedText;
        // System.out.println("CHATGPT says: " + generatedText);
    }

    /**
     * Parses the response from the API call to get the title of the recipe
     */
    protected String parseTitle() {
        int indexFirstCharacter = 0;
        while (this.response.charAt(indexFirstCharacter) == '\n') {
            indexFirstCharacter++;
        }
        // If statement for a bug where chatGPT returns "Recipe:"
        String title_checker = this.response; // Use to check where does the title starts
        title_checker.toLowerCase();
        if (this.response.contains("recipe title:")) {
            indexFirstCharacter += 14;
        } else if (this.response.contains("recipe title")) {
            indexFirstCharacter += 13;
        } else if (this.response.contains("recipe:")) {
            indexFirstCharacter += 8;
        } else if (this.response.contains("recipe")) {
            indexFirstCharacter += 7;
        } else if (this.response.contains("title:")) {
            indexFirstCharacter += 7;
        } else if (this.response.contains("title")) {
            indexFirstCharacter += 6;
        }

        int indexOfNewLine = this.response.indexOf("\n", indexFirstCharacter);

        return response.substring(indexFirstCharacter, indexOfNewLine);
    }

    /**
     * Parses the response from the API call to get the ingredients of the recipe
     */
    protected String parseRecipeIngredients() {
        int indexOfIngredients = this.response.indexOf("Ingredients:");

        // Check if the ChatGPT Ingredients did not had semicolons
        if (indexOfIngredients == -1) {
            indexOfIngredients = this.response.indexOf("Ingredients");
        }

        int indexOfInstructions = this.response.indexOf("Instructions:");

        // Check if the ChatGPT Instructions did not had semicolons
        if (indexOfInstructions == -1) {
            indexOfInstructions = this.response.indexOf("Directions");
        }

        return this.response.substring(indexOfIngredients, indexOfInstructions);
    }

    /**
     * Parses the response from the API call to get the instructions of the recipe
     */
    protected String parseRecipeInstructions() {
        int indexOfInstructions = this.response.indexOf("Instructions:");

        // Check if the ChatGPT Instructions did not had semicolons
        if (indexOfInstructions == -1) {
            indexOfInstructions = this.response.indexOf("Instructions");
        }
        return this.response.substring(indexOfInstructions);
    }
}
