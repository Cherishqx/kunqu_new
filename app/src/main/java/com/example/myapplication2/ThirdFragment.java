package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.Adapter_f3;
import com.example.myapplication2.Data.Data_Sentence_f3;

import java.util.List;
public class ThirdFragment extends Fragment {
    private RecyclerView recyclerView;
    private Adapter_f3 adapter;
    private List<Data_Sentence_f3> itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // 初始化 RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // 设置布局管理器

        // 使用 Data_Sentence_f3 类生成数据
        itemList = Data_Sentence_f3.generateData(20); // 生成 20 条数据

        // 设置适配器
        adapter = new Adapter_f3(itemList);
        recyclerView.setAdapter(adapter);

        return view; // 返回视图
    }

}