package com.lj.app.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectPlayLabelPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> list;
    public SelectPlayLabelPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    public void setFragments(List<Fragment> fragments){
        if(list != null){
            list.clear();
            list.addAll(fragments);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        if(list != null && list.size()> position){
           return list.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(list == null) return 0;
        return list.size();
    }
}
