package com.example.bj.superdemo.ui.customview.eventbus;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBus的使用
 */
public class EventBusDemo extends BaseActivity {

    private TextView tv_show_data;
    private Button btn_show_data_a;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        setContentView(R.layout.activity_event_bus_demo);
        tv_show_data = (TextView) findViewById(R.id.tv_show_data);
        btn_show_data_a = (Button) findViewById(R.id.btn_show_data_a);
        btn_show_data_a.setOnClickListener(this);
    }

    /**
     * 该方法必须是public
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(MessageEvent event) {
        tv_show_data.setText(event.getDataString().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_data_a:
                MessageEvent event = new MessageEvent();
                event.setDataString("我怕，恶魔");
                EventBus.getDefault().post(event);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
