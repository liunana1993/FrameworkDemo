package com.mico.framework.mvpdagger.interactor.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LiuNana on 2017/3/21.
 */

public class BaseInteractor1 {
    /**
     * 创建网络请求头
     *
     * @return 网络请求头Map
     */
    protected Map<String, String> createHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("platform", "6");
        header.put("businessType", "1");
        header.put("cityId", "1337");

        header.put("userId", "");
        header.put("token", "");
        header.put("deviceId", "");
        header.put("deviceToken", "");
        //等到master上才能引入push id
        header.put("platformVersion", "8.0.0");
        header.put("apiVersion", "4.6");
        header.put("caller-id", "Android");
        header.put("request-id", UUID.randomUUID().toString().replace("-", ""));
        header.put("mobile", "");
        header.put("userName", "");

        return header;
    }
}
