package com.lj.app.network.entity;

import java.io.Serializable;

/**
 * 返回bean的基本信息
 * Created by 13717 on 2017/8/14.
 */

public class BaseResponse<T> implements Serializable{

    /**
     * 返回状态码
     */
    int code;

    /**
     * 返回信息说明
     */
    String message;

     T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
