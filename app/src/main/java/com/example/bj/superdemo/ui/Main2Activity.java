package com.example.bj.superdemo.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
public class Main2Activity extends Activity implements View.OnClickListener {
    private ImageView iv_beautiful_girl;
    public EventBus eventBus;
    public MessageEvent messageEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv_beautiful_girl= (ImageView) findViewById(R.id.iv_beautiful_girl);
        iv_beautiful_girl.setOnClickListener(this);
        eventBus=EventBus.getDefault();
        messageEvent=new MessageEvent();
        messageEvent.setDataInt(12580);
        eventBus.register(this);
        EventBus.getDefault().post(messageEvent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv_beautiful_girl){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public MessageEvent getMessageEvent(MessageEvent messageEvent){
        Toast.makeText(this,String.valueOf(messageEvent.getDataInt()),Toast.LENGTH_SHORT).show();
        return messageEvent;
    }
}
