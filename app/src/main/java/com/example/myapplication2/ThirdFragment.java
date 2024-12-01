package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.Adapter_f3;
import com.example.myapplication2.Data.Data_Sentence_f3;
import com.example.myapplication2.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Adapter_f3 adapter;
    private List<Data_Sentence_f3> itemList;
    private String sentences;
    private DatabaseHelper databaseHelper;
    ImageView createButton;
    ImageView addButton;

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
        addButton = view.findViewById(R.id.button_add_data);
        addButton.setOnClickListener(this); // 设置点击监听器

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
        // 处理添加数据按钮点击事件
        if (v.getId() == R.id.button_add_data) {
            // 创建一个输入框
            final EditText inputField = new EditText(getContext());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("请输入内容")
                    .setView(inputField) // 设置输入框视图
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String content = inputField.getText().toString().trim();
                            if (!content.isEmpty()) {
                                // 调用 insertCollection 插入数据
                                int newId = databaseHelper.insertCollection(content);

                                // 如果插入成功，更新 RecyclerView
                                if (newId != -1) {
                                    // 新插入的数据已经有了 Id，无需重新获取最大 Id
                                    // 更新 itemList
                                    itemList.add(new Data_Sentence_f3(newId, content));

                                    // 通知适配器更新数据
                                    adapter.notifyItemInserted(itemList.size() - 1);  // 只更新插入的项
                                }
                            } else {
                                Toast.makeText(getContext(), "输入内容不能为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }


        // 创建按钮的点击事件
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
