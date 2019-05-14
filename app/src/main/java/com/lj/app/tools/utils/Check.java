package com.lj.app.tools.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.lj.app.base.app.MyApplication;
import com.lj.app.config.AppConfig;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jf on 2017/8/23.
 */

public class Check {
    private static final String Str_def="";
    private static StringBuilder stringBuilder=new StringBuilder();
    public final static int ms_format_day_ago=110;

    private static final String IMG_WIDTH="?x-oss-process=image/resize,m_mfit,w_";
    private static final String IMG_HEIGHT=",h_";
    private static final String IMG_FORMAT="/format,";
    public static final String WEBP="webp";
    public static final String JPG="png";

    private static StringBuilder getClearStrBuilder(){
        stringBuilder.delete(0,stringBuilder.length());
        return stringBuilder;
    }

    public static String append(String a,String b){
        if (TextUtils.isEmpty(a)){
            return str(b);
        }
        if (TextUtils.isEmpty(b)){
            return str(a);
        }
        return getClearStrBuilder().append(a).append(b).toString();
    }

    public static String str(String str,String def){
        if (TextUtils.isEmpty(str)){
            return def;
        }else {
            return str;
        }
    };

    public static String str(String str){
        if (TextUtils.isEmpty(str)){
            return Str_def;
        }else {
            return str.trim();
        }
    };
    public static String str(int num){
        return String.valueOf(num);
    };

    public static int num(String num){
        if (TextUtils.isEmpty(num)){
            return 0;
        }else {
            return Integer.valueOf(num);
        }
    }

    public static String diff(long start,long end,int format){
        switch (format){
            case ms_format_day_ago:
                int day= (int) ((end-start)/1000/60/60/24);
                return day<=0?"今天":day+"天前";
        }
        return "";
    }

    public static String imgUrl(String url){
        if (TextUtils.isEmpty(url)) return "";
        if (url.startsWith("http")){
            return url;
        }else {
            return getClearStrBuilder().append(AppConfig.CURRENT_IMG_URL).append(url).toString();
        }
    }

    public static String imgUrl(String url, int width, int height){
        if (TextUtils.isEmpty(url)) return "";
        if (url.startsWith("http")){
            return url;
        }else {
            return getClearStrBuilder().append(AppConfig.CURRENT_IMG_URL).append(url)
                    .append(IMG_WIDTH)
                    .append(width)
                    .append(IMG_HEIGHT)
                    .append(height).append(IMG_FORMAT).append(WEBP).toString();
        }
    }

    public static String imgUrlJpg(String url, int width, int height){
        if (TextUtils.isEmpty(url)) return "";
        if (url.startsWith("http")){
            return url;
        }else {
            return getClearStrBuilder().append(AppConfig.CURRENT_IMG_URL).append(url)
                    .append(IMG_WIDTH)
                    .append(width)
                    .append(IMG_HEIGHT)
                    .append(height).append(IMG_FORMAT).append(JPG).toString();
        }
    }

    public static boolean imgUrlNeedBuilde(String url){
        if (TextUtils.isEmpty(url)) return false;
        if (url.startsWith("http")) return false;
        return true;
    }

    //加权算法
    public static int adjustOnlineNum(int online){
//        if (online<0) return 0;
//
//        if (online<101){
//            return (int) (online*4.97f+random(6f,9f));
//        }else if (online<5000){
//            return (int) (online*random(1f,1.33f)+random(300,400));
//        }else {
//            return (int) (online*2-random(online/1.3f,online));
//        }

        return online;
    }


//    public static String color(String rgb){
//        if (rgb.length()<6) return "#00000000";
//        String fixed=rgb.substring(rgb.length()-6);
//        return "#"+fixed;
//    }

    public static int colorAppendAlpha(String rgb,String alpha){
        if (TextUtils.isEmpty(rgb)||TextUtils.isEmpty(alpha)) return Color.TRANSPARENT;
        if (rgb.length()<6) return Color.TRANSPARENT;
        String fixed=rgb.substring(rgb.length()-6);
        try{
            final int result=Color.parseColor("#"+alpha+fixed);
            return result;
        }catch (Exception e){
            return Color.TRANSPARENT;
        }
    }

    public static int colorInt(String rgb){
        if (TextUtils.isEmpty(rgb)) return Color.TRANSPARENT;
        if (rgb.length()<6) return Color.TRANSPARENT;
        String fixed=rgb.substring(rgb.length()-6);
        try{
            final int result=Color.parseColor("#"+fixed);
            return result;
        }catch (Exception e){
            return Color.TRANSPARENT;
        }
    }

    public static int colorInt(String rgb, String alpha){
        if (TextUtils.isEmpty(rgb)) return Color.TRANSPARENT;
        if (rgb.length()<6) return Color.TRANSPARENT;
        if (TextUtils.isEmpty(alpha)) {
            alpha = "FF";
        }
        String fixed=rgb.substring(rgb.length()-6);
        try{
            final int result=Color.parseColor("#" + alpha + fixed);
            return result;
        }catch (Exception e){
            return Color.TRANSPARENT;
        }
    }

