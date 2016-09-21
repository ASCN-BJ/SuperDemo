package com.example.bj.superdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.bean.MessageEvent;
import com.example.bj.superdemo.ui.customview.BigMapActivity;
import com.example.bj.superdemo.ui.customview.ViewPagerActivity;
import com.example.bj.superdemo.ui.customview.eventbus.EventBusDemo;
import com.example.bj.superdemo.ui.customview.subject.CustomViewDrawShape;
import com.example.bj.superdemo.ui.customview.view_ui.CustomViewActivityRect;
import com.example.bj.superdemo.ui.customview.view_ui.DragLeftDeleteListActivity;
import com.example.bj.superdemo.ui.customview.view_ui.GestureLockActivity;
import com.example.bj.superdemo.ui.customview.view_ui.ShapeDemoActivity;
import com.example.bj.superdemo.ui.customview.view_ui.VeticalLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main3Activity extends BaseActivity {
    private TextView tv_my_view_pager;
    private TextView iv_viewp;
    private TextView tv_event_bus_demo;
    private TextView tv_delete_list;

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
        EventBus.getDefault().register(this);
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
            default:
                break;
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
