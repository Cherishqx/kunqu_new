package com.example.myapplication2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication2.Adapter.ImageItemAdapter;
import com.example.myapplication2.Adapter.Adapter_Favcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoritesFragment extends Fragment {

    private GridView gridViewFavMl;
    private LinearLayout favCardContainer;
    private ImageItemAdapter adapterFavMl;
    private ImageItemAdapter adapterFavCard;
    private TextView noFavoritesText1;
    private TextView noFavoritesText2;
    private Adapter_Favcard favoriteButtonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Initialize the Card_FavoritesManager
        Card_FavoritesManager.initialize(requireContext());

        gridViewFavMl = view.findViewById(R.id.gridview_favml);
        favCardContainer = view.findViewById(R.id.fav_card);
        noFavoritesText1 = view.findViewById(R.id.no_favorites_text1);
        noFavoritesText2 = view.findViewById(R.id.no_favorites_text2);

        adapterFavMl = new ImageItemAdapter(getContext(), new ArrayList<>(ML_FavoritesManager.getFavoriteItems()));
        adapterFavCard = new ImageItemAdapter(getContext(), new ArrayList<>(ML_FavoritesManager.getFavoriteCards()));

        gridViewFavMl.setAdapter(adapterFavMl);

        adapterFavMl.setOnFavoriteChangeListener(this::refreshFavorites);
        adapterFavCard.setOnFavoriteChangeListener(this::refreshFavorites);

        // Register the broadcast receiver
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(updateFavoritesReceiver, new IntentFilter("update_favorites"));

        refreshFavorites();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Unregister the broadcast receiver
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(updateFavoritesReceiver);
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

        // Load favorite buttons
        Set<String> favorites = Card_FavoritesManager.getFavoriteCards();
        List<String> favoriteTypes = new ArrayList<>(favorites);
        List<String> favoriteTitles = new ArrayList<>();
        for (String type : favorites) {
            favoriteTitles.add(Card_FavoritesManager.getTitle(type));
        }

        favoriteButtonAdapter = new Adapter_Favcard(requireContext(), favoriteTypes, favoriteTitles);
        favCardContainer.removeAllViews();
        for (int i = 0; i < favoriteButtonAdapter.getCount(); i++) {
            View buttonView = favoriteButtonAdapter.getView(i, null, favCardContainer);
            favCardContainer.addView(buttonView);
        }
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
        if (favCardContainer.getChildCount() == 0) {
            favCardContainer.setVisibility(View.GONE);
            noFavoritesText2.setVisibility(View.VISIBLE);
        } else {
            favCardContainer.setVisibility(View.VISIBLE);
            noFavoritesText2.setVisibility(View.GONE);
        }
    }

    private final BroadcastReceiver updateFavoritesReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshFavorites();
        }
    };
}