    public static int colorInt(String rgb,int defColor){
        if (TextUtils.isEmpty(rgb)) return defColor;
        if (rgb.length()<6) return defColor;
        String fixed=rgb.substring(rgb.length()-6);
        try{
            final int result=Color.parseColor("#"+fixed);
            return result;
        }catch (Exception e){
            return defColor;
        }
    }

    public static Drawable drawable(Context context,int resId,String rgb){
        GradientDrawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable= (GradientDrawable) context.getDrawable(resId);
        }else {
            drawable= (GradientDrawable) context.getResources().getDrawable(resId);
        }
        if (!TextUtils.isEmpty(rgb)){
            drawable.setColor(colorInt(rgb));
        }
        return drawable;
    }


    public static Drawable drawable(Context context,int resId,String rgb,String alpha){
        GradientDrawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable= (GradientDrawable) context.getDrawable(resId);
        }else {
            drawable= (GradientDrawable) context.getResources().getDrawable(resId);
        }
        if (!TextUtils.isEmpty(rgb)){
            drawable.setColor(colorAppendAlpha(rgb,alpha));
        }
        return drawable;
    }

    public static Drawable drawable(int resId){
        GradientDrawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable= (GradientDrawable) MyApplication.getInstance().getDrawable(resId);
        }else {
            drawable= (GradientDrawable) MyApplication.getInstance().getResources().getDrawable(resId);
        }
        return drawable;
    }

    public static double random(float left,float right){
        return Math.random()*(right-left)+left;
    }


    public static String subString(String left,String right,String content){
        if (TextUtils.isEmpty(content)) return "";
        if (TextUtils.isEmpty(left)) return "";
        if (TextUtils.isEmpty(right)) return "";

        String fixedLeft=escapeExprSpecialWord(left);
        String fixedRight=escapeExprSpecialWord(right);


        String[] splitLeft = content.split(fixedLeft);
        if (splitLeft.length>1){
            String[] splitRight = splitLeft[1].split(fixedRight);
            if (splitRight.length>0){
                LogUtil.i("sub string sub:"+splitRight[0]);
                return splitRight[0];
            }
        }
        return "";
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    public static int ms2s(long ms){
        if (ms<0) return 0;
        return (int) (ms/1000);
    }

    public static int ms2min(long ms){
        if (ms<0) return 0;
        return (int) (ms/1000/60);
    }

    public static int ms2h(long ms){
        if (ms<0) return 0;
        return (int) (ms/1000/60/60);
    }

    public static int long2int(long ll){
        return Integer.valueOf(String.valueOf(ll));
    }

    public static SpannableStringBuilder buildImgStr(String left,int imgId,String right){
//        Drawable drawable=MyApplication.getInstance().getResources().getDrawable(imgId);
//        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
//        DrawableMarginSpan drawableMarginSpan=new DrawableMarginSpan(drawable,UIUtils.dip2px(2));
        ImageSpan imageSpan=new ImageSpan(MyApplication.getInstance(),imgId,ImageSpan.ALIGN_BASELINE);
        SpannableStringBuilder builde=new SpannableStringBuilder(getClearStrBuilder().append(left).append(right));
        builde.setSpan(imageSpan,left.length(),left.length()+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return builde;
    }

    public static boolean isTxtEllipsizeEnd(String txt,TextView viewTxt){
        if (TextUtils.isEmpty(txt))return false;
        String usableStr = (String) TextUtils.ellipsize(txt, viewTxt.getPaint(), viewTxt.getMaxWidth(), TextUtils.TruncateAt.END);
        final boolean hasOversize=txt.length()>usableStr.length();
        return hasOversize;
    }

    public static String getTxtEllipsizeEnd(String txt,TextView viewTxt){
        if (TextUtils.isEmpty(txt))return "";
        String usableStr = (String) TextUtils.ellipsize(txt, viewTxt.getPaint(), viewTxt.getMaxWidth(), TextUtils.TruncateAt.END);
        return usableStr.trim();
    }



    public static void buildPrDetaiIntro(boolean expand,String intro,int maxLine,TextView viewIntro){
        if (TextUtils.isEmpty(intro)) return;
        float availableTextWidth= (UIUtils.SCREEN_W-UIUtils.dip2px(30))*maxLine-UIUtils.dip2px(50);
        String usableStr = (String) TextUtils.ellipsize(intro, viewIntro.getPaint(), availableTextWidth, TextUtils.TruncateAt.END);

        final boolean hasOversize=intro.length()>usableStr.length();

        if (!hasOversize){
//            viewIntro.setGravity(Gravity.CENTER);
            viewIntro.setText(intro);
            return;
        }

//        viewIntro.setGravity(Gravity.LEFT);
        //显示展开状态
        if (expand){
            ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.parseColor("#7C77E2"));
            SpannableStringBuilder spStrBuilder=new SpannableStringBuilder(getClearStrBuilder().append(intro).append(" 收起"));
            spStrBuilder.setSpan(colorSpan,spStrBuilder.length()-2,spStrBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            viewIntro.setText(spStrBuilder);
        }else {
            ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.parseColor("#7C77E2"));
            SpannableStringBuilder spStrBuilder=new SpannableStringBuilder(getClearStrBuilder().append(usableStr).append(" 展开"));
            spStrBuilder.setSpan(colorSpan,spStrBuilder.length()-2,spStrBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            viewIntro.setText(spStrBuilder);
        }
    }

    public static void buildDynamicContent(CharSequence content, final int maxLine, final TextView viewContent){
        if (TextUtils.isEmpty(content)) {
            viewContent.setText("");
            return;
        }

        viewContent.setText(content);
        viewContent.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        viewContent.getViewTreeObserver().removeOnPreDrawListener(this);
                        Layout layout2 = viewContent.getLayout();
                        if(layout2 != null){
                            int lines = layout2.getLineCount();
//                            LogUtil.w("xxxxx-charSub", "当前行数是 "+layout2.getLineCount());
//                            LogUtil.w("xxxxx-charSub", "被省略的字符数量是 "+layout2.getEllipsisCount(lines-1));
//                            LogUtil.w("xxxxx-charSub", "被省略的字符起始位置是 "+layout2.getEllipsisStart(lines-1));
//                            LogUtil.w("xxxxx-charSub", "最后一个可见字符的偏移是 "+layout2.getLineVisibleEnd(lines-1));
                            //开始替换系统省略号
                            if(lines < maxLine) return true;
                            if(layout2.getEllipsisCount(lines-1)==0) return true;
                            //如果被省略的字符数量为0，就不管了
//                    if (layout2.getEllipsisCount(lines-1) == 1) { // 省略了个回车
                            SpannableStringBuilder result = new SpannableStringBuilder();
                            CharSequence text=layout2.getText();
                            for(int i = 0; i < lines; i++){
                                int start=layout2.getLineStart(i);
                                int end=layout2.getLineEnd(i);
//                                LogUtil.w("xxxxx-charSub", "start: "+start+" end: "+end);
                                result.append(text.subSequence(start, end));
                            }
//                            LogUtil.w("xxxxx-charSub", "pre result: "+result+" length: "+result.length());
                            if (layout2.getEllipsisStart(lines-1) > 20) {
                                CharSequence showText = viewContent.getText();
                                try {
                                    showText = showText.subSequence(0, layout2.getLineStart(lines - 1) + layout2.getEllipsisStart(lines - 1) - 8);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                LogUtil.w("xxxxx-charSub", "center showText: "+showText+" length: "+showText.length());
                                ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.parseColor("#7C77E2"));
                                SpannableStringBuilder spStrBuilder=new SpannableStringBuilder().append("… 全文");
                                spStrBuilder.setSpan(colorSpan,spStrBuilder.length()-2,spStrBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                result = new SpannableStringBuilder(showText);
                                result.append(spStrBuilder);
//                                LogUtil.w("xxxxx-charSub", ">20 final result: "+result+" length: "+result.length());
                                viewContent.setText(result);
                            } else {
                                CharSequence showText = viewContent.getText();
                                try {
                                    showText = showText.subSequence(0, layout2.getLineStart(lines - 1) + layout2.getEllipsisStart(lines - 1));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ForegroundColorSpan colorSpan=new ForegroundColorSpan(Color.parseColor("#7C77E2"));
                                SpannableStringBuilder spStrBuilder=new SpannableStringBuilder().append("… 全文");
                                spStrBuilder.setSpan(colorSpan,spStrBuilder.length()-2,spStrBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                result = new SpannableStringBuilder(showText);
                                result.append(spStrBuilder);
//                                LogUtil.w("xxxxx-charSub", "<20 final result: "+result);
                                viewContent.setText(result);
                            }
                        }

                        return true;
                    }
                });
//        viewContent.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }


    public static boolean isImmerse(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }


    public static String obNum(int obnum){
        if (obnum<100000){
            return String.valueOf(obnum);
        }else {
            final int nnn=obnum/100000;
            return String.valueOf(nnn)+"万";
        }
    }

    //事件时间 距离当前时间差 以及单位
    public static String difTime(long actionTime){
        int min=ms2min(actionTime);
        if (min<1) return "刚刚";
        if (min<60) return min+"分钟前";
        if (min<60*24) return (min/60)+"小时前";

        String result=new SimpleDateFormat("yyyy-MM-dd").format(actionTime);
        if (TextUtils.isEmpty(result)) return "";
        return result;
    }

    public static long longNum(String time){
        if (!TextUtils.isEmpty(time)){
            try {
                return Long.parseLong(time);
            }catch (Exception e){
                return 0;
            }
        }
        return 0;
    }

    public static int intNum(String time){
        if (!TextUtils.isEmpty(time)){
            try {
                return Integer.parseInt(time);
            }catch (Exception e){
                return 0;
            }
        }
        return 0;
    }


    // 去除空格
    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }
}
