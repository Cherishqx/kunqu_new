package com.example.myapplication2.CardData;

import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PasteImages {
    private ArrayList<Map<String, Object>> listItem;
    private int n = 20;

    public PasteImages() {
        listItem = new ArrayList<>();
        // Adding the first two images manually
        listItem.add(createImageMap(R.drawable.ip0, false));
        listItem.add(createImageMap(R.drawable.ip1, false));
        listItem.add(createImageMap(R.drawable.ip2, false));
        listItem.add(createImageMap(R.drawable.ip3, false));
        listItem.add(createImageMap(R.drawable.ip4, false));

        // Adding the other images in the loop
        for (int i = 0; i < n-5; ++i) {
            listItem.add(createImageMap(R.drawable.mine2, false));
        }
    }

    private Map<String, Object> createImageMap(int imageResId, boolean isSelected) {
        Map<String, Object> map = new HashMap<>();
        map.put("image", imageResId);
        map.put("isSelected", isSelected);
        return map;
    }


    public ArrayList<Map<String, Object>> getListItem() {
        return listItem;
    }

    // Update selection and reset others
    public void updateSelection(int index) {
        for (int i = 0; i < listItem.size(); i++) {
            listItem.get(i).put("isSelected", i == index);
        }
    }
}
