package com.example.myapplication2;

public class DetailItem {
    private final String text;
    private final float textSize;

    public DetailItem(String text, float textSize) {
        this.text = text;
        this.textSize = textSize;
    }

    public String getText() {
        return text;
    }

    public float getTextSize() {
        return textSize;
    }
}

