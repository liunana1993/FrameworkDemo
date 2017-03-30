package com.mico.framework.baselib.animation;

import android.animation.Animator;
import android.view.View;

/**
 * Base animation for item loading.
 *
 */
public interface BaseAnimation {

    Animator[] getAnimators(View view);

}
