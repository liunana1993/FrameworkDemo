package com.mico.framework.baselib.net;

import android.content.Context;
import android.util.Log;

import com.mico.framework.baselib.util.LogUtils;
import com.mico.framework.baselib.util.NetUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiuNana on 2017/3/30.
 */

public class CacheInterceptor implements Interceptor {
    private Context mContext;

    public CacheInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //获取网络状态
        int netWorkState = NetUtils.getNetworkState(mContext);
        //无网络，请求强制使用缓存
        if (netWorkState == NetUtils.NETWORK_NONE) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build();
        }

        Response originalResponse = chain.proceed(request);
        switch (netWorkState) {
            case NetUtils.NETWORK_MOBILE://mobile network 情况下缓存两分钟
                int maxAge = 60 * 2;
                return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();

            case NetUtils.NETWORK_WIFI://wifi network 情况下不使用缓存
                maxAge = 60;
                return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();

            case NetUtils.NETWORK_NONE://none network 情况下离线缓存2周
                int maxStale = 60 * 60 * 24 * 2 * 7;
                return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
            default:
                throw new IllegalStateException("network state  is Erro!");
        }
    }

}
