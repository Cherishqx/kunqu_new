package com.example.myapplication2.Data;

import com.squareup.moshi.Json;

public class HistoryJson {
    @Json(name = "id")
    public int id;

    @Json(name = "title")
    public String title;

    @Json(name = "text")
    public String text;

    // 构造函数
    public HistoryJson(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }
}
