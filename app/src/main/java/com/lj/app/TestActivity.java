package com.lj.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lj.app.tools.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 13717 on 2018/b/9.
 */

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.headlL)
    LinearLayout headLl;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TestDialogFragment.showTestDialogFragment(transaction);
//                DialogActivity.intentDialogActivity(TestActivity.this);
                Intent it = new Intent(TestActivity.this,ThirdActivity.class);
                startActivity(it);
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDialogFragment.showTestDialogFragment(transaction);
            }
        });
        headLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDialogFragment dialogFragment = new TestDialogFragment();
                        dialogFragment.hidenDialog();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i("TestActivity", "onNewIntent");
    }
}
