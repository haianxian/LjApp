package com.lj.app.custom.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * Created by 13717 on 2018/b/7.
 */

public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ThirdActivity", "MyButton_MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ThirdActivity", "MyButton_MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ThirdActivity", "MyButton_MotionEvent.ACTION_UP");
                break;

        }
//        final boolean superResult = super.onTouchEvent(event);
        return super.onTouchEvent(event);
//        return super.onTouchEvent(event);
//        02-07 14:13:36.691 29993-29993/com.lj.app D/ThirdActivity: onTouch
//        02-07 14:13:36.691 29993-29993/com.lj.app D/ThirdActivity: MotionEvent.ACTION_DOWN
//        02-07 14:13:36.811 29993-29993/com.lj.app D/ThirdActivity: onTouch
//        02-07 14:13:36.811 29993-29993/com.lj.app D/ThirdActivity: MotionEvent.ACTION_UP
//        02-07 14:13:36.821 29993-29993/com.lj.app D/ThirdActivity: setOnClickListener

//        return true:
//        02-07 14:12:21.871 28858-28858/com.lj.app D/ThirdActivity: onTouch
//        02-07 14:12:21.881 28858-28858/com.lj.app D/ThirdActivity: MotionEvent.ACTION_DOWN
//        02-07 14:12:21.991 28858-28858/com.lj.app D/ThirdActivity: onTouch
//        02-07 14:12:21.991 28858-28858/com.lj.app D/ThirdActivity: MotionEvent.ACTION_UP
//        02-07 15:38:15.821 2285-2285/com.lj.app D/ThirdActivity: setOnClickListener

//        return false;
//        02-07 14:14:33.231 30805-30805/com.lj.app D/ThirdActivity: onTouch
//        02-07 14:14:33.231 30805-30805/com.lj.app D/ThirdActivity: MotionEvent.ACTION_DOWN
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("ThirdActivity", "MyButton_dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }
}
