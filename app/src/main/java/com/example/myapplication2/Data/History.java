package com.example.myapplication2.Data;

public class History {
    private String title;
    private String text;

    public History(String title, String intro) {
        this.title = title;
        this.text = intro;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntro(String intro) {
        this.text = intro;
    }
}
