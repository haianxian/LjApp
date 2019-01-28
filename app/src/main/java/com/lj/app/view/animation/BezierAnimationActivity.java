package com.lj.app.view.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lj.app.R;
import com.lj.app.view.PeriscopeLayout;

/**
 * Created by 13717 on 2018/11/8.
 */

public class BezierAnimationActivity extends AppCompatActivity{

    LinearLayout fabVisitEdit;
    PeriscopeLayout heartLayout;
    PeriscopeLayout addHeartLayout;
    ImageView ivVisithallHeart;
    private Handler heartHandler = new Handler(Looper.getMainLooper());
    private boolean mOnHeartAnim;
    public static void open(Context context){
        Intent it = new Intent(context, BezierAnimationActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_animation);
        fabVisitEdit = findViewById(R.id.fab_visit_edit);
        heartLayout = findViewById(R.id.heart_layout);
        addHeartLayout = findViewById(R.id.add_heart_layout);
        ivVisithallHeart = findViewById(R.id.iv_visithall_heart);

        fabVisitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnHeartAnim) return;
                addHeartLayout.addRedHeart();
            }
        });
        addHeartLayout.setOnHeartAnimListener(new PeriscopeLayout.OnHeartAnimListener() {

            @Override
            public void onStart() {
                mOnHeartAnim = true;
            }

            @Override
            public void onFinish() {
                mOnHeartAnim = false;
                if (fabVisitEdit != null) {
                    fabVisitEdit.setBackgroundResource(R.drawable.bg_gray_circle_16_000000);
                    ivVisithallHeart.setImageResource(R.mipmap.sec_heart);
                }
            }
        });

        heartHandler.post(heartRunnable);
    }


    private Runnable heartRunnable = new Runnable() {
        @Override
        public void run() {
            if (heartLayout != null && heartHandler != null) {
                heartLayout.addHeart();
                heartHandler.postDelayed(this, 500);
            }
        }
    };
}
