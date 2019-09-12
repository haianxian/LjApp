package com.lj.app.view.scaleview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;

public class VlightValueView extends FrameLayout {

    private Context context;
    private FrameLayout vlightRootRl;
    private WebView vlightWebview;
    private String vlightValueUrl;
    private int viewX;
    private int viewY;
    private String vlightPrice;
    //支付结果h5回调
    private String successCallback;
//    private PayLoadDialog payDialog;
    private String wxOrderNo;
    public static final String QQ_PAY_APPID = "101431548";
    public static final String WX_PAY_APPID = "wxd43df61a46d22e00";
    public static final int PAY_TYPE_ALI = 1;
    public static final int PAY_TYPE_WX = 2;
    public static final int PAY_TYPE_qq = 3;

    private static final int SDK_PAY_FLAG = 1;
    private Handler handler = new Handler();
//    private Handler payHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case SDK_PAY_FLAG:
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultStatus = payResult.getResultStatus();
//                    String result = payResult.getResult();
//                    String out_trade_no = "";
//                    if (!TextUtils.isEmpty(result)) {
//                        JSONObject jsonObject = JSONObject.parseObject(result);
//                        JSONObject obj = jsonObject.getJSONObject("alipay_trade_app_pay_response");
//                        out_trade_no = obj.getString("out_trade_no");
//                    }
//                    if ("9000".equals(resultStatus)) {
//                        StoreOb.notifyPayObserver(StoreOb.PAY_RESULT_SUCCESS_CODE,
//                                StoreWebActivity.PAY_TYPE_ALI, out_trade_no);
//                    } else if ("6001".equals(resultStatus)) {
//                        StoreOb.notifyPayObserver(StoreOb.PAY_RESULT_CANCEL_CODE,
//                                StoreWebActivity.PAY_TYPE_ALI, out_trade_no);
//                    } else if ("8000".equals(resultStatus) || "6004".equals(resultStatus)) {
//                        StoreOb.notifyPayObserver(StoreOb.PAY_RESULT_UNKNOWN_CODE,
//                                StoreWebActivity.PAY_TYPE_ALI, out_trade_no);
//                    } else {
//                        StoreOb.notifyPayObserver(StoreOb.PAY_RESULT_ERROR_CODE,
//                                StoreWebActivity.PAY_TYPE_ALI, out_trade_no);
//                    }
//                    break;
//            }
//        }
//    };

    public VlightValueView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public VlightValueView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VlightValueView(@NonNull Context context, @Nullable AttributeSet attrs,
                           int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_vlight_value, this);
        vlightRootRl = rootView.findViewById(R.id.vlightvalue_root_Ll);
        vlightWebview = rootView.findViewById(R.id.vlight_webview);
        vlightRootRl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //微光号价值鉴定
    public void initH5WebView() {
//        StoreOb.registerPayObserver(payObserver);
//        vlightValueUrl = AppConfig.CURRENT_CMS_URL + "shop/discover" + AppInfoUtils.getHeader();
//        vlightValueUrl = "http://192.168.1.147:8986/"+"discover" + AppInfoUtils.getHeader();
//        LogUtil.i("h5WebView", "鉴定微光号地址>>" + vlightValueUrl);
        vlightValueUrl = "https://www.baidu.com/";
        vlightWebview.setBackgroundColor(0xCC000000);
        //设置填充透明度 范围：0-255
        vlightWebview.getBackground().setAlpha(0);
//        AppInfoUtils.setApplyAnchorCookie(context, vlightValueUrl);
        vlightWebview.loadUrl(vlightValueUrl);

        WebSettings settings = vlightWebview.getSettings();
        settings.setJavaScriptEnabled(true);
//        vlightWebview.addJavascriptInterface(new VlightValueJs(), "vlightValue_js");
//        vlightWebview.addJavascriptInterface(new StoreJs(), "store_js");
        // 设置可以支持缩放
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        // 不用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        String ua = settings.getUserAgentString();
//        settings.setUserAgentString(ua + "; Vlight/" + BuildConfig.VERSION_NAME);

        vlightWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request,
                                            WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (errorResponse == null) return;
                    int statusCode = errorResponse.getStatusCode();
                    if (404 == statusCode || 500 == statusCode) {
//                        LogUtil.i("h5WebView", "errorCode>>" + statusCode);
                    }
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (view.getProgress() == 100) {
                   setVlightRootVisible();
                }
            }

        });

    }

    public void setVlightRootVisible(){
        if (vlightRootRl != null && vlightRootRl.getVisibility() == View.GONE) {
            vlightRootRl.setVisibility(View.VISIBLE);
        }
    }

    public void measureTargetView(View targetView) {
        int[] location = new int[2];
        targetView.getLocationOnScreen(location);
        viewX = location[0];
        viewY = location[1];
        viewX = viewX + targetView.getWidth() / 2;
        viewY = viewY + targetView.getHeight() / 2;
        LogUtil.i("targetView>>","viewX>>"+viewX +"viewY>>"+viewY);
    }

