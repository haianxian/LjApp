package com.lj.app.view.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lj.app.R;

/**
 * Created by 13717 on 2018/11/6.
 */

public class AnimationActivity extends AppCompatActivity{

    Button lightBtn;
    Button startBtn;
    Button jumpBtn;
    Button bezierBtn;
    Button circlePercentBtn;
    public static void open(Context context){
        Intent it = new Intent(context,AnimationActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        setView();
    }

    private void initView(){
        lightBtn = findViewById(R.id.animation_light_btn);
        startBtn = findViewById(R.id.animation_start_btn);
        jumpBtn = findViewById(R.id.animation_jump_btn);
        bezierBtn = findViewById(R.id.animation_bezier_btn);
        circlePercentBtn = findViewById(R.id.circle_percent_btn);
    }

    private void setView(){
        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LightAnimationActivity.open(AnimationActivity.this);
            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartSpreadoutActivity.open(AnimationActivity.this);
            }
        });
        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpSpringActivity.open(AnimationActivity.this);
            }
        });
        bezierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BezierAnimationActivity.open(AnimationActivity.this);
            }
        });
        circlePercentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AnimationActivity.this, CirclePercentActivity.class);
                startActivity(it);
            }
        });
    }
}
