package com.lj.app.view.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lj.app.R;
import com.lj.app.bean.HomeRandomUser;
import com.lj.app.network.glide.GlideTools;
import com.lj.app.tools.utils.Check;
import com.lj.app.tools.utils.LogUtil;
import com.lj.app.tools.utils.rangeseekbar.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadLoopActivity extends AppCompatActivity {

    public static final String TAG = "HeadLoopActivity>>";
    @BindView(R.id.match_head_left)
    ImageView headLeftIv;
    @BindView(R.id.match_head_center)
    ImageView headCenterIv;
    @BindView(R.id.fake_center_iv)
    ImageView fakeCenterIv;
    @BindView(R.id.match_head_right)
    ImageView headRightIv;
    @BindView(R.id.match_head_new)
    ImageView headRightIv2;
    @BindView(R.id.match_head_new2)
    ImageView headRightIv3;
    @BindView(R.id.match_people_iv)
    ImageView matchPeopleIv;
    @BindView(R.id.matchmovie_nick_tv)
    TextView matchmovieNickTv;
    @BindView(R.id.matchmovie_tip_tv)
    TextView matchmovieTipTv;
    List<HomeRandomUser> userListTemp = new ArrayList<>();
    boolean isMatchPeople = false;//匹配上的标志
    boolean startSecondAnima = false;//保证一轮循环动画执行完成后再执行循环动画
    private boolean mMatchPeopleAnimRunning;
    int mCenterIndex = 0;
    public static void open(Context context) {
        Intent it = new Intent(context, HeadLoopActivity.class);
        context.startActivity(it);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headloop);
        ButterKnife.bind(this);
        GlideTools.init();
        initData();
    }

    private void initData(){
        String str = "http://img-cdn.vlightv.com/avatar/eaa8e84ac4834e19b1517c73efd006fb.jpg";
        String str1 = "http://img-cdn.vlightv.com/avatar/44b38eae4b88482589d6eed9f1ff1d9d.jpg";
        String str2 = "http://img-cdn.vlightv.com/avatar/b272b11c295648c98ed29e6342689020.jpeg";
        String str3 = "http://img-cdn.vlightv.com/avatar/2a149c92dc68468385baadcfcda93ff5.jpg";
        String str4 = "http://img-cdn.vlightv.com/avatar/2a149c92dc68468385baadcfcda93ff5.jpg";
        String str5 = "http://img-cdn.vlightv.com/avatar/d2d9145b26bd4fa2b30c500f001e37c2.jpeg";
        String str6 = "http://img-cdn.vlightv.com/avatar/0434f96be40a463ebd4da3bf64f14138.jpeg";
        HomeRandomUser user = new HomeRandomUser();
        user.setAvatar(str);
        userListTemp.add(user);

        HomeRandomUser user1 = new HomeRandomUser();
        user1.setAvatar(str1);
        userListTemp.add(user1);
        HomeRandomUser user2 = new HomeRandomUser();
        user2.setAvatar(str2);
        userListTemp.add(user2);
        HomeRandomUser user3 = new HomeRandomUser();
        user3.setAvatar(str3);
        userListTemp.add(user3);
        HomeRandomUser user4 = new HomeRandomUser();
        user4.setAvatar(str4);
        userListTemp.add(user4);
        HomeRandomUser user5 = new HomeRandomUser();
        user5.setAvatar(str5);
        userListTemp.add(user5);
        HomeRandomUser user6 = new HomeRandomUser();
        user6.setAvatar(str6);
        userListTemp.add(user6);

        HomeRandomUser homeRandomUser = userListTemp.get(1);
        startMatchAnima(homeRandomUser);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                secondAnima();
                handler.postDelayed(this, 1000);
            }
        },1000);
    }


    private void startMatchAnima(HomeRandomUser homeRandomUser) {
        headCenterIv.setVisibility(View.VISIBLE);
        headLeftIv.setVisibility(View.INVISIBLE);
        headRightIv.setVisibility(View.INVISIBLE);
        headRightIv2.setVisibility(View.INVISIBLE);
        matchPeopleIv.setVisibility(View.GONE);
        String firstAvatar = homeRandomUser.getAvatar();
        GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                Check.imgUrl(firstAvatar), headCenterIv);
        AnimatorSet setFirst = new AnimatorSet();
        //开始的头像
        ObjectAnimator scaleXfirst = ObjectAnimator.ofFloat(headCenterIv, "scaleX", 0f, 0.5f, 1f);
        scaleXfirst.setDuration(500);
        ObjectAnimator scaleYfirst = ObjectAnimator.ofFloat(headCenterIv, "scaleY", 0f, 0.5f, 1f);
        scaleYfirst.setDuration(500);
        // 开始的文字
        matchmovieNickTv.setText("正在召唤小伙伴陪你看片...");
        ObjectAnimator scaleXnickFirst = ObjectAnimator.ofFloat(matchmovieNickTv, "scaleX", 0f, 0.5f, 1f);
        scaleXnickFirst.setDuration(500);
        ObjectAnimator scaleYnickFirst = ObjectAnimator.ofFloat(matchmovieNickTv, "scaleY", 0f, 0.5f, 1f);
        scaleYnickFirst.setDuration(500);


        setFirst.playTogether(scaleXfirst, scaleYfirst, scaleXnickFirst, scaleYnickFirst);
        setFirst.start();
        setFirst.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (headRightIv == null || headRightIv3 == null) {
                    return;
                }
                AnimatorSet set = new AnimatorSet();
                if(userListTemp.size() > 1){
                    HomeRandomUser homeRandomUser1 = userListTemp.get(1);
                    if (homeRandomUser1 != null) {
                        headRightIv.setVisibility(View.VISIBLE);
                        String avatar = homeRandomUser1.getAvatar();
                        GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                                Check.imgUrl(avatar), headRightIv);

                        String avatar2 = homeRandomUser1.getAvatar();
                        GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                                Check.imgUrl(avatar2), headRightIv3);
                    }

                    ObjectAnimator scaleXRight = ObjectAnimator.ofFloat(headRightIv, "scaleX", 0f, 0.5f, 1f);
                    scaleXRight.setDuration(500);
                    ObjectAnimator scaleYRight = ObjectAnimator.ofFloat(headRightIv, "scaleY", 0f, 0.5f, 1f);
                    scaleYRight.setDuration(500);
                    set.playTogether(scaleXRight, scaleYRight);
                    set.start();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void secondAnima() {
        startSecondAnima = true;
        LogUtil.i("MatchMovieActivity>>>", "secondAnima()>>mCenterIndex>>"+mCenterIndex);
        //开始轮询动画
        final List<HomeRandomUser> userList = new ArrayList<>();
        userList.addAll(userListTemp);

        int newIndex = mCenterIndex + 2;
        if (userList.size() == newIndex) {
            newIndex = 0;
            LogUtil.i(TAG, "newIndex = 0");
        } else if (userList.size() < newIndex) {
            if (userList.size() > 1) {
                newIndex = 1;
                LogUtil.i(TAG, "newIndex = 1");
            } else {
                newIndex = 0;
                LogUtil.i(TAG, "newIndex = 0");
            }
        }
        HomeRandomUser homeRandomUserNew = userList.get(newIndex);
        if (homeRandomUserNew != null) {
            String firstAvatar = homeRandomUserNew.getAvatar();
            LogUtil.i(TAG, "homeUserNew>>" + Check.imgUrl(firstAvatar));
            GlideTools.loadCropCircleImg(this,
                    Check.imgUrl(firstAvatar), headRightIv2);

        }

        int fakeCenterIndex = mCenterIndex + 1;
        if (userList.size() <= fakeCenterIndex) {
            fakeCenterIndex = 0;
        }

        HomeRandomUser homeRandomUserFake = userList.get(fakeCenterIndex);
        if (homeRandomUserFake != null) {
            String firstAvatar = homeRandomUserFake.getAvatar();
            LogUtil.i(TAG, "homeUserFake>>" + Check.imgUrl(firstAvatar));
            GlideTools.loadCropCircleImg(this,
                    Check.imgUrl(firstAvatar), fakeCenterIv);

        }

        AnimatorSet set = new AnimatorSet();
        // new右2动画
        headRightIv2.setVisibility(View.VISIBLE);
        ObjectAnimator alphaNew = ObjectAnimator.ofFloat(headRightIv2, "alpha", 0f, 0.5f, 1f, 1f);
        final ObjectAnimator transXNew = ObjectAnimator.ofFloat(headRightIv2, "translationX", 0, -Math.abs(headRightIv2.getX() - headRightIv.getX())/2,-Math.abs(headRightIv2.getX() - headRightIv.getX()),-Math.abs(headRightIv2.getX() - headRightIv.getX()));
        ObjectAnimator scaleXNew = ObjectAnimator.ofFloat(headRightIv2, "scaleX", 0.8f, 0.9f, 1f, 1f);
        ObjectAnimator scaleYNew = ObjectAnimator.ofFloat(headRightIv2, "scaleY", 0.8f, 0.9f, 1f, 1f);
        alphaNew.setDuration(750);
        transXNew.setDuration(750);
        scaleXNew.setDuration(750);
        scaleYNew.setDuration(750);


        // 中间左移动同时缩小
        final ObjectAnimator transCenterX = ObjectAnimator.ofFloat(headCenterIv, "translationX", 0
                , -Utils.dp2px(HeadLoopActivity.this, 35), -Utils.dp2px(HeadLoopActivity.this, 70), -Utils.dp2px(HeadLoopActivity.this, 70));
        transCenterX.setDuration(750);
        ObjectAnimator scaleCenterX = ObjectAnimator.ofFloat(headCenterIv, "scaleX", 1f, 0.9f, 0.8f, 0.8f);
        scaleCenterX.setDuration(750);
        ObjectAnimator scaleCenterY = ObjectAnimator.ofFloat(headCenterIv, "scaleY", 1f, 0.9f, 0.8f, 0.8f);
        scaleCenterY.setDuration(750);

        // 右放大移动到中间
        ObjectAnimator transXRight = ObjectAnimator.ofFloat(headRightIv, "translationX", 0
                , -Utils.dp2px(HeadLoopActivity.this, 35), -Utils.dp2px(HeadLoopActivity.this, 70), -Utils.dp2px(HeadLoopActivity.this, 70));
        transXRight.setDuration(750);
        ObjectAnimator scaleXRight = ObjectAnimator.ofFloat(headRightIv, "scaleX", 1f, 1.125f, 1.25f, 1.25f);
        scaleXRight.setDuration(750);
        ObjectAnimator scaleYRight = ObjectAnimator.ofFloat(headRightIv, "scaleY", 1f, 1.125f, 1.25f, 1.25f);
        scaleYRight.setDuration(750);

        // 左边左移缩小淡出
        ObjectAnimator alphaLeft = ObjectAnimator.ofFloat(headLeftIv, "alpha", 1f, 0.5f, 0f, 0f);
        ObjectAnimator transXLeft = ObjectAnimator.ofFloat(headLeftIv, "translationX", 0, -Math.abs(headRightIv2.getX() - headRightIv.getX()));
        ObjectAnimator scaleXLeft = ObjectAnimator.ofFloat(headLeftIv, "scaleX", 1f, 0.9f, 0.8f, 0.8f);
        ObjectAnimator scaleYLeft = ObjectAnimator.ofFloat(headLeftIv, "scaleY", 1f, 0.9f, 0.8f, 0.8f);

        alphaLeft.setDuration(750);
        transXLeft.setDuration(750);
        scaleXLeft.setDuration(750);
        scaleYLeft.setDuration(750);

        set.playTogether(alphaNew, transXNew, scaleXNew, scaleYNew, transCenterX, scaleCenterX, scaleCenterY, transXRight, scaleXRight, scaleYRight, alphaLeft, transXLeft,scaleXLeft,scaleYLeft);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (headCenterIv == null || headRightIv == null || headLeftIv == null || headRightIv3 == null) {
                    return;
                }

                mCenterIndex++;
                if (userList.size() <= mCenterIndex) {
                    mCenterIndex = 0;
                    LogUtil.i(TAG, "mCenterIndex = 0");
                }
                int leftIndex = mCenterIndex - 1;
                if (leftIndex < 0) {
                    leftIndex = userList.size() - 1;
//                    headLeftIv.setVisibility(View.INVISIBLE);
                }

                int rightIndex = mCenterIndex + 1;
                if (userList.size() <= rightIndex) {
                    rightIndex = 0;
                    LogUtil.i(TAG, "rightIndex = 0");
                }
                fakeCenterIv.setVisibility(View.VISIBLE);

                HomeRandomUser homeRandomUserCenter = userList.get(mCenterIndex);
                if (homeRandomUserCenter != null) {

                    String firstAvatar = homeRandomUserCenter.getAvatar();
                    LogUtil.i(TAG, "homeUserCenter>>" + Check.imgUrl(firstAvatar));
                    Glide.with(HeadLoopActivity.this)
                            .load( Check.imgUrl(firstAvatar))
                            .apply(new RequestOptions().circleCrop())
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e,
                                                            Object model, Target<Drawable> target,
                                                            boolean isFirstResource) {
                                    fakeCenterIv.setVisibility(View.INVISIBLE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model,
                                                               Target<Drawable> target,
                                                               DataSource dataSource, boolean isFirstResource) {
                                    fakeCenterIv.setVisibility(View.INVISIBLE);
                                    return false;
                                }
                            })
                            .into(headCenterIv);


                    GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                            Check.imgUrl(firstAvatar), headCenterIv);

                }

                HomeRandomUser homeRandomUserRight = userList.get(rightIndex);
                if (homeRandomUserRight != null) {

                    String firstAvatar = homeRandomUserRight.getAvatar();
                    LogUtil.i(TAG, "homeUserRight>>" + Check.imgUrl(firstAvatar));
                    GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                            Check.imgUrl(firstAvatar), headRightIv);

                    String firstAvatar2 = homeRandomUserRight.getAvatar();
                    GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                            Check.imgUrl(firstAvatar2), headRightIv3);

                }

                HomeRandomUser homeRandomLeft = userList.get(leftIndex);
                if (homeRandomLeft != null) {
                    String firstAvatar = homeRandomLeft.getAvatar();
                    LogUtil.i(TAG, "homeUserLeft>>" + Check.imgUrl(firstAvatar));
                    GlideTools.loadCropCircleImg(HeadLoopActivity.this,
                            Check.imgUrl(firstAvatar), headLeftIv);

                }

                if (headCenterIv != null) {
                    headCenterIv.setTranslationX(0);
                    headCenterIv.setScaleX(1);
                    headCenterIv.setScaleY(1);
                }
                if (headRightIv != null) {
                    headRightIv.setTranslationX(0);
                    headRightIv.setScaleX(1);
                    headRightIv.setScaleY(1);
                }

                if (headRightIv2 != null) {
                    headRightIv2.setTranslationX(0);
                    headRightIv2.setAlpha(0f);
                }

                if (headLeftIv != null) {
                    headLeftIv.setAlpha(1f);
                    headLeftIv.setTranslationX(0);
                    headLeftIv.setScaleX(1);
                    headLeftIv.setScaleY(1);
                }

                if (headCenterIv != null) {
                    headCenterIv.setVisibility(View.VISIBLE);
                }

                if (headRightIv != null) {
                    headRightIv.setVisibility(View.VISIBLE);
                }

                if (headRightIv2 != null) {
                    headRightIv2.setVisibility(View.INVISIBLE);
                }

                if (headLeftIv != null) {
                    headLeftIv.setVisibility(View.VISIBLE);
                }
                startSecondAnima = false;

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                startSecondAnima = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
}
