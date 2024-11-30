package com.example.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.ImageItem;
import com.example.myapplication2.R;

import java.util.List;

public class ImageItemAdapter extends BaseAdapter {
    private Context context;
    private List<ImageItem> items;

    public ImageItemAdapter(Context context, List<ImageItem> items) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView_musicLibrary);
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView subTitleTextView = convertView.findViewById(R.id.subTitleTextView);

        ImageItem item = items.get(position);
        imageView.setImageResource(item.getImageResId());
        titleTextView.setText(item.getTitle());
        subTitleTextView.setText(item.getSubTitle());

        return convertView;
    }
}

