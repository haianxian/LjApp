package com.lj.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lj.app.R;
import com.lj.app.audio.AudioPlayView;
import com.lj.app.bean.AudioData;
import com.lj.app.tools.utils.AnimationUtils;
import com.lj.app.tools.utils.DotsView;
import com.lj.app.tools.utils.LogUtil;

import java.util.List;

/**
 * Created by 13717 on 2018/12/20.
 */

public class AudioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<AudioData> list;
    private int animationPos = -1;
    public AudioAdapter(Context context, List<AudioData> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_audio_item, parent,false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setAudioData((AudioViewHolder)holder,position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if(payloads != null && payloads.size() > 0){
            Object o = payloads.get(0);
            if(o instanceof String){
                String tag = (String)o;
                switch (tag){
                    case "lightAnima":
                        LogUtil.i("AudioAdapter>>", "局部刷新lightAnima>>"+position);
                        if(animationPos == position){
                            AnimationUtils.lightAnim(((AudioViewHolder)holder).dotsView, ((AudioViewHolder)holder).lightIv);
                            animationPos = -1;
                        }
                        break;
                }
            }
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder{
        AudioPlayView audioPlayView;
        DotsView dotsView;
        TextView lightIv;
        public AudioViewHolder(View itemView) {
            super(itemView);
            audioPlayView = itemView.findViewById(R.id.audioplay);
            dotsView = itemView.findViewById(R.id.vDotsView);
            lightIv = itemView.findViewById(R.id.light_img);
        }
    }

    private void setAudioData(AudioViewHolder holder, final int position){
        final AudioData audioData = list.get(position);
        if(audioData != null){
            holder.audioPlayView.init(audioData.isPlayTime(), audioData.getPlayUri());
            holder.lightIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 避免动画复用问题
                    if(!audioData.isLight()){
                        animationPos = position;
                    } else {
                        animationPos = -1;
                    }
                    notifyItemChanged(animationPos, "lightAnima");
//                    notifyDataSetChanged();
                }
            });

        }
    }


}
