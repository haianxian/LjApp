package com.lj.app.network;

/**
 * Created by 13717 on 2017/8/14.
 */

public class HttpEngine {
//
//    //在基础头上添加新的头参数
//    public static HttpHeaders appendBaseHeaders(Map<String,String> headers){
//        HttpHeaders baseHeaders= BasePms.buildHeader();
//        if (headers==null) return baseHeaders;
//        Set<Map.Entry<String, String>> entries = headers.entrySet();
//        for (Map.Entry<String,String> entry:entries){
//            baseHeaders.put(entry.getKey(),entry.getValue());
//        }
//        return baseHeaders;
//    }
//
//    /**
//     * http post请求带回掉（异步线程）
//     *
//     * @param url      请求路径
//     * @param params   请求参数
//     * @param callback 请求结果回掉
//     */
//    public static void postByParams(String url, Map<String, Object> params, Callback callback) {
//        PostRequest postRequest = OkGo.<BaseResponse>post(url).headers(appendBaseHeaders(null));
//        JSONObject jsonStr = new JSONObject(params);
//        String str = jsonStr.toString();
//        LogUtil.w("post params"+str);
//        //上传Json类型的文本
//        postRequest.upJson(jsonStr);
//        //postRequest.params(params);
//        postRequest.execute(callback);
//    }
//
//    /**
//     * http post请求带回掉（异步线程）需要传header
//     * @param url
//     * @param header X-DB-Token当header传
//     * @param params
//     * @param callback
//     */
//    public static void postByParams(String url, String header, HashMap<String, String> params, Callback callback){
////        PostRequest postRequest = OkGo.<BaseResponse>post(url).headers("X-DB-Token", header);
//        PostRequest postRequest = OkGo.<BaseResponse>post(url).headers(appendBaseHeaders(null));
//        JSONObject jsonStr = new JSONObject(params);
//        String str = jsonStr.toString();
//        LogUtil.i("params"+str);
//        //上传Json类型的文本
//        postRequest.upJson(jsonStr);
//        //postRequest.params(params);
//        postRequest.execute(callback);
//    }
//
//    /**
//     * http post请求带回掉（异步线程）需要传header
//     * @param url
//     * @param header  X-DB-Token当header传
//     * @param params  参数传数组    "neteaseIds": ["string"]
//     * @param callBack
//     */
//    public static void postListByParams(String url, String header, HashMap<String, List<String>> params, Callback callBack){
//        PostRequest postRequest = OkGo.<BaseResponse>post(url).headers(appendBaseHeaders(null));
//        JSONObject jsonStr = new JSONObject(params);
//        String str = jsonStr.toString();
//        LogUtil.i("params>>>"+str);
//        // 上传Json类型的文本
//        postRequest.upJson(jsonStr);
//        postRequest.execute(callBack);
//    }
//
//    /**
//     * http post请求带回掉（异步线程）不需要传参数 需要传header
//     * @param url
//     * @param header X-DB-Token当header传
//     * @param callback
//     */
//    public static void postByNoParams(String url, String header, Callback callback){
////        PostRequest postRequest = OkGo.<BaseResponse>post(url).headers("X-DB-Token", header);
//        PostRequest postRequest = OkGo.<BaseResponse>post(url).headers(appendBaseHeaders(null));
//        postRequest.execute(callback);
//    }
//
//    /**
//     * http put请求带回掉（异步线程）需要传header
//     * @param url
//     * @param header  X-DB-Token当header传
//     * @param params
//     * @param callback
//     */
//    public static void putByParams(String url, String header, HashMap<String, String> params, Callback callback){
////        PutRequest putRequest = OkGo.<BaseResponse>put(url).headers("X-DB-Token", header);
//        PutRequest putRequest = OkGo.<BaseResponse>put(url).headers(appendBaseHeaders(null));
//        JSONObject jsonStr = new JSONObject(params);
//        String str = jsonStr.toString();
//        LogUtil.i("params"+str+"token"+header);
//        //上传Json类型的文本
//        putRequest.upJson(jsonStr);
//        //postRequest.params(params);
//        putRequest.execute(callback);
//    }
//
//
//    /**
//     * http get请求带回掉（异步线程）
//     *
//     * @param url      请求路径
//     * @param params   参数
//     * @param callback 请求回掉结果
//     */
//    public static void getByParams(String url, HashMap<String, String> params, Callback callback) {
//        GetRequest getRequest = OkGo.<BaseResponse>get(url);
//        getRequest.params(params);
//        getRequest.execute(callback);
//    }
//
//    /**
//     * http get请求带回掉需要传header
//     * @param url
//     * @param header  X-DB-Token当header传
//     * @param callback
//     */
//    public static void getRequest(String url, String header, Callback callback){
////        GetRequest getRequest = OkGo.<BaseResponse>get(url).headers("X-DB-Token", header);
//        GetRequest getRequest = OkGo.<BaseResponse>get(url).headers(appendBaseHeaders(null));
//        getRequest.execute(callback);
//    }
//
//    /**
//     * http get 不带参数(异步线程)
//     */
//    public static void getRequest(String url, Callback callback) {
//        GetRequest getRequest = OkGo.<BaseResponse>get(url).headers(appendBaseHeaders(null));
//        getRequest.execute(callback);
//    }
//
//    /**
//     * http delete请求 不带参数 需要传header
//     * @param url
//     * @param header X-DB-Token当header传
//     * @param callback
//     */
//    public static void deleteRequest(String url, String header, Callback callback){
////      DeleteRequest deleteRequest = OkGo.<BaseResponse>delete(url).headers("X-DB-Token", header);
//        DeleteRequest deleteRequest = OkGo.<BaseResponse>delete(url).headers(appendBaseHeaders(null));
//        deleteRequest.execute(callback);
//    }
//
//    /**
//     * http delete请求 不带参数 需要传header
//     * @param url
//     * @param header X-DB-Token当header传
//     * @param callback
//     */
//    public static void deleteByParams(String url, String header, Callback callback){
//        DeleteRequest deleteRequest = OkGo.<BaseResponse>delete(url).headers(appendBaseHeaders(null));
//        deleteRequest.execute(callback);
//    }
}
