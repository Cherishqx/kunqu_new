package com.example.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Data.History;
import com.example.myapplication2.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private Context context;
    private List<History> historyList;

    public HistoryAdapter(Context context, List<History> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    // 创建视图 Holder
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建并返回一个视图 Holder
        View itemView = LayoutInflater.from(context).inflate(R.layout.history_data, parent, false);
        return new HistoryViewHolder(itemView);
    }

    // 绑定数据到视图
    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        final History history = historyList.get(position);
        holder.titleText.setText(history.getTitle());
        holder.introText.setText(history.getIntro());
    }

    // 获取数据集的大小
    @Override
    public int getItemCount() {
        return historyList.size();
    }

    // 创建 ViewHolder 类
    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, introText;
        ImageButton deleteButton, modifyButton;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            introText = itemView.findViewById(R.id.intro);
        }
    }
}
