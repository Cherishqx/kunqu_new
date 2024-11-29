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
        for (int i = 0; i < n; ++i) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.drawable.mine2);
            map.put("isSelected", false);
            listItem.add(map);
        }
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
