package com.example.myapplication2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavoritesManager {
    private static final String PREFS_NAME = "favorites_prefs";
    private static final String FAVORITES_KEY = "favorites";
    private static Set<ImageItem> favoriteItems = new HashSet<>();

    public static void initialize(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(FAVORITES_KEY, null);
        if (json != null) {
            Type type = new TypeToken<Set<ImageItem>>() {}.getType();
            favoriteItems = new Gson().fromJson(json, type);
        }
    }

    public static void addFavorite(Context context, ImageItem item) {
        favoriteItems.add(item);
        saveFavorites(context);
    }

    public static void removeFavorite(Context context, ImageItem item) {
        favoriteItems.remove(item);
        saveFavorites(context);
    }

    public static boolean isFavorite(ImageItem item) {
        return favoriteItems.contains(item);
    }

    public static List<ImageItem> getFavoriteItems() {
        return new ArrayList<>(favoriteItems);
    }

    private static void saveFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = new Gson().toJson(favoriteItems);
        editor.putString(FAVORITES_KEY, json);
        editor.apply();
    }


    // Method to get favorite cards
    public static List<ImageItem> getFavoriteCards() {
        // Return a list of favorite cards
        return new ArrayList<>();
    }

}