package com.mico.framework.baselib.base;

import android.app.Application;

import com.mico.framework.baselib.util.LogUtils;

/**
 * Created by LiuNana on 2017/4/6.
 */

public class App {
    public static final Application INSTANCE;

    static {
        Application app = null;
        try {
            app = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null)
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
        } catch (final Exception e) {
            LogUtils.e("Failed to get current application from AppGlobals.",e);
            try {
                app = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
                LogUtils.e("Failed to get current application from ActivityThread." , ex);
            }
        } finally {
            INSTANCE = app;
        }
    }
}
