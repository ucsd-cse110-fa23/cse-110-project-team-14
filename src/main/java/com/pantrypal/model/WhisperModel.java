package com.pantrypal.model;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import com.pantrypal.constants.Constants;
import org.json.*;

public class WhisperModel {
    private static final String API_ENDPOINT = Constants.WHISPER_API_ENDPOINT;
    private static final String TOKEN = Constants.API_KEY;
    private static final String MODEL = Constants.WHISPER_MODEL;
    private static final String FILE_PATH = Constants.FILE_PATH; // path to audio TODO:
    private String currentText;
    private Client model = new Client();

    private static final WhisperModel instance = new WhisperModel();
    public static WhisperModel getInstance() {
        return instance;
    }

    public String getText(){
        return currentText;
    }
    
    public void setText(String s){
        currentText = s;
    }


    private static void writeParameterToOutputStream(
            OutputStream outputStream,
            String parameterName,
            String parameterValue,
            String boundary) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
                ("Content-Disposition: form-data; name=\"" + parameterName + "\"\r\n\r\n").getBytes());
        outputStream.write((parameterValue + "\r\n").getBytes());
    }

    // Helper method to write a file to the output stream in multipart form data
    // format
    private static void writeFileToOutputStream(
            OutputStream outputStream,
            File file,
            String boundary) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
                ("Content-Disposition: form-data; name=\"file\"; filename=\"" +
                        file.getName() +
                        "\"\r\n").getBytes());
        outputStream.write(("Content-Type: audio/mpeg\r\n\r\n").getBytes());

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        fileInputStream.close();
    }

    // Helper method to handle a successful response
    private static String handleSuccessResponse(HttpURLConnection connection)
            throws IOException, JSONException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject responseJson = new JSONObject(response.toString());

        String generatedText = responseJson.getString("text");

        // Print the transcription result
        connection.disconnect();
        return ("Transcription Result: " + generatedText);
    }

    // Helper method to handle an error response
    private static String handleErrorResponse(HttpURLConnection connection)
            throws IOException, JSONException {
        BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(connection.getErrorStream()));
        String errorLine;
        StringBuilder errorResponse = new StringBuilder();
        while ((errorLine = errorReader.readLine()) != null) {
            errorResponse.append(errorLine);
        }
        errorReader.close();
        String errorResult = errorResponse.toString();
        connection.disconnect();
        return ("Error Result: " + errorResult);
    }

    public String getResponse() throws IOException, URISyntaxException {
        // String encodedFileId = URLEncoder.encode("recording.wav", StandardCharsets.UTF_8.toString());
        String downloadResult = model.downloadFile("recording1.wav");
        System.out.println(downloadResult);
        File file = new File(FILE_PATH);

        // Set up HTTP connection
        URL url = new URI(API_ENDPOINT).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);


        // Set up request headers
        String boundary = "Boundary-" + System.currentTimeMillis();
        connection.setRequestProperty(
                "Content-Type",
                "multipart/form-data; boundary=" + boundary
        );
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN);


        // Set up output stream to write request body
        OutputStream outputStream = connection.getOutputStream();


        // Write model parameter to request body
        writeParameterToOutputStream(outputStream, "model", MODEL, boundary);


        // Write file parameter to request body
        writeFileToOutputStream(outputStream, file, boundary);


        // Write closing boundary to request body
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());


        // Flush and close output stream
        outputStream.flush();
        outputStream.close();


        // Get response code
        int responseCode = connection.getResponseCode();


        // Check response code and handle response accordingly
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return handleSuccessResponse(connection);
        }
        return handleErrorResponse(connection);
    }
}
