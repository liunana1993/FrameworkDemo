package com.mico.framework.baselib.base.adapter;


public abstract class SimpleMulItemViewType<T> implements IMulItemViewType<T> {

    @Override
    public int getViewTypeCount() {
        return 1;
    }

}
