package com.lj.app.view.rv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lj.app.R;
import com.lj.app.view.rv.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestRVItemDecoration extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rv_itemdecoration);
        recyclerView = findViewById(R.id.recyclerview);
        initView();
    }

    private void initView(){
        myAdapter = new MyAdapter(this);
        List<String> list = new ArrayList<>();
        for(int i=0;i<30;i++){
            list.add("数据"+i);
        }
        myAdapter.setData(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 添加系统分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        recyclerView.addItemDecoration(new MyRecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,2, ContextCompat.getColor(this,R.color.colorAccent)));
//        recyclerView.addItemDecoration(new MyRecyclerViewDivider(this,LinearLayoutManager.VERTICAL,2, ContextCompat.getColor(this,R.color.colorAccent)));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        recyclerView.addItemDecoration(new GridDividerItemDecoration(this, 20,true));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(myAdapter);
    }
}

