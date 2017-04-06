package com.mico.framework.mvpdagger.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mico.framework.baselib.util.AndroidUtils;
import com.mico.framework.baselib.util.ImageUtils;

/**
 * Created by LiuNana on 2017/3/24.
 */

public class ImageLoader {
    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).error(error).into(view);
    }
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
    @BindingAdapter({"thumbImageUrl"})
    public static void loadThumbImage(ImageView view, String url) {
        url = ImageUtils.getThumbImageUrl(url, AndroidUtils.dip2px(view.getContext(), 100),
            AndroidUtils.dip2px(view.getContext(),75), false);
        Glide.with(view.getContext()).load(url).into(view);
    }


}
