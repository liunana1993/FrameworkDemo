package com.mico.framework.mvpdagger.component;

import com.mico.framework.mvpdagger.activity.BookDetailActivity;
import com.mico.framework.mvpdagger.module.BookDetailModule;
import com.mico.framework.mvpdagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by LiuNana on 2017/3/29.
 */
@ActivityScope
@Component(modules = {BookDetailModule.class})
public interface BookDetailComponent {
    void inject(BookDetailActivity activity);
}
