package com.lj.app.tools.utils;

import android.text.TextUtils;
import android.util.Log;


import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;

/**
 * Created by lj on 2017/b/23.
 */

public class LogUtil {

    private static final String TAG = "电波";
    /**
     * 调试模式的标识（true:debug; false:release）
     */
//    private static boolean debug = AppConfig.LOG_ENABLE;
    private static boolean debug = true;
    private LogUtil(){
        throw new Error("工具类不要实例化！");
    }
    public static void v(String msg) {
        log(VERBOSE, null, msg);
    }
    public static void v(String tag, String msg) {
        log(VERBOSE, tag, msg);
    }
    public static void v(String tag, String msg, Throwable t) {
        logWithThrowable(VERBOSE, tag, msg, t);
    }
    public static void d(String msg) {
        log(DEBUG, null, msg);
    }
    public static void d(String tag, String msg) {
        log(DEBUG, tag, msg);
    }
    public static void d(String tag, String msg, Throwable t) {
        logWithThrowable(DEBUG, tag, msg, t);
    }
    public static void i(String msg) {
        log(INFO, null, msg);
    }
    public static void i(String tag, String msg) {
        log(INFO, tag, msg);
    }
    public static void i(String tag, String msg, Throwable t) {
        logWithThrowable(INFO, tag, msg, t);
    }
    public static void w(String msg) {
        log(WARN, null, msg);
    }
    public static void w(String tag, String msg) {
        log(WARN, tag, msg);
    }
    public static void w(String tag, String msg, Throwable t) {
        logWithThrowable(WARN, tag, msg, t);
    }
    public static void e(String msg) {
        log(ERROR, null, msg);
    }
    public static void e(String tag, String msg) {
        log(ERROR, tag, msg);
    }
    public static void e(String tag, String msg, Throwable t) {
        logWithThrowable(ERROR, tag, msg, t);
    }
    private static void log(int logLevel, String tag, String msg) {
        logWithThrowable(logLevel, tag, msg, null);
    }
    private static void logWithThrowable(int logLevel, String tag, String msg, Throwable t) {
        if (debug) {
            msg = TextUtils.isEmpty(tag) ? msg : tag + " -> " + msg;
            switch (logLevel) {
                case VERBOSE:
                    Log.v(TAG, msg, t);
                    break;
                case DEBUG:
                    Log.d(TAG, msg, t);
                    break;
                case INFO:
                    Log.i(TAG, msg, t);
                    break;
                case WARN:
                    Log.w(TAG, msg, t);
                    break;
                case ERROR:
                    Log.e(TAG, msg, t);
                    break;
            }
        }
    }
}
