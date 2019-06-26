package com.lj.app.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {

    int paddingLeft;
    int paddingRight;
    int paddingTop;
    int paddingBottom;
    int viewsWidth;//所有子View的宽度之和
    int viewsHeight;//所有子View的高度之和
    int viewGroupWidth =0;//ViewGroup算上padding之后的宽度
    int viewGroupHeight = 0;//ViewGroup算上padding之后的高度
    int marginLeft;
    int marginRight;
    int marginTop;
    int marginBottom;

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paddingBottom = getPaddingBottom();
        paddingTop = getPaddingTop();
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();

        viewsWidth = 0;
        viewsHeight = 0;
        marginBottom=0;
        marginLeft = 0;
        marginRight=0;
        marginTop = 0;

        int childCount = getChildCount();
        for(int i=0;i < childCount;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            viewsHeight += childView.getMeasuredHeight();
            viewsWidth = Math.max(viewsWidth,childView.getMeasuredWidth());
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

            marginLeft = Math.max(0,lp.leftMargin);//在本例中找出最大的左边距
            marginRight = Math.max(0,lp.rightMargin);//在本例中找出最大的右边距
            marginTop += lp.topMargin;//在本例中求出所有的上边距之和
            marginBottom +=lp.bottomMargin;//在本例中求出所有的下边距之和
        }

        /* 用于处理ViewGroup的wrap_content情况 */
        viewGroupWidth = paddingLeft+paddingRight+viewsWidth+marginLeft+marginRight;
        viewGroupHeight = paddingTop+paddingBottom+viewsHeight+marginTop+marginBottom;
        setMeasuredDimension(measureWidth(widthMeasureSpec,viewGroupWidth),measureHeight(heightMeasureSpec,viewGroupHeight));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //参数changed表示view有新的尺寸或位置
        if(changed){
            int childCount = getChildCount();
            int mTop = paddingTop;
            for(int i=0; i< childCount;i++){
                View childView = getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int mLeft = paddingLeft+lp.leftMargin;
                mTop += paddingTop;
                childView.layout(mLeft,mTop,mLeft+childView.getMeasuredWidth(),mTop+childView.getMeasuredHeight());
                mTop += (childView.getMeasuredHeight()+lp.bottomMargin);
            }
        }
    }

    private int measureWidth(int measureSpec,int viewGroupWidth){
        int result = 0;
        int widthMode = MeasureSpec.getMode(measureSpec);
        int widthSize = MeasureSpec.getSize(measureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(widthSize,viewGroupWidth);
                break;
            case MeasureSpec.UNSPECIFIED:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int measureSpec,int viewGroupHeight){
        int result = 0;
        int heightMode = MeasureSpec.getMode(measureSpec);
        int heightSize = MeasureSpec.getSize(measureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(heightSize,viewGroupHeight);
                break;
            case MeasureSpec.UNSPECIFIED:
                result = heightSize;
                break;
        }
        return result;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

}

//        总结一下：
//        在自定义View中处理padding，只需要在onDraw()中处理，别忘记处理布局为wrap_content的情况。
//        在自定义ViewGroup中处理padding，只需要在onLayout()中，给子View布局时算上padding的值即可，也别忘记处理布局为wrap_content的情况。
//        自定义View无需处理margin，在自定义ViewGroup中处理margin时，需要在onMeasure()中根据margin计算ViewGroup的宽、高，同时在onLayout中布局子View时也别忘记根据margin来布局。

