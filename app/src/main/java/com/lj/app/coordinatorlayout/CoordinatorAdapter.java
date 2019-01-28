package com.lj.app.coordinatorlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 13717 on 2018/9/14.
 */

public class CoordinatorAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    public CoordinatorAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

}
