package com.lj.app.tools.utils;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

/**
 * 发散的点, 由大点小点组成, 两类点排列和颜色均错开, 速度先慢后快向外发射
 * Created by Miroslaw Stanek on 20.12.2015.
 */
public class DotsView extends View {
    private static final int DOTS_COUNT = 7;
    private static final int OUTER_DOTS_POSITION_ANGLE = 360 / DOTS_COUNT;

//    private static final int COLOR_1 = 0xFFFFC107;// 橙色
//    private static final int COLOR_2 = 0xFFFF9800;// 橙带红
//    private static final int COLOR_3 = 0xFFFF5722;// 橙带更红
//    private static final int COLOR_4 = 0xFFF44336;// 红

    private static final int COLOR_1 = 0xFFE8D62D;
    private static final int COLOR_2 = 0xFF8CD1E9;
    private static final int COLOR_3 = 0xFF8E27FC;
    private static final int COLOR_4 = 0xFFFF8AC9;
    private static final int COLOR_5 = 0xFFFFDD7B;
    private static final int COLOR_6 = 0xFF4AAA89;

    private final Paint[] circlePaints = new Paint[6];

    private int centerX;
    private int centerY;

    private float maxOuterDotsRadius;// 最大外圈半径
    private float maxInnerDotsRadius;// 最大内圈半径
    private float maxDotSize;// 圆圈的最大尺寸

    private float currentProgress = 0;// 当前进度, 核心参数 ， <0.0f~1.0f>

    private float currentRadius1 = 0;// 外圈点的半径 ，圈的半径，不是圆点的半径
    private float currentDotSize1 = 0;// 外圈点的大小

    private float currentDotSize2 = 0;
    private float currentRadius2 = 0;

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public DotsView(Context context) {
        super(context);
        init();
    }

    public DotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        for (int i = 0; i < circlePaints.length; i++) {
            circlePaints[i] = new Paint();
            circlePaints[i].setStyle(Paint.Style.FILL);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
//        maxDotSize = 20;// 最大圆点大小
        maxDotSize = 4;
        /**
         * 保证弹射出来的圆点能不会弹到屏幕外（看不见），
         * 所以圆点运动的最大半径是view的宽度减去圆点的直径。
         */
        maxOuterDotsRadius = w / 2 - maxDotSize * 2;
        // 控制散开的点内圈半径大小
//        maxInnerDotsRadius = 0.8f * maxOuterDotsRadius;
        maxInnerDotsRadius = 0.5f * maxOuterDotsRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawOuterDotsFrame(canvas);
        drawInnerDotsFrame(canvas);
    }

    private void drawOuterDotsFrame(Canvas canvas) {
        /**
         * 计算出位置，在各个位置绘制圆点。
         */
        for (int i = 0; i < DOTS_COUNT; i++) {
            //Math.cos， in radians
            //PI是弧度的180度。
            int cX = (int) (centerX + currentRadius1 * Math.cos(i * OUTER_DOTS_POSITION_ANGLE * Math.PI / 180));
            int cY = (int) (centerY + currentRadius1 * Math.sin(i * OUTER_DOTS_POSITION_ANGLE * Math.PI / 180));
            canvas.drawCircle(cX, cY, currentDotSize1, circlePaints[i % circlePaints.length]);
        }
    }

    private void drawInnerDotsFrame(Canvas canvas) {
        for (int i = 0; i < DOTS_COUNT; i++) {
            int cX = (int) (centerX + currentRadius2 * Math.cos((i * OUTER_DOTS_POSITION_ANGLE - 10) * Math.PI / 180));
            int cY = (int) (centerY + currentRadius2 * Math.sin((i * OUTER_DOTS_POSITION_ANGLE - 10) * Math.PI / 180));
            canvas.drawCircle(cX, cY, currentDotSize2, circlePaints[(i + 1) % circlePaints.length]);
        }
    }

    public void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;

