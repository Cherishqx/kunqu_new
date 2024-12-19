package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        // 获取界面上的 TextView 用于展示数据
        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        favCardButton = findViewById(R.id.fav_card);

        // 获取传递过来的 type
        String type = getIntent().getStringExtra("type");
        if (type != null && !type.isEmpty()) {
            // 根据传递的 typeId 查找 CSV 中的数据并展示
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

        // 设置返回按钮的点击事件
        ImageButton backButton = findViewById(R.id.card_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to fragment_second
                finish();
            }
        });

        // 设置收藏按钮的点击事件
        favCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;
                favCardButton.setSelected(isFavorite);
                Toast.makeText(KnowledgeActivity.this, isFavorite ? "Added to favorites" : "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Map<String, String> getKnowledgeFromCsv(String typeId) {
        Map<String, String> knowledgeDetails = new HashMap<>();
        try {
            // Open the CSV file from assets
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("knowledge.csv"), "UTF-8"));

            String line;
            // Read each line of the CSV file
            while ((line = reader.readLine()) != null) {
                // Split each line by comma
                String[] tokens = line.split(",");
                // Check if this is the row we are looking for
                if (tokens.length >= 4 && (tokens[0].trim().equals(typeId))) {
                    // Extract and store details from the CSV row
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