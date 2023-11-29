package com.pantrypal;

import java.util.ArrayList;

/*
 * This class is used to store global variables that are used in the project
 */
public class Globals {
    public static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    public static int recipeIndex = 0;
    enum SortingState {
        NEWTOOLD, OLDTONEW, ATOZ, ZTOA
    }
    public static SortingState sortingState = SortingState.NEWTOOLD;
}
