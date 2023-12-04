package com.pantrypal.model;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileDownloadHandler implements HttpHandler {
    private Map<String, byte[]> data;

    public FileDownloadHandler(Map<String, byte[]> data) {
        this.data = data;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            String fileId = exchange.getRequestURI().getPath().substring("/download/".length());

            // Check if the requested file ID exists
            if (data.containsKey(fileId)) {
                // Get the file content from the map
                byte[] fileContent = data.get(fileId);

                // Set the response headers
                exchange.getResponseHeaders().add("Content-Type", "audio/x-wav");
                exchange.getResponseHeaders().add("Content-Disposition", "attachment; filename=\"" + fileId + "\"");
                
                // Send the file content as the response
                exchange.sendResponseHeaders(200, fileContent.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(fileContent);
                }
            } else {
                exchange.sendResponseHeaders(404, 0); // File not found
            }
        } else {
            exchange.sendResponseHeaders(405, 0); // Method Not Allowed
        }
    }
}