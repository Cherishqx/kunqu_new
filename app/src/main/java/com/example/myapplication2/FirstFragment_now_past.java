package com.example.myapplication2;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewBottom;
    private HistoryAdapter topAdapter;
    private InformationAdapter bottomAdapter;
    private NestedScrollView nestedScrollView;
    private ImageView imageView;
    private ProgressBar progressBar;

    public FirstFragment_now_past() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_now_past, container, false);

        // Initialize SwipeRefreshLayout and other views
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        recyclerViewTop = rootView.findViewById(R.id.recycler_view_top);
        recyclerViewBottom = rootView.findViewById(R.id.recycler_view_bottom);
        nestedScrollView = rootView.findViewById(R.id.nested_scroll_view);
        imageView = rootView.findViewById(R.id.imgcenter);
        progressBar = rootView.findViewById(R.id.progressBar);

        // Set layout managers
        recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBottom.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show progress bar while loading data
        progressBar.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.GONE);

        // Set SwipeRefreshLayout listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Reload data when the user swipes down
                getTopData(rootView,false);
            }
        });

        // Load data initially
        getTopData(rootView,true);

        return rootView;
    }

    private void getTopData(View view,Boolean fresh) {
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

                    //票的数据
                    Request request1 = new Request.Builder()
                            .url(Config.url + "ticketinformation/selectall")
                            .build();
                    Response response1 = client.newCall(request1).execute();
                    String responseBody1 = response1.body().string();
                    Log.e("111", responseBody1);

                    // Parse JSON response using Moshi
                    Moshi moshi1 = new Moshi.Builder().build();
                    JsonAdapter<List<TicketInformation>> jsonAdapter1 = moshi1.adapter(Types.newParameterizedType(List.class, TicketInformation.class));
                    List<TicketInformation> historyList1 = jsonAdapter1.fromJson(responseBody1);
                    Log.e("1111",historyList1.toString());

                    // Update RecyclerView on the main thread
                    if (historyList != null && historyList1 != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Set adapters to RecyclerViews
                                topAdapter = new HistoryAdapter(getContext(), historyList);
                                recyclerViewTop.setAdapter(topAdapter);

                                bottomAdapter = new InformationAdapter(getContext(), historyList1);
                                recyclerViewBottom.setAdapter(bottomAdapter);

                                // Hide ProgressBar and show content
                                progressBar.setVisibility(View.GONE);
                                nestedScrollView.setVisibility(View.VISIBLE);
                                if(fresh){
                                    smoothScrollToImageView();
                                }
                                // Stop the swipe refresh animation
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(view.getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }
        }).start();
    }

    private List<TicketInformation> getBottomData() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        return dbHelper.getTicketAll(); // Replace with actual method
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

