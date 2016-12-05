package com.example.bj.superdemo.ui.ui.web_view_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by bj on 2016-12-5.
 * Description：
 */

public class UcWebView extends WebView {
    public UcWebView(Context context) {
        this(context, null);
    }

    public UcWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UcWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}


