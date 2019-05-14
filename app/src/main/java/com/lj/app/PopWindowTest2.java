package com.lj.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.lj.app.view.popwindow.PopWindowView;

public class PopWindowTest2 extends Activity {

    private ImageView filterIv;
    PopWindowView popWindowView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow2);
        filterIv = findViewById(R.id.filter_iv);
        popWindowView = new PopWindowView(this);
        filterIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterIv != null && popWindowView != null) {
                    popWindowView.measurePopArrow(filterIv);
                    popWindowView.show(filterIv);
                }
            }
        });
    }
}
