package com.mico.framework.mvpdagger.utils;

import android.text.TextUtils;
import android.view.View;

/**
 * Created by LiuNana on 2017/3/27.
 */

public class TextHelper {
    public static int getVisibility(String url) {
        return TextUtils.isEmpty(url) ? View.GONE : View.VISIBLE;
    }
}
