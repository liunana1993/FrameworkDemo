package com.mico.framework.baselib.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


public class SuperViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> childViews = new SparseArray<>();
    private Object Tag;

    public SuperViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int id) {
        View childView = childViews.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            if (childView != null)
                childViews.put(id, childView);
        }
        return (T) childView;
    }

    public Object getTag() {
        return Tag;
    }

    public void setTag(final Object tag) {
        Tag = tag;
    }
}
