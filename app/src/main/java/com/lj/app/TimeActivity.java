package com.lj.app;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lj.app.common.PrefUtilsCommon;
import com.lj.app.tools.utils.DataUtils;
import com.lj.app.tools.utils.PrefUtils;
import com.lj.app.tools.utils.UIUtils;
import com.lj.app.tools.utils.WilddogSyncUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimeActivity extends AppCompatActivity {

    TextView tv;
    LinearLayout lL;
    List<String> tagList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_twolins);
        tv = (TextView) findViewById(R.id.textview);
        lL = (LinearLayout) findViewById(R.id.lL);

//        checkWilddogTimerAuth();
        tagList.add("fdsjkhgfdks");
        tagList.add("123");
        tagList.add("我们");
        tagList.add("我们fsaffas");
        // 标签
        if(tagList != null && tagList.size()>0){
            for(int i=0 ;i< tagList.size();i++){
                TextView tv = new TextView(this);
                tv.setText(tagList.get(i));
                tv.setTextSize(12);
                tv.setTextColor(0xFFAAAAAA);
                tv.setPadding(14,8,14,8);
//                tv.setBackgroundResource(R.drawable.);
                lL.addView(tv);
                if(lL.getWidth()< UIUtils.dipToPx(this,270)){
                    break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WilddogSyncUtils.getInstance().cancleMatch();
        WilddogSyncUtils.getInstance().syncRefOffLine();
        //关闭计时器
        handler.removeCallbacks(runnable);
        // 关闭定时器
        timer.cancel();
    }

    private void getWilddogAuth(){
         if(System.currentTimeMillis() >= PrefUtils.getLong(TimeActivity.this, PrefUtilsCommon.TIME_STEMPS, 0)){
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {

                     Log.i("测试", "输出内容");
                     long currentTime = System.currentTimeMillis();
                     Log.i("测试", currentTime+"");
                     String currData = DataUtils.timeToData9(currentTime);
                     Log.i("测试", currData+"");
//        计算两天后的时间
//                 long twoDay = b*24*60*60*1000;
                     long mins2 = 2*60*1000;
                     long day2later = DataUtils.day2later(currentTime,mins2);
//        long lastTime = currentTime+twoDay;
                     PrefUtils.putLong(TimeActivity.this, PrefUtilsCommon.TIME_STEMPS, day2later);
                     String lastData = DataUtils.timeToData9(day2later);
                 }
             });
         }

    }

    // 无法默认执行第一次，不好用
    Handler handler = new Handler();
    Runnable runnable;
    private void checkWildDogAuth(){
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("测试", "输出内容");
            }
        };
        handler.postDelayed(runnable, 2*60*1000);//启动计时器 每两分钟执行一次runnable
    }

// 这样每次启动app都会启动一个计时器，不好用
    TimerTask task;
    Timer timer;
    private void checkWilddogTimerAuth(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Log.i("测试", "输出内容");
            }
        };
        timer.schedule(task, 0, 2*60*1000);
    }

    void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
