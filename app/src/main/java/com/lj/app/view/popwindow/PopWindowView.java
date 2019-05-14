package com.lj.app.view.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.lj.app.R;
import com.lj.app.tools.utils.UIUtils;

public class PopWindowView extends PopupWindow {

    private Context context;
    private ImageView arrowIv;
    public PopWindowView(Context context){
        super(context);
        this.context = context;
        // 设置popw可获取焦点
        setFocusable(true);
        // 设置popw可触摸
        setTouchable(true);
        // 设置popw点击外边区域不可消失
        setOutsideTouchable(true);

        View viewRoot = LayoutInflater.from(context).inflate(R.layout.pop_layout2, null);
        initView(viewRoot);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(viewRoot);
        initData();
        // 避免点击Popw最外层布局以及点击返回键PopW不会消失的bug
        this.setBackgroundDrawable(new ColorDrawable());
    }

    private void initView(View viewRoot) {
        arrowIv = viewRoot.findViewById(R.id.match_arrow_iv);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    private void initData() {

    }

    public void show(View view) {
        // PopupWindow显示在控件的左下方，x和y方向上有偏移
//        showAsDropDown(view, -UIUtils.dipToPx(context, 0) , 0);
        showAsDropDown(view, 0 , UIUtils.dipToPx(context, 3));
        backgroundAlpha(0.5f);
    }

    // 设置popWindow显示时候背景色
    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =((Activity)context).getWindow().getAttributes();
        lp.alpha = f;
        ((Activity)context).getWindow().setAttributes(lp);
    }

    public void measurePopArrow(final ImageView filterIv){
        filterIv.post(new Runnable() {
            @Override
            public void run() {
                int[] viewLocation = new int[2];
                filterIv.getLocationInWindow(viewLocation);
                final int filterX = viewLocation[0];
                int filterY = viewLocation[1];
                arrowIv.post(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) arrowIv.getLayoutParams();
                        layoutParams.leftMargin = filterX+filterIv.getWidth()/2-arrowIv.getWidth()/2;
                        arrowIv.setLayoutParams(layoutParams);
                    }
                });
            }
        });
    }
}
