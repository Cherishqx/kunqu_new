package com.example.myapplication2.Data;

public class Sentence {//两个字段：id content
    private Integer Id;
    private String Content;

    public Sentence(Integer id, String content) {
        this.Id = id;
        this.Content = content;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "Id=" + Id +
                ", Content='" + Content + '\'' +
                '}';
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getId() {
        return Id;
    }

    public String getContent() {
        return Content;
    }

}
