package com.lj.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lj.app.tools.utils.LogUtil;
import com.lj.app.tools.utils.rangeseekbar.OnRangeChangedListener;
import com.lj.app.tools.utils.rangeseekbar.RangeSeekBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 13717 on 2019/1/2.
 */

public class DJSTimeActivity extends AppCompatActivity{

    Button start;
    Button stop;
    TextView tv;
    long time = 60;
    CountDownTimer countDownTimer;
    private RangeSeekBar seekbar,seekbar1, seekbar2, seekbar3, seekbar4, seekbar5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djs);
        start = findViewById(R.id.btn_start);
        stop = findViewById(R.id.btn_stop);
        tv = findViewById(R.id.text_content);

        seekbar = findViewById(R.id.other);
        seekbar1 = findViewById(R.id.seekbar1);
        seekbar2 = findViewById(R.id.seekbar2);
        seekbar3 = findViewById(R.id.seekbar3);
        seekbar4 = findViewById(R.id.seekbar4);
        seekbar5 = findViewById(R.id.seekbar5);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startTimer();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });



        seekbar.setRange(0, 50);
        seekbar.setValue(16, 25);
        seekbar.setIndicatorTextDecimalFormat("0");
        seekbar.setIndicatorTextStringFormat("%s岁");



        seekbar2.setTypeface(Typeface.DEFAULT_BOLD);
        seekbar2.getLeftSeekBar().setTypeface(Typeface.DEFAULT_BOLD);
        seekbar2.setIndicatorTextDecimalFormat("0.00");
        seekbar4.setIndicatorTextDecimalFormat("0");
        seekbar5.setIndicatorTextStringFormat("你是%s吗");

        seekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
//                seekbar.setIndicatorText((int)leftValue+"");
//                seekbar.getRightSeekBar().setIndicatorText((int)rightValue+"岁");

                LogUtil.i("显示的数据", "left>>"+leftValue+"right>>"+rightValue);
                if(leftValue < 12){
                    seekbar.getLeftSeekBar().setIndicatorText("不限制");
                } else {
                    seekbar.getLeftSeekBar().setIndicatorText((int)leftValue+"");
                }


                if(rightValue > 40){
                    seekbar.getRightSeekBar().setIndicatorText("不限制");
                } else {
                    seekbar.getRightSeekBar().setIndicatorText((int)rightValue+"");
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //do what you want!!
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //do what you want!!
            }
        });

        seekbar2.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {

            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
                //do what you want!!
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                //do what you want!!
            }
        });

        seekbar4.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                if (leftValue <= 50){
                    view.setProgressColor(getResources().getColor(R.color.colorAccent));
                    view.getLeftSeekBar().setThumbDrawableId(R.drawable.thumb_activated);
                    view.getLeftSeekBar().setIndicatorBackgroundColor(getResources().getColor(R.color.colorAccent));
                }else {
                    view.setProgressColor(getResources().getColor(R.color.colorPrimary));
                    view.getLeftSeekBar().setIndicatorBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    view.getLeftSeekBar().setThumbDrawableId(R.drawable.thumb_blue);
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });

        seekbar5.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {

            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });

        seekbar1.setValue(16,24);
        seekbar2.setValue(-0.5f,0.8f);
        seekbar3.setValue(-26, 90);
        seekbar5.setValue(25, 75);
    }

//    private void startTime(){
//        countDownTimer = new CountDownTimer(time*1000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tv.setText((millisUntilFinished/1000)+"s");
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
//    }
//
//    private void stopTime(){
//        countDownTimer.cancel();
//    }

    Timer timer;
    TimerTask task;
    private void startTimer(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                time--;
                if(time == 0){
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                } else if(time > 0){
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        };
        if(timer != null && task != null){
            timer.schedule(task,0,1000);
        }
    }

    private void stopTimer(){
        if(timer != null){
           timer.cancel();
            timer = null;
        }
        if(task != null){
            task.cancel();
            task = null;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if(tv != null){
                        tv.setText("60s");
                    }
                    break;
                case 1:
                    if(tv != null){
                        tv.setText(time+"s");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
