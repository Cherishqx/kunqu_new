package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.myapplication2.Adapter.ImageItemAdapter;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private GridView gridView;
    private ImageItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        gridView = view.findViewById(R.id.gridview_favorites);
        adapter = new ImageItemAdapter(getContext(), new ArrayList<>(FavoritesManager.getFavoriteItems()));
        gridView.setAdapter(adapter);

        adapter.setOnFavoriteChangeListener(this::refreshFavorites);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshFavorites();
    }

    private void refreshFavorites() {
        adapter.clear();
        adapter.addAll(FavoritesManager.getFavoriteItems());
        adapter.notifyDataSetChanged();
    }
}