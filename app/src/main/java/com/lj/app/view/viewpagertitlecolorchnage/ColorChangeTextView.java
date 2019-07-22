package com.lj.app.view.viewpagertitlecolorchnage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lj.app.R;
// https://www.jianshu.com/p/7ec99ebf264e
public class ColorChangeTextView extends View {

    private String txt;
    private float currProgress = 0.0f;
    private int textSize = 0;
    private Paint originPaint;
    private Paint changePaint;
    private Directtion mDirection = Directtion.LEFT_TO_RIGHT;
    public enum Directtion{
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }
    public ColorChangeTextView(Context context) {
        this(context,null);
    }

    public ColorChangeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorChangeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initColor(context,attrs);
    }

    private void initColor(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorChangeTextView);
        int originColor = array.getColor(R.styleable.ColorChangeTextView_origin_color, 0xFF0000);
        int changeColor = array.getColor(R.styleable.ColorChangeTextView_change_color,0x00FF00);
        originPaint = getPaint(originColor);
        changePaint = getPaint(changeColor);
        textSize = array.getColor(R.styleable.ColorChangeTextView_text_size,13);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int middle = (int)currProgress*getWidth();
        if(mDirection == Directtion.LEFT_TO_RIGHT){
            drawText(canvas,changePaint,0,middle);
            drawText(canvas,originPaint,middle,getWidth());
        } else if(mDirection == Directtion.RIGHT_TO_LEFT){
            drawText(canvas,changePaint,middle,getWidth());
            drawText(canvas,originPaint,0,middle);
        }

    }

    private Paint getPaint(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置防抖动
        paint.setDither(true);
        paint.setTextSize(textSize);
        return paint;
    }


    // drawText
    public void drawText(Canvas canvas,Paint paint,int start,int end){
        canvas.save();
        Rect rect = new Rect(start,0,end,getHeight());
        canvas.clipRect(rect);
        String txt = getTxt();
        Rect bounds = new Rect();
        paint.getTextBounds(txt,0,txt.length(),bounds);
        float txtWidth = paint.measureText(txt);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float y = getHeight()/2+(Math.abs(fontMetrics.ascent)-fontMetrics.descent)/2;
        canvas.drawText(txt,txtWidth/2,y,paint);
        canvas.restore();
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public float getCurrProgress() {
        return currProgress;
    }

    public void setCurrProgress(float currProgress) {
        this.currProgress = currProgress;
    }

    public void setDirect(Directtion direct){
        mDirection = direct;
    }


    /**
     * 设置改变颜色
     */
    public void setChangeColor(int changeColor) {
        this.changePaint.setColor(changeColor);
    }

    /**
     * 设置原始的原色
     */
    public void setOriginColor(int originColor) {
        this.originPaint.setColor(originColor);
    }

}
