package com.example.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EmoCalendarView extends CalendarView {

    private static final String TAG = "EmoCalendarView";
    private Map<String, Drawable> dateImages;
    private OnDateClickListener onDateClickListener;
    private int imageSize;
    private int margin;
    private int cellSpacing;
    private int currentMonth;
    private int currentYear;
    private Paint selectedDatePaint;
    private Paint dateTextPaint;
    private int screenDensity;

    public EmoCalendarView(Context context) {
        super(context);
        init(context);
    }

    public EmoCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmoCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    // 初始化方法
    private void init(Context context) {
        dateImages = new HashMap<>();
        imageSize = dpToPx(context, 38); // 将38dp转换为像素
        margin = dpToPx(context, 20); // 将20dp转换为像素
        cellSpacing = dpToPx(context, 5); // 将5dp转换为像素

        selectedDatePaint = new Paint();
        selectedDatePaint.setColor(Color.RED); // 设置选中日期的背景颜色

        dateTextPaint = new Paint();
        dateTextPaint.setColor(Color.BLACK); // 设置日期文本颜色
        dateTextPaint.setTextSize(dpToPx(context, 16)); // 设置日期文本大小

        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        // 将日历设置为当前月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long minDate = calendar.getTimeInMillis();

        // 将日历设置为当前月的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        long maxDate = calendar.getTimeInMillis();

        // 设置最小和最大日期以限制日历到当前月
        setMinDate(minDate);
        setMaxDate(maxDate);

        // 获取屏幕密度
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenDensity = displayMetrics.densityDpi;

        setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "点击的日期: " + dayOfMonth);
                if (onDateClickListener != null) {
                    onDateClickListener.onDateClick(dayOfMonth);
                }
                if (month != currentMonth || year != currentYear) {
                    currentMonth = month;
                    currentYear = year;
                    dateImages.clear();
                }
            }
        });
    }

    // 绘制方法
    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        super.dispatchDraw(canvas);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(getDate());

        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        // 绘制每个日期单元格的图像
        for (Map.Entry<String, Drawable> entry : dateImages.entrySet()) {
            String date = entry.getKey();
            Drawable image = entry.getValue();
            int day = Integer.parseInt(date);

            // 计算给定日期的周和星期几
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, day);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 调整为0基索引（星期天=0）

            // 检查日期是否在当前月
            if (calendar.get(Calendar.MONTH) == currentMonth && calendar.get(Calendar.YEAR) == currentYear) {
                float cellWidth = (getWidth() - 2 * margin - 6 * cellSpacing) / 7f;
                float cellHeight = dpToPx(getContext(), 41); // 每个日期行高41dp
                float x = margin + dayOfWeek * (cellWidth + cellSpacing) + (cellWidth - imageSize) / 2; // 水平居中图像
                float y = dpToPx(getContext(), 98) + (weekOfMonth - 1) * cellHeight + (cellHeight - imageSize) / 2; // 垂直居中图像，从第三行开始

                // 根据屏幕密度和行调整y位置
                if (screenDensity == DisplayMetrics.DENSITY_XXHIGH) { // 480 dpi
                    switch (weekOfMonth) {
                        case 1:
                            y += dpToPx(getContext(), 24);
                            break;
                        case 2:
                            y += dpToPx(getContext(), 30);
                            break;
                        case 3:
                            y += dpToPx(getContext(), 36);
                            break;
                        case 4:
                            y += dpToPx(getContext(), 43);
                            break;
                        case 5:
                            y += dpToPx(getContext(), 50);
                            break;
                    }

                    // 根据列调整x位置
                    switch (dayOfWeek) {
                        case 0:
                            x += dpToPx(getContext(), 7);
                            break;
                        case 1:
                            x += dpToPx(getContext(), 5);
                            break;
                        case 2:
                            x += dpToPx(getContext(), 1);
                            break;
                        case 4:
                            x -= dpToPx(getContext(), 1);
                            break;
                        case 5:
                            x -= dpToPx(getContext(), 5);
                            break;
                        case 6:
                            x -= dpToPx(getContext(), 7);
                            break;
                    }
                }

//                y -= dpToPx(getContext(), 41); // 调整y位置，位置不对就调这条

                // 额外调整API级别
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && dayOfWeek == 0) {
                    if (Build.VERSION.SDK_INT == 34) {
                        y += dpToPx(getContext(), 41);
                    } else if (Build.VERSION.SDK_INT == 35) {
                        y -= dpToPx(getContext(), 41);
                    }
                }

                Log.d(TAG, "绘制日期图像: " + date + " 位置: (" + x + ", " + y + ")");
                image.setBounds((int) x, (int) y, (int) (x + imageSize), (int) (y + imageSize)); // 设置大小为38dp x 38dp
                image.draw(canvas);
            }
        }
    }

    // 设置日期图像
    public void setDateImage(String date, Drawable image) {
        Log.d(TAG, "设置日期图像: " + date);
        dateImages.put(date, image);
        invalidate(); // 重绘视图
    }

    // 设置日期点击监听器
    public void setOnDateClickListener(OnDateClickListener listener) {
        this.onDateClickListener = listener;
    }

    // 将dp转换为像素
    private int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    // 日期点击监听器接口
    public interface OnDateClickListener {
        void onDateClick(int day);
    }
}