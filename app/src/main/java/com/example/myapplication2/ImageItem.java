package com.example.myapplication2;

public class ImageItem {
    private int imageResId; // 图像资源ID
    private String title; // 图像标题
    private String imageName; // 图片名称
    private boolean isFavorite; // 新增属性，用于标记是否收藏

    // 构造函数
    public ImageItem(int imageResId, String title, String subTitle, String imageName) {
        this.imageResId = imageResId;
        this.title = title;
        this.imageName = imageName;
        this.isFavorite = false; // 默认未收藏
    }

    // Getter方法
    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }


    public String getImageName() { return imageName; }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}


