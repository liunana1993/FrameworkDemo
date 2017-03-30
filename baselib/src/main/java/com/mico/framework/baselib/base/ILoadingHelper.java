package com.mico.framework.baselib.base;

/**
 * Created by LiuNana on 2017/3/15.
 */

public interface ILoadingHelper {
    void beforeLoading();
    void showLoading();
    void afterLoading();
    void loadingFail();
}
