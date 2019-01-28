package com.lj.app.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 13717 on 2018/3/13.
 */

public class MyPagerAdapter extends PagerAdapter{

    private List<View> list;
    public MyPagerAdapter(List<View> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        int size = list.size();
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position), 0);
        return list.get(position);// 实例化页卡
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));// 删除页卡
    }
}
