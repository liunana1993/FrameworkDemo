package com.mico.framework.mvpdagger.component;

import com.mico.framework.mvpdagger.fragment.impl.BookListFragment;
import com.mico.framework.mvpdagger.module.BookListModule;
import com.mico.framework.mvpdagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by LiuNana on 2017/3/28.
 */
@ActivityScope
@Component (modules = {BookListModule.class})
public interface BookListComponent {
    void inject(BookListFragment fragment);
}
