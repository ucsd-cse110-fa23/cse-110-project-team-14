package com.pantrypal;

import java.util.ArrayList;

/*
 * This class is used to store global variables that are used in the project
 */
public class Globals {
    public static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    public static ArrayList<Recipe> recipesWithFilter = new ArrayList<Recipe>();
    public static int recipeIndex = 0;
    enum SortingState {
        NEWTOOLD, OLDTONEW, ATOZ, ZTOA
    }
    enum FilterType {
        NONE, BREAKFAST, LUNCH, DINNER
    }
    public static FilterType filterType = FilterType.NONE;
    public static SortingState sortingState = SortingState.NEWTOOLD;
    public static String username = "recipes";
}
