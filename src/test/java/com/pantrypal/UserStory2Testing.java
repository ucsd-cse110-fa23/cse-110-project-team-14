package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.net.URISyntaxException;
public class UserStory2Testing {
    @Test
    public void testUserStory()  {
        Recipe r = new Recipe();
        MockTextToRecipe textToRecipe = new MockTextToRecipe("chicken, rice", "dinner", r);
        try {
            textToRecipe.createRecipeObj();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        

    }
}
