package com.mico.framework;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mico.framework.baselib.net.NetManager;


/**
 * Created by LiuNana on 2017/3/15.
 */

public class CustomerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        //初始化网络FddNetwork
        NetManager.init(this, Api.BASE_BOOK_URL);
        MvpDaggerSDK.getInstance().initialize(this);
    }
}
