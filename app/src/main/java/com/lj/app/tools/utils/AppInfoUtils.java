package com.lj.app.tools.utils;


/**
 * Created by jf on 2017/9/30.
 */

public class AppInfoUtils {

//    public static String getVName(){
//        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
//            return packageInfo.versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "1.0.0";
//    }
//
//    public static int getVCode(){
//        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
//            return packageInfo.versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return 1;
//    }
//
//    public static String getChannName(){
//        final String channelName=getMetaData(BuildConfig.app_channel_key);
//        return channelName;
//    }
//
//    public static String getMetaData(String key) {
//        String resultData = null;
//        try {
//            PackageManager packageManager = MyApplication.getInstance().getPackageManager();
//            if (packageManager != null) {
//                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(MyApplication.getInstance().getPackageName(), PackageManager.GET_META_DATA);
//                if (applicationInfo != null) {
//                    if (applicationInfo.metaData != null) {
//                        resultData = applicationInfo.metaData.getString(key);
//                    }
//                }
//
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return resultData;
//    }
//
//    public static boolean stackResumed(Context context) {
//        ActivityManager manager = (ActivityManager) context
//                .getApplicationContext().getSystemService(
//                        Context.ACTIVITY_SERVICE);
//        String packageName = context.getApplicationContext().getPackageName();
//        List<ActivityManager.RunningTaskInfo> recentTaskInfos = manager.getRunningTasks(1);
//        if (recentTaskInfos != null && recentTaskInfos.size() > 0) {
//            ActivityManager.RunningTaskInfo taskInfo = recentTaskInfos.get(0);
//            if (taskInfo.baseActivity.getPackageName().equals(packageName) && taskInfo.numActivities > 1) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    protected static final String PREFS_FILE = "gank_device_id.xml";
//    protected static final String PREFS_DEVICE_ID = "gank_device_id";
//    protected static String uuid;
//    public static String getUDID(Context ctx) {
//        if(uuid == null ) {
//            synchronized (AppInfoUtils.class) {
//                if(uuid == null) {
//                    final SharedPreferences prefs = ctx.getSharedPreferences(PREFS_FILE, 0);
//                    final String id = prefs.getString(PREFS_DEVICE_ID, null );
//
//                    if (id != null) {
//                        // Use the ids previously computed and stored in the prefs file
//                        uuid = id;
//                        if ("f522a066-516b-31d0-bdb7-09c0630811d5".equalsIgnoreCase(uuid)) {
//                            uuid = UUID.randomUUID().toString();
//                            // Write the value out to the prefs file
//                            prefs.edit().putString(PREFS_DEVICE_ID, uuid).apply();
//                        }
//                    } else {
//
//                        final String androidId = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
//                        boolean isSave = true;
//                        // Use the Android ID unless it's broken, in which case fallback on deviceId,
//                        // unless it's not available, then fallback on a random number which we store
//                        // to a prefs file
//                        try {
//                            if (!"9774d56d682e549c".equals(androidId)) {
//                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString();
//                            } else {
//                                if(ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE ) != PackageManager.PERMISSION_GRANTED) {
//                                    isSave = false;
//                                }
//
//                                final String deviceId = isSave ? ((TelephonyManager) ctx.getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId() : null;
//                                uuid = deviceId != null
//                                        && !"0123456789abcdef".equals(deviceId.toLowerCase())
//                                        && !"000000000000000".equals(deviceId.toLowerCase())
//                                        ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")).toString() : UUID.randomUUID().toString();
//                            }
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                        if ("f522a066-516b-31d0-bdb7-09c0630811d5".equalsIgnoreCase(uuid)) {
//                            uuid = UUID.randomUUID().toString();
//                        }
//                        // Write the value out to the prefs file
//                        prefs.edit().putString(PREFS_DEVICE_ID, uuid).apply();
//                    }
//
//
//                }
//            }
//        }
//        return uuid;
//    }
}
