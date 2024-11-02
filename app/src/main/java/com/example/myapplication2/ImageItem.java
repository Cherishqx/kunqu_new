package com.example.myapplication2;

public class ImageItem {
    private int imageResId; // 图像资源ID
    private String title; // 图像标题
    private String subTitle; // 图像副标题

    // 构造函数
    public ImageItem(int imageResId, String title, String subTitle) {
        this.imageResId = imageResId;
        this.title = title;
        this.subTitle = subTitle;
    }

    // Getter方法
    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
