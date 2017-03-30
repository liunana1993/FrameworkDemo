package com.mico.framework.baselib.util;

import io.reactivex.disposables.Disposable;

/**
 * Created by LiuNana on 2017/3/16.
 */
public class RxUtils {
    private static void dispose(Disposable disposable) {
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            } else {
                LogUtils.i("Already disposed");
            }
        } else {
            LogUtils.i("Disposable doesn't exist");
        }
    }

    public static void dispose(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            dispose(disposable);

        }
    }
}
