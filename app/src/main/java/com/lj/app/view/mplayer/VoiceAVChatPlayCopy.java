package com.lj.app.view.mplayer;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.AUDIO_SERVICE;

public class VoiceAVChatPlayCopy {
    private static List<MediaPlayer> mediaPlayers = new ArrayList<>(3);

    private static MediaPlayer mPlayer;
    private static boolean playBeep;
    private static int playId;

    /**
     * 播放声音
     * @param context xxx
     * @param resId 资源id
     */
    public static void playBeep(Activity context, int resId) {
        playBeep = true;
        AudioManager audioService = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        if (playBeep && mPlayer != null && playId == resId) {
            start();
        } else {
            init(context, resId);
        }
    }

    /**
     * 暂停当前对象
     */
    public static void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            playBeep = false;
        }
    }

    /**
     * 暂停所有缓存对象
     */
    public static void pauseAll() {
        for (MediaPlayer player : mediaPlayers) {
            player.pause();
        }
    }

    /**
     * 播放
     */
    private static void start() {
        mPlayer.start();
    }

    /**
     * 清除当前播放资源对象
     */
    public static void remove() {
        mediaPlayers.remove(mPlayer);
        mPlayer = null;
        playBeep = false;
        playId = 0;
    }

    /**
     * 清空播放过的资源对象
     */
    public static void removeAll() {
        if (mediaPlayers.isEmpty()) {
            return;
        }
        for (MediaPlayer player : mediaPlayers) {
            mediaPlayers.remove(player);
        }
    }

    private static void init(Activity context, int resId) {
        init(context, resId, false, new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        }, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
            }
        });
    }

    /**
     * 初始化
     *
     * @param context            activity
     * @param resId              raw id
     * @param loop               true循环
     * @param preparedListener   监听
     * @param completionListener 监听
     */
    private static void init(Activity context, int resId, boolean loop, MediaPlayer.OnPreparedListener preparedListener, MediaPlayer.OnCompletionListener completionListener) {
        context.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnCompletionListener(completionListener);
        mPlayer.setLooping(loop);
        AssetFileDescriptor file = context.getResources().openRawResourceFd(resId);
        playId = resId;
        try {
            mPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
            file.close();
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(preparedListener);
            mediaPlayers.add(mPlayer);
        } catch (IOException e) {
            remove();
        }
    }
}
