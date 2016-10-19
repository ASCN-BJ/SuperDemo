package com.example.bj.superdemo.ui.utils.viewutil;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by bj on 2016/10/17.
 * description：
 */
public class MyImageLoader {
    private static ImageLoader mImageLoader = ImageLoader.getInstance();

    /**
     * 显示图片
     *
     * @param url
     * @param imageView
     */
    public static void showImage(String url, ImageView imageView) {
        if (!url.isEmpty() && url != null && !url.equals(imageView.getTag())) {
            imageView.setTag(url);
            ImageLoader.getInstance().displayImage(url, imageView);
        }
    }
}
