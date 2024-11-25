package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.Adapter_f3;
import com.example.myapplication2.Data.Data_Sentence_f3;

import java.util.List;
public class ThirdFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private Adapter_f3 adapter;
    private List<Data_Sentence_f3> itemList;
    private String sentences;
    ImageView createButton;

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
        //recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Adapter_f3.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                sentences = itemList.get(position).getTitle().toString();
                //跳转到详情页
                Intent intent =new Intent(getActivity(), CreateActivity.class);
                //传对象
                intent.putExtra("sentence", sentences);
                intent.putExtra("hasSentence",true);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        createButton = view.findViewById(R.id.button3_1);
        createButton.setOnClickListener(this);
        return view; // 返回视图
    }

    @Override
    public void onClick(View v) {
        //跳转到别的activity
        if(v.getId() == R.id.button3_1) {
            Intent intent = new Intent(getActivity(), CreateActivity.class);
            intent.putExtra("sentence", " ");
            startActivity(intent);
        }
        //else if (v.getId() == )
    }
}