package com.pantrypal.model;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    private static final int SERVER_PORT = 8100;
    private static final String SERVER_HOSTNAME = "localhost";

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        Map<String, byte[]> data = new HashMap<>();

        HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), 0);

        // Context for handling file upload
        server.createContext("/upload", new FileUploadHandlerTemp(data));

        // Context for handling file download
        server.createContext("/download", new FileDownloadHandler(data));
        server.createContext("/share", new URLShareHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
    

        System.out.println("Server started on port " + SERVER_PORT);
        
    }
    

    

}
