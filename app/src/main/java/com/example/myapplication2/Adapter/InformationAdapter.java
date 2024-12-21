package com.example.myapplication2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication2.R;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationViewHolder> {
    private Context context;
    private List<TicketInformation> informationList;

    // 修改构造函数，参数为 TicketInformation 类型的 List
    public InformationAdapter(Context context, List<TicketInformation> informationList) {
        this.context = context;
        this.informationList = informationList;
    }

    // 创建视图 Holder
    @Override
    public InformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建并返回一个视图 Holder
        View itemView = LayoutInflater.from(context).inflate(R.layout.information_data, parent, false);
        return new InformationViewHolder(itemView);
    }

    // 绑定数据到视图
    @Override
    public void onBindViewHolder(InformationViewHolder holder, int position) {
        final TicketInformation ticketInformation = informationList.get(position);
        // 将 TicketInformation 的数据绑定到视图控件上
        holder.nameText.setText(ticketInformation.getName());
        holder.timeText.setText(ticketInformation.getTime());
        holder.placeText.setText(ticketInformation.getPlace());
        holder.fareText.setText(ticketInformation.getFare());

        // 根据 picName 设置 ImageView 的图片
        String picName = ticketInformation.getPicName();
        Log.d("PicNameDebug", "PicName: " + picName);
        int imageResId = context.getResources().getIdentifier(picName, "drawable", context.getPackageName());
        if (imageResId != 0) {
            holder.imageView.setImageResource(imageResId);
        } else {
            // 如果没有找到图片资源，可以设置一个默认的图片
            holder.imageView.setImageResource(R.drawable.ticket0);
        }
    }

    // 获取数据集的大小
    @Override
    public int getItemCount() {
        return informationList.size();
    }

    // 创建 ViewHolder 类
    public static class InformationViewHolder extends RecyclerView.ViewHolder {
        TextView  nameText, timeText, placeText, fareText;
        ImageView imageView;

        public InformationViewHolder(View itemView) {
            super(itemView);
            // 将 ViewHolder 中的视图与布局中的控件绑定
            nameText = itemView.findViewById(R.id.name);
            timeText = itemView.findViewById(R.id.time);
            placeText = itemView.findViewById(R.id.place);
            fareText = itemView.findViewById(R.id.fare);
            imageView = itemView.findViewById(R.id.img); // 假设你的布局文件中有一个 ImageView 用于显示图片
        }
    }
}
