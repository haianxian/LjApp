package com.lj.app.bean;

/**
 * Package Name：com.qingqi.dianbo.home.entity
 * Class Describe：
 * Author：Tangyan
 * Creation Time：2018/10/19 11:30
 * Modifier：
 * Modification Time：
 * Modify Describe：
 */

public class HomeRandomUser {
    private String avatar; // 头像

    private String nickname; // 昵称

    private long userId; // 用户id

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HomeRandomUser that = (HomeRandomUser) o;

        return avatar != null ? avatar.equals(that.avatar) : that.avatar == null;
    }

    @Override
    public int hashCode() {
        return avatar != null ? avatar.hashCode() : 0;
    }
}
