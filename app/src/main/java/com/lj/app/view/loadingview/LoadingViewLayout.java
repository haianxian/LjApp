package com.lj.app.view.loadingview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class LoadingViewLayout extends RelativeLayout {

    private Context context;
    private CircleView mLeftView;
    private CircleView mCenterView;
    private CircleView mRightView;
    private int distance = 30;
    public LoadingViewLayout(Context context) {
        this(context,null);
        this.context = context;
    }

    public LoadingViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);
        mLeftView = getCircleView(context);
        mLeftView.changeColor(Color.BLUE);
        mCenterView = getCircleView(context);
        mCenterView.changeColor(Color.RED);
        mRightView = getCircleView(context);
        mRightView.changeColor(Color.GREEN);

        addView(mLeftView);
        addView(mCenterView);
        addView(mRightView);

        //开启动画
        openAnimation();

    }

    private CircleView getCircleView(Context context){
        CircleView circleView = new CircleView(context);
        LayoutParams params = new LayoutParams(dp2x(10),dp2x(10));
        params.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(params);
        return circleView;
    }

    private void openAnimation(){
        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(mLeftView,"translationX",0,-distance);
        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(mRightView,"translationX",0,-distance);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(350);
        set.playTogether(leftAnim,rightAnim);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                closeAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    private void closeAnimation(){
        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(mLeftView,"translationX",-distance,0);
        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(mRightView,"translationX",distance,0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(350);
        set.playTogether(leftAnim,rightAnim);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //交换颜色 切换颜色顺序  左边的给中间 中间的给右边  右边的给左边
                int mLeftColor = mLeftView.getColor();
                int mCenterColor = mCenterView.getColor();
                int mRightColor = mRightView.getColor();

                mCenterView.changeColor(mLeftColor);
                mRightView.changeColor(mCenterColor);
                mLeftView.changeColor(mRightColor);

                openAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    private int dp2x(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    /**
     * 性能优化
     */
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(INVISIBLE);
        //清理所有动画
        mLeftView.clearAnimation();
        mRightView.clearAnimation();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(mLeftView);//父布局中移除当前view
            parent.removeView(mRightView);
            removeAllViews();//移除自己的
        }
    }

}
