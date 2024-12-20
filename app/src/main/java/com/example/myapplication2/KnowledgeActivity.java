package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeActivity extends AppCompatActivity {

    private TextView titleTextView, contentTextView;
    private ImageButton favCardButton;
    private String contentText;
    private boolean isFavorite = false;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        // Initialize the Card_FavoritesManager
        Card_FavoritesManager.initialize(this);

        // Get the views
        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        favCardButton = findViewById(R.id.fav_card_bt);

        // Get the type from the intent
        type = getIntent().getStringExtra("type");
        if (type != null && !type.isEmpty()) {
            // Get the knowledge details from the CSV
            Map<String, String> knowledgeDetails = getKnowledgeFromCsv(type);

            if (!knowledgeDetails.isEmpty()) {
                titleTextView.setText(knowledgeDetails.get("title"));
                contentText = knowledgeDetails.get("content");
                contentTextView.setText(contentText.replace("\\n", "\n"));
            } else {
                Toast.makeText(this, "No data found for type: " + type, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid type", Toast.LENGTH_SHORT).show();
        }

        // Initialize the favorite status
        isFavorite = Card_FavoritesManager.isFavorite(type);
        favCardButton.setSelected(isFavorite);

        // Set the back button click listener
        ImageButton backButton = findViewById(R.id.card_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set the favorite button click listener
        favCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;
                favCardButton.setSelected(isFavorite);
                if (isFavorite) {
                    Card_FavoritesManager.addFavorite(KnowledgeActivity.this, type, titleTextView.getText().toString());
                    Toast.makeText(KnowledgeActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Card_FavoritesManager.removeFavorite(KnowledgeActivity.this, type);
                    Toast.makeText(KnowledgeActivity.this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                }

                // Send broadcast to update FavoritesFragment
                Intent intent = new Intent("update_favorites");
                intent.putExtra("type", type);
                intent.putExtra("title", titleTextView.getText().toString());
                intent.putExtra("isFavorite", isFavorite);
                LocalBroadcastManager.getInstance(KnowledgeActivity.this).sendBroadcast(intent);
            }
        });
    }

    private Map<String, String> getKnowledgeFromCsv(String typeId) {
        Map<String, String> knowledgeDetails = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("knowledge.csv"), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 4 && (tokens[0].trim().equals(typeId))) {
                    knowledgeDetails.put("typeId", tokens[0].trim());
                    knowledgeDetails.put("title", tokens[2].trim());
                    knowledgeDetails.put("content", tokens[3].trim());
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return knowledgeDetails;
    }
}