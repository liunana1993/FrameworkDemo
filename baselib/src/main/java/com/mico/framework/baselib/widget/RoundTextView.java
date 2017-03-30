package com.mico.framework.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mico.framework.baselib.R;

/**
 * Created by LiuNana on 2017/3/1.
 * 自定义控件：背景为圆角矩形的TextView
 */

public class RoundTextView extends AppCompatTextView {

    private int mBgColor = 0;
    private int mCornerSize = 0;

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        setBackgroundRounded(canvas, this.getMeasuredWidth(), this.getMeasuredHeight());
        super.onDraw(canvas);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        mBgColor = ta.getColor(R.styleable.RoundTextView_backgroundColor, Color.TRANSPARENT);
        mCornerSize = (int) ta.getDimension(R.styleable.RoundTextView_radius, 4);
        ta.recycle();
    }

    private void setBackgroundRounded(Canvas c, int w, int h) {
        if (w <= 0 || h <= 0) {
            return;
        }

        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(mBgColor);

        RectF rec = new RectF(0, 0, w, h);
        c.drawRoundRect(rec, mCornerSize, mCornerSize, paint);
    }

    public void setRoundBackgroundColor(@ColorInt int color) {
        if (color != mBgColor) {
            mBgColor = color;
            invalidate();
        }
    }

}

