package com.example.bj.superdemo.ui.customview.view_ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.customview.subject.CusomViewDragHelper;
import com.example.bj.superdemo.ui.customview.subject.CustomViewDrawCircle;

public class CustomViewActivityRect extends BaseActivity {
    private CustomViewDrawCircle cdc_view;
    private CusomViewDragHelper cdh_view;
    private TextView tv_the_first;

    @Override
    public void initData() {
        setContentView(R.layout.activity_custom_view_activity_rect);
        cdc_view = (CustomViewDrawCircle) findViewById(R.id.cdc_view);
        cdh_view = (CusomViewDragHelper) findViewById(R.id.cdh_view);
        findViewById(R.id.tv_the_first).setOnClickListener(this);
//        System.out.println(cdc_view.getMeasuredHeight() + "initDataCustomViewActivityRect_onMeasuremeasureHeight");
//        System.out.println(cdc_view.getHeight() + "initDataCustomViewActivityRect_Height");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        System.out.println(cdc_view.getMeasuredHeight() + "ResumeDataCustomViewActivityRect_onMeasuremeasureHeight");
//        System.out.println(cdc_view.getHeight() + "ResumeDataCustomViewActivityRect_Height");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_the_first) {
            Toast.makeText(context, "这是一个TextView", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cdc_view.setDrop(false);
    }
}
