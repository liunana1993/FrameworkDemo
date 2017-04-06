package com.mico.framework.mvpdagger.presenter.impl;

import com.mico.framework.baselib.net.BaseResponse;
import com.mico.framework.baselib.net.PayLoad;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuNana on 2017/3/20.
 */

public class BasePresenter {

    /**
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected  <T> Observable<T> observe(Observable<BaseResponse<T>> observable){
        return observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new PayLoad<T>());
    }

}
