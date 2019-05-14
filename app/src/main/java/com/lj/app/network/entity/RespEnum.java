package com.lj.app.network.entity;

/**
 * 返回状态的枚举
 * Created by 13717 on 2017/8/14.
 */

public enum RespEnum {
    /**
     * 网络连接失败
     */
    NET_ERROR,

    /**
     * 网络连接超时
     */
    NET_TIMEOUT,

    /**
     * 请求失败
     */
    FAILD,

    /**
     * 解析异常
     */
    EXCEPTION,

    /**
     * 返回成功
     */
    OK;
}
