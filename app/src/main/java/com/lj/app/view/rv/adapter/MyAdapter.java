package com.lj.app.view.rv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lj.app.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    public MyAdapter(Context context){
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dapter_item_content,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setViewHolderData((MyViewHolder)holder,position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }

    private void setViewHolderData(MyViewHolder holder, int position){
        if(list == null) return;
        String content = list.get(position);
        holder.textView.setText(content);
    }


    @Override
    public int getItemCount() {
        if(list == null) return 0;
        return list.size();
    }

    public void setData(List<String> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}