        updateInnerDotsPosition();
//        updateOuterDotsPosition();
        updateDotsPaints();
        updateDotsAlpha();

        postInvalidate();
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    private void updateInnerDotsPosition() {
        if (currentProgress < 0.3f) {
            //前30%时间，走完全程
            this.currentRadius2 = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0, 0.3f, 0.f, maxInnerDotsRadius);
        } else {
            this.currentRadius2 = maxInnerDotsRadius;
        }

        if (currentProgress < 0.2) {
            //前20%的时间，圆点大小为 mMaxDotSize
            this.currentDotSize2 = maxDotSize;
        } else if (currentProgress < 0.5) {
            //20%到50%的时间内，大小由 mMaxDotSize 缩小成 30%的大小
            this.currentDotSize2 = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0.2f, 0.5f, maxDotSize, 0.3 * maxDotSize);
        } else {
            //50%到结束的时间，大小由 30%的大小 缩小成 0
            this.currentDotSize2 = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0.5f, 1f, maxDotSize * 0.3f, 0);
        }

    }

    private void updateOuterDotsPosition() {
        if (currentProgress < 0.3f) {
            /**
             * mCurrentRadius1是圈的半径，不是圆点的半径。
             *
             * 用前30%的时间，走80%的距离。
             *
             * 【mCurrentProgress（时间值）】----->【mCurrentRadius1（半径值）】
             */
            this.currentRadius1 = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0.0f, 0.3f, 0, maxOuterDotsRadius * 0.8f);
        } else {
            /**
             * 用剩下的70%的时间，走完剩下的20%的距离。
             */
            this.currentRadius1 = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0.3f, 1f, 0.8f * maxOuterDotsRadius, maxOuterDotsRadius);
        }

        // 圆点的大小, 前70%的时间为mMaxDotSize, 后30%的时间逐渐缩小为0.
        if (currentProgress < 0.7) {
            this.currentDotSize1 = maxDotSize;
        } else {
            this.currentDotSize1 = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0.7f, 1f, maxDotSize, 0);
        }
    }

    private void updateDotsPaints() {
        if (currentProgress < 0.5f) {
            float progress = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0f, 0.5f, 0, 1f);
            circlePaints[0].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_1, COLOR_2));
            circlePaints[1].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_2, COLOR_3));
            circlePaints[2].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_3, COLOR_4));
            circlePaints[3].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_4, COLOR_5));
            circlePaints[4].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_5, COLOR_6));
            circlePaints[5].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_6, COLOR_1));
        } else {
            float progress = (float) UIUtils.mapValueFromRangeToRange(currentProgress, 0.5f, 1f, 0, 1f);
            circlePaints[0].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_2, COLOR_3));
            circlePaints[1].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_3, COLOR_4));
            circlePaints[2].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_4, COLOR_5));
            circlePaints[3].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_5, COLOR_6));
            circlePaints[4].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_6, COLOR_2));
            circlePaints[5].setColor((Integer) argbEvaluator.evaluate(progress, COLOR_2, COLOR_3));
        }
    }

    private void updateDotsAlpha() {
        float progress = (float) UIUtils.clamp(currentProgress, 0.6f, 1f);// 最小0.6, 最大1
        int alpha = (int) UIUtils.mapValueFromRangeToRange(progress, 0.6f, 1f, 255, 0);// 直到消失
        circlePaints[0].setAlpha(alpha);
        circlePaints[1].setAlpha(alpha);
        circlePaints[2].setAlpha(alpha);
        circlePaints[3].setAlpha(alpha);
        circlePaints[4].setAlpha(alpha);
        circlePaints[5].setAlpha(alpha);
    }

    public static final Property<DotsView, Float> DOTS_PROGRESS = new Property<DotsView, Float>(Float.class, "dotsProgress") {
        @Override
        public Float get(DotsView object) {
            return object.getCurrentProgress();
        }

        @Override
        public void set(DotsView object, Float value) {
            object.setCurrentProgress(value);
        }
    };
}
