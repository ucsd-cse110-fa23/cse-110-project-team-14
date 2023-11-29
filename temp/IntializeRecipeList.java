package com.pantrypal;

import java.io.*;
import java.util.Scanner;

public class IntializeRecipeList {

    public static void uploadRecipes() {
        try {
            File contactsFile = new File("recipes.txt");
            Scanner fr = new Scanner(contactsFile);
            while(fr.hasNextLine()) {
                String line = fr.nextLine();
                String[] parts = line.split(";");
                String name = parts[0];
                String ingredients = parts[1];
                String instructions = parts[2];
                System.out.println(name + ";" + ingredients + ";" + instructions);
                Recipe recipe = new Recipe();
                recipe.setRecipeTitle(name);
                recipe.setRecipeIngredients(ingredients);
                recipe.setRecipeInstructions(instructions);
                RecipeTitleView recipeTitleView = new RecipeTitleView(recipe);
                SeeRecipePage SRP = new SeeRecipePage(constants.width, constants.height);
                SRP.setRecipe(recipe);
                recipeTitleView.getRecipeTitleButton().setOnAction(e1 -> {
                    StageController stg = StageController.getInstance();
                    stg.registerPage(recipe.getRecipeTitle(), SRP);
                    stg.changeTo(recipe.getRecipeTitle());
                });
                RecipeTitleListView.getInstance().getChildren().add(recipeTitleView);
            }
            fr.close();
        }
        catch(IOException e) {
            System.out.println("Could not open recipes file");
        }
    }

    public static void saveRecipe(Recipe recipe) {
        try {
            File recipeFile = new File("recipes.txt");
            FileWriter fw = new FileWriter(recipeFile);
            fw.write(recipe.getRecipeTitle() + ";");
            fw.write(recipe.getRecipeIngredients() + ";");
            fw.write(recipe.getRecipeInstructions() + "\n");
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Could save recipe");
        }
    }
}
