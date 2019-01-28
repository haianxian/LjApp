package com.lj.app.custom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 13717 on 2018/b/8.
 */

public class MyLinearLayout extends LinearLayout{

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("ThirdActivity", "MyLinearLayout_dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("ThirdActivity", "MyLinearLayout_onInterceptTouchEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ThirdActivity", "MyLinearLayout_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ThirdActivity", "MyLinearLayout_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ThirdActivity", "MyLinearLayout_ACTION_UP");
                break;
        }
        return false;
    }


}
