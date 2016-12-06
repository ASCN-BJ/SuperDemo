package com.example.bj.superdemo.ui.ui.web_view_demo;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

public class WebViewDownload extends BaseActivity implements Ipresenter {

    private UcWebView wv_main;
    @Override
    public void initData() {

        setContentView(R.layout.activity_web_view_download);
        PresenterImpl mPresenter = new PresenterImpl(this);
        mPresenter.showInformation();

        findId();
    }

    private void findId() {
        wv_main = (UcWebView) findViewById(R.id.wv_main);
    }

    private void setWebSetting(WebView webView) {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            //webView的加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });
    }

    private class MyWebViewClient extends WebViewClient {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void setData(WebInformation information) {

    }

    @Override
    public void closeDialog() {

    }
}
