package com.example.myapplication2.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.item_image);
            itemText = view.findViewById(R.id.item_text);
        }
    }
}