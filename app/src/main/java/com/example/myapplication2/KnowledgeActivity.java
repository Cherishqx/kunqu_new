package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeActivity extends AppCompatActivity {

    private TextView titleTextView, introTextView, contentTextView;
    String contentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        // 获取界面上的 TextView 用于展示数据
        titleTextView = findViewById(R.id.titleTextView);
        introTextView = findViewById(R.id.introTextView);
        contentTextView = findViewById(R.id.contentTextView);

        // 获取传递过来的 typeId
        String typeId = getIntent().getStringExtra("typeId");
        if (typeId != null && !typeId.isEmpty()) {
            // 根据传递的 typeId 查找 CSV 中的数据并展示
            Map<String, String> knowledgeDetails = getKnowledgeFromCsv(typeId);

            if (!knowledgeDetails.isEmpty()) {
                titleTextView.setText(knowledgeDetails.get("title"));
                introTextView.setText(knowledgeDetails.get("intro"));
                contentText=knowledgeDetails.get("content");
                contentTextView.setText(contentText.replace("\\n", "\n"));
//                contentTextView.setText("test1\ntest2\r\ntest3");//可以正常显示

            } else {
                Toast.makeText(this, "No data found for typeId: " + typeId, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid typeId", Toast.LENGTH_SHORT).show();
        }
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
                    knowledgeDetails.put("title", tokens[1].trim());
                    knowledgeDetails.put("intro", tokens[2].trim());
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