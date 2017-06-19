package com.mico.framework.mvpdagger.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.mico.framework.baselib.base.adapter.SuperViewHolder;
import com.mico.framework.mvpdagger.BR;
import com.mico.framework.mvpdagger.R;

import java.util.List;

/**
 * Created by LiuNana on 2017/3/16.
 * 适配使用databing的ListView,GridView,RecyclerView等
 * item的layout中，有一个"item"的Variable
 */

public class SimpleDataAdapter<T> extends BaseAdapter<T> {

    public SimpleDataAdapter(Context context, List<T> items, int layoutId) {
        super(context, items, layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, T item) {
        ViewDataBinding binding = (ViewDataBinding) holder.getTag();
        binding.setVariable(BR.item, item);
        binding.executePendingBindings();
    }
}