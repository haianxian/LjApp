package com.lj.app.view.viewpagertitlecolorchnage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.app.R;

public class TitleFragment extends Fragment {


    public static TitleFragment buildFragment(){
        TitleFragment titleFragment = new TitleFragment();
        return titleFragment;
    }
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test,container,false);
        return view;
    }
}
