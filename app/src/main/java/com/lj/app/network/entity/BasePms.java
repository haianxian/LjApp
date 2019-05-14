package com.lj.app.network.entity;

/**
 * Created by jf on 2017/9/30.
 * x-vlight-os-name // 系统名称ios android
 x-vlight-os-version // 系统版本
 x-vlight-market // 发布渠道 appstore adhoc mainsite
 x-vlight-version //app版本号1.0.0
 x-vlight-user-id //已登录的用户Id，1
 */

public class BasePms {
//    public static HttpHeaders buildHeader(){
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.put("x-vlight-os-name","android");
//        httpHeaders.put("x-vlight-version", AppInfoUtils.getVName());
//        httpHeaders.put("x-vlight-market", AppInfoUtils.getChannName());
//        httpHeaders.put("x-vlight-os-version", String.valueOf(Build.VERSION.SDK_INT));
//        if (UserInfoHelper.getUserId()>0){
//            httpHeaders.put("x-vlight-user-id",String.valueOf(UserInfoHelper.getUserId()));
//        }
//        httpHeaders.put("x-vlight-udid",AppInfoUtils.getUDID(MyApplication.getInstance()));
//        final String token=UserInfoHelper.getToken();
//        if (!TextUtils.isEmpty(token)){
//            httpHeaders.put("X-DB-Token",token);
//        }
//        final String clientId = UserInfoHelper.getClientId();
//        if (!TextUtils.isEmpty(clientId)){
//            httpHeaders.put("x-vlight-gtcid",clientId);
//        }
//
//        LogUtil.i("baseheader",new Gson().toJson(httpHeaders.headersMap));
//        return httpHeaders;
//    }
//
//    public static void buildJSHeader(V8Object obj) {
//        if (obj != null) {
//            obj.add("x-vlight-os-name","android");
//            obj.add("x-vlight-version", AppInfoUtils.getVName());
//            obj.add("x-vlight-market",AppInfoUtils.getChannName());
//            obj.add("x-vlight-os-version", String.valueOf(Build.VERSION.SDK_INT));
//            obj.add("x-vlight-user-id",String.valueOf(UserInfoHelper.getUserId()));
//            final String token=UserInfoHelper.getToken();
//            if (!TextUtils.isEmpty(token)){
//                obj.add("X-DB-Token",token);
//            }
//        }
//    }
//
//    public static String buildPmsUrl(String url,Map<String ,String > pms){
//        if (pms==null) return url;
//        try {
//            StringBuilder sb = new StringBuilder();
//            sb.append(url);
//            if (url.indexOf('&') > 0 || url.indexOf('?') > 0) sb.append("&");
//            else sb.append("?");
//            for (Map.Entry<String, String> urlParams : pms.entrySet()) {
//                String urlValues = urlParams.getValue();
//                String urlValue = URLEncoder.encode(urlValues, "UTF-8");
//                sb.append(urlParams.getKey()).append("=").append(urlValue).append("&");
//            }
//            sb.deleteCharAt(sb.length() - 1);
//            return sb.toString();
//        } catch (UnsupportedEncodingException e) {
//            OkLogger.printStackTrace(e);
//        }
//        LogUtil.i("requesturl",url);
//        return url;
//    }
//
//    public static String buildPmsUrl(String url,int page,int size){
//        Map<String,String> getpms=new HashMap<>();
//        getpms.put("page",String.valueOf(page));
//        getpms.put("size",String.valueOf(size));
//        return buildPmsUrl(url,getpms);
//    }
}
