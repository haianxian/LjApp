package com.lj.app.view.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lj.app.R;

public class JCView extends Activity {

    public static void open(Context context){
        Intent it = new Intent(context,JCView.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc_view);
    }
}
