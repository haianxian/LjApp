package com.lj.app.view.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lj.app.R;
import com.lj.app.tools.utils.AnimationUtils;
import com.lj.app.tools.utils.DotsView;

/**
 * Created by 13717 on 2018/11/6.
 */

public class LightAnimationActivity extends AppCompatActivity{

    FrameLayout frameLayout;
    DotsView dotsView;
    TextView textView;
    public static void open(Context context){
        Intent it = new Intent(context, LightAnimationActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_animation);
        initView();
        setView();
    }

    private void initView(){
        frameLayout = findViewById(R.id.dynamic_light_layout);
        dotsView = findViewById(R.id.vDotsView);
        textView = findViewById(R.id.dynamic_light_text);
    }

    private void setView(){
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationUtils.lightAnim(dotsView,textView);
            }
        });
    }
}
