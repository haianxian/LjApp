package com.lj.app.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;

public class MoveViewLayout extends FrameLayout {

    public static final String TAG = "MoveViewLayout";
    private Context context;
    public MoveViewLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MoveViewLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MoveViewLayout(@NonNull Context context, @Nullable AttributeSet attrs,
                          int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_moveview, this, true);
    }

    int startX;
    int startY;
    int endX;
    int endY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int)event.getRawX();
        int rawY = (int)event.getRawY();
        LogUtil.i(TAG,"rawx:"+rawX +"rawy:"+rawY);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int)event.getX();
                startY = (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = (int)event.getX();
                endY = (int)event.getY();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)this.getLayoutParams();
//                params.leftMargin = rawX-this.getWidth()/2;
//                params.topMargin = rawY - this.getHeight()/2;
                if(Math.abs(endX-startX) > 3 && Math.abs(endY-startY)> 3){
                    params.leftMargin = rawX;
                    params.topMargin = rawY;
                    this.setLayoutParams(params);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
