package com.mico.framework.mvpdagger.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuNana on 2017/3/16.
 */
@Module
public class AppModule  {
    private Context context;

    public AppModule(Context application)
    {
        context = application;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return context;
    }

    @Provides
    @Singleton
    public Resources provideResources(Context context)
    {
        return context.getResources();
    }
}