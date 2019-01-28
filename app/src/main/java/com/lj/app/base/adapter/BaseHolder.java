package com.lj.app.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.lj.app.R;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13717 on 2018/b/5.
 */

public class BaseHolder extends RecyclerView.ViewHolder{

    private Map<Integer,View> mViews = new HashMap<>();
    public TextView textView;
    public BaseHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textview);
    }

    private <T> T getView(Integer mViewId){
        View view = mViews.get(mViewId);
      if(view == null){
          view = itemView.findViewById(mViewId);
          mViews.put(mViewId, view);
      }
      return (T)view;
    }

    protected void setText(Integer viewId, String value){
         TextView tv = getView(viewId);
        if(tv != null){
            tv.setText(value);
        }
    }
}
