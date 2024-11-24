package com.example.myapplication2;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.DetailsAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        VideoView videoView = findViewById(R.id.video_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 获取从Intent传递过来的imageName
        String imageName = getIntent().getStringExtra("imageName");

        if (imageName != null && !imageName.isEmpty()) {
            playVideoFromAssets(videoView, imageName + ".mp4");
        }

        if (imageName != null && !imageName.isEmpty()) {
            Map<String, String> details = getDetailsFromCsv(imageName);
            if (!details.isEmpty()) {
                List<DetailItem> items = new ArrayList<>();
                // 根据需要调整字号大小
                items.add(new DetailItem("标题: " + details.get("title"), 30));
                items.add(new DetailItem("导演信息: " + details.get("subTitle"), 23));
                items.add(new DetailItem(details.get("mainPeople"), 23));
                items.add(new DetailItem(details.get("profile"), 23));
                items.add(new DetailItem(details.get("detail"), 23));

                DetailsAdapter adapter = new DetailsAdapter(this, items);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    private void playVideoFromAssets(VideoView videoView, String fileName) {
        // 复制文件到缓存目录
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

        // 设置VideoView播放复制后的文件
        videoView.setVideoPath(file.getPath());
        videoView.start();
    }


    private Map<String, String> getDetailsFromCsv(String imageName) {
        Map<String, String> details = new HashMap<>();
        try {
            // 打开assets文件夹下的CSV文件
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("detail.csv"), "UTF-8"));

            String line;
            // 读取CSV文件的每一行
            while ((line = reader.readLine()) != null) {
                // 使用逗号分隔每一列
                String[] tokens = line.split(",");
                Log.e("my", String.valueOf(tokens.length));
                // 检查是否是要找的行
                if (tokens.length > 5 && tokens[0].trim().equalsIgnoreCase(imageName)) {
                    Log.e("my", "1");
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
