package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.myapplication2.Adapter.Adapter_f2;
import com.example.myapplication2.Data.Data_Knowledge_f2;
import java.util.List;

public class SecondFragment extends Fragment {

    private GridView gridView;
    private Adapter_f2 adapter;
    private List<Data_Knowledge_f2> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflater加载Fragment布局
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // 初始化 GridView
        gridView = view.findViewById(R.id.f2_GridView);

        // 使用 Data_Knowledge_f2 生成数据
        itemList = Data_Knowledge_f2.generateData();

        // 设置适配器
        adapter = new Adapter_f2(getContext(), itemList);
        gridView.setAdapter(adapter);

        // 设置点击事件监听器
        adapter.setOnItemClickListener(new Adapter_f2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // 处理点击事件，可以弹出 Toast 或做其他处理
                Data_Knowledge_f2 item = itemList.get(position);
                Toast.makeText(getContext(), "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                // 创建 Intent 跳转到 knowledge_activity
                Intent intent = new Intent(getContext(), KnowledgeActivity.class);

                // 将 typeId 作为额外数据传递
                intent.putExtra("typeId", item.getTypeId());

                // 启动目标活动
                startActivity(intent);
            }
        });

        return view;
    }
}
