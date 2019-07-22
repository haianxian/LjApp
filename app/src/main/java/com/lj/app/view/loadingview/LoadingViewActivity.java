package com.lj.app.view.loadingview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
// https://blog.csdn.net/qq_24675479/article/details/79429473
//花束直播加载动画
public class LoadingViewActivity extends AppCompatActivity {

    public static void open(Context context){
        Intent it = new Intent(context,LoadingViewActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadingViewLayout layout = new LoadingViewLayout(this);
        setContentView(layout);
    }
}
