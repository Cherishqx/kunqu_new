package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        VideoView videoView = findViewById(R.id.video_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMainPeople = findViewById(R.id.tv_main_people);
        TextView tvDirectorInfo = findViewById(R.id.tv_director_info);
        TextView tvProfile = findViewById(R.id.tv_profile);
        TextView tvDetail = findViewById(R.id.tv_detail);

        // Get the imageName from the Intent
        String imageName = getIntent().getStringExtra("imageName");

        if (imageName != null && !imageName.isEmpty()) {
            playVideoFromAssets(videoView, imageName + ".mp4");
        }

        if (imageName != null && !imageName.isEmpty()) {
            Map<String, String> details = getDetailsFromCsv(imageName);
            if (!details.isEmpty()) {
                tvTitle.setText(details.get("title"));
                tvMainPeople.setText(details.get("mainPeople"));
                tvDirectorInfo.setText(details.get("subTitle"));
                tvProfile.setText(details.get("profile"));
                tvDetail.setText(details.get("detail"));
            }
        }
    }

    private void playVideoFromAssets(VideoView videoView, String fileName) {
        // Copy file to cache directory
        File file = new File(getCacheDir() + "/" + fileName);
        if (!file.exists()) {
            try (InputStream is = getAssets().open(fileName);
                 OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Set VideoView to play the copied file
        videoView.setVideoPath(file.getPath());
        videoView.start();
    }

    private Map<String, String> getDetailsFromCsv(String imageName) {
        Map<String, String> details = new HashMap<>();
        try {
            // Open the CSV file from assets
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("detail.csv"), "UTF-8"));

            String line;
            // Read each line of the CSV file
            while ((line = reader.readLine()) != null) {
                // Split each line by comma
                String[] tokens = line.split(",");
                // Check if this is the row we are looking for
                if (tokens.length > 5 && tokens[0].trim().equalsIgnoreCase(imageName)) {
                    details.put("imageName", tokens[0].trim());
                    details.put("title", tokens[1].trim());
                    details.put("subTitle", tokens[2].trim());
                    details.put("mainPeople", tokens[3].trim());
                    details.put("profile", tokens[4].trim());
                    details.put("detail", tokens[5].trim());
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return details;
    }
}