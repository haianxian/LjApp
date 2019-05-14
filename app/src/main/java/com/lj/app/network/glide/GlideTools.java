package com.lj.app.network.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;
import com.lj.app.tools.utils.UIUtils;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide图片工具类
 * Created by 13717 on 2017/8/14.
 */

public class GlideTools {
    //圆角 方形 剪裁
    private static RequestOptions op_crop_round_rect;
    private static RequestOptions op_crop_round_rect_8dp;
    private static RequestOptions op_crop_round_fit_rect;
    private static RequestOptions op_crop_round_rect_fit_center;
    private static RequestOptions op_crop_round_rect_center_inside;
    private static RequestOptions op_crop_round_rect_center_crop;
    private static RequestOptions op_crop_round_rect_top;
    private static RequestOptions op_crop_round_rect_top_fit_center;
    private static RequestOptions op_crop_round_rect_bottom;
    private static RequestOptions op_crop_round_rect_top_left;
    private static RequestOptions op_crop_round_rect_bottom_right;
    //方形 剪裁
    private static RequestOptions op_crop_rect;
    //高斯
    private static RequestOptions op_blur;
    //高斯
    private static RequestOptions op_blur_round;
    //淡入动画
    private static DrawableTransitionOptions tran_fade;
    // 圆形 居中
    private static RequestOptions op_centerfit_circle;
    // 圆形 拉伸
    private static RequestOptions op_fit_circle_home_tab;
    // 圆形 拉伸
    private static RequestOptions op_fit_circle_secondary_tab;
    //加载占位 和 错误占位
    private static RequestOptions op_place_error;
    //重置宽高
    private static RequestOptions op_resize;

