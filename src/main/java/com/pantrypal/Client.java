package com.pantrypal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private String user;
    private String recipeName;

    public Client(String user, String recipeName){
        // have to encode raw strings in case they have special charaacters
        // most commonly a space in the recipe
         try {
            this.user = URLEncoder.encode(user, "UTF-8");
            this.recipeName = URLEncoder.encode(recipeName, "UTF-8");
            // Replace '+' with '%20' for spaces
            this.user = this.user.replace("+", "%20");
            this.recipeName = this.recipeName.replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest() throws IOException, InterruptedException {
        try {
            // Replace with the actual URL
            String url = "http://localhost:8100/share/" + user + "/" + recipeName;
            System.out.println(url);
    
            // Perform HTTP GET request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
    
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
            // Check the HTTP status code
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                // Parse HTML response using Jsoup
                Document doc = Jsoup.parse(response.body());
    
                // Extract information from the HTML
                String title = doc.select("h1").text();
                String ingredients = doc.select("p:contains(Ingredients)").text();
                String instructions = doc.select("p:contains(Instructions)").text();
                String mealType = doc.select("p:contains(Meal Type)").text();
                String imgUrl = doc.select("img").attr("src");
    
                // Display the extracted information (you can use this data as needed)
                System.out.println("Title: " + title);
                System.out.println("Ingredients: " + ingredients);
                System.out.println("Instructions: " + instructions);
                System.out.println("Meal Type: " + mealType);
                System.out.println("Image URL: " + imgUrl);
            } else {
                System.err.println("Unexpected HTTP status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}