package com.lj.app.view.animation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lj.app.R;
import com.lj.app.base.app.MyApplication;
import com.lj.app.tools.utils.MyInterpolator;
import com.lj.app.tools.utils.UIUtils;
import com.lj.app.tools.utils.rangeseekbar.Utils;
import com.lj.app.view.TestFragment;
import com.lj.app.view.TestFragment1;
import com.lj.app.view.TestFragment2;
import com.lj.app.view.TestFragment3;
import com.lj.app.view.adapter.SelectPlayLabelPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class PingFenViewActivity extends FragmentActivity {

    FrameLayout fl1;
    FrameLayout fl2;
    FrameLayout fl3;
    FrameLayout fl4;

    TranslateAnimation translateAnimationx;
    TranslateAnimation translateAnimationy;
    AnimationSet animationSet;
    private ImageView chicken;
    MagicIndicator magicIndicator;
    CommonNavigator navigator;
    CommonNavigatorAdapter navigatorAdapter;
    ViewPager selectplayViewpager;
    List<String> labelList = new ArrayList<>();
    SelectPlayLabelPagerAdapter playLabelPagerAdapter;

    public static void open(Context context){
        Intent it = new Intent(context, PingFenViewActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingfen_view);
        magicIndicator = findViewById(R.id.magic_indicator);
        selectplayViewpager = findViewById(R.id.selectplay_viewpager);
        chicken = findViewById(R.id.iv_long_dictor);
        fl1 = findViewById(R.id.root_fl);
        fl2 = findViewById(R.id.root_fl2);
        fl3 = findViewById(R.id.root_fl3);
        fl4 = findViewById(R.id.root_fl4);
//        fl1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fl1.setScaleX(1.5f);
//                fl1.setScaleY(1.5f);
//                fl2.setScaleX(0.8f);
//                fl2.setScaleY(0.5f);
//                fl3.setScaleX(0.5f);
//                fl3.setScaleY(0.5f);
//                fl4.setScaleX(0.5f);
//                fl4.setScaleY(0.5f);
//                  ViewGroup.LayoutParams params = fl1.getLayoutParams();
//                  int width = fl1.getMeasuredWidth();
//                  int height = fl1.getMeasuredHeight();
//                  if(params != null){
//                      params.width = 2*width;
//                      params.height = 2*height;
//                      fl1.setLayoutParams(params);
//                  }
//            }
//        });
//        animation();
        for(int i=0; i< 4;i++){
            labelList.add("测试"+i);
        }
        initMagicIndicator();
    }

    public void initMagicIndicator() {
        playLabelPagerAdapter = new SelectPlayLabelPagerAdapter(this.getSupportFragmentManager());
        List<Fragment> fragmentsList = new ArrayList<>();
        TestFragment testFragment = TestFragment.buildFragment();
        TestFragment1 testFragment1 = TestFragment1.buildFragment();
        TestFragment2 testFragment2 = TestFragment2.buildFragment();
        TestFragment3 testFragment3 = TestFragment3.buildFragment();
        fragmentsList.add(testFragment);
        fragmentsList.add(testFragment1);
        fragmentsList.add(testFragment2);
        fragmentsList.add(testFragment3);
        playLabelPagerAdapter.setFragments(fragmentsList);
        selectplayViewpager.setAdapter(playLabelPagerAdapter);
        magicIndicator.setBackgroundColor(Color.TRANSPARENT);
        navigator = new CommonNavigator(this);
        navigator.setLeftPadding(Utils.dp2px(MyApplication.getInstance(), 12));
        navigator.setRightPadding(Utils.dp2px(MyApplication.getInstance(), 12));
        navigator.setAdapter(navigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                if (labelList == null) return 0;
                return labelList.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                commonPagerTitleView.setContentView(R.layout.layout_label_type_indicator);
                final FrameLayout rootFl = commonPagerTitleView.findViewById(R.id.root_fl);
                final ImageView bgIv = commonPagerTitleView.findViewById(R.id.label_bg);
                final TextView nameTv = commonPagerTitleView.findViewById(R.id.label_title);
                final ImageView imgIv = commonPagerTitleView.findViewById(R.id.label_img);

                final int screenWidth = UIUtils.getScreenWidthPixels(context);
                final int mormalWidth = (screenWidth - UIUtils.dip2px(48))/4;

                LinearLayout.LayoutParams layoutParams =
                        (LinearLayout.LayoutParams) commonPagerTitleView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = UIUtils.dip2px(117);
                    commonPagerTitleView.setLayoutParams(layoutParams);
                }

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) rootFl.getLayoutParams();
                if (params != null) {
                    params.gravity = Gravity.BOTTOM;
                    rootFl.setLayoutParams(params);
                }

                if (labelList.get(index) != null) {
                    final String title = labelList.get(index);
                    if(title != null){
                        nameTv.setText(title);

                        commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                            @Override
                            public void onSelected(int i, int i1) {
                                if(nameTv == null) return;
                                nameTv.setTextColor(0xFFFFFFFF);
                                nameTv.setTextSize(14);
                                TextPaint paint = nameTv.getPaint();
                                if(paint != null){
                                    paint.setFakeBoldText(true);
                                }

                                if(labelList != null && labelList.size()>i){
                                    String labelEntity = labelList.get(i);
                                    nameTv.setText(labelEntity);
                                }

                                if(rootFl == null ) return;
                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rootFl.getLayoutParams();
                                if(params != null){
                                    params.width = mormalWidth*4/3;
//                                    params.height = UIUtils.dip2px(117);
                                    params.leftMargin = UIUtils.dip2px(5);
                                    params.rightMargin = UIUtils.dip2px(5);
                                    rootFl.setLayoutParams(params);
                                }
                                rootFl.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        int[] viewLocation = new int[2];
                                        rootFl.getLocationInWindow(viewLocation);
                                        final int filterX = viewLocation[0];
                                        int filterY = viewLocation[1];
                                        chicken.post(new Runnable() {
                                            @Override
                                            public void run() {
//                                                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) chicken.getLayoutParams();
//                                                layoutParams.leftMargin = filterX+rootFl.getWidth()/2-chicken.getWidth()/2;
//                                                chicken.setLayoutParams(layoutParams);
                                                  int transX = filterX+rootFl.getWidth()/2-chicken.getWidth()/2;
                                                  chicken.animate().translationX(transX).setDuration(300);
                                            }
                                        });
                                    }
                                });

                            }

                            @Override
                            public void onDeselected(int i, int i1) {

                                if(nameTv == null) return;
                                nameTv.setTextColor(0xFFFFFFFF);
                                nameTv.setTextSize(12);
                                TextPaint paint = nameTv.getPaint();
                                if(paint != null){
                                    paint.setFakeBoldText(false);
                                }

                                if(rootFl == null) return;
                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) rootFl.getLayoutParams();
                                if(params != null){
                                    params.width = (screenWidth - UIUtils.dip2px(60)-mormalWidth*4/3)/3;
//                                    params.height = UIUtils.dip2px(100);
//                                    params.topMargin = UIUtils.dip2px(20);
                                    params.leftMargin = UIUtils.dip2px(5);
                                    params.rightMargin = UIUtils.dip2px(5);
                                    rootFl.setLayoutParams(params);
                                }
                            }

                            @Override
                            public void onLeave(int i, int i1, float v, boolean b) {

                            }

                            @Override
                            public void onEnter(int i, int i1, float v, boolean b) {

                            }
                        });
                        commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(selectplayViewpager == null) return;
                                selectplayViewpager.setCurrentItem(index);
                            }
                        });
                    }

                }

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(navigator);
        LinearLayout titleContainer = navigator.getTitleContainer();
        if (titleContainer != null) {
            titleContainer.setGravity(Gravity.BOTTOM);
        }
        selectplayViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(magicIndicator == null) return;
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if(magicIndicator == null) return;
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(magicIndicator == null) return;
                magicIndicator.onPageScrollStateChanged(state);
            }
        });

    }

    private void animation(){
        animationSet=new AnimationSet(false);//此处必须设为false
                translateAnimationx=new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.2f,
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f );
        translateAnimationx.setDuration(5000);

        translateAnimationy=new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 1f );
        translateAnimationy.setDuration(5000);
        translateAnimationy.setInterpolator(new MyInterpolator());
        animationSet.addAnimation(translateAnimationx);
        animationSet.addAnimation(translateAnimationy);
        chicken.setAnimation(animationSet);
        animationSet.start();

    }
}
