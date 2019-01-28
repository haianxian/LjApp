package com.lj.app.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lj.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13717 on 2018/b/5.
 */

public class BaseMyAdapter<T> extends RecyclerView.Adapter<BaseHolder> {

    List<T> list = new ArrayList<>();
    int layoutId;
    private Context context;
    public BaseMyAdapter(int layoutId, List<T> list){
        this.layoutId = layoutId;
        this.list = list;
//        for(int i=0; i< list.size(); i++){
//            list.add("商品记录"+i);
//        }
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        BaseHolder holder = new BaseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
      T item = list.get(position);
        onConvert(holder, item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected void onConvert(BaseHolder holder, T item){

    }
}
