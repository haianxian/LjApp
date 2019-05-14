package com.lj.app.config;


/**
 * Created by 13717 on 2017/8/14.
 */

public class ApiHelper {

    private static ApiHelper apiHelper = null;
    public boolean enabledPlaybackLog = false;
    private ApiHelper(){

    }

    public static ApiHelper getInstance(){
        if(apiHelper == null){
            apiHelper = new ApiHelper();
        }
        return apiHelper;
    }

    public void cancel(Object tag) {
//        HttpContextEngine.cancel(tag);
    }


}
