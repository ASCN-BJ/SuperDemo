package com.example.bj.superdemo.ui.customview.eventbus;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * EventBus的使用b
 */
public class EventBusDemoB extends BaseActivity {

    private TextView tv_text_all_add;
    private List<String> list;
    private Handler mHandler;

    @Override
    public void initData() {
        setContentView(R.layout.activity_event_bus_demo_b);
        tv_text_all_add = (TextView) findViewById(R.id.tv_text_all_add);
        EventBus.getDefault().register(this);
//        mHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//            }
//        };
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void setText(MessageEvent event) {
        if (list != null) {
            list = new ArrayList<>();
        }
        tv_text_all_add.setText(event.getDataString());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
//                for (int x = 0; x < 100; x++) {
//                    list.add(x, event.getDataString());
//                }
//                tv_text_all_add.setText(list.toString());
            }
        });
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                for (int x = 0; x < 100; x++) {
//                    list.add(x, event.getDataString());
//                }
//                tv_text_all_add.setText(list.toString());
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

    }
}
