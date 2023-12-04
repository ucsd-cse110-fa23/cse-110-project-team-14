package com.pantrypal.model;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Map;

public class FileUploadHandlerTemp implements HttpHandler {
    private Map<String, byte[]> data;

    public FileUploadHandlerTemp(Map<String, byte[]> data) {
        this.data = data;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            try (InputStream inputStream = exchange.getRequestBody()) {
                // Read the file content
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                // Store the file content as byte array
                byte[] fileContent = byteArrayOutputStream.toByteArray();

                // Store the file content along with the file extension
                String fileId = "recording.wav"; 
                data.put(fileId, fileContent);

                // Send a response
                String response = "{\"status\": \"success\", \"fileId\": \"" + fileId + "\"}";
                exchange.sendResponseHeaders(200, response.length());
                System.out.println("uploaded file: " + fileId);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, 0); // Internal Server Error
            }
        } else {
            exchange.sendResponseHeaders(400, 0); // Bad Request
        }
    }
}
