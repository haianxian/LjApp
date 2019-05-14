package com.lj.app.tools.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 13717 on 2019/1/11.
 */

public class AutoFitScrollView extends ScrollView{

    private static final int MAX_HEIGHT = 300;

    public AutoFitScrollView(Context context) {
        super(context);
    }

    public AutoFitScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFitScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoFitScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View child = getChildAt(0);
        child.measure(widthMeasureSpec,heightMeasureSpec);
        int width = child.getMeasuredWidth();
        int height = Math.min(child.getMeasuredHeight(), MAX_HEIGHT);
        setMeasuredDimension(width,height);
    }
}
