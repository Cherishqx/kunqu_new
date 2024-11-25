package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.SentenceAdapter;
import com.example.myapplication2.CardData.Sentences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {
    ListView imageList;
    RecyclerView editList;
    private ArrayList<Map<String,Object>> listItem;
    SentenceAdapter sentenceAdapter;

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

    private SentenceAdapter getSentenceAdapter() {
        return sentenceAdapter;
    }
}