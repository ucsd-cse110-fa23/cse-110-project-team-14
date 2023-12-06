package com.pantrypal;

import org.junit.jupiter.api.Test;

import com.pantrypal.model.ImageCreation;
import com.pantrypal.model.Recipe;
import com.pantrypal.model.TextToRecipe;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.net.URISyntaxException;

/**
 * This class tests the functionality of the image generation with DallE using a MockImageCreation class.
 */
public class UserStoryMS2_5Testing {

    /**
     * Test case to verify the functionality of the image generation with DallE with first example
     * @throws URISyntaxException
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testMockImageCreation() throws IOException, InterruptedException, URISyntaxException{
        ImageCreation ic = new MockImageCreation();
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-76wKBb0ThmagtT74FHAlyiUc.png?st=2023-12-04T18%3A26%3A22Z&se=2023-12-04T20%3A26%3A22Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A44%3A01Z&ske=2023-12-04T22%3A44%3A01Z&sks=b&skv=2021-08-06&sig=2zQka8wpL7CtkON1ngUrOFLRyr4EXiaBHzzYidOqFv8%3D";

        assertEquals(expectedURL, ic.generateImageURL("test"));

    }

    /**
     * Test case to verify the functionality of the image generation with DallE with second example
     * @throws URISyntaxException
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testMockImageCreation2() throws IOException, InterruptedException, URISyntaxException{
        ImageCreation ic = new MockImageCreation();
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-AERBuFQ6ZfPy3OAAIJicjJtu.png?st=2023-12-04T18%3A33%3A45Z&se=2023-12-04T20%3A33%3A45Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A48%3A09Z&ske=2023-12-04T22%3A48%3A09Z&sks=b&skv=2021-08-06&sig=frAQK%2BXdCSyxdD90Pi%2BVJsRIQNgDXc0t1TMO69Jnihw%3D";
        assertEquals(expectedURL, ic.generateImageURL("lab"));
    }

    /**
     * Test case to verify the functionality of the image generation with DallE with third example
     * @throws URISyntaxException
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testCreateAccount3() throws IOException, InterruptedException, URISyntaxException{
        ImageCreation ic = new MockImageCreation();
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-yvoR7YVxVaXFlgv1Z8tTOyBz.png?st=2023-12-04T18%3A42%3A33Z&se=2023-12-04T20%3A42%3A33Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A41%3A12Z&ske=2023-12-04T22%3A41%3A12Z&sks=b&skv=2021-08-06&sig=FPN2oEvg3vwX5GuV71M41Lh6A%2Bg84RcCCCr5Fq695GA%3D";
        String prompt = "chilaquiles";
        assertEquals(expectedURL, ic.generateImageURL(prompt));
    }

    @Test
    public void testMockImageInRecipe() throws IOException, InterruptedException, URISyntaxException{
        ImageCreation ic = new MockImageCreation();
        Recipe recipe = new Recipe();
        recipe.setRecipeTitle("Chilaquiles");
        recipe.setRecipeIngredients("test");
        recipe.setRecipeInstructions("test");
        recipe.setMealType("test");
        recipe.setRecipeImageURL(ic.generateImageURL("chilaquiles"));
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-yvoR7YVxVaXFlgv1Z8tTOyBz.png?st=2023-12-04T18%3A42%3A33Z&se=2023-12-04T20%3A42%3A33Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A41%3A12Z&ske=2023-12-04T22%3A41%3A12Z&sks=b&skv=2021-08-06&sig=FPN2oEvg3vwX5GuV71M41Lh6A%2Bg84RcCCCr5Fq695GA%3D";
        assertEquals(expectedURL, recipe.getRecipeImageURL());}

    
    @Test void testMS2_5BDD(){
        MockWhisper whisper = new MockWhisper();
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-yvoR7YVxVaXFlgv1Z8tTOyBz.png?st=2023-12-04T18%3A42%3A33Z&se=2023-12-04T20%3A42%3A33Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A41%3A12Z&ske=2023-12-04T22%3A41%3A12Z&sks=b&skv=2021-08-06&sig=FPN2oEvg3vwX5GuV71M41Lh6A%2Bg84RcCCCr5Fq695GA%3D"; 
        TextToRecipe textToRecipe = new TextToRecipe(whisper.getResponse(6), whisper.getResponse(2), new Recipe(), new MockChatGPT(), new MockImageCreation());
        try {
            textToRecipe.createRecipeObj();
            assertEquals(expectedURL, textToRecipe.getRecipe().getRecipeImageURL());
            assertEquals("Egg Tortilla Breakfast Sandwich", textToRecipe.getRecipe().getRecipeTitle());
            String ingredients = "Ingredients:" +
            "\n- 2 slices of bread\n- 1 egg\n- 1 flour tortilla\n";
            assertEquals(ingredients, textToRecipe.getRecipe().getRecipeIngredients());
            String instructions = "Instructions:\n1. Toast the bread slices and set aside.\n2. Heat a small nonstick skillet over medium heat." +
            "\n3. Add the egg and cook until done, about 3 minutes.\n4. Remove egg from the skillet and set aside." +
            "\n5. Place tortilla in the skillet and warm for about 30 seconds.\n6. Place one of the slices of bread in the skillet and top with the egg." + 
            "\n7. Top the egg with the tortilla and the other slice of bread.\n8. Heat until bread";
            assertEquals(instructions, textToRecipe.getRecipe().getRecipeInstructions()); 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
