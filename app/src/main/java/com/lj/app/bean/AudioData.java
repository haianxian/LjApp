package com.lj.app.bean;

/**
 * Created by 13717 on 2018/12/20.
 */

public class AudioData {

    private long playTime;
    private String playUri;
    private boolean isLight;

    public long isPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public String getPlayUri() {
        return playUri;
    }

    public void setPlayUri(String playUri) {
        this.playUri = playUri;
    }

    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }
}
