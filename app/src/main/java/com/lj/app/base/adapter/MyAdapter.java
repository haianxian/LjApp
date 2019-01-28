package com.lj.app.base.adapter;

import com.lj.app.R;
import com.lj.app.base.model.BaseResponse;

import java.util.List;

/**
 * Created by 13717 on 2018/b/5.
 */

public class MyAdapter extends BaseMyAdapter<String>{

    public MyAdapter(List<String> list) {
        super(R.layout.item_second, list);
    }

    @Override
    protected void onConvert(BaseHolder holder, String item) {
        super.onConvert(holder, item);
        holder.setText(R.id.textView, item);
    }
}
