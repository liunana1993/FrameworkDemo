package com.mico.framework.baselib.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mico.framework.baselib.util.AndroidUtils;

/**
 * Created by LiuNana on 2017/3/29.
 */

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private int marginLeftRight = 0;
    private int marginTopBottom = 0;
    private Context mContext;

    public SimpleItemDecoration(Context context) {
        marginLeftRight = AndroidUtils.dip2px(context, 20);
        marginTopBottom = AndroidUtils.dip2px(context, 10);
        mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
//        if (itemPosition == 0) {
            outRect.set(marginLeftRight, marginTopBottom, marginLeftRight, marginTopBottom);
//        } else {
//
//            outRect.set(marginLeftRight, marginTopBottom, marginLeftRight, 0);
//        }
    }
}
