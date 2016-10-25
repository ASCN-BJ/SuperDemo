package com.example.bj.superdemo.ui.utils.viewutil;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by bj on 2016/10/25.
 * description：
 */
public class GlideImageLoader {
    /**
     * @param imageView
     * @param url
     */
    public void showImage(ImageView imageView, String url, Activity activity) {
        Glide.with(activity).load(url).into(imageView);
    }

    /**
     * @param imageView
     * @param url
     */
    public void showImage(ImageView imageView, String url, Fragment fragment) {

    }

    /**
     * @param imageView
     * @param url
     */
    public void showImage(ImageView imageView, String url, Context context) {

    }
}
