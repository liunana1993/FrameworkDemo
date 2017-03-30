package com.mico.framework.baselib.net;


import io.reactivex.functions.Function;

/**
 * Created by LiuNana on 2017/3/20.
 * 剥离最终数据
 */

public class PayLoad<T> implements Function<BaseResponse<T>,T> {
    @Override
    public T apply( BaseResponse<T> tBaseResponse){//获取数据失败时，包装一个Fault 抛给上层处理错误
        if(!tBaseResponse.isSuccess()){
            throw new NetFault(tBaseResponse.getCode(),tBaseResponse.getMsg());
        }
        return tBaseResponse.getData();
    }
}
