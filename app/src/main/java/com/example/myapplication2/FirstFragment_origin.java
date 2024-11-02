package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment_origin extends Fragment {

    private Banner banner;
    private List<BannerDataInfo> mBannerDataInfos = new ArrayList<>();
    final String TAG = FirstFragment_origin.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_origin, container, false);
        //初始化控件
        banner = view.findViewById(R.id.banner);

        //模拟数据
        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.banner_1, "first"));
        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.banner_2, "second"));
        mBannerDataInfos.add(new BannerDataInfo(R.mipmap.banner_3, "third"));

        //设置adapter
        banner.setAdapter(new BannerImageAdapter<BannerDataInfo>(mBannerDataInfos) {

                    @Override
                    public void onBindView(BannerImageHolder holder, BannerDataInfo data, int position, int size) {
                        // 设置数据
                        holder.imageView.setImageResource(data.getImg());
                    }
                })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()));

        TextView tv = view.findViewById(R.id.tv_music_library);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MusicLibraryActivity.class);
                startActivity(intent);
            }
        });
        

        return view;
    }

}
