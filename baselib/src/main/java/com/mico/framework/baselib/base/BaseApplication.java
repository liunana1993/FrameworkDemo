package com.mico.framework.baselib.base;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by LiuNana on 2017/4/6.
 */

public class BaseApplication extends MultiDexApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(App.INSTANCE);
    }
}
