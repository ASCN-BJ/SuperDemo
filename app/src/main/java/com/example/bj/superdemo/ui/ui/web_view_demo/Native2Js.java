package com.example.bj.superdemo.ui.ui.web_view_demo;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

public class Native2Js extends BaseActivity {
    private WebView wb_web;
    private LinearLayout ll_progress;
    private Handler mHandler;
    private TextView tv_progress;
    private Button btn_onlick;
    private final int LOAD_URLA = 10;
    private final int LOAD_URLB = 11;

    @Override
    public void initData() {
        setContentView(R.layout.activity_native2_js);
        wb_web = (WebView) findViewById(R.id.wv_web);
        initWebSetting(wb_web);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        btn_onlick = (Button) findViewById(R.id.btn_onlick);
        btn_onlick.setOnClickListener(this);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg != null) {
                    if (msg.what == LOAD_URLA) {
                        tv_progress.setText(msg.obj + "%");
                        if ((int) msg.obj == 100) {
                            ll_progress.setVisibility(View.GONE);
                        }
                    }

                    if (msg.what == LOAD_URLB) {
                        btn_onlick.setText((String) msg.obj);
                    }

                }
            }
        };
    }

    /*
    * 初始化web設置
    * */
    private void initWebSetting(final WebView wv) {
        //重寫webView的返回事件
        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
                    wv.goBack();
                    return true;
                }
                return false;
            }
        });

        //截圖功能
        wv.setOnTouchListener(new View.OnTouchListener() {
            int downX = 0;
            int downY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getX();
                        downY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        int upX = (int) event.getX();
                        int upY = (int) event.getY();
                        if (Math.abs(upX - downX) > Math.abs(upY - downY) && Math.abs(upX - downX) > 100) {
                            Toast.makeText(Native2Js.this, "截圖", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });


        WebSettings webSetting = wv.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDefaultTextEncodingName("utf-8");
//        wv.loadUrl("http://www.baidu.com");
        wv.loadUrl("file:///android_asset/test.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                mHandler.sendMessage(new Message());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (url != null) {
//                    Toast.makeText(Native2Js.this, url, Toast.LENGTH_SHORT).show();
//                }
//                return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
        });
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Message message = new Message();
                message.obj = newProgress;
                message.what = LOAD_URLA;
                mHandler.sendMessage(message);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Message msg = new Message();
                msg.what = LOAD_URLB;
                msg.obj = title;
                mHandler.sendMessage(msg);
            }
        });
//        wv.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_onlick) {
            wb_web.loadUrl("javascript:funFromjs()");
        }
    }
}
