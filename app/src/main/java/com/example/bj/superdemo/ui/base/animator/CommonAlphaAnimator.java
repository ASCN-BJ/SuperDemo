package com.example.bj.superdemo.ui.base.animator;

import android.view.animation.AlphaAnimation;

/**
 * Created by BJ on 2016/11/21.
 */

public class CommonAlphaAnimator {
    private static CommonAlphaAnimator animator;
//    private CommonAlphaAnimator animator;

    CommonAlphaAnimator() {
    }

    public static CommonAlphaAnimator getInstance() {
        if (animator == null) {
            animator = new CommonAlphaAnimator();
        }
        return animator;
    }

    public AlphaAnimation showVisible() {
        AlphaAnimation animation = new AlphaAnimation(0, 1.0f);
        animation.setDuration(500);
        return animation;
    }

    public AlphaAnimation showInvisible() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(800);
        return animation;
    }

}
