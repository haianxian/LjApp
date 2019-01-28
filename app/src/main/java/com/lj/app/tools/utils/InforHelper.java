package com.lj.app.tools.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lj.app.common.PrefUtilsCommon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13717 on 2017/12/5.
 */

public class InforHelper {

    Context context;
    public InforHelper(Context context){
        this.context = context;
    }



    // 保存敏感词
    public <T>void saveSensitiveWord(List<T> datalist){
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        PrefUtils.putString(context, PrefUtilsCommon.SAVE_SENSITIVE_WORD, strJson);
    }

    // 获取敏感词
    public  <T> List<T> getSensitiveWord(){
        List<T> datalist = new ArrayList<T>();
        String strJson = PrefUtils.getString(context, PrefUtilsCommon.SAVE_SENSITIVE_WORD, "");
        if (TextUtils.isEmpty(strJson)) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {}.getType());
        return datalist;
    }

    // 保存需要替换的敏感词
    public  <T> void saveReplSensiWord(List<T> replaceList){
        if(null == replaceList || replaceList.size() <= 0) return;
        Gson gson = new Gson();
        // 转话为json再保存
        String strJson = gson.toJson(replaceList);
        PrefUtils.putString(context, PrefUtilsCommon.SAVE_REPLACE_SENSIWORD, strJson);
    }

    // 获取需要替换的敏感词
    public  <T> List<T> getReplSensiWord(){
        List<T> replaceList = new ArrayList<T>();
        String strJson = PrefUtils.getString(context, PrefUtilsCommon.SAVE_REPLACE_SENSIWORD, "");
        if(TextUtils.isEmpty(strJson)){
            return replaceList;
        }
        Gson gson = new Gson();
        replaceList = gson.fromJson(strJson, new TypeToken<List<T>>(){}.getType());
        return replaceList;
    }

}
