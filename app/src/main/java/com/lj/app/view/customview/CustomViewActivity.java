package com.lj.app.view.customview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lj.app.R;

public class CustomViewActivity extends Activity{

    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
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
    }
}
