package com.mico.framework.baselib.base.adapter;


import android.support.annotation.LayoutRes;

/**
 * Interface for multiple view types.
 *
 */
public interface IMulItemViewType<T> {

    /**
     * @return Will not be called if using a RecyclerView.
     */
    int getViewTypeCount();

    /**
     * Item view type, a non-negative integer is better.
     *
     * @param position current position of ViewHolder
     * @param t        model item
     * @return viewType
     */
    int getItemViewType(int position, T t);

    /**
     * Layout res.
     *
     * @param viewType {@link #getItemViewType(int, T)}
     * @return {@link LayoutRes}
     */
    @LayoutRes
    int getLayoutId(int viewType);
}