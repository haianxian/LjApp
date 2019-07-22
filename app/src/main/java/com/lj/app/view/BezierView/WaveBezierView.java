package com.lj.app.view.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WaveBezierView extends View {

    public WaveBezierView(Context context) {
        this(context,null);
    }

    public WaveBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
