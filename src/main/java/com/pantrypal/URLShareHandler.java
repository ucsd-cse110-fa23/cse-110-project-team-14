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
        String user = pathSegments[2];
        if(pathSegments.length == 3){
            
            System.out.println("request: " + "http://localhost:8100/share/" + user);
            db.setCollectionName("users");
            if (db.findUno(user) != null) {
                // Do something with the user
                db.setCollectionName(user);
                String htmlResponse = "<html><head><style>" +
                "body {" +
                "   font-family: helvetica, sans-serif;" +
                "   margin: 0 auto;" +
                "   max-width: 600px;" +
                "   background: #f4f4f4;" +  // Light background color
                "}" +
                "h1 {" +
                "   text-align: center;" +
                "   font-size: 24px;" +  // Adjust the font size
                "   color: #333;" +  // Dark text color
                "   margin: 20px 0;" +
                "}" +
                "a {" +
                "   display: block;" +
                "   text-decoration: none;" +
                "   color: #007bff;" +  // Link color
                "   padding: 10px 0;" +
                "   border-bottom: 1px solid #ccc;" +  // Border between links
                "}" +
                "</style></head><body>" +
                "<h1>" + user + "'s Recipes" + "</h1>";
        ArrayList<Recipe> rs = db.initializeRecipesToList();
        for (int i = rs.size() - 1; i >= 0; i--) {
            String title = rs.get(i).getRecipeTitle();
            String link = "http://localhost:8100/share/" + user + "/" + title;
            htmlResponse += "<a href=\"" + link + "\">" + title + "</a>";
        }
        htmlResponse += "</body></html>";
                // Set the response headers
                exchange.sendResponseHeaders(200, htmlResponse.length());

                // Get the output stream and write the HTML response
                OutputStream os = exchange.getResponseBody();
                os.write(htmlResponse.getBytes());

                // Close the output stream
                os.close();
                
            }
            else {
            // User doesn't exist
            sendResponse(exchange, "User not found");
            }
        }
        
        if (pathSegments.length == 4) {
            
            String recipeName = pathSegments[3];
            System.out.println("request: " + "http://localhost:8100/share/" + user + "/" + recipeName);
            // Now you can use 'user' and 'recipeName' as needed
            db.setCollectionName("users");
            if (db.findUno(user) != null) {
                // Do something with the user
                db.setCollectionName(user);

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
                       String htmlResponse = "<html><head><style>" +
                       "body {" +
                       "   font-family: helvetica, sans-serif;" +
                       "   margin: 0 auto;" +
                       "   max-width: 600px;" +
                       "   background: #f4f4f4;" +  // Light background color
                       "}" +
                       "h1 {" +
                       "   text-align: center;" +
                       "   font-size: 24px;" +  // Adjust the font size
                       "   color: #333;" +  // Dark text color
                       "   margin: 20px 0;" +
                       "}" +
                       "p {" +
                       "   color: #333;" +  // Dark text color
                       "   background: #fff;" +  // White background for paragraphs
                       "   padding: 10px;" +
                       "   margin: 10px 0;" +
                       "}" +
                       "img {" +
                       "   display: block;" +
                       "   margin: 0 auto;" +
                       "   max-width: 100%;" +  // Make sure the image doesn't exceed the container width
                       "   height: auto;" +  // Maintain aspect ratio
                       "}" +
                       "</style></head><body>" +
                       "<h1>Title: " + title + "</h1>" +
                       "<p>Ingredients: " + ingredients + "</p>" +
                       "<p>Instructions: " + instructions + "</p>" +
                       "<p>Meal Type: " + mealType + "</p>" +
                       "<img src=\"" + imgUrl + "\" alt=\"Recipe Image\">" +
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