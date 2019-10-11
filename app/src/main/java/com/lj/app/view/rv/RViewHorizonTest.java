package com.lj.app.view.rv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lj.app.R;
import com.lj.app.view.rv.adapter.RvHorizonAdapter;

import java.util.ArrayList;
import java.util.List;

public class RViewHorizonTest extends AppCompatActivity {

    RecyclerView recyclerView;
    RvHorizonAdapter adapter;

    public static void open(Context context){
        Intent it = new Intent(context,RViewHorizonTest.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_horizon_test);
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RvHorizonAdapter(this);
        List<String> list = new ArrayList<>();
        list.add("111111");
        list.add("222222");
        list.add("333333");
        list.add("444444");
        list.add("555555");
        list.add("666666");
        list.add("777777");
        list.add("888888");
        adapter.setDatas(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
