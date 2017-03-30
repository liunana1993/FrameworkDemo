package com.mico.framework.baselib.util;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by LiuNana on 2017/3/16.
 */

public class AndroidUtils {

    public static boolean hasText(String iconUrl) {
        return !TextUtils.isEmpty(iconUrl);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
