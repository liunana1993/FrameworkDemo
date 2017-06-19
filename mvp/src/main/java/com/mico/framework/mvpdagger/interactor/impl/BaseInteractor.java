package com.mico.framework.mvpdagger.interactor.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LiuNana on 2017/3/21.
 */

public class BaseInteractor {
    /**
     * 创建网络请求头
     *
     * @return 网络请求头Map
     */
    protected Map<String, String> createHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return header;
    }
}
