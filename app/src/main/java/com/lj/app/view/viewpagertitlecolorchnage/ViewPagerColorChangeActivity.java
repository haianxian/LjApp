package com.lj.app.view.viewpagertitlecolorchnage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lj.app.R;

import java.util.ArrayList;
import java.util.List;
// https://blog.csdn.net/qq_24675479/article/details/81411996
public class ViewPagerColorChangeActivity extends AppCompatActivity {

    LinearLayout indictorLl;
    ViewPager viewPager;
    String[] title = {"aaa","bbb","ccc","dddd"};
    ViewPagerAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    List<View> indictorList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagercolorchange);
        indictorLl = findViewById(R.id.indictor_linear);
        viewPager = findViewById(R.id.viewpager);
        for(int i=0 ;i< title.length;i++){
            ColorChangeTextView textView = new ColorChangeTextView(this);
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            textView.setTxt(title[i]);
            textView.setOriginColor(Color.BLUE);
            textView.setChangeColor(Color.RED);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(params);
            indictorLl.addView(textView);
            indictorList.add(textView);
            fragmentList.add(TitleFragment.buildFragment());
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
//                ColorChangeTextView left = indictorList.get(position);
//                left.setDirect(ColorChangeTextView.Directtion.RIGHT_TO_LEFT);
//                left.setCurrProgress(1-positionOffset);
//
//                ColorChangeTextView right = indictorList.get(position+1);
//                right.setDirect(ColorChangeTextView.Directtion.LEFT_TO_RIGHT);
//                right.setCurrProgress(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(fragmentList != null && fragmentList.size()> position) return fragmentList.get(position);
            return null;
        }

        @Override
        public int getCount() {
            if(fragmentList == null) return 0;
            return fragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
