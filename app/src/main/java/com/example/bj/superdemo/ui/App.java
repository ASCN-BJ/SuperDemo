package com.example.bj.superdemo.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by bj on 2017/1/24.
 */

public class App extends Application {
    private static Application app;
    private static Context mContext;

    /**
     * 获取一个application
     *
     * @return
     */
    public static Application getApplictaion() {
        synchronized (App.class) {
            if (app == null) {
                app = new App();
            }
            return app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取总程序的上下文
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }
}
