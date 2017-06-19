package com.mico.framework.mvpdagger.module;

import com.mico.framework.mvpdagger.interactor.IBookDetailInteractor;
import com.mico.framework.mvpdagger.interactor.impl.BooInteractorImpl;
import com.mico.framework.mvpdagger.presenter.IBookDetailPresenter;
import com.mico.framework.mvpdagger.presenter.impl.BookDetailPresenterImpl;
import com.mico.framework.mvpdagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuNana on 2017/3/29.
 */
@Module
public class BookDetailModule {
    @Provides
    @ActivityScope
    IBookDetailInteractor providerInteractor(){
        return new BooInteractorImpl();
    }

    @Provides
    @ActivityScope
    IBookDetailPresenter providerPresenter(IBookDetailInteractor interactor) {
        return new BookDetailPresenterImpl(interactor);
    }
}
