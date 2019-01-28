package com.lj.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by 13717 on 2018/b/9.
 */

public class TestDialogFragment extends DialogFragment{

    static TestDialogFragment dialog;
    public static void showTestDialogFragment(FragmentTransaction transaction){
        dialog = new TestDialogFragment();
        transaction.replace(R.id.frame_layout,dialog);
        transaction.commitAllowingStateLoss();
    }

    public  void hidenDialog(){
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getDialog().setCanceledOnTouchOutside(false);
        dialog.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dilaog, container, false);
    }
}
