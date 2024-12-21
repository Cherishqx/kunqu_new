package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.example.myapplication2.Data.Data_Knowledge_f2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FirstFragment_origin extends Fragment {

    private Banner banner;
    private List<BannerDataInfo> mBannerDataInfos = new ArrayList<>();
    private LinearLayout llRecommend;
    private LinearLayout llKnowledgeCards;
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
        llKnowledgeCards = view.findViewById(R.id.ll_knowledge_cards);

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

        // 获取5个随机知识卡片
        List<Data_Knowledge_f2> knowledgeItems = Data_Knowledge_f2.generateData();
        Collections.shuffle(knowledgeItems); // 随机打乱列表
        setKnowledgeItems(knowledgeItems.subList(0, 4), view); // 只取前5个

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
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                    (int) getResources().getDimension(R.dimen.image_width), // width in dp
//                    (int) getResources().getDimension(R.dimen.image_height) // height in dp
//            );
//            layoutParams.setMargins(
//                    (int) getResources().getDimension(R.dimen.image_margin_right),
//                    0,
//                    0,
//                    0
//            );
//            imageView.setLayoutParams(layoutParams);
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

    private void setKnowledgeItems(List<Data_Knowledge_f2> knowledgeItems, View view) {
        // 随机打乱列表
        Collections.shuffle(knowledgeItems);

        // 只取前5个知识卡片
        List<Data_Knowledge_f2> randomKnowledgeItems = knowledgeItems.subList(0, 4);

        for (int i = 0; i < randomKnowledgeItems.size(); i++) {
            Data_Knowledge_f2 item = randomKnowledgeItems.get(i);
            List<Integer> imageResources = item.getImageResources();

            // 为每个知识卡片创建一个ImageButton，并随机选择一个图片资源
            if (!imageResources.isEmpty()) {
                ImageButton imageButton = new ImageButton(view.getContext());
                int sizeInPx = (int) (120 * view.getContext().getResources().getDisplayMetrics().density);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                imageButton.setPadding(0, 0, 0, 0);
                params.setMargins(0, 20, 20, 20);
                imageButton.setLayoutParams(params);
                imageButton.setScaleType(ImageView.ScaleType.FIT_XY);

                // 随机选择一个图片资源
                int randomIndex = new Random().nextInt(imageResources.size());
                imageButton.setImageResource(imageResources.get(randomIndex));
                llKnowledgeCards.addView(imageButton);

                final int index = randomIndex;
                imageButton.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), KnowledgeActivity.class);
                    intent.putExtra("type", item.getTypeId() + index);
                    startActivity(intent);
                });
            }
        }
    }
}