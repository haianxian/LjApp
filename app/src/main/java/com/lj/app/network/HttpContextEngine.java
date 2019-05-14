package com.lj.app.network;

/**
 * Created by jf on 2017/8/18.
 */

public class HttpContextEngine extends HttpEngine {
//    //在基础头上添加新的头参数
//    public static HttpHeaders appendBaseHeaders(Map<String,String> headers){
//        HttpHeaders baseHeaders= BasePms.buildHeader();
//        if (headers==null) return baseHeaders;
//        Set<Map.Entry<String, String>> entries = headers.entrySet();
//        for (Map.Entry<String,String> entry:entries){
//            baseHeaders.put(entry.getKey(),entry.getValue());
//        }
//        LogUtil.i("requestheader:"+new Gson().toJson(baseHeaders));
//        return baseHeaders;
//    }
//
//    //基础post请求
//    private static void post(Object tag,String url,Map<String,String> getPms,Object postPms,Map<String,String> header,Callback callback){
//        String targetUrl=url;
//        //get 参数
//        if (getPms!=null){
//            targetUrl= BasePms.buildPmsUrl(url,getPms);
//        }
//        LogUtil.e("Post Url",targetUrl);
//        PostRequest postRequest = OkGo.<BaseResponse>post(targetUrl).tag(tag).headers(appendBaseHeaders(header));
//        //post 参数
//        if (postPms!=null){
//            String postpms=new Gson().toJson(postPms);
//            postRequest.upJson(postpms);
//            LogUtil.e("Post pms",postpms);
//        }else {
//            postRequest.upJson("{}");
//        }
//        postRequest.execute(callback);
//    }
//
//
////    public static void postEmpty(Object tag, String url,Map<String,String> getPms,Map<String,String> header){
////        HttpHeaders httpHeaders = appendBaseHeaders(header);
////        httpHeaders.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE,"application/json;charset=utf-8");
////        final Request request=new Request.Builder()
////                .url(url)
////                .tag(tag)
////                .post(new EmptyBody.Builder().build())
////                .headers(new Headers.Builder().build().of(httpHeaders.headersMap))
////                .build();
////        OkGo.getInstance().getOkHttpClient().newCall(request).enqueue(new okhttp3.Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                LogUtil.i("empty request","fail");
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                LogUtil.i("empty request",response.body().toString());
////            }
////        });
////    }
//
//    //基础get请求
//    private static void get(Object tag,String url,Map<String,String> getPms,Map<String,String> header, Callback callback) {
//        LogUtil.e("Get Url",url);
//        GetRequest getRequest = OkGo.<BaseResponse>get(url).tag(tag).headers(appendBaseHeaders(header));
//        if (getPms!=null){
//            getRequest.params(getPms);
//        }
//        getRequest.execute(callback);
//    }
//    //基础put请求
//    private static void put(Object tag,String url,Map<String,String> getPms,Object postPms,Map<String ,String> header,Callback callback){
//        String targetUrl=url;
//        //get 参数
//        if (getPms!=null){
//            targetUrl= BasePms.buildPmsUrl(url,getPms);
//        }
//        LogUtil.e("Put Url ",targetUrl);
//        PutRequest postRequest = OkGo.<BaseResponse>put(targetUrl).tag(tag).headers(appendBaseHeaders(header));
//        //post 参数
//        if (postPms!=null){
//            String json = new Gson().toJson(postPms);
//            LogUtil.e("Put Params ",json);
//            postRequest.upJson(json);
//        }else {
//            postRequest.upJson("{}");
//        }
//        postRequest.execute(callback);
//    }
//    //基础delete
//    private static void delete(Object tag,String url,Map<String,String> getPms,Map<String,String> postPms,Map<String ,String> header,Callback callback){
//        String targetUrl=url;
//        //get 参数
//        if (getPms!=null){
//            targetUrl= BasePms.buildPmsUrl(url,getPms);
//        }
//        LogUtil.e("Delete Url",targetUrl);
//        DeleteRequest postRequest = OkGo.<BaseResponse>delete(targetUrl).tag(tag).headers(appendBaseHeaders(header));
//        //post 参数
//        if (postPms!=null){
//            postRequest.upJson(new Gson().toJson(postPms));
//        }else {
//            postRequest.upJson("{}");
//        }
//        postRequest.execute(callback);
//    }
//
//    /**
//     * http post请求带回掉（异步线程）
//     *
//     * @param url      请求路径
//     * @param params   请求参数
//     * @param callback 请求结果回掉
//     */
//    public static void postByParams(Object tag,String url,  Map<String, String> params, Callback callback) {
//        if (params!=null){
//            Map<String,Object> postPms=new HashMap<>();
//            Set<Map.Entry<String, String>> entries = params.entrySet();
//            for (Map.Entry<String,String> en:entries){
//                postPms.put(en.getKey(),en.getValue());
//            }
//            post(tag,url,null,postPms,null,callback);
//        }else {
//            post(tag,url,null,null,null,callback);
//        }
//    }
//    public static void postRequest(Object tag,String url,  Map<String, Object> postPms, Callback callback) {
//        post(tag,url,null,postPms,null,callback);
//    }
//    public static void postRequest(Object tag,String url,Map<String,String> getPms,Map<String,Object> postPms,Callback callback){
//        post(tag,url,getPms,postPms,null,callback);
//    }
//
//    public static void postRequestByHeader(Object tag,String url,Map<String,Object> postPms,Map<String,String> header,Callback callback){
//        post(tag,url,null,postPms,header,callback);
//    }
//
//    public static void postRequestByObject(Object tag, String url, Object params, Callback callback) {
//        post(tag, url, null, params, null, callback);
//    }
//
//    /**
//     * http get请求带回掉（异步线程）
//     *
//     * @param url      请求路径
//     * @param params   参数
//     * @param callback 请求回掉结果
//     */
//    public static void getByParams(Object tag,String url, Map<String, String> params, Callback callback) {
//        get(tag,url,params,null,callback);
//    }
//    public static void getByParams(Object tag,String url, Map<String, String> params,String key,boolean value, Callback callback) {
//        LogUtil.e("Get Url",url);
//        GetRequest getRequest = OkGo.<BaseResponse>get(url).tag(tag).headers(appendBaseHeaders(null));
//        if (params != null) {
//            LogUtil.e("Get pms",params.toString());
//        }
//        getRequest.params(params);
//        getRequest.params(key,value);
//        getRequest.execute(callback);
//    }
//    /**
//     * http get 不带参数(异步线程)
//     */
//    public static void getRequest(Object tag,String url, Callback callback) {
//        get(tag,url,null,null,callback);
//    }
//
//    public static void getRequest(Object tag,String url,Map<String,String> getPms,Map<String,String> headerPms, Callback callback) {
//        get(tag,url,getPms,headerPms,callback);
//    }
//
//    /**
//     * http get 不带参数(异步线程)
//     */
//    public static void getRequest(Object tag,String url,Map<String,String> headers, Callback callback) {
//        get(tag,url,null,headers,callback);
//    }
//
//    public static void cancel(Object tag) {
//        OkGo.getInstance().cancelTag(tag);
//    }
//    /**
//     * http put 参数为集合
//     */
//    public static void putByParams(Object tag, String url, String token , List params, Callback callback) {
////        PutRequest putRequest = OkGo.<BaseResponse>put(url).headers("X-DB-Token",token).tag(tag);
////        JSONArray jsonArray = new JSONArray(params);
////        //上传Json类型的文本
////        putRequest.upJson(jsonArray);
////        putRequest.execute(callback);
//        put(tag,url,null,params,null,callback);
//    }
//
//    public static void putRequest(Object tag,String url,Object postPms,Callback callback){
//        put(tag,url,null,postPms,null,callback);
//    }
//
//    public static void deleteRequest(Object tag,String url,Callback callback){
//        delete(tag,url,null,null,null,callback);
//    }
}
