package com.example.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.Map;

public class TimelineAdapter_history extends RecyclerView.Adapter<TimelineAdapter_history.Viewholder> {
    private LayoutInflater inflater;
    private ArrayList<Map<String, Object>> listItem;

    // Constructor to pass in the data
    public TimelineAdapter_history(Context context, ArrayList<Map<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }

    // Define Viewholder
    static class Viewholder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView Title, Text1, Text2, price , date;
        private TextView lvTopLine, lvDot , lvBottomLine;

        public Viewholder(View root) {
            super(root);
            icon = root.findViewById(R.id.lv_icon);
            Title = root.findViewById(R.id.lv_title);
            Text1 = root.findViewById(R.id.lv_content1);
            Text2 = root.findViewById(R.id.lv_content2);
            price = root.findViewById(R.id.lv_price);
            date = root.findViewById(R.id.lv_date);

            lvTopLine = (TextView) itemView.findViewById(R.id.lvTopLine);
            lvDot = (TextView) itemView.findViewById(R.id.lvDot);
            lvBottomLine = (TextView) itemView.findViewById(R.id.lvBottomLine);
        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout with parent context and attachToRoot as false
        View view = inflater.inflate(R.layout.time_list_cell, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        // Bind data to ViewHolder
        Map<String, Object> item = listItem.get(position);

        if (position == 0) {
            // 第一行头的竖线不显示
            holder.lvTopLine.setVisibility(View.INVISIBLE);
        }
        else if (position == listItem.size() - 1) {
            holder.lvTopLine.setVisibility(View.VISIBLE);
        }

        holder.Title.setText((String) item.get("ItemTitle"));
        holder.Text1.setText((String) item.get("ItemText1"));
        holder.Text2.setText((String) item.get("ItemText2"));
        holder.price.setText((String) item.get("ItemPrice"));
        holder.icon.setImageResource((int) item.get("ItemIcon"));
        holder.date.setText((String) item.get("ItemDate"));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
