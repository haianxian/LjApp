package com.lj.app.view.BezierView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SecondBezierActivity extends AppCompatActivity {

    public static void open(Context context){
        Intent it = new Intent(context,SecondBezierActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SecondBezierView view = new SecondBezierView(this);
        setContentView(view);
    }
}
