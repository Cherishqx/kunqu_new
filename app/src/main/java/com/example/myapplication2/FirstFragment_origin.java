package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout llRecommend;
    final String TAG = FirstFragment_origin.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_origin, container, false);
        //初始化控件
        banner = view.findViewById(R.id.banner);
        llRecommend = view.findViewById(R.id.ll_recommend);

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

        // 获取5个随机推荐项
        List<ImageItem> recommendItems = com.example.myapplication2.DataProvider.getRandomItems(5);
        setRecommendItems(recommendItems, view);

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

    private void setRecommendItems(List<ImageItem> recommendItems, View view) {
        int[] imageViewIds = {
                R.id.recommend_1,
                R.id.recommend_2,
                R.id.recommend_3,
                R.id.recommend_4,
                R.id.recommend_5
        };

        for (int i = 0; i < recommendItems.size(); i++) {
            ImageItem item = recommendItems.get(i);
            ImageView imageView = view.findViewById(imageViewIds[i]);
            imageView.setImageResource(item.getImageResId());

            // Set layout parameters to match the static ImageView
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.image_width), // width in dp
                    (int) getResources().getDimension(R.dimen.image_height) // height in dp
            );
            layoutParams.setMargins(
                    (int) getResources().getDimension(R.dimen.image_margin_left),
                    0,
                    0,
                    0
            );
            imageView.setLayoutParams(layoutParams);
            imageView.setClipToOutline(true);
            imageView.setBackgroundResource(R.drawable.mine_rounded_corners);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // Ensure the image fills the ImageView
            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("imageName", item.getImageName());
                startActivity(intent);
            });
        }
    }
}