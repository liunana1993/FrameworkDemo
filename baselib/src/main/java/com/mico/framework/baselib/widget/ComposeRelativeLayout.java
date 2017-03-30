package com.mico.framework.baselib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description: 动态换行的RelativeLayout
 * Author: linxiaoran
 * Date: 2015-11-24 17:32
 */
public class ComposeRelativeLayout extends ViewGroup {

    private boolean mIsSingleLine;

    private int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    public ComposeRelativeLayout(Context context) {
        this(context, null);
    }

    public ComposeRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComposeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSingleLine(boolean isSingleLine) {
        mIsSingleLine = isSingleLine;
    }

    public void setChildMargins(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginTop = top;
        mMarginRight = right;
        mMarginBottom = bottom;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = 0;// 横坐标开始
        int y;//纵坐标开始
        int rows = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            x += width + mMarginRight + mMarginLeft;
            if (x > autualWidth || rows == 0) {
                x = width + getPaddingLeft();
                rows++;
            }
            if (rows == 1) {
                y = rows * (height + mMarginTop);
            } else {
                y = rows * (height + mMarginTop + mMarginBottom);
            }
            view.layout(x - width, y - height, x, y);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int actualWidth = MeasureSpec.getSize(widthMeasureSpec);//实际宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth() + mMarginLeft + mMarginRight;
            int height = child.getMeasuredHeight() + mMarginTop + mMarginBottom;
            x += width;
            if (!mIsSingleLine) {
                if (x > actualWidth) {//换行
                    x = width;
                    rows++;
                }
            } else {
                if (x > actualWidth) {//不换行且不截断，隐藏其他子view
                    x -= width;
                }
            }
            y = rows * height;
        }
        int measuredWidth = mIsSingleLine ? x : actualWidth;
        setMeasuredDimension(measuredWidth, y + getPaddingTop() + getPaddingBottom());
    }

}
