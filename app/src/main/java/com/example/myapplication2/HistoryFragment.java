package com.example.myapplication2;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class HistoryFragment extends Fragment {

    private static final String TAG = "HistoryFragment"; // 用于日志的标签
    private boolean isUp = true;
    private ImageButton upDownButton;
    private EmoCalendarView emoCalendarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        Locale locale = new Locale("zh");
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        emoCalendarView = view.findViewById(R.id.emoCalendarView);
        if (emoCalendarView == null) {
            Log.e(TAG, "EmoCalendarView is not found in the layout.");
            return view;
        }

        emoCalendarView.setOnDateClickListener(new EmoCalendarView.OnDateClickListener() {
            @Override
            public void onDateClick(int day) {
                Log.d(TAG, "Date clicked: " + day);
                showBackgroundSelectionDialog(day);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        upDownButton = view.findViewById(R.id.up_down);
        if (upDownButton == null) {
            Log.e(TAG, "UpDownButton is not found in the layout.");
            return;
        }
        upDownButton.setImageResource(R.drawable.ic_up);

        upDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUp) {
                    upDownButton.setImageResource(R.drawable.ic_down);
                    emoCalendarView.setVisibility(View.GONE); // 隐藏日历视图
                } else {
                    upDownButton.setImageResource(R.drawable.ic_up);
                    emoCalendarView.setVisibility(View.VISIBLE); // 显示日历视图
                }
                isUp = !isUp;
            }
        });
    }

    public void showBackgroundSelectionDialog(final int day) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_background_selection, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        ImageButton image1 = dialogView.findViewById(R.id.emotion1);
        ImageButton image2 = dialogView.findViewById(R.id.emotion2);
        ImageButton image3 = dialogView.findViewById(R.id.emotion3);
        ImageButton image4 = dialogView.findViewById(R.id.emotion4);
        ImageButton image5 = dialogView.findViewById(R.id.emotion5);
        ImageButton customEmotion = dialogView.findViewById(R.id.custom_emotion);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Background selection clicked: " + v.getId());
                Drawable selectedImage = null;
                int id = v.getId();
                if (id == R.id.emotion1) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion1, null);
                } else if (id == R.id.emotion2) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion2, null);
                } else if (id == R.id.emotion3) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion3, null);
                } else if (id == R.id.emotion4) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion4, null);
                } else if (id == R.id.emotion5) {
                    selectedImage = ResourcesCompat.getDrawable(getResources(), R.drawable.emotion5, null);
                } else if (id == R.id.custom_emotion) {
                    // Handle custom emotion logic here
                }
                if (selectedImage != null) {
                    emoCalendarView.setDateImage(String.valueOf(day), selectedImage);
                    Log.d(TAG, "Date image set for day: " + day);
                }
                dialog.dismiss();
            }
        };

        image1.setOnClickListener(listener);
        image2.setOnClickListener(listener);
        image3.setOnClickListener(listener);
        image4.setOnClickListener(listener);
        image5.setOnClickListener(listener);
        customEmotion.setOnClickListener(listener);

        dialog.show();
    }
}