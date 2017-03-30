package com.mico.framework.mvpdagger.module;

import com.mico.framework.mvpdagger.interactor.IBookListInteractor;
import com.mico.framework.mvpdagger.interactor.impl.BooInteractorImpl;
import com.mico.framework.mvpdagger.presenter.IBookListPresenter;
import com.mico.framework.mvpdagger.presenter.impl.BookListPresenterImpl;
import com.mico.framework.mvpdagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuNana on 2017/3/28.
 */
@Module
public class BookListModule {
    @Provides
    @ActivityScope
    IBookListInteractor providerInteractor(){
        return new BooInteractorImpl();
    }

    @Provides
    @ActivityScope
    IBookListPresenter providerPresenter(IBookListInteractor interactor) {
        return new BookListPresenterImpl(interactor);
    }
}
