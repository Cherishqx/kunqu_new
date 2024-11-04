package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView favoritesRecyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<FavoriteItem> favoriteItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        favoritesRecyclerView = view.findViewById(R.id.favorites_recycle);
        favoritesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        favoriteItems = new ArrayList<>();
        // Add some sample data
        favoriteItems.add(new FavoriteItem("《白蛇传》", R.drawable.mine1, R.drawable.ic_favorite));
        favoriteItems.add(new FavoriteItem("《牡丹亭》", R.drawable.mine2, R.drawable.ic_favorite));
        favoriteItems.add(new FavoriteItem("《西厢记》", R.drawable.mine3, R.drawable.ic_favorite));

        favoriteAdapter = new FavoriteAdapter(favoriteItems);
        favoritesRecyclerView.setAdapter(favoriteAdapter);

        return view;
    }
}