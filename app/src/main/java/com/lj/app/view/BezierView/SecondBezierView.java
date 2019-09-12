package com.lj.app.view.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
// https://blog.csdn.net/ITermeng/article/details/80264577
public class SecondBezierView extends View {

    //起点
    private float mStartPointX;
    private float mStartPointY;
    //终点
    private float mEndPointX;
    private float mEndPointY;
    //支撑点
    private float mFlagPointX;
    private float mFlagPointY;

    private Path mPath;
    private Paint bezierPaint;
    private Paint flagPaint;
    private Paint flagPaintText;

    public SecondBezierView(Context context) {
        this(context,null);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setStyle(Paint.Style.STROKE);
        bezierPaint.setStrokeWidth(8);

        flagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        flagPaint.setStyle(Paint.Style.STROKE);
        flagPaint.setStrokeWidth(3);

        flagPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        flagPaintText.setStyle(Paint.Style.STROKE);
        flagPaintText.setTextSize(20);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h / 2 - 200;

        mFlagPointX = w / 2;
        mFlagPointY = h / 2 - 300;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX,mStartPointY);
        mPath.quadTo(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY);

        canvas.drawPoint(mStartPointX,mStartPointY,flagPaint);
        canvas.drawText("起点",mStartPointX,mStartPointY,flagPaintText);

        canvas.drawPoint(mEndPointX,mEndPointY,flagPaint);
        canvas.drawText("终点",mEndPointX,mEndPointY,flagPaintText);

        canvas.drawPoint(mFlagPointX,mFlagPointY,flagPaint);
        canvas.drawText("控制点",mFlagPointX,mFlagPointY,flagPaintText);

        canvas.drawLine(mStartPointX,mStartPointY,mFlagPointX,mFlagPointY,flagPaint);
        canvas.drawLine(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY,flagPaint);

        canvas.drawPath(mPath,bezierPaint);

        //画直线
        mPath.moveTo(200,200);
        mPath.lineTo(300,300);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawPath(mPath,paint);
        //画三阶贝塞尔曲线
        mPath.moveTo(400,400);
        //(x1,y1) 为控制点，(x2,y2)为控制点，(x3,y3) 为结束点
        mPath.cubicTo(500,500,550,550,600,600);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawPath(mPath,p);
//        //画弧线（截取圆或者椭圆的一部分）
//        RectF rectF = new RectF(10,10,600,600);
//        mPath.arcTo(rectF,0,90);
//        Paint pain = new Paint();
//        pain.setColor(Color.GREEN);
//        canvas.drawPath(mPath,pain);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mFlagPointX = event.getX();
                mFlagPointY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
