package com.example.myapplication2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.myapplication2.CreateActivity;
import com.example.myapplication2.Data.Sentence;
import com.example.myapplication2.DatabaseHelper;
import com.example.myapplication2.R;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Sentence_f3_Adapter extends RecyclerView.Adapter<Sentence_f3_Adapter.SentenceViewHolder> {
    private Context context;
    private List<Sentence> sentenceList;

    public Sentence_f3_Adapter(Context context, List<Sentence> sentenceList) {
        this.context = context;
        this.sentenceList = sentenceList;
    }

    // 创建视图 Holder
    @Override
    public SentenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建并返回一个视图 Holder
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_f3, parent, false);
        return new SentenceViewHolder(itemView);
    }

    // 绑定数据到视图
    @Override
    public void onBindViewHolder(SentenceViewHolder holder, int position) {
        final Sentence sentence = sentenceList.get(position);
        holder.sentenceText.setText(sentence.getContent());

        // 设置删除按钮的点击事件
        holder.deleteButton.setOnClickListener(v -> {
            // 显示删除确认对话框
            new AlertDialog.Builder(context)
                    .setTitle("确认删除?")
                    .setMessage("您确定要删除此条内容吗?")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("删除", (dialog, which) -> {
                        // 删除句子
                        DatabaseHelper myDataHelper = new DatabaseHelper(context);
                        myDataHelper.deleteOne(sentence);
                        sentenceList.remove(position); // 移除列表项
                        notifyItemRemoved(position); // 刷新 RecyclerView
                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });

        // 设置按钮的点击事件 (即 button3_5)
        holder.createButton.setOnClickListener(v -> {
            // 使用 Intent 启动 CreateActivity
            Intent intent = new Intent(context, CreateActivity.class);
            intent.putExtra("sentence", sentence.getContent());  // 将 sentence 内容传递过去
            intent.putExtra("hasSentence", true);
            context.startActivity(intent);  // 启动 CreateActivity

            Toast.makeText(context, "sentence值为"+sentence.getContent(), Toast.LENGTH_SHORT).show();
        });

    }

    // 获取数据集的大小
    @Override
    public int getItemCount() {
        return sentenceList.size();
    }

    // 创建 ViewHolder 类
    public static class SentenceViewHolder extends RecyclerView.ViewHolder {
        TextView sentenceText;
        Button deleteButton, createButton;

        public SentenceViewHolder(View itemView) {
            super(itemView);
            sentenceText = itemView.findViewById(R.id.sentence);
            deleteButton = itemView.findViewById(R.id.button3_4);
            createButton = itemView.findViewById(R.id.button3_5); // 确保 shareButton 是正确的 ID
        }
    }
}

//
//public class Sentence_f3_Adapter extends ArrayAdapter<Sentence> {
//    private Context context;
//    private List<Sentence> sentenceList;
//
//    public Sentence_f3_Adapter(Context context, List<Sentence> sentenceList) {
//        super(context, R.layout.listview_f3, sentenceList);
//        this.context = context;
//        this.sentenceList = sentenceList;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        // 获取列表项的布局
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.listview_f3, parent, false);
//        }
//
//        // 获取当前 Sentence 对象
//        final Sentence sentence = sentenceList.get(position);
//
//        // 设置 TextView 显示内容
//        TextView sentenceText = convertView.findViewById(R.id.sentence);
//        sentenceText.setText(sentence.getContent());
//
//        // 获取按钮并设置点击事件
//        ImageButton deleteButton = convertView.findViewById(R.id.button3_4);
//        ImageButton createButton = convertView.findViewById(R.id.button3_5);
//        ImageButton shareButton=convertView.findViewById(R.id.button3_5);
//        // 设置删除按钮的点击事件
//        deleteButton.setOnClickListener(v -> {
//            // 显示删除确认对话框
//            new AlertDialog.Builder(context)
//                    .setTitle("确认删除?")
//                    .setMessage("您确定要删除此条内容吗?")
//                    .setNegativeButton("取消", null)
//                    .setPositiveButton("删除", (dialog, which) -> {
//                        // 删除句子
//                        DatabaseHelper myDataHelper = new DatabaseHelper(context);
//                        myDataHelper.deleteOne(sentence);
//                        sentenceList.remove(position); // 移除列表项
//                        notifyDataSetChanged(); // 刷新 ListView
//                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
//                    })
//                    .show();
//        });
//
//        // 设置按钮的点击事件 (即 button3_5)
//        createButton.setOnClickListener(v -> {
//            // 使用 Intent 启动 CreateActivity
//            Intent intent = new Intent(context, CreateActivity.class);
//            intent.putExtra("sentence", sentence.getContent());  // 将 sentence 内容传递过去
//            intent.putExtra("hasSentence", true);
//            context.startActivity(intent);  // 启动 CreateActivity
//
//            Toast.makeText(context, "sentence值为"+sentence.getContent(), Toast.LENGTH_SHORT).show();
//        });
//
//        return convertView;
//    }
//}
