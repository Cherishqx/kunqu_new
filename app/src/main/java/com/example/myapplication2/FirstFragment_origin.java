package com.example.myapplication2;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        // 获取3个随机推荐项
        List<ImageItem> recommendItems = com.example.myapplication2.DataProvider.getRandomItems(3);
        setRecommendItems(recommendItems, view);

        // 获取5个随机知识卡片
        List<Data_Knowledge_f2> knowledgeItems = Data_Knowledge_f2.generateData();
        Collections.shuffle(knowledgeItems); // 随机打乱列表
        setKnowledgeItems(knowledgeItems.subList(0, 4), view); // 只取前5个



        //设置曲库跳转
        ImageButton musicLibraryButton = view.findViewById(R.id.tv_music_library);
        TextView musicLibraryText = view.findViewById(R.id.music_library_text);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 执行相应的动作
                Intent intent = new Intent(getActivity(), MusicLibraryActivity.class);
                startActivity(intent);
            }
        };

        musicLibraryButton.setOnClickListener(listener);
        musicLibraryText.setOnClickListener(listener);


        //fragment跳转
//        ImageButton knowledge = view.findViewById(R.id.knowledge);
//        knowledge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 调用现有的 selectedFragment 方法，并传递相应的 position
//                selectedFragment(0); // 假设 0 是 KnowledgeFragment 对应的 position
//            }
//        });
//
//        ImageButton nowpast = view.findViewById(R.id.nowpast);
//        nowpast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 调用现有的 selectedFragment 方法，并传递相应的 position
//                selectedFragment(1); // 假设 1 是 NowPastFragment 对应的 position
//            }
//        });

        ImageButton nowpast = view.findViewById(R.id.nowpast);
        nowpast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个Intent，指定从当前Activity跳转到TargetActivity
                Intent intent = new Intent(getActivity(), NowPastActivity.class);

                // 启动目标Activity
                startActivity(intent);
            }
        });

        ImageButton knowledgeButton = view.findViewById(R.id.knowledge);
        knowledgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个Intent，指定从当前Activity跳转到TargetActivity
                Intent intent = new Intent(getActivity(), KnowledgeButtonActivity.class);

                // 启动目标Activity
                startActivity(intent);
            }
        });

        return view;
    }

    private void setRecommendItems(List<ImageItem> recommendItems, View parentView) {
        // 获取推荐项的父布局
        LinearLayout llRecommend = parentView.findViewById(R.id.ll_recommend);

        // 遍历推荐项列表
        for (ImageItem item : recommendItems) {
            // 创建一个水平布局
            LinearLayout container = new LinearLayout(parentView.getContext());
            container.setOrientation(LinearLayout.HORIZONTAL); // 水平布局
            container.setPadding(0, 16, 0, 16); // 设置内边距
//            container.setBackgroundResource(R.drawable.textview_border);
            container.setClipToOutline(true); // 圆角裁剪
            LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, // 宽度填充父布局
                    ViewGroup.LayoutParams.WRAP_CONTENT  // 高度自适应
            );
            containerParams.setMargins(0, 0, 0, 16); // 设置外边距
            container.setLayoutParams(containerParams);

            // 动态创建 ImageView
            ImageView imageView = new ImageView(parentView.getContext());
            imageView.setImageResource(item.getImageResId());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // 设置为 FIT_XY 进行缩放
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    (int) parentView.getResources().getDimension(R.dimen.image_width), // 90dp
                    (int) parentView.getResources().getDimension(R.dimen.image_height) // 90dp
            );
            imageParams.setMargins(0, 0, 16, 0); // 图片右侧设置外边距
            imageView.setLayoutParams(imageParams);
            imageView.setClipToOutline(true); // 圆角裁剪
            imageView.setBackgroundResource(R.drawable.mine_rounded_corners); // 设置背景

            // 动态创建 TextView
            TextView textView = new TextView(parentView.getContext());
            textView.setText(item.getTitle()); // 显示图片名称
            textView.setTextSize(18); // 设置文字大小
            textView.setTextColor(parentView.getResources().getColor(R.color.black)); // 设置文字颜色
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    0, // 使用权重分配宽度
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f // 权重
            );
            textParams.setMargins(16, 0, 16, 0); // 文字右侧设置外边距
            textView.setLayoutParams(textParams);

            // 动态创建 ImageButton
            ImageButton button = new ImageButton(parentView.getContext());
            button.setImageResource(R.drawable.play); // 设置按钮图标
            button.setBackgroundResource(android.R.color.transparent); // 按钮背景透明
            button.setScaleType(ImageButton.ScaleType.CENTER_INSIDE); // 设置缩放方式
            button.setOnClickListener(v -> {
                // 按钮点击事件
                Intent intent = new Intent(parentView.getContext(), DetailActivity.class);
                intent.putExtra("imageName", item.getImageName());
                parentView.getContext().startActivity(intent);
            });
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                    (int) parentView.getResources().getDimension(R.dimen.image_width), // 50dp
                    (int) parentView.getResources().getDimension(R.dimen.image_height) // 50dp
            );
//            buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
//            buttonParams.addR
            button.setLayoutParams(buttonParams);

            // 将 ImageView、TextView 和 Button 添加到父布局中
            container.addView(imageView);
            container.addView(textView);
            container.addView(button);

            // 将整个父布局添加到推荐项的父布局中
            llRecommend.addView(container);
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
                int sizeInPx = (int) (100 * view.getContext().getResources().getDisplayMetrics().density);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                imageButton.setPadding(0, 0, 0, 0);
                params.setMargins(0, 20, 20, 20);
                imageButton.setLayoutParams(params);
                imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);

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