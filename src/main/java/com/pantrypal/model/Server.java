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

/**
 * The Server class handles the setup and initiation of an HTTP server using
 * Java's HttpServer class.
 */
public class Server {
    // Constants defining server properties
    private static final int SERVER_PORT = 8100;
    private static final String SERVER_HOSTNAME = "0.0.0.0";
    private static boolean isServerRunning = false;

    /**
     * The main method that initiates the HTTP server and starts it on a defined
     * port.
     * 
     * @param args Command-line arguments (not used in this implementation)
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {
        // Creating a thread pool to manage incoming HTTP requests
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        // Creating an instance of HttpServer and binding it to a specific hostname and
        // port
        HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), 0);

        // Mapping specific contexts to their respective handlers
        server.createContext("/share", new URLShareHandler()); // Handler for "/share" context
        server.createContext("/status", new StatusHandler()); // Handler for "/status" context

        // Setting the executor for the server to manage incoming requests using the
        // created thread pool
        server.setExecutor(threadPoolExecutor);

        // Starting the server
        server.start();
        isServerRunning = true;

        // Printing a message indicating that the server has started
        System.out.println("Server started on port " + SERVER_PORT);
    }

    /**
     * Method to check if the server is running.s
     * 
     * @return true if the server is running, false otherwise
     */
    public static boolean isServerRunning() {
        return isServerRunning;
    }
}