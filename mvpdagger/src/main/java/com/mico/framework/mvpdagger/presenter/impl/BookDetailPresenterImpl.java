package com.mico.framework.mvpdagger.presenter.impl;

import com.mico.framework.baselib.util.LogUtils;
import com.mico.framework.baselib.util.RxUtils;
import com.mico.framework.mvpdagger.fragment.IBookDetailView;
import com.mico.framework.mvpdagger.interactor.IBookDetailInteractor;
import com.mico.framework.mvpdagger.model.Book;
import com.mico.framework.mvpdagger.presenter.IBookDetailPresenter;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LiuNana on 2017/3/29.
 */

public class BookDetailPresenterImpl implements IBookDetailPresenter {
    private IBookDetailInteractor mInteractor;
    private IBookDetailView mView;
    private Disposable mDisposable;

    public BookDetailPresenterImpl(IBookDetailInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void showBookDetail(String bookId) {
        if (!isViewAttached()) {
            return;
        }
        mDisposable = mInteractor.getBookDetail(bookId).subscribe(new Consumer<Book>() {
            @Override
            public void accept(@NonNull Book book) throws Exception {
                LogUtils.e(book.toString());
                mView.showBookDetail(book);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                LogUtils.e(throwable);
            }
        });
    }

    @Override
    public void setView(IBookDetailView view) {
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