//    public class VlightValueJs {
//
//        //方法名都需要接收参数,否则js调用不到
//        @JavascriptInterface
//        public void NativeBack(String paramsJson) {
//            //返回
//            LogUtil.i("h5WebView", "NativeBack");
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (mVlightValueListen != null) {
//                        mVlightValueListen.nativeBack();
//                    }
//                }
//            });
//        }
//
//        @JavascriptInterface
//        public void ShareVlightValue(String paramsJson) {
//            LogUtil.i("h5WebView", "ShareVlightValue");
//            //晒一下
//            if (!TextUtils.isEmpty(paramsJson)) {
//                try {
//                    JSONObject json = JSON.parseObject(paramsJson);
//                    if (json != null && json.containsKey("price")) {
//                        vlightPrice = json.getString("price");
//                        ShareVlightValueActivity.open(context, vlightPrice);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        @JavascriptInterface
//        public void VlightTestFinish(String paramsJson) {
//            LogUtil.i("h5WebView", "VlightTestFinish");
//            //测试完成
//            saveState();
//        }
//
//        @JavascriptInterface
//        public void RefreshUserInfo(String paramsJson) {
//            LogUtil.i("h5WebView", "刷新用户信息" + paramsJson);
//            EventCenter.postEvent(EventCenterConstant.refresh_vlightvalue_userinfo, paramsJson);
//        }
//
//    }

//    public class StoreJs {
//        @JavascriptInterface
//        public void Recharge(String paramsJson) {
//            showPayLoading("充值中");
//            wxOrderNo = "";
//            JSONObject jsonObject = JSONObject.parseObject(paramsJson);
//            if (jsonObject != null) {
//                int productId = jsonObject.getInteger("productId");
//                int num = jsonObject.getInteger("num");
//                String token = jsonObject.getString("token");
//                final int payType = jsonObject.getInteger("payType");
//                successCallback = jsonObject.getString("successCallback");
//
//                Product product = new Product(productId, num, token);
//                ArrayList<Product> products = new ArrayList<>();
//                products.add(product);
//                ApiHelper.getInstance().postAppOrder(context, products, payType,
//                        new BaseHttpCallback<BaseResponse>(BaseResponse.class) {
//                    @Override
//                    public void onSuc(int httpCode, int code, BaseResponse data) {
//                        if (data == null || data.getData() == null || !(data.getData() instanceof String)) {
//                            toastFail("充值失败，请重试");
//                            return;
//                        }
//                        String json = (String) data.getData();
//                        //1支付宝2微信3qq
//                        switch (payType) {
//                            case 1:
//                                payByAli(json);
//                                break;
//                            case 2:
//                                payByWwChat(json);
//                                break;
//                            case 3:
//                                payByQQ(json);
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void onFai(int httpCode, int code, String message) {
//                        toastFail("充值失败，请重试");
//                    }
//
//                    @Override
//                    public void onError(int httpCode) {
//                        toastFail("充值失败，请重试");
//                    }
//                });
//            } else {
//                toastFail("充值失败，请重试");
//            }
//        }
//
//        @JavascriptInterface
//        public void UMengTongJi(String json) {
//            try {
//                if (!TextUtils.isEmpty(json) && json.contains("event_name")) {
//                    Map maps = (Map) JSON.parse(json);
//                    String event_name = (String) maps.get("event_name");
//                    maps.remove("event_name");
//                    maps.remove("key");
//                    UMengStatistics.buyItem(event_name, maps);
//                    int item_tid = (int) maps.get("item_tid");
//                    if (item_tid == 1 || item_tid == 2 || item_tid == 3) {
//                        long currentTimeMillis = System.currentTimeMillis();
//                        MyItemsActivity.sLastBuyItems = currentTimeMillis;
//                        PrefUtils.putLong(context, PreUtilsConstant.LAST_BUY_ITEM_DATE,
//                                currentTimeMillis);
//                    }
//                    EventCenter.postEvent(EventCenterConstant.store_buy_notify, item_tid);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //检测完成记录状态
//    private void saveState() {
//        boolean finish = PrefUtils.getBoolean(context, PreUtilsConstant.VLIGHT_VALUE_TEST_FINISH,
//                false);
//        if (!finish) {
//            PrefUtils.putBoolean(context, PreUtilsConstant.VLIGHT_VALUE_TEST_FINISH, true);
//        }
//    }


    public void viewBack() {
        viewShrinkAnim();
    }

    //收起动画
    public void viewShrinkAnim() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(vlightRootRl, "scaleX", 1f, 0.1f);
        vlightRootRl.setPivotX(viewX);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(vlightRootRl, "scaleY", 1f, 0.1f);
        vlightRootRl.setPivotY(viewY);
        LogUtil.i("targetView>>","viewX1111>>"+viewX +"viewY1111>>"+viewY);
        scaleX.setDuration(400);
        scaleY.setDuration(400);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(vlightRootRl == null) return;
                vlightRootRl.post(new Runnable() {
                    @Override
                    public void run() {
//                        vlightRootRl.setVisibility(View.GONE);
                        //重置vlightRootRl
                        vlightRootRl.setScaleX(1);
                        vlightRootRl.setScaleY(1);
//                        setVisibility(View.GONE);
//                        viewDestroy();
                    }
                });

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    VlightValueListen mVlightValueListen;

    public interface VlightValueListen {
        void nativeBack();
    }

    public VlightValueListen getmVlightValueListen() {
        return mVlightValueListen;
    }

    public void setmVlightValueListen(VlightValueListen mVlightValueListen) {
        this.mVlightValueListen = mVlightValueListen;
    }

//    private void showPayLoading(final String msg) {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (!Check.checkContext(context)) return;
//                if (payDialog == null) {
//                    payDialog = new PayLoadDialog(context);
//                }
//                payDialog.show();
//                payDialog.setMessage(msg);
//            }
//        });
//
//    }
//
//    private void hidePayLoading() {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (payDialog != null) {
//                    payDialog.dismiss();
//                }
//            }
//        });
//    }
//
//
//    private void toastFail(String msg) {
//        hidePayLoading();
//        ToastUtils.showToast1(context, msg, R.mipmap.ic_notice_fai);
//    }
//
//    private void toastSuccess() {
//        hidePayLoading();
//        vlightWebview.loadUrl("javascript:" + successCallback + "()");
//        ToastUtils.showToast1(context, "充值成功", R.mipmap.ic_notice_suc);
//        EventCenter.postEvent(EventCenterConstant.vlightvalue_echarge_success);
//    }
//
//    /**
//     * 支付宝支付业务示例
//     */
//    private void payByAli(final String orderInfo) {
//        final Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask((Activity) context);
//                Map<String, String> result = alipay.payV2(orderInfo, true);
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                payHandler.sendMessage(msg);
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }
//
//    /**
//     * 微信支付
//     */
//    public void payByWwChat(String json) {
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        String appId = jsonObject.getString("appid");
//        String partnerId = jsonObject.getString("partnerid");
//        String prepayId = jsonObject.getString("prepayid");
//        String nonceStr = jsonObject.getString("noncestr");
//        String timeStamp = jsonObject.getString("timestamp");
//        String sign = jsonObject.getString("sign");
//        wxOrderNo = jsonObject.getString("orderId");
//        IWXAPI api = WXAPIFactory.createWXAPI(context, WX_PAY_APPID);
//        if (api.isWXAppInstalled()) {
//            PayReq req = new PayReq();
//            req.appId = appId;//你的微信appid
//            req.partnerId = partnerId;//商户号
//            req.prepayId = prepayId;//预支付交易会话ID
//            req.nonceStr = nonceStr;//随机字符串
//            req.timeStamp = timeStamp;//时间戳
//            req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
//            req.sign = sign;//签名
//            //              req.extData         = "app data"; // optional
//            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//            api.sendReq(req);
//        } else {
//            toastFail("请安装最新版本的微信");
//        }
//    }
//
//    //QQ钱包和支付
//    public void payByQQ(String json) {
//        IOpenApi openApi = OpenApiFactory.getInstance(context, QQ_PAY_APPID);
//        boolean isSupport = openApi.isMobileQQSupportApi(OpenConstants.API_NAME_PAY);
//        if (isSupport) {
//            JSONObject jsonObject = JSONObject.parseObject(json);
//            String appId = jsonObject.getString("appId");
//            String nonce = jsonObject.getString("nonce");
//            String tokenId = jsonObject.getString("tokenId");
//            String pubAcc = jsonObject.getString("pubAcc");
//            String sign = jsonObject.getString("sign");
//            String signType = jsonObject.getString("signType");
//            String bargainorId = jsonObject.getString("bargainorId");
//            String timestamp = jsonObject.getString("timestamp");
//            String orderId = jsonObject.getString("orderId");
//
//            PayApi api = new PayApi();
//            api.appId = appId; // 在http://open.qq.com注册的AppId,参与支付签名，签名关键字key为appId
//            api.serialNumber = orderId; // 支付序号,用于标识此次支付
//            api.callbackScheme = "qwallet101431548"; // QQ钱包支付结果回调给urlscheme为callbackScheme
//            // 的activity.，参看后续的“支付回调结果处理”
//            api.tokenId = tokenId; // QQ钱包支付生成的token_id
//            api.pubAcc = pubAcc; // 手Q公众帐号id.参与支付签名，签名关键字key为pubAcc
//            api.pubAccHint = ""; // 支付完成页面，展示给用户的提示语：提醒关注公众帐号
//            api.nonce = nonce; // 随机字段串，每次支付时都要不一样.参与支付签名，签名关键字key为nonce
//            api.timeStamp = Check.longNum(timestamp); // 时间戳，为1970年1月1日00:00到请求发起时间的秒数
//            api.bargainorId = bargainorId; // 商户号.参与支付签名，签名关键字key为bargainorId
//            api.sig = sign; // 商户Server下发的数字签名，生成的签名串，参看“数字签名”
//            api.sigType = signType; // 签名时，使用的加密方式，默认为"HMAC-SHA1"
//            if (api.checkParams()) {
//                openApi.execApi(api);
//            } else {
//                toastFail("参数错误");
//            }
//        } else {
//            toastFail("请安装最新版本的手机QQ");
//        }
//    }
//
//    //支付回调
//    private StoreOb.PayObserver payObserver = new StoreOb.PayObserver() {
//        @Override
//        public void onPayResult(final int resultCode, final int payType, final String orderNo) {
//            payHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    ApiHelper.getInstance().getCheckOrder(this, payType == 2 ? wxOrderNo :
//                            orderNo, new StringCallback() {
//                        @Override
//                        public void onSuccess(Response<String> response) {
//                            if (response != null && !TextUtils.isEmpty(response.body())) {
//                                JSONObject json = JSON.parseObject(response.body());
//                                if (json != null && json.get("code") instanceof Integer && json.getInteger("code") == 0 && json.get("data") instanceof JSONObject) {
//                                    JSONObject obj = (JSONObject) json.get("data");
//                                    int resultCode = obj.getInteger("code");
//                                    if (resultCode == 0) {
//                                        toastSuccess();
//                                        try {
//                                            CheckOrderResult checkOrderResult =
//                                                    JSON.toJavaObject(obj, CheckOrderResult.class);
//                                            switch (checkOrderResult.getPayType()) {
//                                                case PAY_TYPE_ALI:
//                                                    UMengStatistics.depositVmoney(checkOrderResult.getMoney(), "支付宝");
//                                                    break;
//                                                case PAY_TYPE_qq:
//                                                    UMengStatistics.depositVmoney(checkOrderResult.getMoney(), "QQ钱包");
//                                                    break;
//                                                case PAY_TYPE_WX:
//                                                    UMengStatistics.depositVmoney(checkOrderResult.getMoney(), "微信支付");
//                                                    break;
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    } else {
//                                        toastFail("充值失败，请重试");
//                                    }
//                                } else {
//                                    payCbByLocal(resultCode, payType, orderNo);
//                                }
//                            } else {
//                                payCbByLocal(resultCode, payType, orderNo);
//                            }
//                        }
//
//                        @Override
//                        public void onError(Response<String> response) {
//                            super.onError(response);
//                            payCbByLocal(resultCode, payType, orderNo);
//                        }
//                    });
//                }
//            }, 1000);
//        }
//    };
//
//    private void payCbByLocal(int resultCode, int payType, String orderNo) {
//        switch (resultCode) {
//            case StoreOb.PAY_RESULT_SUCCESS_CODE:
//                toastSuccess();
//                break;
//            case StoreOb.PAY_RESULT_ERROR_CODE:
//                toastFail("充值失败，请重试");
//                break;
//            case StoreOb.PAY_RESULT_UNKNOWN_CODE:
//                ApiHelper.getInstance().getCheckOrder(this, payType == 2 ? wxOrderNo : orderNo,
//                        new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        if (response != null && !TextUtils.isEmpty(response.body())) {
//                            JSONObject json = JSON.parseObject(response.body());
//                            if (json != null && json.get("code") instanceof Integer && json.getInteger("code") == 0 && json.get("data") instanceof JSONObject) {
//                                JSONObject obj = (JSONObject) json.get("data");
//                                int resultCode = obj.getInteger("code");
//                                if (resultCode == 0) {
//                                    toastSuccess();
//                                    try {
//                                        CheckOrderResult checkOrderResult = JSON.toJavaObject(obj
//                                                , CheckOrderResult.class);
//                                        switch (checkOrderResult.getPayType()) {
//                                            case PAY_TYPE_ALI:
//                                                UMengStatistics.depositVmoney(checkOrderResult.getMoney(), "支付宝");
//                                                break;
//                                            case PAY_TYPE_qq:
//                                                UMengStatistics.depositVmoney(checkOrderResult.getMoney(), "QQ钱包");
//                                                break;
//                                            case PAY_TYPE_WX:
//                                                UMengStatistics.depositVmoney(checkOrderResult.getMoney(), "微信支付");
//                                                break;
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } else {
//                                    toastFail("充值失败，请重试");
//                                }
//                            } else {
//                                toastFail("订单正在处理中，请稍后刷新页面查看");
//                            }
//                        } else {
//                            toastFail("订单正在处理中，请稍后刷新页面查看");
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        toastFail("订单正在处理中，请稍后刷新页面查看");
//                    }
//                });
//                break;
//            case StoreOb.PAY_RESULT_CANCEL_CODE:
//                toastFail("充值失败，请重试");
//                break;
//        }
//    }
//
//    public void viewDestroy() {
//        StoreOb.unRegisterPayObserver(payObserver);
//    }
//
//    public void jsBack() {
//        vlightWebview.loadUrl("javascript:handleBackKey()");
//    }
}
