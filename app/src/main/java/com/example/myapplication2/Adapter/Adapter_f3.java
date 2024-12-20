//旧版本 已弃用 后续删掉
package com.example.myapplication2.Adapter;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Data.Data_Sentence_f3;
import com.example.myapplication2.R;

import java.util.List;

public class Adapter_f3 extends RecyclerView.Adapter<Adapter_f3.ViewHolder> {
    private List<Data_Sentence_f3> itemList;
    private OnItemClickListener onItemClickListener;

    public Adapter_f3(List<Data_Sentence_f3> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_f3, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Data_Sentence_f3 item = itemList.get(position);
        holder.textView.setText(item.getContent()); // 绑定设置数据

        //通过接口名调用方法
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageButton plus;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sentence);
//            plus = itemView.findViewById(R.id.button3_5);
        }
    }

    public static interface OnItemClickListener{
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

