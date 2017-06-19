package com.mico.framework.mvpdagger.fragment;

import com.mico.framework.mvpdagger.model.Book;

import java.util.List;

/**
 * Created by LiuNana on 2017/3/28.
 */

public interface IBookListView {
    void showBooks(List<Book> bookList);
    void showEmptyView();
    void showFailView();
}
