package com.lj.app;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by 13717 on 2018/7/31.
 */

public class PopWindowTest extends Activity{

    TextView textView;
    private TextView tvDelete;
    private TextView tvCopy;
    private PopupWindow popupWindow;
    private int touchX;
    private int touchY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);
        textView = findViewById(R.id.text);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touchX = (int)event.getX();
                touchY = (int)event.getY();
                return true;
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop();
            }
        });
    }

    private void initPop(){
        View popView = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
        tvDelete = (TextView) popView.findViewById(R.id.tv_delete);
        tvCopy = (TextView) popView.findViewById(R.id.tv_copy);
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setAnimationStyle(R.style.PopAnimStyle);
        // 设置popw可获取焦点
        popupWindow.setFocusable(true);
        // 设置popw可触摸
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        // 获得位置 这里的v是目标控件，就是你要放在这个v的上面还是下面
        textView.getLocationOnScreen(location);

        popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = popView.getMeasuredWidth();    //  获取测量后的宽度
        int popupHeight = popView.getMeasuredHeight();  //获取测量后的高度

        if (popupWindow.isShowing())
            popupWindow.dismiss();
//        popupWindow.showAtLocation(textView, Gravity.NO_GRAVITY,0, 0);
//        popupWindow.showAtLocation(textView, Gravity.LEFT|Gravity.TOP,0, 0);
//        popupWindow.showAtLocation(textView, Gravity.RIGHT|Gravity.TOP,0, 0);
//        popupWindow.showAtLocation(textView, Gravity.LEFT|Gravity.BOTTOM,0, 0);
//        popupWindow.showAtLocation(textView, Gravity.RIGHT|Gravity.BOTTOM,0, 0);

//        popupWindow.showAtLocation(textView, Gravity.NO_GRAVITY,location[0]-popupWidth/b, location[1n]-popupHeight); //textView的左上角
//        popupWindow.showAtLocation(textView, Gravity.NO_GRAVITY,location[0]+textView.getWidth()/b-popupWidth/b, location[1n]-popupHeight); //textView顶部中间
//        popupWindow.showAtLocation(textView, Gravity.NO_GRAVITY,location[0]+textView.getWidth()/b-popupWidth/b, location[1n]-popupHeight/b); // textView中心位置
          popupWindow.showAtLocation(textView, Gravity.NO_GRAVITY,location[0]+textView.getWidth()/2-popupWidth/2, location[1]-popupHeight/2); // textView中心位置

    }
}
