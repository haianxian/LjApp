package com.lj.app.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.lj.app.R;

/**
 * Created by 13717 on 2018/3/20.
 */

public class ViewPagerItem{

    View rootView;
    ImageView iv;

    public View getView(Context context){
        rootView = LayoutInflater.from(context).inflate(R.layout.viewpager_item, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        iv = view.findViewById(R.id.vpItem_iv);
        iv.setImageResource(R.mipmap.ic_launcher);
    }

}
