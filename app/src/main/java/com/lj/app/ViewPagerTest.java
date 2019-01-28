package com.lj.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lj.app.base.adapter.MyPagerAdapter;
import com.lj.app.transformer.ScaleTransformer;
import com.lj.app.view.ViewPagerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13717 on 2018/3/13.
 */

public class ViewPagerTest extends AppCompatActivity{

    ViewPager viewPager;
    MyPagerAdapter pagerAdapter;
    List<View> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_slide);
        initView();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setPageMargin(80);
        viewPager.setOffscreenPageLimit(Integer.MAX_VALUE);
        for(int i=0;i<10;i++){
//            iv.setImageResource(R.mipmap.ic_launcher);
            list.add(new ViewPagerItem().getView(this));
        }
        pagerAdapter = new MyPagerAdapter(list);
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setPageTransformer(false, new AlphaTransformer());
        viewPager.setPageTransformer(false, new ScaleTransformer());
    }
}
