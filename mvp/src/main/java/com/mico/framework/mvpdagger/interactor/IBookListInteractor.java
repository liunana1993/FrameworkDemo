package com.mico.framework.mvpdagger.interactor;

import com.mico.framework.mvpdagger.constant.Api;
import com.mico.framework.mvpdagger.model.Book;
import com.mico.framework.mvpdagger.model.BookList;

import io.reactivex.Observable;
import retrofit2.http.Query;
import retrofit2.http.GET;

/**
 * Created by LiuNana on 2017/3/28.
 */

public interface IBookListInteractor {
    @GET(Api.GET_BOOKS)
    Observable<BookList> getBooks(@Query("q") String key, @Query("fields") String fields);
}
