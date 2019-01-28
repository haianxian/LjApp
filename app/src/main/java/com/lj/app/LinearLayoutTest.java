package com.lj.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by 13717 on 2018/8/8.
 */

public class LinearLayoutTest extends AppCompatActivity{

    private LinearLayout ll;
    private ImageView imageIv1;
    private ImageView imageIv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout_test);
        ll = (LinearLayout) findViewById(R.id.linearlayout);
        imageIv1 = (ImageView) findViewById(R.id.image1);
        imageIv2 = (ImageView) findViewById(R.id.image2);

        imageIv1.setImageResource(R.mipmap.ic_launcher);
        imageIv2.setImageResource(R.drawable.a);

//        imageIv1.setImageResource(R.drawable.b);
//        imageIv2.setImageResource(R.drawable.b);
    }

    // 动态测量view
    public void getMeasureView(){
        //View代表方法传入的控件
        imageIv1.post(new Runnable() {
            @Override
            public void run() {
                int[] viewLocation = new int[2];
                imageIv1.getLocationInWindow(viewLocation);
                final int viewX = viewLocation[0]; // x 坐标
                int viewY = viewLocation[1]; // y 坐标
//                guidIv.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)guidIv.getLayoutParams();
//                        params.leftMargin = viewX + avChatIv.getWidth()/2 - guidIv.getWidth()/2;
//                        guidIv.setLayoutParams(params);
//                    }
//                });
            }
        });


    }
}
