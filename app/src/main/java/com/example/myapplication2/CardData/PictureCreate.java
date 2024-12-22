package com.example.myapplication2.CardData;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;
import java.util.Calendar;
import com.example.myapplication2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PictureCreate {
    private int height = 1800;
    private int width = 1000;
    private Context context;
    private List<String> inputTexts;
    private int imageId;
    Bitmap bitmap;

    String year;
    String date;
    String tgdz;
    String jieqi;
    String holiday;

    // 构造函数，传递上下文、输入文本列表和图片资源ID
    public PictureCreate(Context context, List<String> inputTexts, int imageId) {
        this.context = context;
        this.inputTexts = inputTexts;
        this.imageId = imageId;

        Calendar calendar = Calendar.getInstance();
        year = String.valueOf(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1; // 月份从 0 开始，所以要加 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date = String.format("%02d", month)+"月"+String.format("%02d", day) + "日";
        tgdz = CalendarUtil.getCyclical();
        jieqi = CalendarUtil.getTwenty_tour();
        holiday = CalendarUtil.getHoliday();

        method1();
    }

    // 创建图片并保存为文件
    public void method1() {
        // 创建一个空白Bitmap
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true); // 抗锯齿

        // 设置背景色
        canvas.drawColor(Color.WHITE);
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.paper_texture);
        // 获取原始图片资源
        if (image != null) {
            // 按比例绘制图片
            canvas.drawBitmap(image, null, new Rect(0, 0, width, height), null);
        }

        /* 右上角的日期 */
        // 日期文字
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40); // 设置文字大小
        Typeface typeface = ResourcesCompat.getFont(context, R.font.qijic);
        textPaint.setTypeface(Typeface.create(typeface, Typeface.BOLD));
        textPaint.setTextAlign(Paint.Align.RIGHT); // 设置文字右对齐

        // 横线
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(5); // 设置线条宽度

        // 绘制内容
        canvas.drawText(tgdz, 945, 200, textPaint); // 从右对齐的 945 坐标开始
        canvas.drawText(year, 945, 250, textPaint);
        canvas.drawLine(697, 150, 945, 150, linePaint); // 绘制横线
        textPaint.setColor(Color.argb(255,50,50,50));
        canvas.drawText(date, 945, 120, textPaint);

        textPaint.setTextSize(150);
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(jieqi+holiday, 40, 180, textPaint);

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ink_box_shu);
        // 获取原始图片资源
        if (image != null) {
            // 按比例绘制图片
            canvas.drawBitmap(image, null, new Rect(200, 240, 800, height-400), null);
        }

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.taohua);
        // 获取原始图片资源
        if (image != null) {
            // 按比例绘制图片
            canvas.drawBitmap(image, null, new Rect(70, 700, 920, height-300), null);
        }


        // 加载资源图片并绘制
        image = BitmapFactory.decodeResource(context.getResources(), imageId);
        // 获取原始图片资源
        if (image != null) {
            // 获取图片的宽高
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

            // 目标矩形的尺寸
            int targetLeft = 700;
            int targetTop = 1400;
            int targetRight = 930; // 初始宽度：340 - 130 = 210
            int targetBottom = 1800; // 初始高度：900 - 600 = 300

            // 计算目标宽高
            int targetWidth = targetRight - targetLeft;
            int targetHeight = targetBottom - targetTop;

            // 按图片的宽高比调整目标矩形的尺寸
            float originalAspectRatio = (float) originalWidth / originalHeight;
            float targetAspectRatio = (float) targetWidth / targetHeight;

            if (originalAspectRatio > targetAspectRatio) {
                // 图片更宽，调整高度
                targetHeight = (int) (targetWidth / originalAspectRatio);
                targetBottom = targetTop + targetHeight;
            } else {
                // 图片更高，调整宽度
                targetWidth = (int) (targetHeight * originalAspectRatio);
                targetRight = targetLeft + targetWidth;
            }

            // 按比例绘制图片
            canvas.drawBitmap(image, null, new Rect(targetLeft, targetTop, targetRight, targetBottom), null);
        }

        /* 收藏的句子 */
        // 设置字体
        typeface = ResourcesCompat.getFont(context, R.font.qijic);
        textPaint.setTypeface(Typeface.create(typeface, Typeface.BOLD)); // 应用自定义字体
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(70);

        // 绘制文字
        int x = 700; // X坐标，确定文字的起始水平位置
        for (String text : inputTexts) {
            // 预处理文本
            int y = 350; // 初始Y坐标
            text = text.replaceAll("—|-|——|《|》| ", ""); // 删除 "——" 和 "《》"
            String[] lines = text.split("[，。]"); // 根据逗号和句号分割文本

            for (String line : lines) {
                Log.e("111", line);

                // 逐个字符绘制竖向文字
                for (int i = 0; i < line.length(); i++) {
                    // 计算每个字符的 X、Y 位置
                    float charX = x;
                    float charY = y + i * 70; // 每个字符纵向间隔 70px

                    // 绘制当前字符
                    canvas.drawText(String.valueOf(line.charAt(i)), charX, charY, textPaint);
                }

                // 换行处理
                y += line.length() * 80; // 按照当前段落长度计算 Y 偏移量
            }

            x -= 100; // 每个输入文本 X 偏移量
        }

    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public String saveBitmapToFile(Bitmap bitmap) {
        // 获取存储路径
        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        // 动态生成文件名
        String fileName = "output_image_with_font_" + System.currentTimeMillis() + ".png";
        File file = new File(directoryPath, fileName);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // 保存为PNG格式
            outputStream.flush();
            Log.e("File ", file.getAbsolutePath());

            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    Log.e("MediaScanner", "Scanned " + path + ":");
                    Log.e("MediaScanner", "-> uri=" + uri);
                    Toast.makeText(context, "已保存到相册", Toast.LENGTH_LONG).show(); // 注意这里需要调用show()来显示Toast
                }
            });
            return file.getAbsolutePath(); // 返回文件路径
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 发生异常，返回null
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

    public void shareBitmapToWeChat(Bitmap bitmap) {
        try {
            // 创建缓存目录中的临时文件
            File cacheDir = new File(context.getCacheDir(), "images");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File tempFile = new File(cacheDir, "temp_image.png");

            Log.e("111","12e12e12");

            // 将 Bitmap 写入临时文件
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Log.e("111","1212");
            // 获取文件的 Uri
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", tempFile);
            Log.d("Generated Uri", uri.toString());
            // 创建分享 Intent
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setPackage("com.tencent.mm"); // 设置目标应用为微信

            // 启动分享
            context.startActivity(Intent.createChooser(shareIntent, "分享到微信"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "分享失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
