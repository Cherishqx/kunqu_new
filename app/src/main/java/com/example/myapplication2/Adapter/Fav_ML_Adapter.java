package com.example.myapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.FavoriteItem;
import com.example.myapplication2.R;

import java.util.List;

public class Fav_ML_Adapter extends RecyclerView.Adapter<Fav_ML_Adapter.ViewHolder> {

    private List<FavoriteItem> favoriteItems;
    private Context context;
    private LinearLayout favMlContainer;

    public Fav_ML_Adapter(Context context, List<FavoriteItem> favoriteItems, LinearLayout favMlContainer) {
        this.context = context;
        this.favoriteItems = favoriteItems;
        this.favMlContainer = favMlContainer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteItem item = favoriteItems.get(position);
        holder.favoriteTitle.setText(item.getTitle());
        holder.favoriteImage.setImageResource(item.getImageResId());
        holder.favoriteIcon.setImageResource(item.getIconResId());

        // 将视图添加到 fav_ml_container
        favMlContainer.addView(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return favoriteItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favoriteImage;
        TextView favoriteTitle;
        ImageView favoriteIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteImage = itemView.findViewById(R.id.fav_ml_img);
            favoriteTitle = itemView.findViewById(R.id.fav_ml_text);
            favoriteIcon = itemView.findViewById(R.id.fav_ml_imgbt);
        }
    }
}