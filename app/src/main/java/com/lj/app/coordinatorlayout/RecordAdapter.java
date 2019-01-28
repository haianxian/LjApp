package com.lj.app.coordinatorlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lj.app.R;

import java.util.List;

/**
 * Created by 13717 on 2018/9/14.
 */

public class RecordAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<String> list;
    public RecordAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecordViewHolder)holder).textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public RecordViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.record_tv);
        }

    }
}
