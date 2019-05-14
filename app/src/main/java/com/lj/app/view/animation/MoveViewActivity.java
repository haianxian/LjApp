package com.lj.app.view.animation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lj.app.view.MoveViewLayout;

public class MoveViewActivity extends Activity {

    public static void open(Context context){
        Intent it = new Intent(context, MoveViewActivity.class);
        context.startActivity(it);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoveViewLayout moveViewLayout = new MoveViewLayout(this);
        setContentView(moveViewLayout);
    }
}
