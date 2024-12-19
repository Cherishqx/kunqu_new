package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication2.Adapter.ImageItemAdapter;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private GridView gridViewFavMl;
    private LinearLayout favCardContainer;
    private ImageItemAdapter adapterFavMl;
    private ImageItemAdapter adapterFavCard;
    private TextView noFavoritesText1;
    private TextView noFavoritesText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        gridViewFavMl = view.findViewById(R.id.gridview_favml);
        favCardContainer = view.findViewById(R.id.fav_card);
        noFavoritesText1 = view.findViewById(R.id.no_favorites_text1);
        noFavoritesText2 = view.findViewById(R.id.no_favorites_text2);

        adapterFavMl = new ImageItemAdapter(getContext(), new ArrayList<>(ML_FavoritesManager.getFavoriteItems()));
        adapterFavCard = new ImageItemAdapter(getContext(), new ArrayList<>(ML_FavoritesManager.getFavoriteCards()));

        gridViewFavMl.setAdapter(adapterFavMl);

        adapterFavMl.setOnFavoriteChangeListener(this::refreshFavorites);
        adapterFavCard.setOnFavoriteChangeListener(this::refreshFavorites);

        refreshFavorites();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshFavorites();
    }

    private void refreshFavorites() {
        adapterFavMl.clear();
        adapterFavMl.addAll(ML_FavoritesManager.getFavoriteItems());
        adapterFavMl.notifyDataSetChanged();

        adapterFavCard.clear();
        adapterFavCard.addAll(ML_FavoritesManager.getFavoriteCards());
        adapterFavCard.notifyDataSetChanged();

        updateView(gridViewFavMl, noFavoritesText1, adapterFavMl);
        updateFavCardContainer();
    }

    private void updateView(GridView gridView, TextView noFavoritesTextView, ImageItemAdapter adapter) {
        if (adapter.getCount() == 0) {
            gridView.setVisibility(View.GONE);
            noFavoritesTextView.setVisibility(View.VISIBLE);
        } else {
            gridView.setVisibility(View.VISIBLE);
            noFavoritesTextView.setVisibility(View.GONE);
        }
    }

    private void updateFavCardContainer() {
        if (adapterFavCard.getCount() == 0) {
            favCardContainer.setVisibility(View.GONE);
            noFavoritesText2.setVisibility(View.VISIBLE);
        } else {
            favCardContainer.setVisibility(View.VISIBLE);
            noFavoritesText2.setVisibility(View.GONE);
        }
    }
}