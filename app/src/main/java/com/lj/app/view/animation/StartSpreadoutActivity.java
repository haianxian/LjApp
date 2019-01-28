package com.lj.app.view.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lj.app.R;


public class StartSpreadoutActivity extends AppCompatActivity{

    public static void open(Context context){
        Intent it = new Intent(context, StartSpreadoutActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startspreadout);
    }
}
