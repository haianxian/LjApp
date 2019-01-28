package com.lj.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lj.app.custom.widget.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13717 on 2018/b/6.
 */

public class ThirdActivity extends AppCompatActivity {

    @BindView(R.id.text)
    MyButton text;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.edittext)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);
        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("ThirdActivity", "onTouch");
                return false;
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ThirdActivity", "setOnClickListener");
//                Toast.makeText(ThirdActivity.this, "setOnClickListener", Toast.LENGTH_LONG).show();
            }
        });


    }

//    @OnClick({R.id.text, R.id.ll, R.id.edittext})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.text:
//                Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
//                break;
////            case R.id.ll:
////                Toast.makeText(this, "LinearLayout", Toast.LENGTH_SHORT).show();
////                break;
//            case R.id.edittext:
//
//                break;
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("ThirdActivity", "onTouchEvent");
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ThirdActivity", "Activity_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ThirdActivity", "Activity_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ThirdActivity", "Activity_ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("ThirdActivity", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        02-08 09:27:15.870 22625-22625/com.lj.app D/ThirdActivity: dispatchTouchEvent
//        02-08 09:27:15.870 22625-22625/com.lj.app D/ThirdActivity: onTouch
//        02-08 09:27:15.870 22625-22625/com.lj.app D/ThirdActivity: MotionEvent.ACTION_DOWN
//        02-08 09:27:18.540 22625-22625/com.lj.app D/ThirdActivity: dispatchTouchEvent
//        return true:ThirdActivity: dispatchTouchEvent
// return false:ThirdActivity: dispatchTouchEvent
    }




//    都按照默认打印出
//    02-08 10:18:19.080 14888-14888/com.lj.app D/ThirdActivity: dispatchTouchEvent
//02-08 10:18:19.080 14888-14888/com.lj.app D/ThirdActivity: MyLinearLayout_dispatchTouchEvent
//02-08 10:18:19.080 14888-14888/com.lj.app D/ThirdActivity: MyLinearLayout_onInterceptTouchEvent
//02-08 10:18:19.080 14888-14888/com.lj.app D/ThirdActivity: MyButton_dispatchTouchEvent
//02-08 10:18:19.080 14888-14888/com.lj.app D/ThirdActivity: MyButton_MotionEvent.ACTION_DOWN
//02-08 10:18:19.210 14888-14888/com.lj.app D/ThirdActivity: dispatchTouchEvent
//02-08 10:18:19.210 14888-14888/com.lj.app D/ThirdActivity: MyLinearLayout_dispatchTouchEvent
//02-08 10:18:19.210 14888-14888/com.lj.app D/ThirdActivity: MyLinearLayout_onInterceptTouchEvent
//02-08 10:18:19.210 14888-14888/com.lj.app D/ThirdActivity: MyButton_dispatchTouchEvent
//02-08 10:18:19.210 14888-14888/com.lj.app D/ThirdActivity: MyButton_MotionEvent.ACTION_UP
}
