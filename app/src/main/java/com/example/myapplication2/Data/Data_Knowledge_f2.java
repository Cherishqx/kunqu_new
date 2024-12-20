package com.example.myapplication2.Data;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.List;

public class Data_Knowledge_f2 {
    private String typeId; // 类型ID
    private String title;   // 标题
    private String intro;   // 标题
    private List<Integer> imageResources; // 存储每个类别下的图片资源

    // 构造函数
    public Data_Knowledge_f2(String typeId, String title, String intro,List<Integer> imageResources) {
        this.typeId = typeId;
        this.title = title;
        this.intro=intro;
        this.imageResources = imageResources;
    }

    // Getter 方法
    public String getTypeId() {
        return typeId;
    }

    public String getTitle() {
        return title;
    }
    public String getIntro() {
        return intro;
    }

    public List<Integer> getImageResources() {
        return imageResources;
    }

    // 用于生成数据的方法
    public static List<Data_Knowledge_f2> generateData() {
        List<Data_Knowledge_f2> itemList = new ArrayList<>();

        // 为每个类别设置对应的图片资源
        itemList.add(new Data_Knowledge_f2("1", "语音", "唱念语音一般为苏州吴语或者有入声的中州韵，一些丑角的念白亦使用有翘舌音的苏白。",new ArrayList<Integer>() {{
            add(R.drawable.pic21);
            add(R.drawable.pic22);
        }}));
        itemList.add(new Data_Knowledge_f2("2", "声音行腔","指演员以特定的韵律、节奏和语调说出台词，来表达人物的内心感受和情绪变化", new ArrayList<Integer>() {{
            add(R.drawable.pic31);
            add(R.drawable.pic32);
            add(R.drawable.pic33png);
        }}));
        itemList.add(new Data_Knowledge_f2("3", "角色行当","昆剧行腔优美，以缠绵婉转、柔漫悠远见长。在演唱技巧上注重声音的控制，节奏速度的顿挫疾徐和咬字吐音的讲究，场面伴奏乐曲齐全。", new ArrayList<Integer>() {{
            add(R.drawable.pic41);
            add(R.drawable.pic42);
            add(R.drawable.pic43);
            add(R.drawable.pic44);
            add(R.drawable.pic45);
        }}));
        itemList.add(new Data_Knowledge_f2("4", "度曲八法","语出清·王德晖、徐沅澄《顾误录》，指唱曲的八点要领。", new ArrayList<Integer>() {{
            add(R.drawable.pic51);
            add(R.drawable.pic52);
        }}));
        itemList.add(new Data_Knowledge_f2("5", "学曲六戒","", new ArrayList<Integer>() {{
            add(R.drawable.pic3);
            add(R.drawable.pic3);
            add(R.drawable.pic3);
            add(R.drawable.pic3);
            add(R.drawable.pic3);
        }}));

        // 添加更多的数据项

        return itemList;
    }

}
