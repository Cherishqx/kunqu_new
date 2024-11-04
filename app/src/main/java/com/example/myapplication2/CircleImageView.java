package com.example.myapplication2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

public class CircleImageView extends AppCompatImageView {

    private Paint borderPaint;
    private final float borderWidth = 5.0f; // Adjust the border width as needed
    private Path borderPath;

    public CircleImageView(Context context) {
        super(context);
        init(null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleImageView);
            try {
                int borderColor = a.getColor(R.styleable.CircleImageView_borderColor, ContextCompat.getColor(getContext(), android.R.color.white));
                borderPaint.setColor(borderColor);
            } finally {
                a.recycle();
            }
        } else {
            borderPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = Math.min(getWidth(), getHeight()) / 2.0f;
        float centerX = getWidth() / 2.0f;
        float centerY = getHeight() / 2.0f;

        if (borderPath == null) {
            borderPath = new Path();
            borderPath.addCircle(centerX, centerY, radius, Path.Direction.CCW);
        }

        canvas.clipPath(borderPath);

        super.onDraw(canvas);

        // Draw the border
        canvas.drawCircle(centerX, centerY, radius - borderWidth / 2.0f, borderPaint);
    }
}