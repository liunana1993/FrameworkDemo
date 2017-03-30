package com.mico.framework.baselib.net;

import android.content.Context;

import com.mico.framework.baselib.util.LogUtils;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Created by LiuNana on 2017/3/20.
 * 封装Retrofit的默认配置
 */

public class DefaultConfigFactory {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 6;

    private DefaultConfigFactory() {
        //to hide the public constructor
    }

    public static OkHttpClient.Builder initOkHttp(Context context) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接超时时间
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
            .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)//读操作超时时间
            .sslSocketFactory(getSSLSocketFactory(), new CustomerTrustManager())
            .hostnameVerifier(createHostnameVerifier());
        if (context != null) {
            okHttpBuilder.cache(new Cache(new File(context.getExternalCacheDir(),
                "okhttpCache"), 10 * 1024 * 1024));
        }
        return okHttpBuilder;
    }

    /**
     * set SSLSocketFactory
     * {@link HostnameVerifier}
     */
    private static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] {new CustomerTrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return null;
    }

    private static class CustomerTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            //
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            //
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    /**
     * set HostnameVerifier
     * {@link HostnameVerifier}
     */
    private static HostnameVerifier createHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

}
