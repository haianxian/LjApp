package com.lj.app.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePrefrence的封装
 * Created by lj on 2017/b/21.
 */

public class PrefUtils {

    private static SharedPreferences mPrefs;
    public static void putBoolean(Context ctx, String key, boolean value) {
        initPrefs(ctx);
        mPrefs.edit().putBoolean(key, value).commit();
    }
    /**
     * 实例化 mPrefs对象
     * @param ctx
     */
    private static void initPrefs(Context ctx) {
        if (mPrefs == null) {
            mPrefs = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
    }
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        initPrefs(ctx);
        return mPrefs.getBoolean(key, defValue);
    }

    public static void setBoolean(Context ctx, String key, boolean defValue){
        initPrefs(ctx);
        mPrefs.edit().putBoolean(key, defValue).commit();
    }
    public static void putString(Context ctx, String key, String value) {
        initPrefs(ctx);
        mPrefs.edit().putString(key, value).commit();
    }
    public static String getString(Context ctx, String key, String defValue) {
        initPrefs(ctx);
        return mPrefs.getString(key, defValue);
    }
    public static void putInt(Context ctx, String key, int value) {
        initPrefs(ctx);
        mPrefs.edit().putInt(key, value).commit();
    }
    public static int getInt(Context ctx, String key, int defValue) {
        initPrefs(ctx);
        return mPrefs.getInt(key, defValue);
    }
    public static void putLong(Context ctx, String key, long value) {
        initPrefs(ctx);
        mPrefs.edit().putLong(key, value).commit();
    }
    public static long getLong(Context ctx, String key, int defValue) {
        initPrefs(ctx);
        return mPrefs.getLong(key, defValue);
    }
    public static void remove(Context ctx, String key) {
        initPrefs(ctx);
        mPrefs.edit().remove(key).commit();
    }
    /**
     * 清除当前登录用户的一些配置信息
     * @param ctx
     */
    public static void clear(Context ctx){
        initPrefs(ctx);
        mPrefs.edit().clear().commit();
    }

}
