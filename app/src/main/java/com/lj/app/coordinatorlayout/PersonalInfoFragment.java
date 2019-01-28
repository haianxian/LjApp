package com.lj.app.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13717 on 2018/9/14.
 */

public class PersonalInfoFragment extends Fragment{

    RecyclerView rlv;
    List<String> list = new ArrayList<>();
    RecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personinfo,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        rlv = view.findViewById(R.id.persioninfo_rlv);
        for(int i=0; i< 50;i++){
            list.add(i+"");
        }
        adapter = new RecordAdapter(getActivity(), list);
        rlv.setAdapter(adapter);
    }
}
