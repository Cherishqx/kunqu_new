package com.example.myapplication2.Data;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.List;

public class Data_Knowledge_f2 {
    private String typeId; // 类型ID
    private String title;   // 标题
    private int imageId;  // 图片名称

    // 构造函数
    public Data_Knowledge_f2(String typeId, String title , int imageId) {
        this.typeId = typeId;
        this.title = title;
        this.imageId= imageId;
    }

    // Getter 方法
    public String getTypeId() {
        return typeId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

public static List<Data_Knowledge_f2> generateData() {
    List<Data_Knowledge_f2> itemList = new ArrayList<>();

    // 将提供的数据添加到 List 中
    itemList.add(new Data_Knowledge_f2("1", "语音", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("2", "声音行腔", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("3", "角色行当", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("4", "度曲八法", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("5", "学曲六戒", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("6", "度曲十病", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("7", "五难", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("8", "三绝", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("9", "两不杂", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("10", "嘴劲", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("11", "喷口", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("12", "气口", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("13", "换气", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("14", "偷气", R.drawable.pic2));
    itemList.add(new Data_Knowledge_f2("15", "膛音", R.drawable.pic2));
    return itemList; // 返回填充好的列表
}
}