package com.pantrypal;

import java.io.*;
import java.net.*;
import org.json.*;

public class MockWhisper {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/audio/transcriptions";
    private static final String TOKEN = "";
    private static final String MODEL = "whisper-1";
    private static final String FILE_PATH = "recording.wav"; // path to audio TODO: 


    public String getResponse(int response){
        if(response == 1){
            return "apple, cinammon, sugar and flour";
        }
        
        return "chicken, rice";
    }
}
