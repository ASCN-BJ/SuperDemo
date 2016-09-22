package com.example.bj.superdemo.ui.customview.view_ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.customview.subject.gesturelock.GestureLockViewGroup;

public class GestureLockActivity extends Activity {
    private GestureLockViewGroup rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_lock);
        rl = (GestureLockViewGroup) findViewById(R.id.rl);

    }

}
