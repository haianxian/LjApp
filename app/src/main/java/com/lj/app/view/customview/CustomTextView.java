package com.lj.app.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomTextView extends View {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        String str = "dhsjkfskfjksfk";
        float strWidth = paint.measureText(str);
        float x = (getWidth()-strWidth)/2;
        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

        canvas.drawText(str,x,getHeight()/2+(Math.abs(fontMetrics.ascent)-fontMetrics.descent)/2,paint);

    }
}
