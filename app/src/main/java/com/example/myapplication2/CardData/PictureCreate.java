package com.example.myapplication2.CardData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.example.myapplication2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PictureCreate {
    private int height = 1000;
    private int width = 800;
    private Context context;
    private List<String> inputTexts;
    private int imageId;
    Bitmap bitmap;

    // 构造函数，传递上下文、输入文本列表和图片资源ID
    public PictureCreate(Context context, List<String> inputTexts, int imageId) {
        this.context = context;
        this.inputTexts = inputTexts;
        this.imageId = imageId;
        createAndSaveFile();
    }

    // 创建图片并保存为文件
    public void createAndSaveFile() {
        // 创建一个空白Bitmap
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true); // 抗锯齿

        // 设置背景色
        canvas.drawColor(Color.WHITE);

        // 设置字体
        Typeface typeface = ResourcesCompat.getFont(context, R.font.islide);
        paint.setTypeface(typeface); // 应用自定义字体
        paint.setColor(Color.BLACK);
        paint.setTextSize(35);

        // 加载资源图片并绘制
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageId);
        if (image != null) {
            canvas.drawBitmap(image, null, new Rect(150, 610, 340, 900), null);
        }

        // 绘制文字
        int x = 650; // X坐标，确定文字的起始水平位置
        int y = 100; // 初始Y坐标
        for (String text : inputTexts) {
            Log.e("111", text);

            // 逐个字符绘制竖向文字
            for (int i = 0; i < text.length(); i++) {
                // 计算每个字符的X、Y位置
                float charX = x;
                float charY = y + i * 50; // 每个字符纵向间隔50px

                // 绘制当前字符
                canvas.drawText(String.valueOf(text.charAt(i)), charX, charY, paint);
            }

            // 为下一个字符串调整Y坐标
            y += 20;// 每个字符串之间的纵向间隔
            x -= 100;
        }
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void saveBitmapToFile(Bitmap bitmap) {
        // 获取存储路径
        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File file = new File(directoryPath, "output_image_with_font.png");

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // 保存为PNG格式
            outputStream.flush();
            Log.e("File " , file.getAbsolutePath());

            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    Log.e("MediaScanner", "Scanned " + path + ":");
                    Log.e("MediaScanner", "-> uri=" + uri);
                    Toast.makeText(context,"已保存到现场", Toast.LENGTH_LONG);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
