package com.mico.framework.mvpdagger;

import android.content.Context;

import com.mico.framework.baselib.net.NetManager;
import com.mico.framework.mvpdagger.component.AppComponent;
import com.mico.framework.mvpdagger.component.DaggerAppComponent;
import com.mico.framework.mvpdagger.constant.Api;
import com.mico.framework.mvpdagger.module.AppModule;

/**
 * Created by LiuNana on 2017/3/15.
 * 整个module的控制类，进行一些初始化操作
 * module只能通过{MvpDaggerSDK}与外界交互
 */

public class MvpDaggerSDK {
    private Context mContext;
    private AppComponent appComponent;

    private MvpDaggerSDK() {
    }

    /***
     * 静态内部类单例模式
     * @return
     */
    public static MvpDaggerSDK getInstance(){
        return MvpDaggerSDKHolder.sInstance;
    }

    /**
     * 在Application类中调用，进行初始化操作
     * @param appContext ApplicationContext
     */
    public void initialize(Context appContext){
        mContext = appContext.getApplicationContext();
        NetManager.getInstance().setBaseUrl(Api.BASE_BOOK_URL);
        appComponent = createAppComponent();
    }

    public Context getAppContext(){
        return mContext;
    }


    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
            .appModule(new AppModule(mContext))
            .build();
    }

    private static class MvpDaggerSDKHolder {
        private static MvpDaggerSDK sInstance = new MvpDaggerSDK();
    }

}
