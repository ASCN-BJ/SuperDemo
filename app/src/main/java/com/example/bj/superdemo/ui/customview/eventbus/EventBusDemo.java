package com.example.bj.superdemo.ui.customview.eventbus;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EventBus的使用
 */
public class EventBusDemo extends BaseActivity {

    private TextView tv_show_data;
    private Button btn_show_data_a;
    private Button btn_show_data_delay;
    private Handler mHandler;
    private Timer mTimer;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        setContentView(R.layout.activity_event_bus_demo);
        tv_show_data = (TextView) findViewById(R.id.tv_show_data);
        btn_show_data_a = (Button) findViewById(R.id.btn_show_data_a);
        btn_show_data_a.setOnClickListener(this);
        btn_show_data_delay = (Button) findViewById(R.id.btn_show_data_delay);
        btn_show_data_delay.setOnClickListener(this);
        this.findViewById(R.id.btn_show_data_sticky).setOnClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 10:
                        tv_show_data.setText(msg.obj.toString());
                        break;
                    default:
                        break;
                }

            }
        };
    }

    /**
     * 该方法必须是public
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(MessageEvent event) {
        System.out.println(Thread.currentThread().getName() + "setData()");
        tv_show_data.setText(event.getDataString().toString());
    }

    private List<String> mLists;

    /**
     * 该方法必须是public
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void setData1(final MessageEvent event) {
        System.out.println(Thread.currentThread().getName() + "setData1");
//        mLists = new Vector<>();
//        for (int x = 0; x < 1000; x++) {
//            mLists.add(x, event.getDataString());
//        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "__runOnUiThread");
                tv_show_data.setText(event.getDataString());
            }
        });
//        Message msg = Message.obtain();
//        msg.what = 10;
//        msg.obj = mLists;
//        mHandler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_data_a:
                MessageEvent event = new MessageEvent();
                event.setDataString("我怕，恶魔");
                EventBus.getDefault().post(event);
                break;
            case R.id.btn_show_data_sticky:
//                ExecutorService service = Executors.newCachedThreadPool();
//                service.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("这是一个线程1");
//                    }
//                });
//
//                service.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("这是一个线程2");
//                    }
//                });

                MessageEvent eventDelay = new MessageEvent();
                eventDelay.setDataString("我好怕怕");
                EventBus.getDefault().postSticky(eventDelay);
                System.out.println();
                break;
            case R.id.btn_show_data_delay:
//                System.out.println(Thread.currentThread().getName() + "线程名字");
//                startActivity(new Intent(this, EventBusDemoB.class));
                final MessageEvent eventDelay1 = new MessageEvent();
                eventDelay1.setDataString("我是一个文本");
                mTimer = new Timer();
                mTimer.schedule(new TimerTask() {
                    int x = 0;

                    @Override
                    public void run() {
                        x++;
                        eventDelay1.setDataString("文本" + x);
//                        System.out.println(Thread.currentThread().getName() + "线程名字");
                        EventBus.getDefault().post(eventDelay1);
                    }
                }, 200, 1000);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mTimer != null) {
            mTimer.cancel();
        }
        super.onDestroy();
    }
}
