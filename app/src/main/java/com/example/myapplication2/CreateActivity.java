package com.example.myapplication2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.ImageAdapter;
import com.example.myapplication2.Adapter.SentenceAdapter;
import com.example.myapplication2.CardData.PasteImages;
import com.example.myapplication2.CardData.Sentences;

import java.util.ArrayList;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {
    RecyclerView editList;
    GridView imageList;
    private ArrayList<Map<String,Object>> listItem;
    private ArrayList<Map<String, Object>> imageItem;
    SentenceAdapter sentenceAdapter;
    ImageAdapter imageAdapter;

    Boolean hasSentence;//是否传入句子
    String value;//句子
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // 获取 Intent 数据
        Intent intent = getIntent();
        hasSentence = intent.getBooleanExtra("hasSentence",false); // 接收整数，默认值为 0
        value = intent.getStringExtra("sentence"); // 接收字符串

        initView();
        initView2();
    }

    public void initView(){
        editList = findViewById(R.id.editList);

        if(hasSentence){
            listItem = new Sentences(value).getListItem();
        }
        else {
            listItem = new Sentences().getListItem();
        }
        //为ListView绑定适配器
        sentenceAdapter = new SentenceAdapter(listItem);
        editList.setLayoutManager(new LinearLayoutManager(this));
        editList.setAdapter(sentenceAdapter);

        int position = listItem.size();
        editList.scrollToPosition(position);
    }

    public void initView2(){
        imageList = findViewById(R.id.imglist);
        PasteImages pasteImages = new PasteImages();
        imageItem = pasteImages.getListItem();
        //为ListView绑定适配器
        imageAdapter = new ImageAdapter(this, imageItem);
        imageList.setAdapter(imageAdapter);
        imageList.setOnItemClickListener((parent, view, position, id) -> {
            pasteImages.updateSelection(position);
            imageAdapter.notifyDataSetChanged();
            Log.e("item",position+" id:"+id);
        });
    }
}