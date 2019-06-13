package com.lj.app.view.animation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lj.app.R;
import com.lj.app.tools.utils.MyInterpolator;

public class PingFenViewActivity extends Activity {

    FrameLayout fl1;
    FrameLayout fl2;
    FrameLayout fl3;
    FrameLayout fl4;

    TranslateAnimation translateAnimationx;
    TranslateAnimation translateAnimationy;
    AnimationSet animationSet;
    private ImageView chicken;

    public static void open(Context context){
        Intent it = new Intent(context, PingFenViewActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingfen_view);
        chicken = findViewById(R.id.iv);
        fl1 = findViewById(R.id.root_fl);
        fl2 = findViewById(R.id.root_fl2);
        fl3 = findViewById(R.id.root_fl3);
        fl4 = findViewById(R.id.root_fl4);
        fl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fl1.setScaleX(1.5f);
//                fl1.setScaleY(1.5f);
//                fl2.setScaleX(0.8f);
//                fl2.setScaleY(0.5f);
//                fl3.setScaleX(0.5f);
//                fl3.setScaleY(0.5f);
//                fl4.setScaleX(0.5f);
//                fl4.setScaleY(0.5f);
//                  ViewGroup.LayoutParams params = fl1.getLayoutParams();
//                  int width = fl1.getMeasuredWidth();
//                  int height = fl1.getMeasuredHeight();
//                  if(params != null){
//                      params.width = 2*width;
//                      params.height = 2*height;
//                      fl1.setLayoutParams(params);
//                  }
            }
        });
        animation();
    }

    private void animation(){
        animationSet=new AnimationSet(false);//此处必须设为false
                translateAnimationx=new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.2f,
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f );
        translateAnimationx.setDuration(5000);

        translateAnimationy=new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 1f );
        translateAnimationy.setDuration(5000);
        translateAnimationy.setInterpolator(new MyInterpolator());
        animationSet.addAnimation(translateAnimationx);
        animationSet.addAnimation(translateAnimationy);
        chicken.setAnimation(animationSet);
        animationSet.start();

    }
}
