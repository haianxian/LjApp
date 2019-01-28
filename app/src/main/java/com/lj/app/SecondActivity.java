package com.lj.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.lj.app.base.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 13717 on 2018/b/5.
 */

public class SecondActivity extends AppCompatActivity {

//    @InjectView(R.id.recycleview)
    RecyclerView recycleview;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        ButterKnife.inject(this);
        List<String> list = new ArrayList<>();
        adapter = new MyAdapter(list);
        recycleview.setAdapter(adapter);
    }
}
