package src;
import java.util.List;

/*
 * Recipe class to store recipe information
 * This is what is shown once you clicked on the recipe list
 */
public class Recipe {
    private String recipeTitle;
    private List<String> recipeDetails; // Take the JSON response object from ChatGPT and put instructions into list (TODO Later)
    private String mealType;

    Recipe() {
    }
    
    void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    String getRecipeTitle() {
        return recipeTitle;
    }
    
    void setRecipeDetails(List<String> recipeDetails) {
        this.recipeDetails = recipeDetails;
    }

    List<String> getRecipeDetails() {
        return recipeDetails;
    }

    void setMealType(String mealType) {
        this.mealType = mealType;
    }

    String getMealType() {
        return mealType;
    }

    //TODO: Once we have the JSON response object from ChatGPT, we can parse it and put the instructions into a list
}
