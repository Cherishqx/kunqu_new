package com.example.myapplication2.CardData;

import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sentences {
    private ArrayList<Map<String,Object>> listItem;
    private String[] list;
    public Sentences(){
        initData();
    }

    public Sentences(String s){
        setData(s);
    }

    public void initData(){
        listItem = new ArrayList<>();/*在数组中存放数据*/
        for (int i = 0; i < 4; ++i) {
            Map<String, Object> map = new HashMap<>();
            map.put("hasSentence",false);
            map.put("sentence","");
            listItem.add(map);
        }
    }

    public void setData(String s){
        listItem = new ArrayList<>();/*在数组中存放数据*/
        list =  s.split("\\n");
        for (int i = 0; i < list.length; ++i) {
            Map<String, Object> map = new HashMap<>();
            map.put("sentence",list[i]);
            //map.put("exist",null);
            listItem.add(map);
        }
        if(list.length < 4){
            for (int i = 0; i < 4 - list.length; ++i) {
                Map<String, Object> map = new HashMap<>();
                map.put("exist",null);
                listItem.add(map);
            }
        }
    }

    public ArrayList<Map<String,Object>> getListItem(){
        return listItem;
    }
}
