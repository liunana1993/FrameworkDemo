package com.mico.framework.mvpdagger.interactor;

import com.mico.framework.mvpdagger.constant.Api;
import com.mico.framework.mvpdagger.model.Book;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LiuNana on 2017/3/29.
 */

public interface IBookDetailInteractor {
    @GET(Api.GET_BOOK_DETAIL)
    Observable<Book> getBookDetail(@Path("id") String bookId);
}
