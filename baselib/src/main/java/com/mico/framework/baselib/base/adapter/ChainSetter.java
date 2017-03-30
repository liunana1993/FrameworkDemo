package com.mico.framework.baselib.base.adapter;

import android.view.View;

/**
 * Some convenient methods called like a chain.
 */
interface ChainSetter<VH> {

    VH setTag(int viewId, Object tag);

    VH setOnClickListener(int viewId, View.OnClickListener listener);

    VH setOnLongClickListener(int viewId, View.OnLongClickListener listener);

}
