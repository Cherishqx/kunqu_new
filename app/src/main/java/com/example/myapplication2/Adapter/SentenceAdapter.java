package com.example.myapplication2.Adapter;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
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
    private List<String> inputTexts = new ArrayList<>();  // To store the input text for each item

    public SentenceAdapter(ArrayList<Map<String, Object>> listItem) {
        this.listItem = listItem;
        // Initialize the inputTexts list with empty strings corresponding to each list item
        for (int i = 0; i < listItem.size(); i++) {
            inputTexts.add((String) listItem.get(i).get("sentence")); // Initially empty text for each item
        }
    }

    // Define Viewholder
    public static class Viewholder extends RecyclerView.ViewHolder {
        private EditText ed;

        public Viewholder(View root) {
            super(root);
            ed = root.findViewById(R.id.edit_sentence);  // Ensure this is the correct ID for your EditText
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_list_cell, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        Map<String, Object> item = listItem.get(position);
        String sentence = (String) item.get("sentence");
        holder.ed.setText(sentence);

        // Add a TextWatcher to listen for changes in the EditText
        holder.ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Update the inputTexts list when the text changes
                inputTexts.set(position, charSequence.toString());
                // Optionally, update the original item map as well
                item.put("sentence", charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    // Method to get all input texts
    public List<String> getInputTexts() {
        return inputTexts;
    }
}
