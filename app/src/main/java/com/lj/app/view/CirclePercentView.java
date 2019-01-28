package com.lj.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lj.app.R;
import com.lj.app.tools.utils.UIUtils;

/**
 * Created by 13717 on 2018/12/25.
 */

public class CirclePercentView extends View{

    private int bigCircleBg;
    private int smallCircleBg;
    private int radius;
    private int x;
    private int y;
    private float stripeWidth;
    private int mWidth;
    private int mHeight;
    private int mPercent;
    private int mEndAngle;
    private int mCurPercent;
    private int mTextSize;

    private int startX;
    private int startY;

    private int endX;
    private int endY;
    public CirclePercentView(Context context) {
        this(context,null);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        bigCircleBg = a.getColor(R.styleable.CirclePercentView_bigCircleBg,0xff6950a1);
        smallCircleBg = a.getColor(R.styleable.CirclePercentView_smallCircleBg, 0xffafb4db);
        radius = a.getDimensionPixelSize(R.styleable.CirclePercentView_radius, UIUtils.dip2px(100));
        stripeWidth = a.getDimension(R.styleable.CirclePercentView_stripeWidth,UIUtils.dip2px(30));
        mCurPercent = a.getInteger(R.styleable.CirclePercentView_currPercent,0);
        mTextSize = a.getDimensionPixelSize(R.styleable.CirclePercentView_textSize,UIUtils.dip2px(15));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
            radius = widthSize/2;
            x = widthSize/2;
            y = heightSize/2;
            mWidth = widthSize;
            mHeight = heightSize;
        }

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            mWidth = radius*2;
            mHeight = radius*2;

            x = mWidth;
            y = mHeight;
        }
        setMeasuredDimension(mWidth,mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //大圆
        mEndAngle = (int)(mCurPercent*3.6);
        Paint bigPaint = new Paint();
        // 消除锯齿
        bigPaint.setAntiAlias(true);
        bigPaint.setColor(Color.RED);
        canvas.drawCircle(x,y,radius,bigPaint);

        // 饼状
        Paint arcPaint = new Paint();
        // 消除锯齿
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(Color.BLUE);
        RectF rect = new RectF(0,0,mWidth, mHeight);
        canvas.drawArc(rect, 270, mEndAngle, true, arcPaint);

        // 小圆
        Paint smallPaint = new Paint();
        // 消除锯齿
        smallPaint.setAntiAlias(true);
        smallPaint.setColor(Color.GREEN);
        canvas.drawCircle(x,y,radius-stripeWidth, smallPaint);

        // 文字
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        String text = mCurPercent+"%";
        textPaint.setTextSize(mTextSize);
        Rect rect1 = new Rect();
        textPaint.getTextBounds(text,0, text.length(),rect1);
        int textW = rect1.width();
        int textH = rect1.height();
//        float textLength = textPaint.measureText(text);
//        canvas.drawText(text,x-textLength/2,y,textPaint);
        canvas.drawText(text,x-textW/2,y+textH/2,textPaint);
    }

    public void setPercent(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");
        }

        setCurPercent(percent);
    }
    //内部设置百分比 用于动画效果
    private void setCurPercent(int percent) {

        mPercent = percent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<mPercent;i++){
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    CirclePercentView.this.postInvalidate();
                }
            }

        }).start();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                startX = (int)event.getX();
//                startY = (int)event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                endX = (int)event.getX();
//                endY = (int)event.getY();
////                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) this.getLayoutParams();
////                params.leftMargin = endX;
////                params.topMargin = endY;
////                this.setLayoutParams(params);
////                invalidate();
////                闪烁
////                setTranslationX(endX-startX);
////                setTranslationY(endY-startY);
//                scrollTo(endX-startX,endY-startY);
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return true;
//    }
}
