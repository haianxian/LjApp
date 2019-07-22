package com.lj.app.view.customview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lj.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomViewActivity extends Activity{

    TextView textView1;
    TextView textView2;
    TextView textView3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv_set_color);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JCView.open(CustomViewActivity.this);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JCView.open(CustomViewActivity.this);
            }
        });
        final List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int size = colorList.size();
                int r = random.nextInt(size);
                if(size> r){
                    textView3.setBackgroundColor(colorList.get(r));
                }
            }
        });
    }
}
