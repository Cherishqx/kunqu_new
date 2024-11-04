package com.example.myapplication2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class HistoryFragment extends Fragment {

    private boolean isUp = true;
    private ImageButton upDownButton;
    private CalendarView calendarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // Set the locale to Chinese (for example)
        Locale locale = new Locale("zh");
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        calendarView = view.findViewById(R.id.calendarView); // Ensure your layout has a CalendarView with this ID
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // You can handle the date change here, for example, show a toast
                Toast.makeText(getContext(), "Selected Date: " + year + "-" + (month + 1) + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        upDownButton = view.findViewById(R.id.up_down);
        upDownButton.setImageResource(R.drawable.ic_up);

        upDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUp) {
                    upDownButton.setImageResource(R.drawable.ic_down);
                    calendarView.setVisibility(View.GONE); // Hide the calendar view
                } else {
                    upDownButton.setImageResource(R.drawable.ic_up);
                    calendarView.setVisibility(View.VISIBLE); // Show the calendar view
                }
                isUp = !isUp;
            }
        });
    }
}