package com.mico.framework.mvpdagger.presenter;

import com.mico.framework.mvpdagger.fragment.IBookDetailView;

/**
 * Created by LiuNana on 2017/3/29.
 */

public interface IBookDetailPresenter {
    void showBookDetail(String bookId);
    void setView(IBookDetailView view);
    void destroy();
}
