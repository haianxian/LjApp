package com.lj.app.sensor;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;

public class ImageGrivitySensorActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageIv;
    SensorManager sensorManager;
    private static int LEFT = 0;
    private static int RIGHT = 1;
    private static int TOP = 2;
    private static int BOTTOM = 3;
    private static int LEFT_TOP = 4;
    private static int LEFT_BOTTOM = 5;
    private static int RIGHT_TOP = 6;
    private static int RIGHT_BOTTOM = 7;
    private static int ORIENTATION = -1;
    private long endTime = 0;
    private int lastX;
    private int lastY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagegrivity_sensor);
        imageIv = findViewById(R.id.image_iv);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int currX = (int) event.values[SensorManager.DATA_X];
            int currY = (int) event.values[SensorManager.DATA_Y];
            if (currX < 0 && currY == 0) {
                //右移动
                ORIENTATION = RIGHT;
            } else if (currX > 0 && currY == 0) {
                // 左移动
                ORIENTATION = LEFT;
            } else if (currX == 0 && currY < 0) {
                // 上移动
                ORIENTATION = TOP;
            } else if (currX == 0 && currY > 0) {
                // 下移动
                ORIENTATION = BOTTOM;
            } else if (currX < 0 && currY < 0) {
                //右上移动
                ORIENTATION = RIGHT_TOP;
            } else if (currX < 0 && currY > 0) {
                // 右下移动
                ORIENTATION = RIGHT_BOTTOM;
            } else if (currX > 0 && currY > 0) {
                // 左下移动
                ORIENTATION = LEFT_BOTTOM;
            } else if (currX > 0 && currY < 0) {
                // 左上移动
                ORIENTATION = LEFT_TOP;
            }
            LogUtil.i("传感器方向", "currX: " + currX + "currY: " + currY+"lastX: "+lastX);
            if (System.currentTimeMillis() - endTime > 0.2*1000L) {
                setAnimation(currX, currY);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void setAnimation(final int x, final int y) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator transX = ObjectAnimator.ofFloat(imageIv, "translationX", lastX, x);
        transX.setDuration(200);

        ObjectAnimator transY = ObjectAnimator.ofFloat(imageIv, "translationY", lastY, y);
        transY.setDuration(200);
        set.playTogether(transX, transY);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                endTime = System.currentTimeMillis();
                lastX = x;
                lastY = y;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
}
