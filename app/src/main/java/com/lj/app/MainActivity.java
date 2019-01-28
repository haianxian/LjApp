package com.lj.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lj.app.view.animation.AnimationActivity;

/**
 * Created by 13717 on 2018/11/6.
 */

public class MainActivity extends AppCompatActivity{

    Button animationBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setView();
    }

    private  void initView(){
        animationBtn = findViewById(R.id.main_animation_btn);
    }

    private void setView(){
        animationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationActivity.open(MainActivity.this);
            }
        });
    }
}
