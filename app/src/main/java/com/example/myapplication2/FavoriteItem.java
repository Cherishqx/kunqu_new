package com.example.myapplication2;

public class FavoriteItem {
    private String title;
    private int imageResId;
    private int iconResId;

    public FavoriteItem(String title, int imageResId, int iconResId) {
        this.title = title;
        this.imageResId = imageResId;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getIconResId() {
        return iconResId;
    }
}