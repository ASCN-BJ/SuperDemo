package com.example.bj.superdemo.ui.ui.web_view_demo;

/**
 * Created by bj on 2016-12-7.
 * Description：
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.bj.superdemo.R;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class BppFragment extends AppCompatActivity implements ReWebChomeClient.OpenFileChooserCallBack {

    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    private static final String TAG = "BppFragment";
    private static final int REQUEST_CODE_PICK_IMAGE = 0;
    private static final int REQUEST_CODE_IMAGE_CAPTURE = 1;
    private WebView wView;
    private Intent mSourceIntent;
    private ValueCallback<Uri> mUploadMsg;
    private RelativeLayout bppLoginRl;
    private Button login;
    private String httpUrl;
    private SwipeRefreshLayout swipeLayout;

    @Nullable
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_bpp, container, false);
        setContentView(R.layout.fragment_bpp);
//        login = (Button) view.findViewById(R.id.bpp_buttonLogin);
        wView = (WebView) this.findViewById(R.id.bpp_webView);
        wView.getSettings().setJavaScriptEnabled(true);//支持js
        wView.getSettings().setAllowFileAccess(true);
        wView.setWebChromeClient(new ReWebChomeClient(this));
        wView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        fixDirPath();
        wView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                wView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        wView.getOriginalUrl();
        wView.getUrl();
//        httpUrl = Const.LOGIN_SERVER_URL.replace("https://", "http://") + "/tools/team/EnterTeamPage?openid=" + openID;
        httpUrl = "http://baidu.com";
        wView.loadUrl(httpUrl);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            mUploadMsg.onReceiveValue(null);//不加这段代码的话，第一次若取消第二次会报错
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_IMAGE_CAPTURE:
            case REQUEST_CODE_PICK_IMAGE: {
                try {
                    if (mUploadMsg == null) {
                        return;
                    }
                    String sourcePath = ImageUtil.retrievePath(this, mSourceIntent, data);
                    if (TextUtils.isEmpty(sourcePath) || !new File(sourcePath).exists()) {
                        break;
                    }
                    Uri uri = Uri.fromFile(new File(sourcePath));
                    mUploadMsg.onReceiveValue(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMsg = uploadMsg;
        showOptions();
    }

    public void showOptions() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setOnCancelListener(new ReOnCancelListener());
        alertDialog.setTitle("图片");
        alertDialog.setItems(new String[]{"photo", "ps"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mSourceIntent = ImageUtil.choosePicture();
                            startActivityForResult(mSourceIntent, REQUEST_CODE_PICK_IMAGE);
                        } else {
                            mSourceIntent = ImageUtil.takeBigPicture();
                            startActivityForResult(mSourceIntent, REQUEST_CODE_IMAGE_CAPTURE);
                        }
                    }
                }
        );
        alertDialog.show();
    }

    private void fixDirPath() {
        String path = ImageUtil.getDirPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

//    @Override
//    public void onRefresh() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                wView.loadUrl(wView.getUrl());
//                swipeLayout.setRefreshing(false);
//            }
//        }, 3000);
//    }


    private class ReOnCancelListener implements DialogInterface.OnCancelListener {

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMsg != null) {
                mUploadMsg.onReceiveValue(null);
                mUploadMsg = null;
            }
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        /**
//         * 游客模式
//         * */
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String login_pass = sharedPreferences.getString("Service_Password", null);
//
//        if (login_pass == null) {
//            bppLoginRl.setVisibility(View.VISIBLE);
//        } else {
//            bppLoginRl.setVisibility(View.GONE);
//        }
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//        swipeLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                if (wView.getScrollY() == 0) {
//                    swipeLayout.setEnabled(true);
//                } else {
//                    swipeLayout.setEnabled(false);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        swipeLayout.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
//    }
}


