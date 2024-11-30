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

    private void init(Context context) {
        dateImages = new HashMap<>();
        imageSize = dpToPx(context, 38); // Convert 38dp to pixels
        margin = dpToPx(context, 20); // Convert 20dp to pixels
        cellSpacing = dpToPx(context, 5); // Convert 5dp to pixels

        selectedDatePaint = new Paint();
        selectedDatePaint.setColor(Color.RED); // Set selected date background color

        dateTextPaint = new Paint();
        dateTextPaint.setColor(Color.BLACK); // Set date text color
        dateTextPaint.setTextSize(dpToPx(context, 16)); // Set date text size

        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        // Set the calendar to the first day of the current month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long minDate = calendar.getTimeInMillis();

        // Set the calendar to the last day of the current month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        long maxDate = calendar.getTimeInMillis();

        // Set the min and max date to restrict the calendar to the current month
        setMinDate(minDate);
        setMaxDate(maxDate);

        // Get screen density
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenDensity = displayMetrics.densityDpi;

        setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "Date clicked: " + dayOfMonth);
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

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        super.dispatchDraw(canvas);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(getDate());

        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        // Draw each date cell's image
        for (Map.Entry<String, Drawable> entry : dateImages.entrySet()) {
            String date = entry.getKey();
            Drawable image = entry.getValue();
            int day = Integer.parseInt(date);

            // Calculate the week and day of the week for the given date
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, day);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Adjust to 0-based index (Sunday = 0)

            // Check if the date is in the current month
            if (calendar.get(Calendar.MONTH) == currentMonth && calendar.get(Calendar.YEAR) == currentYear) {
                float cellWidth = (getWidth() - 2 * margin - 6 * cellSpacing) / 7f;
                float cellHeight = dpToPx(getContext(), 41); // Each date row is 41dp high
                float x = margin + dayOfWeek * (cellWidth + cellSpacing) + (cellWidth - imageSize) / 2; // Center the image horizontally
                float y = dpToPx(getContext(), 98) + (weekOfMonth - 1) * cellHeight + (cellHeight - imageSize) / 2; // Center the image vertically, starting from the third row

                // Adjust y position based on screen density and row
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

                    // Adjust x position based on column
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

                // Additional adjustment for API level >= 34
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && dayOfWeek == 0) {
                    y += dpToPx(getContext(), 41);
                }

                Log.d(TAG, "Drawing image for date: " + date + " at position: (" + x + ", " + y + ")");
                image.setBounds((int) x, (int) y, (int) (x + imageSize), (int) (y + imageSize)); // Set size to 38dp x 38dp
                image.draw(canvas);
            }
        }
    }

    public void setDateImage(String date, Drawable image) {
        Log.d(TAG, "Setting image for date: " + date);
        dateImages.put(date, image);
        invalidate(); // Redraw the view
    }

    public void setOnDateClickListener(OnDateClickListener listener) {
        this.onDateClickListener = listener;
    }

    public interface OnDateClickListener {
        void onDateClick(int day);
    }

    private int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}