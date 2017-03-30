package com.mico.framework.baselib.net;

import java.io.Serializable;

/**
 * Created by LiuNana on 2017/3/20.
 * 封装网络请求的实体
 */

public class BaseResponse<T> implements Serializable{

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return code == 200;
    }

}
