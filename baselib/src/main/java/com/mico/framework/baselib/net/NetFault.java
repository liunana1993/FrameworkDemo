package com.mico.framework.baselib.net;

/**
 * Created by LiuNana on 2017/3/20.
 * 网络请求失败，将结果包装成NetFault返回给主线程处理
 */

public final class NetFault extends RuntimeException{
    private final int errCode;

    public NetFault(int errCode,String message){
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
