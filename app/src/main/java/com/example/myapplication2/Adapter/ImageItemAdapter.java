package com.example.myapplication2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.DetailActivity;
import com.example.myapplication2.FavoritesManager;
import com.example.myapplication2.ImageItem;
import com.example.myapplication2.R;

import java.util.List;

public class ImageItemAdapter extends BaseAdapter {
    private Context context;
    private List<ImageItem> items;
    private OnFavoriteChangeListener favoriteChangeListener;

    public ImageItemAdapter(Context context, List<ImageItem> items) {
        this.context = context;
        this.items = items;
        FavoritesManager.initialize(context);
    }

    public void setOnFavoriteChangeListener(OnFavoriteChangeListener listener) {
        this.favoriteChangeListener = listener;
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
        ImageButton imageButtonFavorite = convertView.findViewById(R.id.imageButton_favorite);

        ImageItem item = items.get(position);
        imageView.setImageResource(item.getImageResId());
        titleTextView.setText(item.getTitle());
        imageButtonFavorite.setSelected(FavoritesManager.isFavorite(item));

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("imageName", item.getImageName());
            context.startActivity(intent);

        });

        imageButtonFavorite.setOnClickListener(v -> {
            boolean isFavorite = FavoritesManager.isFavorite(item);
            if (isFavorite) {
                FavoritesManager.removeFavorite(context, item);
                imageButtonFavorite.setSelected(false);
            } else {
                FavoritesManager.addFavorite(context, item);
                imageButtonFavorite.setSelected(true);
            }
            if (favoriteChangeListener != null) {
                favoriteChangeListener.onFavoriteChanged();
            }
        });

        return convertView;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ImageItem> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public interface OnFavoriteChangeListener {
        void onFavoriteChanged();
    }


}