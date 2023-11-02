package src;
/*
 * Processor class will do the following:
 * - It will take the transcribed file and send it to ChatGPT
 * - Grab response from ChatGpt and parse it
 * - Send the parsed response to the Recipe (with getters and setters)
 */
public class TextToRecipe {
    String ingredients; //Grabbed from second whisper
    String mealType; //Grabbed from first whisper
    Recipe recipe;
    
}
