package com.pantrypal;

import java.util.ArrayList;

import com.pantrypal.model.Recipe;

/*
 * This class is used to store global variables that are used in the project
 */
public class Globals {
    public static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    public static ArrayList<Recipe> recipesWithFilter = new ArrayList<Recipe>();
    public static int recipeIndex = 0;
    public enum SortingState {
        NEWTOOLD, OLDTONEW, ATOZ, ZTOA
    }
    public enum FilterType {
        NONE, BREAKFAST, LUNCH, DINNER
    }
    public static FilterType filterType = FilterType.NONE;
    public static SortingState sortingState = SortingState.NEWTOOLD;
    public static String username = "recipes";
    public static double appWidth = 600;
    public static double appHeight = 600;
    public static String ip = "192.168.1.69"; //marcos ip address so server works on other device
}