    public static void init(){
        op_crop_round_rect=new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(UIUtils.dip2px(4)));
        op_crop_round_rect_8dp=new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(UIUtils.dip2px(8)));
        op_crop_round_fit_rect = new RequestOptions().transforms(new FitCenter(),new RoundedCorners(UIUtils.dip2px(4)));
        op_crop_round_rect_fit_center=new RequestOptions().transforms(new FitCenter());
        op_crop_round_rect_center_inside=new RequestOptions().transforms(new CenterInside());
        op_crop_round_rect_center_crop=new RequestOptions().transforms(new CenterCrop());
        op_crop_round_rect_top = new RequestOptions().transforms(new CenterCrop(),new RoundedCornersTransformation(UIUtils.dip2px(4), 0, RoundedCornersTransformation.CornerType.TOP));
        op_crop_round_rect_top_fit_center = new RequestOptions().transforms(new CenterCrop(), new RoundedCornersTransformation(UIUtils.dip2px(8), 0, RoundedCornersTransformation.CornerType.TOP));
        op_crop_round_rect_bottom = new RequestOptions().transforms(new CenterCrop(),new RoundedCornersTransformation(UIUtils.dip2px(4), 0, RoundedCornersTransformation.CornerType.BOTTOM));
        op_crop_round_rect_top_left = new RequestOptions().transforms(new CenterCrop(),new RoundedCornersTransformation(UIUtils.dip2px(4), 0, RoundedCornersTransformation.CornerType.TOP_LEFT));
        op_crop_round_rect_bottom_right = new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(UIUtils.dip2px(4)), new RoundedCornersTransformation(UIUtils.dip2px(20), 0, RoundedCornersTransformation.CornerType.BOTTOM_RIGHT));
        op_blur=new RequestOptions().bitmapTransform(new BlurTransformation(14,2));
        op_blur_round = new RequestOptions().transforms(new BlurTransformation(14,2),new CenterCrop(),new RoundedCorners(UIUtils.dip2px(4)));
        tran_fade=new DrawableTransitionOptions().crossFade();
        op_centerfit_circle=new RequestOptions().circleCrop();
        op_fit_circle_home_tab =new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(UIUtils.dip2px(25)));
        op_fit_circle_secondary_tab =new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(UIUtils.dip2px(30)));
        op_place_error=new RequestOptions().placeholder(R.mipmap.ic_icon).error(R.mipmap.ic_icon);
        op_resize=new RequestOptions().override(UIUtils.dip2px(50));
        op_crop_rect=new RequestOptions().centerCrop();
    }

    /**
     * 加载网络图片直接显示
     *
     * @param url 图片地址
     * @param iv  要显示的imageView
     */
    public static void loadUrlImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context).load(url).apply(op_crop_rect).into(iv);
    }

    /**
     * 加载本地资源图片
     *
     * @param res 资源图片
     * @param iv  要显示的imageView
     */
    public static void loadResImg(Context context, int res, ImageView iv) {
        if (!checkContext(context)) return;
        Glide.with(context)
                .load(res)
                .apply(op_crop_round_rect_8dp)
                .transition(tran_fade)
                .into(iv);
    }

    /**
     * 加载文件图片
     *
     * @param f
     * @param iv
     */
    public static void loadFileImg(Context context, File f, ImageView iv) {
        if (!checkContext(context)) return;
        Glide.with(context).load(f).into(iv);
    }

    /**
     * 加载Uri图片
     *
     * @param f
     * @param iv
     */
    public static void loadUriImg(Context context, File f, ImageView iv) {
        if (!checkContext(context)) return;
        Glide.with(context).load(Uri.fromFile(f)).into(iv);
    }

    /**
     * 加载显示占位图
     *
     * @param url
     * @param iv
     */
    public static void loadUrlPlaceImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_place_error.placeholder(R.mipmap.ic_icon).error(R.mipmap.ic_icon)) //设置占位图，在加载之前显示
                .into(iv);
    }

    public static void loadUrlPlaceImg(Context context, String url, ImageView iv ,Drawable def){
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_rect)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    /**
     * 显示Gif图片 （Glide中支持显示GIFs和视频，视频的加载和图像的加载基本一致）
     *
     * @param url
     * @param iv
     */
    public static void loadGifImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        loadUrlPlaceImg(context,url,iv);
    }

    /**
     * 通过URL获取Bitmap
     *
     * @param url
     * @param iv
     */
    public static void loadBitmapImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    public static void getBitmap(Context context, String url,final OnBitmapListener listener){
        if (!checkContext(context)) return;
        url = checkUrl(url);
        RequestBuilder<Bitmap> requestBuilder = Glide.with(context).asBitmap();
        requestBuilder.load(url).apply(op_place_error).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                listener.getBitmap(bitmap);
            }
        });
    }
    public interface OnBitmapListener{
        void getBitmap(Bitmap bt);
    }

    /**
     * 设置显示指定大小的图片 (图像在裁剪之后图像可能会变形，Glide还提供了两种方法 centerCrop()和fitCenter使图像等比例的缩放和显示)
     *
     * @param url
     * @param width  图片宽
     * @param height 图片高
     * @param iv
     */
    public static void loadScaleImg(Context context, String url, int width, int height, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_resize.override(width,height))
                .apply(op_crop_rect)
                //.fitCenter()
                .into(iv);
    }

    /**
     * 通过url获取的图片进行圆形裁剪
     *
     * @param url
     * @param iv
     */
    public static void loadCropCircleImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
