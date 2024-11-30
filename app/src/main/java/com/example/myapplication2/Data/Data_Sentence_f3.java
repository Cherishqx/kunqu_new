package com.example.myapplication2.Data;

import java.util.ArrayList;
import java.util.List;

public class Data_Sentence_f3 {

    private int Id;
    private String content;

    public Data_Sentence_f3(int Id,String content) {
        this.Id= Id;
        this.content=content;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}