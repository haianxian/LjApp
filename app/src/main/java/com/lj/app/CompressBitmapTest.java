package com.lj.app;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lj.app.tools.utils.LogUtil;

/**
 * Created by 13717 on 2018/8/31.
 */

public class CompressBitmapTest extends AppCompatActivity{

    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compressbitmap);
        imageView = (ImageView) findViewById(R.id.before);
        imageView2 = (ImageView) findViewById(R.id.after);

//        imageView.setImageResource(R.drawable.img);
        Bitmap bitmap = compressBitmap(getResources(), R.drawable.img, 300 , 300);
        BitmapFactory.Options options = new BitmapFactory.Options();
        LogUtil.i("measure","outWidth>>"+bitmap.getWidth());
        LogUtil.i("measure","outHeight>>"+bitmap.getHeight());
        imageView2.setImageBitmap(bitmap);
    }

    private Bitmap compressBitmap(Resources res, int id, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id,options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        options.inScaled = false;

        return BitmapFactory.decodeResource(res, id,options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
       int width = options.outWidth;
       int height = options.outHeight;
       int insampleSize = 1;
        if(width > reqWidth || height > reqHeight){
            while ((width/insampleSize) > reqWidth && (height/insampleSize)>reqHeight){
                insampleSize *=2;
                LogUtil.i("measure","width>>"+width);
                LogUtil.i("measure","height>>"+height);
                LogUtil.i("measure","insampleSize>>"+insampleSize);
            }
        }
        return insampleSize;
    }
}
