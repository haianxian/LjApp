package com.lj.app.view.video;

import android.media.MediaPlayer;

public class VoiceAVChatPlay {

    public static final String TAG = "VoiceAVChatPlay>>";
    private static VoiceAVChatPlay instance;
    private MediaPlayer mPlayer;
    public static VoiceAVChatPlay getInstance(){
        if(instance == null){
            instance = new VoiceAVChatPlay();
        }
        return instance;
    }

    private VoiceAVChatPlay(){
//        mPlayer = MediaPlayer.create(MyApplication.getInstance(),com.netease.nim.uikit.R.raw.voice_invita);
//        LogUtil.i(TAG, "实例化MediaPlayer>>"+mPlayer);
////        mPlayer = new MediaPlayer();
//        if(mPlayer != null){
//            mPlayer.setLooping(true);
//        }
    }

    public void start(){
//        if (PrefUtils.getBoolean(MyApplication.getInstance(), PreUtilsConstant.IM_SOUND_OPEN, true)) {
//            LogUtil.i(TAG, "是否能播放"+mPlayer);
//            if(mPlayer == null){
//                mPlayer = MediaPlayer.create(MyApplication.getInstance(),com.netease.nim.uikit.R.raw.voice_invita);
//            }
//            if (mPlayer != null && !mPlayer.isPlaying()) {
//                LogUtil.i(TAG, "开始播放");
//                mPlayer.reset();
//                mPlayer.start();
//                mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//                    @Override
//                    public boolean onError(MediaPlayer mp, int what, int extra) {
//                        LogUtil.i(TAG, "播放错误>>"+"what>>"+what+"extra>>"+extra);
//                        if(mPlayer != null){
//                            mPlayer.reset();
//                        }
//                        return false;
//                    }
//                });
//            }
//        }
    }

    public void stop(){
//        LogUtil.i(TAG, "停止语音播放");
//        if(mPlayer != null && mPlayer.isPlaying()){
//            mPlayer.stop();
//        }
    }

    public void destroyPlayer(){
//        if(mPlayer != null){
//            mPlayer.stop();
//            mPlayer.release();
//            mPlayer = null;
//            LogUtil.i(TAG, "销毁语音播放器");
//        }
    }


}
