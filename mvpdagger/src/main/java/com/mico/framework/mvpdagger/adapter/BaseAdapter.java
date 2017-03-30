package com.mico.framework.mvpdagger.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mico.framework.baselib.animation.AlphaInAnimation;
import com.mico.framework.baselib.base.adapter.DataBindAdapter1;
import com.mico.framework.baselib.base.adapter.SuperViewHolder;

import java.util.List;

/**
 * Created by LiuNana on 2017/3/16.
 * 楼盘列表的Adapter
 */

public class BaseAdapter<T> extends DataBindAdapter1<T> {

    public BaseAdapter(Context context, List<T> items, int layoutResId) {
        super(context, items, layoutResId);
        enableLoadAnimation(500, new AlphaInAnimation());
        setOnlyOnce(false);
    }

    @Override
    protected SuperViewHolder get(View convertView, ViewGroup parent, int Res) {
        SuperViewHolder holder;
        ViewDataBinding binding;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            binding = DataBindingUtil.inflate(inflater, Res, parent, false);
            convertView = binding.getRoot();
            holder = initHolder(convertView);
            holder.setTag(binding);
            convertView.setTag(holder);
        } else {
            holder = (SuperViewHolder) convertView.getTag();
        }
        return holder;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, T item) {
        //
    }
}