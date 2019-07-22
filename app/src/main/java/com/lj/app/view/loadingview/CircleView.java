package com.lj.app.view.loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    Paint mPaint;
    int mColor;
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth()/2;
        float cy = getHeight()/2;
        canvas.drawCircle(cx,cy,cx,mPaint);
    }

    public void changeColor(int color){
        this.mColor = color;
        mPaint.setColor(mColor);
        invalidate();
    }

    public int getColor(){
        return mColor;
    }
}
