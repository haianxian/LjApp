package com.lj.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lj.app.adapter.WatchMovieTagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoFitScrollViewActivity extends Activity {

    TagFlowLayout tagFlowLayout;
    WatchMovieTagAdapter adapter;
    List<String> secTagList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autofitscrollview);
        tagFlowLayout = findViewById(R.id.watchmovie_tag_list);
        Random random = new Random();
        int r = random.nextInt(20);
        for(int i=0; i< r; i++){
            secTagList.add(i+"cs");
        }
        adapter = new WatchMovieTagAdapter(AutoFitScrollViewActivity.this, secTagList);
        tagFlowLayout.setAdapter(adapter);
        adapter.notifyDataChanged();
    }
}
