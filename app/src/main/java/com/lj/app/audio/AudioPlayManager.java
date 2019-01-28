package com.lj.app.audio;

import android.media.MediaPlayer;

import com.lj.app.tools.utils.LogUtil;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13717 on 2018/12/19.
 */

public class AudioPlayManager {

    public static final String TAG = "AudioPlayManager>>>";
    private static AudioPlayManager audioPlayManager;
    private MediaPlayer mediaPlayer;

    private AudioPlayManager() {
        mediaPlayer = new MediaPlayer();
        // 设置不循环播放
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogUtil.i(TAG, "播放错误>>"+"what>>"+what+"extra>>"+extra);
                if(mediaPlayer != null){
                    mediaPlayer.reset();
                }
                return false;
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.i(TAG, "播放完成");
                if(mediaPlayer != null){
                    mediaPlayer.reset();
                }
                if(audioPlayListen != null){
                   audioPlayListen.onPlayComplet();
                }
//                for(AudioPlayListen aplayListen: audioPlayListenList){
//                    if(aplayListen != null){
//                        aplayListen.onPlayComplet();
//                    }
//                }
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                LogUtil.i(TAG, "网络流媒体缓存进度>>"+percent);
            }
        });
    }

    public static AudioPlayManager getInstance() {
        if (audioPlayManager == null) {
            audioPlayManager = new AudioPlayManager();
        }
        return audioPlayManager;
    }

    // 播放网络资源
    public void start(String uri, AudioPlayListen audioPlay){
        if(mediaPlayer == null) return;

        if(audioPlayListen != null){
            audioPlayListen.onPlayComplet();
        }
        audioPlayListen = audioPlay;

//        for(AudioPlayListen aplayListen: audioPlayListenList){
//            if(aplayListen != null){
//                aplayListen.onPlayComplet();
//            }
//        }
//
//        audioPlayListenList.add(audioPlayListen);
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(uri);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    LogUtil.i(TAG, "加载完毕开始播放");
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            LogUtil.i(TAG, "播放异常IllegalStateException"+e.toString());
        }
    }

    // 播放本地文件
    public void setStartLocal(FileDescriptor fd, long offset, long length) throws IOException{
        if(mediaPlayer == null) return;
        mediaPlayer.setDataSource(fd, offset, length);
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                LogUtil.i(TAG, "加载完毕开始播放");
                mediaPlayer.start();
            }
        });

    }

    public void stop() {
        LogUtil.i(TAG, "停止播放");
        if(mediaPlayer == null) return;
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
        if(audioPlayListen != null){
            audioPlayListen.onPlayComplet();
            audioPlayListen = null;
        }
//        for(AudioPlayListen aplayListen: audioPlayListenList){
//            if(aplayListen != null){
//                aplayListen.onPlayComplet();
//            }
//        }
    }

    public boolean isPlaying() {
        if(mediaPlayer == null) return false;
        LogUtil.i(TAG, "播放中");
       return mediaPlayer.isPlaying();
    }

    public void destroy() {
        // 释放MediaPlayer对象
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            audioPlayManager = null;
        }
    }

    AudioPlayListen audioPlayListen;
    List<AudioPlayListen> audioPlayListenList = new ArrayList<>();
    private void addAudioPlayListen(AudioPlayListen audioPlayListen){
        audioPlayListenList.add(audioPlayListen);
    }

    private void removeAudioPlayListen(AudioPlayListen audioPlayListen){
        audioPlayListenList.remove(audioPlayListen);
    }

    interface AudioPlayListen{
        void onPlayComplet();
    }
    public AudioPlayListen getAudioPlayListen() {
        return audioPlayListen;
    }

    public void setAudioPlayListen(AudioPlayListen audioPlayListen) {
        this.audioPlayListen = audioPlayListen;
    }

}
