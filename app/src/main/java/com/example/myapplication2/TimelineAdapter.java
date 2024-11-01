package com.example.myapplication2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimelineAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Map<String, Object>> listItem;

    //构造函数，传入数据
    public TimelineAdapter(Context context, ArrayList<Map<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }


    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder  {
        private ImageView icon;
        private TextView Title, Text1,Text2,price;

        public Viewholder(View root) {
            super(root);
            icon = root.findViewById(R.id.lv_icon);
            Title = root.findViewById(R.id.lv_title);
            Text1 = root.findViewById(R.id.lv_content1);
            Text2 = root.findViewById(R.id.lv_content2);
            price = root.findViewById(R.id.lv_price);

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText1() {
            return Text1;
        }

        public TextView getText2() {
            return Text2;
        }

        public TextView getPrice() {
            return price;
        }

        public ImageView getIcon() {
            return icon;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(inflater.inflate(R.layout.time_list_cell, null));
    }//在这里把ViewHolder绑定Item的布局

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewholder vh = (Viewholder) holder;
        // 绑定数据到ViewHolder里面
        //vh.icon.setImageDrawable( listItem.get(position).get("ItemIcon"));
        vh.Title.setText((String) listItem.get(position).get("ItemTitle"));
        vh.Text1.setText((String) listItem.get(position).get("ItemText"));
    }

    //返回Item数目
    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
