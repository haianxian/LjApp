package com.lj.app.base.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.lj.app.db.SensitiveWord;
import com.lj.app.db.manager.DbManager;
import com.lj.app.tools.utils.FileUtils;

import java.util.List;

/**
 * Created by 13717 on 2017/12/5.
 */

public class InitializeService extends IntentService {

    public InitializeService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 加载本地敏感词库
//        getLocalSensiWords();
//        // 加载本地替换词库
//        getLocalReplaceWords();
//        // 获取服务器敏感词库
//        getSensitiveWord();
    }

    public static void start(Context context){
        Intent intent = new Intent(context, InitializeService.class);
        context.startService(intent);
    }

    private void getLocationSensiWord(){
        // 先查询数据库中是否有数据，没有则说明是第一次加载，需加载本地
        // 查询全部
        // List< Shop> list = getShopDao().loadAll();
        // List< Shop> list = getShopDao().queryBuilder().list();
        if(DbManager.getInstance().getDaoSession().getSensitiveWordDao().loadAll().size()<=0){
            List<String> localSensiWordList = FileUtils.getFromAssets("sensitivewords.txt");
            if (localSensiWordList == null) return;
            for(int i=0; i< localSensiWordList.size();i++){
                SensitiveWord sw = new SensitiveWord();
                sw.setName(localSensiWordList.get(i));
                DbManager.getInstance().getDaoSession().getSensitiveWordDao().insert(sw);
//                添加数据，如果有重复则覆盖
                DbManager.getInstance().getDaoSession().getSensitiveWordDao().insertOrReplace(sw);
            }
        }

    }

    private void getSensitiveWord(){
        // 从服务器下载完数据后
//        HttpEngine.getRequest(UrlConfig.SERVER_GET_SENSWORD, new HttpCallBack(GetSensitiveWord.class) {
//            @Override
//            public void onResult(RespEnum respEnum, String message, BaseResponse response) {
//                switch (respEnum) {
//                    case OK:
//                        GetSensitiveWord getSensitiveWord = (GetSensitiveWord) response.getData();
//                        List<String> sensitiveWordList = getSensitiveWord.getSensitiveWord();
//                        List<String> replaceWordList = getSensitiveWord.getReplaceWord();
//                        NimInforHelper nimInforHelper = new NimInforHelper(MyApplication.getInstance());
//                        nimInforHelper.saveSensitiveWord(sensitiveWordList);
//                        nimInforHelper.saveReplSensiWord(replaceWordList);
//                        break;
//                    case NET_ERROR:
//                        ToastUtils.showErrorToast(context, context.getString(R.string.network_error));
//                        break;
//                    case NET_TIMEOUT:
//                        ToastUtils.showErrorToast(context, context.getString(R.string.network_timeout));
//                        break;
//                    case FAILD:
//                        ToastUtils.showErrorToast(context, message);
//                    default:
//                        break;
//                }
//            }
//        });
        // 先删除本地数据库数据，再把服务器的保存数据库中
        DbManager.getInstance().getDaoSession().getSensitiveWordDao().deleteAll();
        // 保存到数据库
//        DbManager.getInstance().getDaoSession().insert(sw);
    }
}
