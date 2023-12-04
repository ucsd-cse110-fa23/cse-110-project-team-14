package com.pantrypal.model;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Map;

public class FileUploadHandler implements HttpHandler {
    private Map<String, byte[]> data;

    public FileUploadHandler(Map<String, byte[]> data) {
        this.data = data;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String CRLF = "\r\n";
        int fileSize = 0;
        
        File f = new File("recording1.wav");
        if (!f.exists()) {
            f.createNewFile();
        }
        
        InputStream input = exchange.getRequestBody();
        String nextLine = "";
        do {
            nextLine = readLine(input, CRLF);
            if (nextLine.startsWith("Content-Length:")) {
                fileSize = 
                    Integer.parseInt(
                        nextLine.replaceAll(" ", "").substring(
                            "Content-Length:".length()
                        )
                    );
            }
            System.out.println(nextLine);
        } while (!nextLine.equals(""));
        
        byte[] midFileByteArray = new byte[fileSize];
        int readOffset = 0;
        while (readOffset < fileSize) {
            int bytesRead = input.read(midFileByteArray, readOffset, fileSize);
            readOffset += bytesRead;
        }
        
        BufferedOutputStream bos = 
            new BufferedOutputStream(new FileOutputStream("recording1.wav"));
        
        bos.write(midFileByteArray, 0, fileSize);
        bos.flush();
        
        exchange.sendResponseHeaders(200, 0);
}

private static String readLine(InputStream is, String lineSeparator) 
    throws IOException {

    int off = 0, i = 0;
    byte[] separator = lineSeparator.getBytes("UTF-8");
    byte[] lineBytes = new byte[1024];
    
    while (is.available() > 0) {
        int nextByte = is.read();
        if (nextByte < -1) {
            throw new IOException(
                "Reached end of stream while reading the current line!");
        }
        
        lineBytes[i] = (byte) nextByte;
        if (lineBytes[i++] == separator[off++]) {
            if (off == separator.length) {
                return new String(
                    lineBytes, 0, i-separator.length, "UTF-8");
            }
        }
        else {
            off = 0;
        }
        
        if (i == lineBytes.length) {
            throw new IOException("Maximum line length exceeded: " + i);
        }
    }
    
    throw new IOException(
        "Reached end of stream while reading the current line!");   
    }
}
