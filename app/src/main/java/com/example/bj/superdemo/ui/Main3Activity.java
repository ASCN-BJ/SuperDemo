package com.example.bj.superdemo.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.bean.MessageEvent;
import com.example.bj.superdemo.ui.camera.MyCameraActivity;
import com.example.bj.superdemo.ui.customview.BigMapActivity;
import com.example.bj.superdemo.ui.customview.ViewPagerActivity;
import com.example.bj.superdemo.ui.customview.eventbus.EventBusDemo;
import com.example.bj.superdemo.ui.customview.gallery_picture.Main4Activity;
import com.example.bj.superdemo.ui.customview.view_ui.CustomViewActivityRect;
import com.example.bj.superdemo.ui.customview.view_ui.DragLeftDeleteListActivity;
import com.example.bj.superdemo.ui.customview.view_ui.GestureLockActivity;
import com.example.bj.superdemo.ui.customview.view_ui.ShapeDemoActivity;
import com.example.bj.superdemo.ui.customview.view_ui.VeticalLayoutActivity;
import com.example.bj.superdemo.ui.multititle.MultiTitleActivity;
import com.example.bj.superdemo.ui.mvpdemo_a.views.views.ShowUserData;
import com.example.bj.superdemo.ui.mvpdemo_b.MvpDemo_b;
import com.example.bj.superdemo.ui.ui.MyDialogActivity;
import com.example.bj.superdemo.ui.ui.PictureCompressionActivity;
import com.example.bj.superdemo.ui.ui.RetrofitActivity;
import com.example.bj.superdemo.ui.ui.RxJavaActivity;
import com.example.bj.superdemo.ui.ui.web_view_demo.BppFragment;
import com.example.bj.superdemo.ui.ui.web_view_demo.Native2Js;
import com.example.bj.superdemo.ui.ui.PullToRefreshAndDragToLoadActivity;
import com.example.bj.superdemo.ui.ui.web_view_demo.SmartWebViewActivity;
import com.example.bj.superdemo.ui.ui.web_view_demo.WebViewDemo;
import com.example.bj.superdemo.ui.ui.web_view_demo.WebViewDemo2;
import com.example.bj.superdemo.ui.ui.web_view_demo.WebViewDemo3Activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main3Activity extends BaseActivity {
    private TextView tv_my_view_pager;
    private TextView iv_viewp;
    private TextView tv_event_bus_demo;
    private TextView tv_delete_list;
    private TextView tv_gallery_picture;
    private TextView tv_pull_push;
    private TextView tv_mvp_demo;
    private TextView tv_mvp_demo_b;
    private TextView tv_web2js;
    private TextView tv_web_camera;
    private TextView tv_web_dialog;
    private TextView tv_picture_compression;
    private TextView tv_multi_table;
    private TextView tv_rx_java;
    private TextView tv_retrofit;

    public void initData() {
        setContentView(R.layout.activity_main3);
        tv_my_view_pager = (TextView) this.findViewById(R.id.tv_my_view_pager);
        // tv_my_view_pager.setOnClickListener(this);
        iv_viewp = (TextView) findViewById(R.id.iv_viewp);
        iv_viewp.setOnClickListener(this);
        tv_my_view_pager.setOnClickListener(this);
        findViewById(R.id.tv_custom_rect).setOnClickListener(this);
        tv_event_bus_demo = (TextView) findViewById(R.id.tv_event_bus_demo);
        tv_event_bus_demo.setOnClickListener(this);
        findViewById(R.id.tv_delete_list).setOnClickListener(this);
        findViewById(R.id.tv_vertical_layout).setOnClickListener(this);
        findViewById(R.id.tv_dmeo_shape).setOnClickListener(this);
        findViewById(R.id.tv_gesture_lock).setOnClickListener(this);
        tv_gallery_picture = (TextView) findViewById(R.id.tv_gallery_picture);
        tv_gallery_picture.setOnClickListener(this);
        tv_mvp_demo = (TextView) findViewById(R.id.tv_mvp_demo);
        tv_mvp_demo.setOnClickListener(this);
        tv_mvp_demo_b = (TextView) findViewById(R.id.tv_mvp_demo_b);
        tv_mvp_demo_b.setOnClickListener(this);
        findViewById(R.id.tv_pull_push).setOnClickListener(this);
        EventBus.getDefault().register(this);
        tv_web2js = (TextView) findViewById(R.id.tv_web2js);
        tv_web2js.setOnClickListener(this);
        tv_web_camera = (TextView) findViewById(R.id.tv_web_camera);
        tv_web_camera.setOnClickListener(this);
        tv_web_dialog = (TextView) findViewById(R.id.tv_web_dialog);
        tv_web_dialog.setOnClickListener(this);
        tv_picture_compression = (TextView) findViewById(R.id.tv_picture_compression);
        tv_picture_compression.setOnClickListener(this);
        tv_multi_table = (TextView) findViewById(R.id.tv_multi_table);
        tv_multi_table.setOnClickListener(this);
        tv_rx_java = (TextView) findViewById(R.id.tv_rx_java);
        tv_rx_java.setOnClickListener(this);
        tv_retrofit = (TextView) findViewById(R.id.tv_retrofit);
        tv_retrofit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_view_pager:
                startActivity(new Intent(this, BigMapActivity.class));
                break;
            case R.id.iv_viewp:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.tv_custom_rect:
                startActivity(new Intent(this, CustomViewActivityRect.class));
                break;
            case R.id.tv_event_bus_demo:
                startActivity(new Intent(this, EventBusDemo.class));
                break;
            case R.id.tv_delete_list:
                startActivity(new Intent(this, DragLeftDeleteListActivity.class));
                break;
            case R.id.tv_vertical_layout:
                startActivity(new Intent(this, VeticalLayoutActivity.class));
                break;
            case R.id.tv_dmeo_shape:
                startActivity(new Intent(this, ShapeDemoActivity.class));
                break;
            case R.id.tv_gesture_lock:
                startActivity(new Intent(this, GestureLockActivity.class));
                break;
            case R.id.tv_gallery_picture:
                startActivity(new Intent(this, Main4Activity.class));
                break;
            case R.id.tv_pull_push:
                startActivity(new Intent(this, PullToRefreshAndDragToLoadActivity.class));//// TODO: 2016/11/21  unfinshied
                break;
            case R.id.tv_mvp_demo:
                startActivity(new Intent(this, ShowUserData.class));
                break;
            case R.id.tv_mvp_demo_b:
                startActivity(new Intent(this, MvpDemo_b.class));
                break;
            case R.id.tv_web2js:
//                startActivity(new Intent(this, WebViewDemo3Activity.class));
//                startActivity(new Intent(this, SmartWebViewActivity.class));
                startActivity(new Intent(this, BppFragment.class));
//                startActivity(new Intent(this, WebViewDemo2.class));
                break;
            case R.id.tv_web_camera:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
                } else {
                    startActivity(new Intent(this, MyCameraActivity.class));
                }
                break;
            case R.id.tv_web_dialog:
                startActivity(new Intent(this, MyDialogActivity.class));
                break;
            case R.id.tv_picture_compression:
                startActivity(new Intent(this, PictureCompressionActivity.class));
                break;
            case R.id.tv_multi_table:
                startActivity(new Intent(this, MultiTitleActivity.class));
                break;
            case R.id.tv_rx_java:
                startActivity(new Intent(this, RxJavaActivity.class));
                break;
            case R.id.tv_retrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            startActivity(new Intent(this, MyCameraActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(MessageEvent event) {
        System.out.println(event.getDataString() + "ssssssssss");
        tv_event_bus_demo.setText(event.getDataString());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
