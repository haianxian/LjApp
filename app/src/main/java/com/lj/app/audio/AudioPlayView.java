package com.lj.app.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;
import com.lj.app.tools.utils.UIUtils;

import java.io.IOException;

/**
 * Created by 13717 on 2018/12/19.
 */

public class AudioPlayView extends FrameLayout implements AudioPlayManager.AudioPlayListen {

    private ViewGroup bgLl;
    private ImageView animIv;
    private TextView timeTv;
    private long playTime;
    private String playUri;
    private Context context;
    // 最大音频背景长度
    private int maxLength = 230;
    // 最小音频背景长度
    private int minlength = 70;
    private int bgWidth = 0;
    AssetFileDescriptor fd;
    private boolean isPlaying = false;

    public AudioPlayView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AudioPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AudioPlayView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_audioplayview, this, true);
        bgLl = rootView.findViewById(R.id.audio_play_ll);
        animIv = rootView.findViewById(R.id.audio_play_anim_iv);
        timeTv = rootView.findViewById(R.id.audio_play_time_tv);
//        AudioPlayManager.getInstance().setAudioPlayListen(this);
    }

    public void init(long playTime, String playUri) {
        this.playTime = playTime;
        this.playUri = playUri;
        setData();
    }

        public void initLocal(long playTime){
        this.playTime = playTime;
        try {
            fd = context.getAssets().openFd("myrecording_2.aac");
            setData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        ViewGroup.LayoutParams params = bgLl.getLayoutParams();
        if (playTime >= 1) {
            float mid = ((maxLength - minlength) / 60) * (playTime - 1) + minlength;
            bgWidth = UIUtils.dip2px((int) mid);
        } else {
            bgWidth = UIUtils.dip2px(70);
        }
        params.width = bgWidth;
        bgLl.setLayoutParams(params);
        timeTv.setText(String.valueOf(playTime) + "″");
        bgLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    LogUtil.i("音频播放>>", "正在播放中");
                    stopPlay();
                } else {
                    startPlay();
                }
            }
        });
    }

    public void startPlay() {
        LogUtil.i("音频播放>>", "开始播放");
        isPlaying = true;
        animIv.setImageResource(R.drawable.audio_play_frame);
        AnimationDrawable startAnim = (AnimationDrawable) animIv.getDrawable();
        startAnim.start();
        AudioPlayManager.getInstance().start(playUri, this);
        //        try {
//            AudioPlayManager.getInstance().setStartLocal(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void stopPlay() {
        LogUtil.i("音频播放>>", "停止播放");
        isPlaying = false;
//        AnimationDrawable stopAnim = (AnimationDrawable) animIv.getDrawable();
//        stopAnim.stop();
//        animIv.setImageResource(R.mipmap.ic_video_three);
        AudioPlayManager.getInstance().stop();
    }


    @Override
    public void onPlayComplet() {
        isPlaying = false;
        LogUtil.i("音频播放>>", "播放完成");
        if(animIv.getDrawable() instanceof  AnimationDrawable){
            AnimationDrawable stopAnim = (AnimationDrawable) animIv.getDrawable();
            stopAnim.stop();
        }
        animIv.setImageResource(R.mipmap.ic_video_three);
    }
}
