package com.mico.framework.baselib.base.adapter;

import android.support.v7.widget.RecyclerView;

import com.mico.framework.baselib.animation.BaseAnimation;

/**
 * Animation interface for adapter.
 *
 */
interface IAnimation {

    void enableLoadAnimation();

    void enableLoadAnimation(long duration, BaseAnimation animation);

    void cancelLoadAnimation();

    void setOnlyOnce(boolean onlyOnce);

    void addLoadAnimation(RecyclerView.ViewHolder holder);

}
