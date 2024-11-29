package com.example.myapplication2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication2.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.Viewholder> {
    private ArrayList<Map<String, Object>> listItem;
    private List<String> inputTexts = new ArrayList<>();

    public SentenceAdapter(ArrayList<Map<String, Object>> listItem) {
        this.listItem = listItem;
    }

    // Define Viewholder
    public static class Viewholder extends RecyclerView.ViewHolder {
        private EditText ed;

        public Viewholder(View root) {
            super(root);
            ed = root.findViewById(R.id.edit_sentence);
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_list_cell, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Map<String, Object> item = listItem.get(position);
        holder.ed.setText((String) item.get("sentence"));

    }


    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public List<String> getInputTexts() {
        return inputTexts;
    }


}
