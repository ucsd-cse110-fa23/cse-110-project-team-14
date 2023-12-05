package com.pantrypal;

import java.io.IOException;
import java.io.OutputStream;

import org.bson.Document;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class URLShareHandler implements HttpHandler{
@Override
public void handle(HttpExchange exchange) throws IOException {
    DatabaseOPS db = new DatabaseOPS("users");
    if ("GET".equals(exchange.getRequestMethod())) {
        // link will look like localhost/share/user/recipeName
        String path = exchange.getRequestURI().getPath();
        
        // Extracting user and recipeName
        String[] pathSegments = path.split("/");
        if (pathSegments.length >= 4) {
            String user = pathSegments[2];
            String recipeName = pathSegments[3];
            
            // Now you can use 'user' and 'recipeName' as needed
            if (db.findUno(user) != null) {
                // Do something with the user
                db.setCollectionName(user);
                db.getCollectionSize();

                Recipe r = new Recipe();
                r.setRecipeTitle(recipeName);

                Document d = db.findUno(r); 
                if(d != null) {
                    // if the recipe exists output to webpage
                    String title = d.get("Title").toString();
                    String ingredients = d.get("Ingredients").toString();
                    String instructions = d.get("Instructions").toString();    
                    String mealType = d.get("Meal Type").toString(); 
                    String imgUrl = d.get("ImageURL").toString();
                     // Prepare the HTML response
                        String htmlResponse = "<html><body>" +
                                "<h1>Title: " + title + "</h1>" +
                                "<p>Ingredients: " + ingredients + "</p>" +
                                "<p>Instructions: " + instructions + "</p>" +
                                "<p>Meal Type: " + mealType + "</p>" +
                                "<img src=" + imgUrl + ">" + "</img>" + 
                                "</body></html>";

                        // Set the response headers
                        exchange.sendResponseHeaders(200, htmlResponse.length());

                        // Get the output stream and write the HTML response
                        OutputStream os = exchange.getResponseBody();
                        os.write(htmlResponse.getBytes());

                        // Close the output stream
                        os.close();
                    } else {
                        // Recipe doesn't exist
                        sendResponse(exchange, "Recipe not found");
                    }
                } else {
                    // User doesn't exist
                    sendResponse(exchange, "User not found");
                }
            }
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        // Set the response headers
        exchange.sendResponseHeaders(404, response.length());

        // Get the output stream and write the response
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        // Close the output stream
        os.close();
    }
}