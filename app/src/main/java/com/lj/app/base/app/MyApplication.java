package com.lj.app.base.app;

import android.app.Application;



/**
 * Created by 13717 on 2017/12/5.
 */

public class MyApplication extends Application{

    private static MyApplication INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        //所有的初始化操作转移到后台，优化app启动速度  一些不需要立马用到的库类的初始化操作可以放在后台
        InitializeService.start(getApplicationContext());
//        WilddogOptions options = new WilddogOptions.Builder().setSyncUrl("https://wd3023140968euohlo.wilddogio.com/").build();
//        WilddogApp.initializeApp(MyApplication.getInstance(), options);
    }

    public static MyApplication getInstance(){
        return INSTANCE;
    }
}
