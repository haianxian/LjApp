package com.lj.app.view.rv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lj.app.R;
import com.lj.app.tools.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class RvHorizonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;
    public RvHorizonAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RvHorViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_horn_show,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setBindViewHoler((RvHorViewHolder)holder,position);
    }

    private void setBindViewHoler(RvHorViewHolder holer,int position){
        if(list == null) return;
        String str = list.get(position);
        if(!TextUtils.isEmpty(str)){
            holer.textView.setText(str);
        }

        if(position == 0){
            holer.placeView.setVisibility(View.VISIBLE);
        } else {
            holer.placeView.setVisibility(View.GONE);
        }

        ViewGroup.LayoutParams params = holer.rootLl.getLayoutParams();
        if(params != null){
            params.width = (UIUtils.getScreenWidthPixels(context) - 30)/4;
            holer.rootLl.setLayoutParams(params);
        }
    }

    public class RvHorViewHolder extends RecyclerView.ViewHolder{
        View placeView;
        LinearLayout rootLl;
        TextView textView;
        public RvHorViewHolder(View itemView) {
            super(itemView);
            placeView = itemView.findViewById(R.id.view_place);
            rootLl = itemView.findViewById(R.id.root_linear);
            textView = itemView.findViewById(R.id.text);
        }
    }



    @Override
    public int getItemCount() {
        if(list == null) return 0;
        return list.size();
    }

    public void setDatas(List<String> tempList){
        if(list != null){
            list.clear();
            list.addAll(tempList);
            notifyDataSetChanged();
        }
    }
}
