package com.example.bj.superdemo.ui.ui;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava的使用
 */
public class RxJava2Activity extends BaseActivity implements Observer<String> {
    private TextView tv_rx_content2;
    private Button btn_show_rx_picture;

    @Override
    public void initData() {
        setContentView(R.layout.activity_rx_java2);
        tv_rx_content2 = (TextView) findViewById(R.id.tv_rx_content2);
        btn_show_rx_picture = (Button) findViewById(R.id.btn_show_rx_picture);
        btn_show_rx_picture.setOnClickListener(this);
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
//                SystemClock.sleep(2000);
//                subscriber.onNext("World");
//                SystemClock.sleep(2000);
                subscriber.onCompleted();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.newThread()).subscribe(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_rx_picture:
                startActivity(new Intent(this, RxJava3Activity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(String s) {
        tv_rx_content2.setText(s);
    }

//    @Override
//    protected void onDestroy() {
//        this.
//    }
}
