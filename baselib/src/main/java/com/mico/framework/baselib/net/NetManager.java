package com.mico.framework.baselib.net;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mico.framework.baselib.base.App;
import com.mico.framework.baselib.exception.InitException;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by LiuNana on 2017/3/20.
 * Retrofit + RxJava 网络封装
 */

public class NetManager {
    private static Context mContext;
    private Retrofit.Builder mBuilder;
    private OkHttpClient.Builder okHttpBuilder;
    private static String mBaseUrl = "";
    private static Interceptor mCacheInterceptor;

    private NetManager() {
        mContext = App.INSTANCE;
        // 创建Retrofit,初始化部分设置
        mBuilder = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

        okHttpBuilder = DefaultConfigFactory.initOkHttp(mContext);
    }

    public static void init(@NonNull Context context, @NonNull String baseUrl) {
        mContext = context;
        mBaseUrl = baseUrl;
    }

    /**
     * 获取NetManager
     *
     * @return NetManager
     */
    public static NetManager getInstance() {
        if (mContext == null) {
            throw new InitException("init the context first");
        }
        if (mBaseUrl == null) {
            throw new InitException("init the baseUrl first");
        }
        mCacheInterceptor = null;
        return SingletonHolder.INSTANCE;
    }

    public void setBaseUrl(String baseUrl) {
        if (!TextUtils.isEmpty(baseUrl)) {
            mBuilder.baseUrl(baseUrl);
            mBaseUrl = baseUrl;
        }
    }

    public void setCacheInterceptor(Interceptor interceptor) {
        mCacheInterceptor = interceptor;
    }

    public void setCacheInterceptor() {
        mCacheInterceptor = new CacheInterceptor(mContext);
    }


    /**
     * 获取对应的Service
     *
     * @param header  网络请求的header
     * @param service Service 的 class
     * @return <T>
     */
    public <T> T create(Map<String, String> header, Class<T> service) {
        setOkHttpInterceptor();
        okHttpBuilder.addInterceptor(new BaseInterceptor(header));
        return mBuilder.client(okHttpBuilder.build())
            .build().create(service);
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @return <T>
     */
    public <T> T create(Class<T> service) {
        return create(null, service);
    }

    private static class SingletonHolder {
        private static final NetManager INSTANCE = new NetManager();

        private SingletonHolder() {
        }
    }

    private void setOkHttpInterceptor() {
        okHttpBuilder.interceptors().clear();
        okHttpBuilder.networkInterceptors().clear();
        okHttpBuilder.addInterceptor(new LoggingInterceptor());
        if (mCacheInterceptor != null) {
            okHttpBuilder.addInterceptor(mCacheInterceptor);
            okHttpBuilder.addNetworkInterceptor(mCacheInterceptor);
        }
    }

}
