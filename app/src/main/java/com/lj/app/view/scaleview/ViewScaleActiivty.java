package com.lj.app.view.scaleview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;

public class ViewScaleActiivty extends AppCompatActivity {

    TextView tvWgId;
    VlightValueView mVlightValueView;

    public static void open(Context context){
        Intent it = new Intent(context,ViewScaleActiivty.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scale);
        tvWgId = findViewById(R.id.tv_wg_id);
        mVlightValueView = findViewById(R.id.vlightvalue_root_view);
        tvWgId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVlightValueView();
            }
        });

//        mVlightValueView.setmVlightValueListen(new VlightValueView.VlightValueListen() {
//            @Override
//            public void nativeBack() {
//                if(mVlightValueView != null && mVlightValueView.getVisibility() == View.VISIBLE){
//                    mVlightValueView.viewBack();
//                    setVlightScaleAnim();
//                }
//            }
//        });
    }

    public void showVlightValueView(){
        if(mVlightValueView != null){
            mVlightValueView.measureTargetView(tvWgId);
            mVlightValueView.setVisibility(View.VISIBLE);
            mVlightValueView.initH5WebView();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0) {
            LogUtil.i("BaseActivity","back pressed");
            leftBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void leftBack(){
        if(mVlightValueView != null && mVlightValueView.getVisibility() == View.VISIBLE){
            mVlightValueView.viewBack();
            setVlightScaleAnim();
        }
    }

    //    设置微光号缩放动画
    public void setVlightScaleAnim(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVlightViewScale();
            }
        },500);
    }
    private void setVlightViewScale(){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvWgId,"scaleX",1f,1.2f,1.2f,1.2f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvWgId,"scaleY",1f,1.2f,1.2f,1.2f,1f);
        scaleX.setDuration(500);
        scaleY.setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX,scaleY);
        set.start();
    }
}
