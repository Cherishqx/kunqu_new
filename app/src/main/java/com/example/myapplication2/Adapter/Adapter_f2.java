package com.example.myapplication2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.myapplication2.Data.Data_Knowledge_f2;
import com.example.myapplication2.R;

import java.util.List;

public class Adapter_f2 extends BaseAdapter {

    private List<Data_Knowledge_f2> itemList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    private static final String TAG = "Adapter_f2";  // Log 标签

    public Adapter_f2(Context context, List<Data_Knowledge_f2> itemList) {
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 如果 convertView 为 null，则需要创建新的视图
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_f2, parent, false);
        }

        // 获取当前项的数据
        Data_Knowledge_f2 item = itemList.get(position);

        // 获取视图中的控件并绑定数据
        TextView textView = convertView.findViewById(R.id.title);
        TextView textIntro = convertView.findViewById(R.id.intro);
        LinearLayout imageButtonContainer = convertView.findViewById(R.id.imageButtonContainer); // 获取存放 ImageButton 的容器

        // 设置标题文本
        textView.setText(item.getTitle());
        if(!item.getIntro().equals("")){
            textIntro.setText(item.getIntro());
        }

//        // 输出日志，检查每个 item 的 title 和 imageCount imageResources.size()
//        Log.e(TAG, "Itemid: " + item.getTypeId() + ", Image Count: " + item.getImageResources());

        // 清除现有的 ImageButton
        imageButtonContainer.removeAllViews();

        List<Integer> imageResources = item.getImageResources();

        // 根据 imageResources 动态创建 ImageButton
        for (int i = 0; i < imageResources.size(); i++) {
            ImageButton imageButton = new ImageButton(convertView.getContext());
            // dx-dp转换
            int sizeInPx = (int) (80 * convertView.getContext().getResources().getDisplayMetrics().density);
            // 设置 ImageButton 的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx); // 设置为 140dp 转换后的像素值
            imageButton.setPadding(0, 0, 0, 0);  // 设置四个方向的 padding 为 0
            params.setMargins(0, 20, 40, 20);  // 设置按钮之间的间距
            imageButton.setLayoutParams(params);

//            // 设置背景颜色为透明  padding=0即可
//            imageButton.setBackground(null);
//            imageButton.setBackgroundColor(Color.RED);
//            imageButton.setBackgroundColor(Color.TRANSPARENT);

            // 设置 ImageButton 的图片缩放类型
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);

            // 设置图片资源
            imageButton.setImageResource(imageResources.get(i));

            // 将 ImageButton 添加到容器中
            imageButtonContainer.addView(imageButton);

            // 设置点击事件
            final int index = i; // 用于点击时标识
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, position, index);
                    }
                }
            });

//            // 输出日志，查看每个 ImageButton 是否被添加
//            Log.e(TAG, "Added ImageButton with resource: " +i+"次"+ imageResources.get(i));
        }

        return convertView;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int imageIndex);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
