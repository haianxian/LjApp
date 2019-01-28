package com.lj.app.tools.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式工具类
 *
 * @author Administrator
 */
public class DataUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("MM月dd日 HH:mm");
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy年");
    private static SimpleDateFormat sdf6 = new SimpleDateFormat("MM月");
    private static SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy年MM月");
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat sdf9 = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * 日期转时间戳
     *
     * @param data
     * @return
     */
    public static long dataToTime(String data) {
        long time = 0;
        try {
            time = sdf.parse(data).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 日期转时间戳
     * @param
     * @return
     */
    public static long dataToTime_sdf10(String data){
        long time = 0;
        try {
            time = sdf10.parse(data).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String DateToForm(Date d) {
        return sdf.format(d);
    }

    public static String DateToForm_sdf10(Date date){
        return sdf10.format(date);
    }

    public static String timeToData(long time) {
        String data = sdf.format(time * 1000);
        return data;
    }

    public static String timeToData1(long time) {
        String data = sdf1.format(time * 1000);
        return data;
    }

    public static String timeToData2(long time) {
        String data = sdf2.format(time * 1000);
        return data;
    }

    public static String timeToData3(long time) {
        String data = sdf3.format(time * 1000);
        return data;
    }

    public static String timeToData4(long time) {
//        String data = sdf4.format(time * 1000);
        String data = sdf4.format(time);
        return data;
    }

    public static String timeToDataSdf(long time) {
        String data = sdf4.format(time);
        return data;
    }


    public static String timeToData5(long time) {
        String data = sdf5.format(time * 1000);
        return data;
    }

    public static String timeToData6(long time) {
        String data = sdf6.format(time * 1000);
        return data;
    }

    public static String timeToData7(long time) {
        String data = sdf7.format(time * 1000);
        return data;
    }

    public static String timeToData8(long time) {
        String data = sdf8.format(time * 1000);
        return data;
    }

    public static String timeToData9(long time) {
//        String data = sdf9.format(time * 1000);
        String data = sdf11.format(time);
//        Date date=null;
//        try {
//            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return data;
    }
    /**
     * 根据时间戳得到年龄
     */
    public static String getAge(long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        int year = calendar.get(Calendar.YEAR);    //获取年
        int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        long currentTime = System.currentTimeMillis();
        calendar.setTimeInMillis(currentTime);
        int curYear = calendar.get(Calendar.YEAR);    //获取年
        int curMonth = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

        return curYear - year+"岁";
    }

    /**
     * 根据当前时间算出几天后的时间戳
     * @param currTims 当前时间戳
     * @param addTime  几天后的时间  例如格式：b*24*60*60*1000
     * @return
     */
    public static long day2later(long currTims, long addTime){
      long time = 0;
//       String data = sdf9.format(new Date(d.getTime() + 3 * 24 * 60 * 60 * 1000));
        String data = sdf9.format(new Date(currTims + addTime));
        try {
            time = sdf9.parse(data).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 根据时间戳得到星座
     */
    public static String getConstellation(long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        int year = calendar.get(Calendar.YEAR);    //获取年
        int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数null
        String str = "";
        switch (month){
            case 1:
                if (day<20){
                    str = "摩羯座";
                }else str = "水瓶座";
                break;
            case 2:
                if (day<19){
                    str = "水瓶座";
                }else str = "双鱼座";
                break;
            case 3:
                if (day<21){
                    str = "双鱼座";
                }else str = "白羊座";
                break;
            case 4:
                if (day<20){
                    str = "白羊座";
                }else str = "金牛座";
                break;
            case 5:
                if (day<21){
                    str = "金牛座";
                }else str = "双子座";
                break;
            case 6:
                if (day<22){
                    str = "双子座";
                }else str = "巨蟹座";
                break;
            case 7:
                if (day<23){
                    str = "巨蟹座";
                }else str = "狮子座";
                break;
            case 8:
                if (day<23){
                    str = "狮子座";
                }else str = "处女座";
                break;
            case 9:
                if (day<23){
                    str = "处女座";
                }else str = "天秤座";
                break;
            case 10:
                if (day<24){
                    str = "天秤座";
                }else str = "天蝎座";
                break;
            case 11:
                if (day<23){
                    str = "天蝎座";
                }else str = "射手座";
                break;
            case 12:
                if (day<22){
                    str = "射手座";
                }else str = "摩羯座";
                break;
        }
        return str;
    }

    //获取当前时间戳和给定时间戳的差值
    public static String getTimeDes(long addTime) {
        long currentTime = System.currentTimeMillis() / 1000;
        long timeCuo = currentTime - addTime;
        long min = 60;
        long hour = 60 * min;
        long day = hour * 24;

//		if(timeCuo>365*day){
//			return sdf.format(new Date(addTime));
//		}
//
//		if(timeCuo>b*day){
//			return sdf3.format(new Date(addTime));
//		}
        // 大于等于24小时 显示时间格式是"yyyy-MM-dd"
        if (timeCuo > day) {
            return sdf.format(new Date(addTime));
        }

        //大于1小时且小于24小时
        if (timeCuo > hour) {
            return (timeCuo / hour) + "小时前";
        }

        //大于1分钟小于1小时
        if (timeCuo > min) {
            return (timeCuo / min) + "分钟前";
        }
        return "刚刚";
    }

}
