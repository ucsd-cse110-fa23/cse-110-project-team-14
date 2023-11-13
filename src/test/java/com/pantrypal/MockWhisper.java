package com.pantrypal;

import java.io.*;
import java.net.*;
import org.json.*;

public class MockWhisper {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/audio/transcriptions";
    private static final String TOKEN = "";
    private static final String MODEL = "whisper-1";
    private static final String FILE_PATH = "recording.wav"; // path to audio TODO: 

    //TODO: Implement mocking framework
    public String getResponse(int response){
        if (response == 0){
            return "chicken, rice";
        }
        else if(response == 1){
            return "apple, cinammon, sugar and flour";
        }
        else if(response == 2){
            return "breakfast";
        }
        else if(response == 3){
            return "lunch";
        }
        else if(response == 4){
            return "dinner";
        }
        else if(response == 5){
            return "pasta, chicken, tomatoes";
        }
        else if(response == 6){
            return "bread, egg, tortilla";
        }
        
        return "chicken, rice";
    }
}
