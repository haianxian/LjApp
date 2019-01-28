package com.lj.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;

/**图片缩放
 * Created by 13717 on 2018/8/24.
 */

public class ImageViewScaleTest extends AppCompatActivity{

    PhotoView photoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagescale);
        photoView = (PhotoView) findViewById(R.id.photo_view);
        // 设置图片资源
        photoView.setImageResource(R.drawable.a);
        // 图片填充类型
//        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 设置可以缩放
        photoView.setZoomable(true);
        // X方法拉伸5倍
//        photoView.setScaleX(5);
        // 设置长按事件
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }
}
