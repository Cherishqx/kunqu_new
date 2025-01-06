package com.example.myapplication2;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication2.Data.History;
import com.example.myapplication2.Adapter.HistoryAdapter;
import com.example.myapplication2.Adapter.InformationAdapter;
import com.example.myapplication2.Data.HistoryJson;
import com.example.myapplication2.Data.TicketInformation;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        getTopData(rootView);
        // 获取数据
        //List<History> topData = getTopData(rootView);
        List<TicketInformation> bottomData = getBottomData();

        // 创建适配器
        //topAdapter = new HistoryAdapter(getContext(), topData);
        bottomAdapter = new InformationAdapter(getContext(), bottomData);

        // 设置适配器
        //recyclerViewTop.setAdapter(topAdapter);
        recyclerViewBottom.setAdapter(bottomAdapter);

//        // 在视图加载完成后，滚动到ImageView位置
//        nestedScrollView.post(new Runnable() {
//            @Override
//            public void run() {
//                // 获取ImageView在NestedScrollView中的位置
//                int[] location = new int[2];
//                imageView.getLocationInWindow(location);
//                int y = location[1]-270;
//                // 使用smoothScrollTo平滑滚动到ImageView的位置
//                nestedScrollView.smoothScrollTo(0, y);
//            }
//        });

        return rootView;
    }

    // 获取上方 RecyclerView 的数据
    private void getTopData(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(Config.url + "selectall")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseBody = response.body().string();
                    Log.e("111", responseBody);

                    // Parse JSON response using Moshi
                    Moshi moshi = new Moshi.Builder().build();
                    JsonAdapter<List<History>> jsonAdapter = moshi.adapter(Types.newParameterizedType(List.class, History.class));

                    List<History> historyList = jsonAdapter.fromJson(responseBody);

                    // Update RecyclerView on the main thread
                    if (historyList != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Initialize the top adapter and set it to the RecyclerView
                                topAdapter = new HistoryAdapter(getContext(), historyList);
                                recyclerViewTop.setAdapter(topAdapter);
                                smoothScrollToImageView();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(view.getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    // 获取下方 RecyclerView 的数据
    private List<TicketInformation> getBottomData() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        return dbHelper.getTicketAll(); // 替换为实际获取数据的方法
    }

    private void smoothScrollToImageView() {
        nestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                imageView.getLocationInWindow(location);
                int y = location[1] - 270;
                nestedScrollView.smoothScrollTo(0, y);
            }
        });
    }
}
