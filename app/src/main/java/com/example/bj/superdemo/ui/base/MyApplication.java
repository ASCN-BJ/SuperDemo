package com.example.bj.superdemo.ui.base;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by bj on 2016/10/17.
 * description：
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MyApplication.this));
        super.onCreate();

    }
}
