package com.mico.framework.baselib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;

/**
 * Created by LiuNana on 2017/2/28.
 *
 */

public class NewImageSpan extends ImageSpan {

    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int BOTTOM = 3;

    private int align = CENTER;

    public NewImageSpan(Context context, final int drawableRes, int verticalAlignment, int align) {
        super(context, drawableRes, verticalAlignment);
        this.align = align;
    }

    public NewImageSpan(Context context, final int drawableRes, int align) {
        this(context, drawableRes, ImageSpan.ALIGN_BOTTOM, align);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end,
                       Paint.FontMetricsInt fontMetricsInt) {
        Drawable drawable = getDrawable();
        Rect rect = drawable.getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            int fontHeight = fmPaint.bottom - fmPaint.top;
            int drHeight = rect.bottom - rect.top;

            int top = drHeight / 2 - fontHeight / 4;
            int bottom = drHeight / 2 + fontHeight / 4;
            if (align == TOP) {
                fontMetricsInt.top = -bottom;
                fontMetricsInt.bottom = top;
                fontMetricsInt.descent = top;
            } else if (align == BOTTOM) {
                fontMetricsInt.top = -bottom;
                fontMetricsInt.bottom = drHeight / 2 - fontHeight / 2;
                fontMetricsInt.descent = drHeight / 2 - fontHeight / 2;
            } else {
                fontMetricsInt.ascent = -bottom;
                fontMetricsInt.top = -bottom;
                fontMetricsInt.bottom = top;
                fontMetricsInt.descent = top;
            }

        }
        return rect.right;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        // image to draw
        Drawable b = getDrawable();
        // font metrics of text to be replaced
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY;
        if (align == TOP) {
            transY = y + fm.ascent;
        } else if (align == BOTTOM) {
            transY = y + fm.descent - b.getBounds().bottom;
        } else {
            transY = (y + fm.descent + y + fm.ascent) / 2
                - b.getBounds().bottom / 2;
        }
        canvas.save();
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}
