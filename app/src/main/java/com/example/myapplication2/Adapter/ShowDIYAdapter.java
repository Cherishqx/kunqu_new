package com.example.myapplication2.Adapter;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication2.Data.ShowDIY;
import com.example.myapplication2.R;
import java.util.List;

public class ShowDIYAdapter extends RecyclerView.Adapter<ShowDIYAdapter.ViewHolder> {

    private List<ShowDIY> mItems;
    private RecyclerView mRecyclerView; // 用于获取 Context

    public ShowDIYAdapter(List<ShowDIY> items, RecyclerView recyclerView) {
        mItems = items;
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_diy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowDIY item = mItems.get(position);

        // 设置文本
        holder.itemText.setText(item.getText());

        // 使用 URI 来显示图片
        Uri imageUri = Uri.parse(item.getImagePath());
        if (imageUri != null) {
            holder.itemImage.setImageURI(imageUri);
        } else {
            holder.itemImage.setImageResource(R.drawable.placeholder); // 设置占位图像
        }

        // 监听 EditText 的输入变化
        holder.itemText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 设置删除按钮的点击事件
        holder.deleteButton.setOnClickListener(v -> {
            // 显示删除确认对话框
            new AlertDialog.Builder(mRecyclerView.getContext())
                    .setTitle("确认删除?")
                    .setMessage("您确定要删除此条内容吗?")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("删除", (dialog, which) -> {
                        // 删除句子
                        mItems.remove(position); // 移除列表项
                        notifyItemRemoved(position); // 刷新 RecyclerView
                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // 添加新项的方法
    public void addItem(ShowDIY item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1); // 通知适配器插入动画
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public EditText itemText;
        public Button deleteButton; // 添加删除按钮的引用

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.item_image);
            itemText = view.findViewById(R.id.item_text);
            deleteButton = view.findViewById(R.id.button_diy_delete); // 绑定删除按钮

            itemText.setPadding(16, 16, 16, 16);
            itemText.setPaintFlags(itemText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
    }
}