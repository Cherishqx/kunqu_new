package com.example.myapplication2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication2.Config;
import com.example.myapplication2.Data.TicketInformation;
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

        // 获取 picName, 假设 picName 是图片文件名，例如 "11.png"
        String picName = ticketInformation.getPicName();
        Log.d("PicNameDebug", "PicName: " + picName);

        // 拼接出完整的图片URL
        String imageUrl = Config.url+"images/" + picName;

        // 使用 Glide 加载图片到 ImageView
        Glide.with(context)
                .load(imageUrl) // 设置图片URL
                .placeholder(R.drawable.ticket0) // 设置加载中的默认图片
                .error(R.drawable.ticket0) // 设置加载失败时的默认图片
                .override(400, 600)
                .fitCenter()
                .into(holder.imageView); // 加载到对应的 ImageView
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
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
