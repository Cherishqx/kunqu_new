package com.example.myapplication2.Data;

import java.util.ArrayList;
import java.util.List;

public class Data_Sentence_f3 {
    private String title;

    public Data_Sentence_f3(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // 静态方法生成数据列表
    public static List<Data_Sentence_f3> generateData(int count) {
        List<Data_Sentence_f3> itemList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            itemList.add(new Data_Sentence_f3("这是收藏的第 " + (i + 1)+" \n段文本")); // 示例数据
        }
        return itemList;
    }
}
