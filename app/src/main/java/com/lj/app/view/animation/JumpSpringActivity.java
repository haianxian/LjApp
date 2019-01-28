package com.lj.app.view.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.lj.app.R;

/**
 * Created by 13717 on 2018/11/7.
 */

public class JumpSpringActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    ImageView playListTipsIv;
    TextView helloSayTv;
    private Spring mSpring;

    public static void open(Context context) {
        Intent it = new Intent(context, JumpSpringActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumpspring);
        frameLayout = findViewById(R.id.playlist_tip_layout);
        playListTipsIv = findViewById(R.id.add_playlist_tips_iv);
        helloSayTv = findViewById(R.id.hellosay_tv);
        setSpringAnimation();
        setHelloSayBigAnimation();
        helloSayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHelloSaySmallAnimation(helloSayTv);
            }
        });
    }

    private void setSpringAnimation() {
        SpringSystem ss = SpringSystem.create();
        mSpring = ss.createSpring();
        mSpring.setCurrentValue(0);
        mSpring.setSpringConfig(new SpringConfig(30, 0));
        mSpring.addListener(new SimpleSpringListener() {

            @Override
            public void onSpringUpdate(Spring spring) {
                // You can observe the updates in the spring
                // state by asking its current value in onSpringUpdate.
                if (playListTipsIv != null) {
                    float value = (float) spring.getCurrentValue();
                    playListTipsIv.setTranslationY(value);
                }
            }
        });
        mSpring.setEndValue(12f);
    }

    private void setHelloSayBigAnimation() {
        SpringSystem ss = SpringSystem.create();
        Spring spring = ss.createSpring();
        spring.setCurrentValue(0.5);
        spring.setSpringConfig(new SpringConfig(90, 8));
        spring.addListener(new SpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float currentValue = (float) spring.getCurrentValue();
                helloSayTv.setScaleX(currentValue);
                helloSayTv.setScaleY(currentValue);
            }

            @Override
            public void onSpringAtRest(Spring spring) {

            }

            @Override
            public void onSpringActivate(Spring spring) {

            }

            @Override
            public void onSpringEndStateChange(Spring spring) {
            }
        });
        spring.setEndValue(1);
    }

    private void setHelloSaySmallAnimation(final TextView helloSayTv){
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(helloSayTv,"scaleX",1f,0f);
//        ObjectAnimator transX=ObjectAnimator.ofFloat(helloSayTv, "translationX", -200,0);
        set.playTogether(scaleX);
        set.setDuration(1000).start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                helloSayTv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
