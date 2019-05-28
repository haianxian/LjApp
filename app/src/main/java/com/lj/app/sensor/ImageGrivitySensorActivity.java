package com.lj.app.sensor;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
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
    Sensor sensor;
    private float lastX;
    private float lastY;
    private boolean animFinish = true;

    public static void open(Context context){
        Intent it = new Intent(context,ImageGrivitySensorActivity.class);
        context.startActivity(it);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagegrivity_sensor);
        imageIv = findViewById(R.id.image_iv);
        initSensor();
        registerSensor();
    }

    private void initSensor() {
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void registerSensor(){
        if(sensorManager != null && sensor != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            LogUtil.i("传感器方向", "注册监听");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float currX = -event.values[SensorManager.DATA_X];
            float currY = event.values[SensorManager.DATA_Y];

            LogUtil.i("传感器方向", "currX: " + currX + "currY: " + currY+"lastX: "+lastX);
            if(animFinish){
                setAnimation(4*currX, 4*currY);
            }
        }
    }

    // 图片跟随传感器移动
    private void setAnimation(final float x, final float y) {
        animFinish = false;
        final AnimatorSet set = new AnimatorSet();
        ObjectAnimator transX = ObjectAnimator.ofFloat(imageIv, "translationX", lastX, x);
        transX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lastX = (float) animation.getAnimatedValue("translationX");
            }
        });
        transX.setDuration(500);

        ObjectAnimator transY = ObjectAnimator.ofFloat(imageIv, "translationY", lastY, y);
        transY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lastY = (float) animation.getAnimatedValue("translationY");
            }
        });
        transY.setDuration(500);
        set.playTogether(transX, transY);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animFinish = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                lastX = x;
//                lastY = y;
                animFinish = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                animFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // 取消注册监听
    public void unRegisterSensor() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            LogUtil.i("传感器方向", "取消注册监听");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterSensor();
    }
}
