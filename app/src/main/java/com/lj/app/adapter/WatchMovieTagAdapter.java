package com.lj.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lj.app.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by 13717 on 2019/1/7.
 */

public class WatchMovieTagAdapter extends TagAdapter<String>{

    private Context context;
    private List<String> datas;
    public WatchMovieTagAdapter(Context context, List<String> datas) {
        super(datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_matchmovie_tag, parent, false);
        LinearLayout rootLl = view.findViewById(R.id.match_tag_Ll);
        TextView contentTv = view.findViewById(R.id.match_tag_item_tv);
        contentTv.setText(s.trim());
        return view;
    }

}
