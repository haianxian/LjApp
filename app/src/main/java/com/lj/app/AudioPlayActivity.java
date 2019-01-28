package com.lj.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lj.app.adapter.AudioAdapter;
import com.lj.app.audio.AudioPlayView;
import com.lj.app.bean.AudioData;

import java.util.ArrayList;
import java.util.List;

/**https://www.qs5.org/tools/music_proxy/y.qq.php?id=001QvW0j49w3ZT/v.mp3
 * Created by 13717 on 2018/12/20.
 */

public class AudioPlayActivity extends Activity{

    AudioPlayView audioplay;
    RecyclerView recyclerView;
    AudioAdapter audioAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioplayview);
        audioplay = findViewById(R.id.audioplay);
        recyclerView = findViewById(R.id.recyclerview);
        List<AudioData> list = new ArrayList<>();
        AudioData audioData = new AudioData();
        audioData.setPlayTime(20);
        audioData.setPlayUri("http://m10.music.126.net/20181219191441/2f40c3a4015d6d970ec28913bb594134/ymusic/f351/ae94/e2e6/9688d9e8a5fdb917efbadbb57b5e6ecd.mp3");
        list.add(audioData);
        for (int i=0; i< 10; i++){
            AudioData audioData2 = new AudioData();
            audioData2.setPlayTime(20);
            audioData2.setPlayUri("https://www.qs5.org/tools/music_proxy/y.qq.php?id=001QvW0j49w3ZT/v.mp3");
            list.add(audioData2);
        }
        audioAdapter = new AudioAdapter(this,list);
        recyclerView.setAdapter(audioAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        playAudio();
    }

    private void playAudio(){
        // 播放网络音频
        audioplay.init(20, "https://www.qs5.org/tools/music_proxy/y.qq.php?id=001QvW0j49w3ZT/v.mp3");
        // 播放本地
//        audioplay.initLocal(20);
    }
}
