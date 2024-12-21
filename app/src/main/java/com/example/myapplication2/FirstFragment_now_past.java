package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.History;
import com.example.myapplication2.Adapter.HistoryAdapter;
import com.example.myapplication2.Adapter.InformationAdapter;
import com.example.myapplication2.Adapter.TicketInformation;

import java.util.List;

public class FirstFragment_now_past extends Fragment {

    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewBottom;
    private HistoryAdapter topAdapter;
    private InformationAdapter bottomAdapter;
    private NestedScrollView nestedScrollView;
    private ImageView imageView;

    public FirstFragment_now_past() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_now_past, container, false);
        // 初始化RecyclerView和NestedScrollView
        recyclerViewTop = rootView.findViewById(R.id.recycler_view_top);
        recyclerViewBottom = rootView.findViewById(R.id.recycler_view_bottom);
        nestedScrollView = rootView.findViewById(R.id.nested_scroll_view);
        imageView = rootView.findViewById(R.id.imgcenter);

        // 设置布局管理器，LinearLayoutManager 用于垂直滚动
        recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBottom.setLayoutManager(new LinearLayoutManager(getContext()));

        // 获取数据
        List<History> topData = getTopData();
        List<TicketInformation> bottomData = getBottomData();

        // 创建两个适配器，分别用于不同的数据
        topAdapter = new HistoryAdapter(getContext(), topData);
        bottomAdapter = new InformationAdapter(getContext(), bottomData);

        // 设置适配器
        recyclerViewTop.setAdapter(topAdapter);
        recyclerViewBottom.setAdapter(bottomAdapter);

        // 在视图加载完成后，滚动到ImageView位置
        nestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                // 获取ImageView在NestedScrollView中的位置
                int[] location = new int[2];
                imageView.getLocationInWindow(location);
                int y = location[1]-240;
                // 使用smoothScrollTo平滑滚动到ImageView的位置
                nestedScrollView.smoothScrollTo(0, y);
            }
        });

        return rootView;
    }

    // 获取上方 RecyclerView 的数据
    private List<History> getTopData() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        return dbHelper.getHistoryAll(); // 替换为实际获取数据的方法
    }

    // 获取下方 RecyclerView 的数据
    private List<TicketInformation> getBottomData() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        return dbHelper.getTicketAll(); // 替换为实际获取数据的方法
    }
}
