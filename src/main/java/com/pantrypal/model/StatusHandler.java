package com.pantrypal.model;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/* Class that handles the HTTP server */
public class StatusHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Status: Server UP");
        if ("GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(200, 0);
        }
    }
}
