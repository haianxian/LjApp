package com.lj.app.tools.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lj.app.base.app.MyApplication;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * UI工具类（屏幕适配）
 * Created by lj on 2017/b/18.
 */

public class UIUtils {
    public static int SCREEN_W;
    public static int SCREEN_H;
    public static int SCREEN_FULL_H;
    public static int ST_BAR_HEIGHT;
    public static int NAVI_BAR_HEIGHT;
    public static void init(Application app){
        SCREEN_W=getScreenWidthPixels(app.getApplicationContext());
        SCREEN_H=getScreenHeightPixels(app.getApplicationContext());
        ST_BAR_HEIGHT=getStatusHeight(app);
        NAVI_BAR_HEIGHT=getNaviHeight(app);
        SCREEN_FULL_H=SCREEN_H+ST_BAR_HEIGHT+NAVI_BAR_HEIGHT;
    }

    /**
     * 获得手机屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    /**
     * 获得手机屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public static int getNaviHeight(Context context) {
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }

    static public int dipToPx(Context context, float dip) {
        float desity = getScreenDensity(context);
        return (int) (dip * desity + 0.5f);
    }
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }


    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, MyApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static int dip2px(int dp){
        float desity = getScreenDensity(MyApplication.getInstance().getApplicationContext());
        return (int) (dp * desity + 0.5f);
    }
    /**
     * px转dip
     * @param context
     * @return
     */
    public static int px2dip(Context context, float pxValue){
        float desity = getScreenDensity(context);
        return (int)(pxValue / desity + 0.5f);
    }
    public static int px2dip(float pxValue){
        return px2dip(MyApplication.getInstance(),pxValue);
    }
    /**
     * 获取屏幕密度
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0){
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 判断设备是否是低配机
     * @param context
     * @return
     */

    public static boolean isLowDevice(Context context) {
        // Log.i("PocoUtils","NativeHeapSizeTotal:"+(Debug.getNativeHeapSize()>>10));
        // Log.i("PocoUtils","NativeAllocatedHeapSize:"+(Debug.getNativeHeapAllocatedSize()>>10));
        // Log.i("PocoUtils","NativeAllocatedFree:"+(Debug.getNativeHeapFreeSize()>>10));
        // 屏幕宽度小于480
        int screenWidth = Math.min(getScreenWidthPixels(context), getScreenWidthPixels(context));
        if (screenWidth < 480)
            return true;
        // RAM小于512M
        int totalRamSize = (int) (getTotalRAMSize() / 1024 / 1024);
        if (totalRamSize <= 400)
            return true;
        // int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024/1024);
        // Log.i("PocoUtils", "maxMemory:" + maxMemory);
        // if (maxMemory<=32) return true;// (虚拟机)最大内存少于等于32M的手机
        return false;
    }

    // RAM 总大小
    public static long getTotalRAMSize() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long totalSize = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("\\s+");
            // 获得系统总内存，单位是KB，乘以1024转换为Byte
            totalSize = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return totalSize;
    }

    /**
     * 设置屏幕的透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(Context context,float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1n.0
        ((Activity)context).getWindow().setAttributes(lp);
    }

    /**
     * 设置屏幕全屏
     */
    public static void setWindowFullScreen(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
    }

    /**
     * 隐藏虚拟按键，并且全屏(适配华为有虚拟按键的时候图片被压缩)
     */
    public static void hideBottomUIMenu(Activity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
//      //设置虚拟按键透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            // Android 5.0 以上 全透明
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            // 状态栏（以上几行代码必须，参考setStatusBarColor|setNavigationBarColor方法源码）
//            window.setStatusBarColor(Color.TRANSPARENT);
//            // 虚拟导航键
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // Android 4.4 以上 半透明
//            Window window = getWindow();
//            // 状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 虚拟导航键
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
    }

    public static double mapValueFromRangeToRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return toLow + ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));
    }

    public static double clamp(double value, double low, double high) {
        return Math.min(Math.max(value, low), high);
    }
}
