package com.example.myapplication2;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Card_FavoritesManager {
    private static final String PREFS_NAME = "card_favorites_prefs";
    private static final String FAVORITES_KEY = "card_favorites";
    private static final String TITLES_KEY = "card_titles";
    private static Set<String> favoriteCards = new HashSet<>();
    private static Map<String, String> cardTitles = new HashMap<>();

    public static void initialize(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        favoriteCards = prefs.getStringSet(FAVORITES_KEY, new HashSet<>());
        cardTitles = new HashMap<>();
        for (String typeId : favoriteCards) {
            cardTitles.put(typeId, prefs.getString(TITLES_KEY + "_" + typeId, ""));
        }
    }

    public static void addFavorite(Context context, String typeId, String title) {
        favoriteCards.add(typeId);
        cardTitles.put(typeId, title);
        saveFavorites(context);
    }

    public static void removeFavorite(Context context, String typeId) {
        favoriteCards.remove(typeId);
        cardTitles.remove(typeId);
        saveFavorites(context);
    }

    public static boolean isFavorite(String typeId) {
        return favoriteCards.contains(typeId);
    }

    public static Set<String> getFavoriteCards() {
        return new HashSet<>(favoriteCards);
    }

    public static String getTitle(String typeId) {
        return cardTitles.get(typeId);
    }

    private static void saveFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(FAVORITES_KEY, favoriteCards);
        for (Map.Entry<String, String> entry : cardTitles.entrySet()) {
            editor.putString(TITLES_KEY + "_" + entry.getKey(), entry.getValue());
        }
        editor.apply();
    }
}