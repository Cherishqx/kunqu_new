package com.example.myapplication2.Data;

public class ShowDIY {
    private String imagePath;
    private String text; // 如果你有文本数据与图片一起显示

    public ShowDIY() {
    }

    public ShowDIY(String imagePath, String text) {
        this.imagePath = imagePath;
        this.text = text;
    }

    // Getter 和 Setter
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


