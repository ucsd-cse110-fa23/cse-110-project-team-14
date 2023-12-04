package com.pantrypal.model;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class URLShareHandler implements HttpHandler{
@Override
public void handle(HttpExchange exchange) throws IOException {
    if ("GET".equals(exchange.getRequestMethod())) {
        // link will look like host/share/user/recipeName
        // end of "/share/" to /
        // String user = exchange.getRequestURI().getPath().substring("/share/".length(), "/");
        // String recipeName = exchange.getRequestURI().getPath().substring("/".length(), "");

        // if() 
    // extract the contents of the URL
    // decide what user, recipe to get
    // PRINT the contents of the recipe to the webserver
}
}
}
