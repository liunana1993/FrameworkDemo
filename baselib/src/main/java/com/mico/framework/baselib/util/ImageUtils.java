package com.mico.framework.baselib.util;

import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by LiuNana on 2017/3/15.
 */

public class ImageUtils {
    public static void displayImage(String iconUrl, ImageView imageView) {

    }
    /**
     * - 800x600 同时指定宽高，非等比缩放，宽为800px，高为600px，不管原图尺寸是什么，都缩放到此尺寸 - 800x 只指定宽，等比缩放，宽为800px，不关心高有多大 -
     * x600 只指定高，等比缩放，高为800px，不关心宽有多大 - 800s600 同时指定宽高，等比缩放，宽最大不超过800px，高最大不超过600px，如果原图尺寸小于缩放尺寸，则不放大
     * - 800S600 同时指定宽高，等比缩放，宽最大不超过800px，高最大不超过600px，如果原图尺寸小于缩放尺寸，则放大 - 800m600
     * 同时指定宽高，等比缩放，宽最小不小于800px，高最小不小于600px，如果原图宽小于指定宽度或高小于指定高度，则不放大 - 800M600
     * 同时指定宽高，等比缩放，宽最小不小于800px，高最小不小于600px，如果原图宽小于指定宽度或高小于指定高度，则放大
     * <p>
     * 800M600 同时指定宽高，等比缩放，宽最小不小于800px，高最小不小于600px，如果原图宽小于指定宽度或高小于指定高度，则放大
     *
     * @return image 替换为 thumb, 其后紧随 /[width]M[height] eg;800M600
     */
    public static String getThumbImageUrl(String url, int w, int h, boolean isWater) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }

        String thumbStr;
        if (isWater) {
            thumbStr = "thumb/" + w + "s" + h;
        } else {
            thumbStr = "thumb/" + w + "s" + h + "/orig";
        }
        return url.replace("image", thumbStr);
    }
}
