package com.pantrypal.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bson.json.JsonObject;

public class Client {
    public static void sendPOST() throws IOException {
        final String POST_URL = "http://localhost:8100/upload";
        final File uploadFile = new File("recording.wav");
    
        String boundary = Long.toHexString(System.currentTimeMillis());
        String CRLF = "\r\n";
        String charset = "UTF-8";
        HttpURLConnection connection = (HttpURLConnection) new URL(POST_URL).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    
        try (
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
        ) {
            // Send the multipart/form-data header
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + uploadFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(uploadFile.getName())).append(CRLF);
            writer.append(CRLF).flush();
    
            // Send the file content
            Files.copy(uploadFile.toPath(), output);
            output.flush();
    
            // Send the closing boundary
            writer.append(CRLF).append("--" + boundary + "--").append(CRLF).flush();
    
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: [" + responseCode + "]");
        }
    }

    // private static void sendPOST() throws IOException{
    //     final int mid = 1;
    //     final String POST_URL = "http://localhost:8100/download?mid="+mid;
    //     final File uploadFile = new File("recording.wav");

    //     String boundary = Long.toHexString(System.currentTimeMillis()); 
    //     String CRLF = "\r\n";
    //     String charset = "UTF-8";
    //     URLConnection connection = new URL(POST_URL).openConnection();
    //     connection.setDoOutput(true);
    //     connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        
    //     try (
    //         OutputStream output = connection.getOutputStream();
    //         PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
    //     ) {
    //         writer.append("--" + boundary).append(CRLF);
    //         writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + uploadFile.getName() + "\"").append(CRLF);
    //         writer.append("Content-Length: " + uploadFile.length()).append(CRLF);
    //         writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(uploadFile.getName())).append(CRLF);
    //         writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    //         writer.append(CRLF).flush();
    //         Files.copy(uploadFile.toPath(), output);
    //         output.flush();

    //         int responseCode = ((HttpURLConnection) connection).getResponseCode();
    //         System.out.println("Response code: [" + responseCode + "]");
    //     }
    // }

    public String downloadFile(String localFilePath) {
    try {
        // Use the provided serverUrl, append the file ID to the URL
        URL url = new URL("http://localhost:8100/download/recording.wav");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = conn.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(localFilePath)) {

                // Use Java NIO to efficiently copy the InputStream to the local file
                Files.copy(inputStream, Paths.get(localFilePath), StandardCopyOption.REPLACE_EXISTING);

                return "File downloaded successfully. Saved as: " + localFilePath;
            }
        } else {
            return "Error downloading file. Response code: " + responseCode;
        }
    } catch (IOException e) {
        e.printStackTrace();
        return "Error: " + e.getMessage();
    }
}
}
