package com.example.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.Map;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Map<String, Object>> items;

    public ImageAdapter(Context context, ArrayList<Map<String, Object>> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.imagelist, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image_to_paste);
        ImageView selector = convertView.findViewById(R.id.select);

        Map<String, Object> item = items.get(position);
        imageView.setImageResource((Integer) item.get("image"));

        // 控制 selector 的可见性
        boolean isSelected = (boolean) item.get("isSelected");
        if (isSelected) {
            selector.setVisibility(View.VISIBLE); // 显示
        } else {
            selector.setVisibility(View.INVISIBLE); // 隐藏
        }

        return convertView;
    }
}

