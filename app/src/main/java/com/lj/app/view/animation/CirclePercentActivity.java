package com.lj.app.view.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lj.app.R;
import com.lj.app.tools.utils.LogUtil;
import com.lj.app.view.CirclePercentView;

/**圆形百分比进度条
 * Created by 13717 on 2018/12/25.
 */

public class CirclePercentActivity extends AppCompatActivity{

    CirclePercentView circlePercentView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circlepercent);
        circlePercentView = findViewById(R.id.circlePercentview);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = (int)(Math.random()*100);
                LogUtil.i("CirclePercent>>", "n>>>"+n);
                circlePercentView.setPercent(n);
            }
        });
    }



}
