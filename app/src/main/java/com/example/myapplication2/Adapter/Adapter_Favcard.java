package com.example.myapplication2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.myapplication2.KnowledgeActivity;
import com.example.myapplication2.R;

import java.util.List;

public class Adapter_Favcard extends BaseAdapter {
    private Context context;
    private List<String> favoriteTypes;
    private List<String> favoriteTitles;

    public Adapter_Favcard(Context context, List<String> favoriteTypes, List<String> favoriteTitles) {
        this.context = context;
        this.favoriteTypes = favoriteTypes;
        this.favoriteTitles = favoriteTitles;
    }

    @Override
    public int getCount() {
        return favoriteTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return favoriteTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.button_fav_card, parent, false);
        }

        Button button = convertView.findViewById(R.id.fav_cards);
        String type = favoriteTypes.get(position);
        String title = favoriteTitles.get(position);

        button.setText(title);
        button.setTag(type);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KnowledgeActivity.class);
                intent.putExtra("type", type);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}