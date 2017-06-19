package com.mico.framework.mvpdagger.interactor.impl;

import com.mico.framework.baselib.net.NetManager;
import com.mico.framework.mvpdagger.interactor.IBookDetailInteractor;
import com.mico.framework.mvpdagger.interactor.IBookListInteractor;
import com.mico.framework.mvpdagger.model.Book;
import com.mico.framework.mvpdagger.model.BookList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Path;

/**
 * Created by LiuNana on 2017/3/28.
 */

public class BooInteractorImpl extends BaseInteractor implements IBookListInteractor, IBookDetailInteractor {
    @Override
    public Observable<BookList> getBooks(String key, String fields) {
        return NetManager.getInstance().create(createHeader(), IBookListInteractor.class)
            .getBooks(key, fields)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Book> getBookDetail(@Path("id") String bookId) {
        NetManager.getInstance().setCacheInterceptor();
        return NetManager.getInstance().create(createHeader(), IBookDetailInteractor.class)
            .getBookDetail(bookId)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
