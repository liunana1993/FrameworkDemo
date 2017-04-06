package com.mico.framework.mvpdagger.presenter.impl;

import android.text.TextUtils;

import com.mico.framework.baselib.util.LogUtils;
import com.mico.framework.baselib.util.RxUtils;
import com.mico.framework.mvpdagger.fragment.IBookListView;
import com.mico.framework.mvpdagger.interactor.IBookListInteractor;
import com.mico.framework.mvpdagger.model.BookList;
import com.mico.framework.mvpdagger.presenter.IBookListPresenter;

import org.apache.commons.collections4.CollectionUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LiuNana on 2017/3/28.
 */

public class BookListPresenterImpl implements IBookListPresenter {
    private IBookListInteractor mInteractor;
    private IBookListView mView;
    private Disposable mDisposable;

    public BookListPresenterImpl(IBookListInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void showBooks(String param) {
        if (!isViewAttached()) {
            return;
        }
        String key = TextUtils.isEmpty(param)? "经典" : param ;
        mDisposable = mInteractor.getBooks(key,"id,title,image,summary,author").subscribe(new Consumer<BookList>() {
            @Override
            public void accept(@NonNull BookList bookList) throws Exception {
                LogUtils.e(bookList.toString());
                if (CollectionUtils.isNotEmpty(bookList.getBooks())) {
                    mView.showBooks(bookList.getBooks());
                } else{
                    mView.showEmptyView();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                LogUtils.e(throwable);
                mView.showFailView();
            }
        });
    }

    @Override
    public void setView(IBookListView view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
        RxUtils.dispose(mDisposable);
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
