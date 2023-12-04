package com.pantrypal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class ImageCreation {
    // TODO: Add class implementation here
    private static final String API_ENDPOINT = "https://api.openai.com/v1/images/generations"; 
    private static final String API_KEY = "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";
    private static final String MODEL = "dall-e-2";
    
    public String generateImageURL(String prompt)  throws IOException, InterruptedException, URISyntaxException {
        // Set request parameters
        // This prompt will be the recipe title of the recipe we generate (before even saving it)
        // Once we save this recipe, add this image to the database and the seeRecipePage
        String recipePrompt = prompt; // Change this prompt to be the recipe title for specific recipe of specific user
        int n = 1;


        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", recipePrompt);
        requestBody.put("n", n);
        // String imageWidth = constants.width/3;
        // String imageHeight = constants.height/3;
        // String imageDims = imageWidth + "x" + imageHeight;
        requestBody.put("size", "256x256"); // Change size of generated image


        // Create the HTTP client
        HttpClient client = HttpClient.newHttpClient();


        // Create the request object
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create(API_ENDPOINT))
            .header("Content-Type", "application/json")
            .header("Authorization", String.format("Bearer %s", API_KEY))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
            .build();


        // Send the request and receive the response
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );

        // Process the response
        String responseBody = response.body();

        JSONObject responseJson = new JSONObject(responseBody);

        JSONArray choices = responseJson.getJSONArray("data");
        
        String generatedImageURL = choices.getJSONObject(0).getString("url");
        // try(
        //     InputStream in = new URI(generatedImageURL).toURL().openStream()
        // )
        // {
        //     Files.copy(in, Paths.get("image.jpg"));
        // }
        return generatedImageURL;
        // Save the image to the mongodb directly and just load it from there
    }
}
