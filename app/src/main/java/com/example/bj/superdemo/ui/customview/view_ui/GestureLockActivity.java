package com.example.bj.superdemo.ui.customview.view_ui;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.customview.subject.gesturelock.GestureLockViewGroup;

import java.util.ArrayList;
import java.util.List;

public class GestureLockActivity extends Activity {
    private GestureLockViewGroup rl;
    private List<Integer> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_lock);
        rl = (GestureLockViewGroup) findViewById(R.id.rl);
        mLists = new ArrayList<>();
        //设置解锁手势
        mLists.add(1);
        mLists.add(2);
        mLists.add(3);
        mLists.add(4);
        mLists.add(5);
        mLists.add(6);
        mLists.add(7);
        mLists.add(8);
        mLists.add(9);
        rl.setGestureLock(mLists);
        rl.setTimer(2);
        rl.setJudgeListner(new GestureLockViewGroup.JudgeGestureListner() {
            @Override
            public void getGestureJudgeResult(boolean result) {
                if (result) {
                    Toast.makeText(getBaseContext(), "解锁成功。。。", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "解锁失败，请重试。。。", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
