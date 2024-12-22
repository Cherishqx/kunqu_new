package com.example.myapplication2.Data;

public class History {
    private String title;
    private String intro;

    public History(String title, String intro) {
        this.title = title;
        this.intro = intro;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
