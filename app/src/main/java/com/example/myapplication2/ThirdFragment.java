package com.example.myapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.Sentence_f3_Adapter;
import com.example.myapplication2.Data.Sentence;
import java.util.List;

public class ThirdFragment extends Fragment {

    private ImageButton button3_1,button_add_data;
    private RecyclerView recyclerView;
    private DatabaseHelper myDataHelper;
    private Sentence_f3_Adapter sentenceAdapter;
    private List<Sentence> sentenceList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // 初始化视图
        button3_1=view.findViewById(R.id.button3_1);
        button_add_data = view.findViewById(R.id.button_add_data);
        recyclerView = view.findViewById(R.id.recyclerview); // 使用 RecyclerView
        myDataHelper = new DatabaseHelper(getActivity());

        // 设置 RecyclerView 布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 初始化适配器
        sentenceList = myDataHelper.getAll();
        sentenceAdapter = new Sentence_f3_Adapter(getActivity(), sentenceList);
        recyclerView.setAdapter(sentenceAdapter);

        // 设置添加按钮的点击事件
        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 弹出输入框，获取用户输入的内容
                final EditText input = new EditText(getActivity());
                input.setHint("请输入内容");

                // 创建对话框
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("输入内容");
                dialog.setView(input);  // 设置输入框
                dialog.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInput = input.getText().toString();
                        if (!userInput.isEmpty()) {
                            // 获取用户输入的内容，创建 Sentence 对象
                            Sentence sentence = new Sentence(-1, userInput);

                            // 调用数据管理类的 addOne 方法将数据添加到数据库
                            String result = myDataHelper.addOne(sentence);

                            // 显示 Toast 提示
                            Toast.makeText(getActivity(), "添加: " + result, Toast.LENGTH_SHORT).show();

                            // 刷新 RecyclerView
                            sentenceList.clear(); // 清空当前列表
                            sentenceList.addAll(myDataHelper.getAll()); // 获取更新后的数据
                            sentenceAdapter.notifyDataSetChanged(); // 通知适配器刷新
                        } else {
                            Toast.makeText(getActivity(), "请输入内容！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("取消", null);
                // 显示对话框
                dialog.show();
            }
        });

        // 给 button3_1 添加点击事件
        button3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateActivity.class);
                intent.putExtra("sentence", "");
                startActivity(intent);
                Toast.makeText(getActivity(), "button3_1 被点击", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    // 用于刷新 RecyclerView，显示所有数据
    private void viewAll() {
        sentenceList = myDataHelper.getAll();
        sentenceAdapter.notifyDataSetChanged(); // 更新适配器数据
    }
}
