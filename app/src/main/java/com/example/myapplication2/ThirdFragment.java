package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.Adapter_f3;
import com.example.myapplication2.Data.Data_Sentence_f3; // 导入 Data_Sentence_f3 类
import com.example.myapplication2.DatabaseHelper; // 导入 DatabaseHelper 类

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Adapter_f3 adapter;
    private List<Data_Sentence_f3> itemList; // 数据类型改为 Data_Sentence_f3
    private String sentences;
    private DatabaseHelper databaseHelper; // 引入数据库助手类
    ImageView createButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getContext()); // 初始化 DatabaseHelper
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // 初始化 RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // 设置布局管理器

        // 从数据库中获取数据
        itemList = getCollectionsFromDatabase(); // 获取 collections 表的数据

        // 设置适配器
        adapter = new Adapter_f3(itemList);
        adapter.setOnItemClickListener(new Adapter_f3.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                sentences = itemList.get(position).getContent(); // 获取内容
                // 跳转到详情页
                Intent intent = new Intent(getActivity(), CreateActivity.class);
                // 传递内容
                intent.putExtra("sentence", sentences);
                intent.putExtra("hasSentence", true);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter); // 设置 RecyclerView 的适配器

        createButton = view.findViewById(R.id.button3_1);
        createButton.setOnClickListener(this); // 设置按钮点击监听器
        return view; // 返回视图
    }

    @Override
    public void onClick(View v) {
        // 跳转到 CreateActivity
        if (v.getId() == R.id.button3_1) {
            Intent intent = new Intent(getActivity(), CreateActivity.class);
            intent.putExtra("sentence", "");
            startActivity(intent);
        }
    }

    // 从数据库中获取数据
    private List<Data_Sentence_f3> getCollectionsFromDatabase() {
        List<Data_Sentence_f3> collections = new ArrayList<>();
        // 查询数据库并获取数据
        Cursor cursor = databaseHelper.getAllCollections(); // 使用数据库助手获取所有数据
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int Id = cursor.getInt(cursor.getColumnIndex("Id"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                collections.add(new Data_Sentence_f3(Id, content)); // 添加到列表
            } while (cursor.moveToNext());
        }
        return collections;
    }
}
