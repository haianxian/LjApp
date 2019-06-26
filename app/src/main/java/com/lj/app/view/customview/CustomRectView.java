package com.lj.app.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lj.app.R;

public class CustomRectView extends View {

    int width;
    int height;
    public CustomRectView(Context context) {
        super(context);
    }

    public CustomRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.customView);
        int bgColor = t.getColor(R.styleable.customView_background_color,0xFFFF00);
        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //当此View设置的宽高是wrap_content时，需要重写此方法，否则View会按照父类的match_parent显示全屏
        width = measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    private int measureWidth(int widthMeasureSpec){
        int result = 200;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,widthSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec){
        int result = 200;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,heightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                result = heightSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        Rect rect = new Rect();
        rect.left = getLeft()+paddingLeft;
        rect.top = getTop()+paddingTop;
        rect.right = width -paddingRight;
        rect.bottom = height-paddingBottom;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect,paint);
    }
}