//        url = checkUrl(url);
//        Drawable def = Check.drawable(context,R.drawable.bg_icon_item,"#ffffff");
        Glide.with(context)
                .load(url)
                .apply(op_centerfit_circle)
                .apply(op_place_error.placeholder(R.drawable.bg_icon_item).error(R.drawable.bg_icon_item))
                .into(iv);
    }
    public static void loadCropCircleImg(Context context, String url, ImageView iv,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_centerfit_circle)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadCropCircleImg(Context context, String url, ImageView iv,int width,int height) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_centerfit_circle)
                .apply(op_resize.override(width,height))
                .into(iv);
    }

    public static void loadCropCircleImg(Context context, String url, ImageView iv,int width,int height,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_centerfit_circle)
                .apply(op_place_error.placeholder(def).error(def))
                .apply(op_resize.override(width,height))
                .into(iv);
    }

    public static void loadHomeTabCircleImg(Context context, String url, ImageView iv,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_fit_circle_home_tab)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadSecondaryTabCircleImg(Context context, String url, ImageView iv,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_fit_circle_secondary_tab)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    /**
     * 通过url获取的图片进行圆角处理
     *
     * @param url
     * @param radius 圆角半径
     * @param iv
     */
    public static void loadRoundCornerImg(Context context, String url, int radius, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect)
                .transition(tran_fade)
                .into(iv);
    }

    /**
     * 通过url获取的图片进行圆角处理
     * @param context
     * @param url
     * @param radius
     * @param iv
     */
    public static void loadRoundCornerRadiusImg(Context context, String url, int radius, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(UIUtils.dip2px(radius))))
                .transition(tran_fade)
                .into(iv);
    }

    /**
     * 通过url获取的图片进行圆角处理
     * @param context
     * @param url
     * @param radius
     * @param iv
     */
    public static void loadRoundCornerRadiusImg(Context context, String url, int radius, ImageView iv, Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(UIUtils.dip2px(radius))))
                .transition(tran_fade)
                .apply(op_place_error.placeholder(def).error(def))
                .into(iv);
    }

    public static void loadFitCenterImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_fit_center)
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadTopFitCenterImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_top_fit_center)
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadTopFitCenterImg(Context context, String url, ImageView iv, Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_top_fit_center)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadCenterInsideImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_center_inside)
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadCenterInsideImg(Context context, String url, ImageView iv, RequestListener<Drawable> listener) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_center_inside)
                .transition(tran_fade)
                .listener(listener)
                .into(iv);
    }

    /**
     * 通过url获取的图片进行圆角处理
     *
     * @param url
     * @param iv
     */
    public static void loadImgFitCenter(Context context, String url, ImageView iv, RequestListener<Drawable> listener) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .listener(listener)
                .apply(op_crop_round_rect_fit_center)
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadRoundCornerImg(Context context, String url, int radius, ImageView iv, RequestListener<Drawable> listener) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .listener(listener)
                .apply(op_crop_round_rect)
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadRoundCornerImgTopLeft(Context context, String url, ImageView iv,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_top_left)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_rect)
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadImg(Context context, String url, ImageView iv,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_rect)
                .transition(tran_fade)
                .apply(op_place_error.placeholder(def).error(def))
                .into(iv);
    }

    public static void loadRoundCornerImg(Context context, String url, int radius, ImageView iv, Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadRoundCorner8dpImg(Context context, String url, ImageView iv, Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect_8dp)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadRoundCornerImg(final Context context, final String url, final ImageView iv, final Drawable def) {
        if (!checkContext(context)) return;
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target,
                                                boolean isFirstResource) {
                        String finalUrl  = checkUrl(url);
                        Glide.with(context)
                                .load(finalUrl)
                                .apply(op_crop_round_rect)
                                .apply(op_place_error.placeholder(def).error(def))
                                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                                .into(iv);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                            DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(op_crop_round_rect)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadRoundCornerImg(Context context, String url, ImageView iv, int rr,Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_crop_round_rect)
                .apply(op_place_error.placeholder(def).error(def))
                .transition(tran_fade)
                .into(iv);
    }

    public static void loadGif(Context context, String url, ImageView iv, Drawable def) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .transition(tran_fade)
                .apply(op_place_error.placeholder(def).error(def))
                .into(iv);
    }

    /**
     * 通过url获取的图片进行灰度处理
     *
     * @param url
     * @param iv
     */
    public static void loadGrayscaleImg(Context context, String url, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    /**
     * 添加图片淡入加载效果
     *
     * @param context
     * @param url
     * @param duration 加载时长
     * @param iv
     */
    public static void loadCrossFadeImg(Context context, String url, int duration, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .transition(tran_fade)
                .into(iv);
    }

    /**
     * 通过url获取图片高斯模糊处理
     *
     * @param context
     * @param url
     * @param blur    设置模糊度(在0.0到25.0之间)，默认”25"
     */
    public static void loadGaussianBlurImg(Context context, String url, int blur, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_blur.bitmapTransform(new BlurTransformation(blur,2)))
                .into(iv);
    }
    public static void loadBlurRoundImg(Context context, String url, int blur, ImageView iv) {
        if (!checkContext(context)) return;
        url = checkUrl(url);
        Glide.with(context)
                .load(url)
                .apply(op_blur_round)
                .into(iv);
    }

    public static void loadLocalImg(Context context,String path,ImageView iv){
        if (!checkContext(context)) return;
        Glide.with(context).load("file://"+path).transition(tran_fade).into(iv);
    }

    public static String checkUrl(String url) {
        try {
            if (!TextUtils.isEmpty(url) && url.contains("?")) {
                String substring = url.substring(0, url.indexOf("?"));
                LogUtil.w("xxxxx-imgFormat", "img url: "+url+" format: "+substring);
                return substring;
            }
        } catch (Exception e) {
            return url;
        }

        return url;
    }

    private static boolean checkContext(Context context) {
        if (context == null) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (context instanceof Activity && ((Activity) context).isDestroyed()) return false;
        }
        return true;
    }
}
