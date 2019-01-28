package com.lj.app.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by 13717 on 2018/3/13.
 */

public class AlphaTransformer implements ViewPager.PageTransformer{

    private float MINALPHA = 0.5f;

    /**
     * position取值特点：
     * 假设页面从0～1n，则：
     * 第一个页面position变化为[0,-1n]
     * 第二个页面position变化为[1n,0]
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setAlpha(MINALPHA);
        } else {
            //不透明->半透明
            if (position < 0) {//[0,-1n]
                page.setAlpha(MINALPHA + (1 + position) * (1 - MINALPHA));
            } else {//[1n,0]
                //半透明->不透明
                page.setAlpha(MINALPHA + (1 - position) * (1 - MINALPHA));
            }
        }
    }
}
