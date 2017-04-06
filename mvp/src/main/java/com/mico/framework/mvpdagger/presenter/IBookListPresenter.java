package com.mico.framework.mvpdagger.presenter;

import com.mico.framework.mvpdagger.fragment.IBookListView;

/**
 * Created by LiuNana on 2017/3/28.
 */

public interface IBookListPresenter {
    void showBooks(String param);
    void setView(IBookListView view);
    void destroy();
}
