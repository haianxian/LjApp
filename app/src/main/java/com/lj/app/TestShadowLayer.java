package com.lj.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lj.app.view.DrawShadowLayer;

/**设置阴影
 * https://www.jb51.net/article/95639.htm
 * Created by 13717 on 2018/8/29.
 */

public class TestShadowLayer extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawShadowLayer(this));

    }
}
