//package com.example.bj.superdemo.ui;
//
//import android.content.Intent;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.bj.superdemo.R;
//import com.example.bj.superdemo.ui.bean.MessageEvent;
//import com.example.bj.superdemo.ui.customview.ViewPagerActivity;
//
//import org.greenrobot.eventbus.EventBus;
//
///**
// * 一个订阅者模式的demo
// *
// */
//public class MainActivity extends BaseActivity {
//    private View mMeanuView;
//    private TextView tv_username, tv_id;
//    private ImageView user_image;
//    private Button btn_demo;
//    private Button btn_demoa;
//
//    @Override
//    public void initData() {
//        setContentView(R.layout.activity_main);
//        mMeanuView = View.inflate(context, R.layout.header_just_username, null);
//        tv_username = (TextView) mMeanuView.findViewById(R.id.tv_username);
//        tv_id = (TextView) mMeanuView.findViewById(R.id.tv_id);
//        user_image = (ImageView) mMeanuView.findViewById(R.id.user_image);
//        btn_demo= (Button) findViewById(R.id.btn_demo);
//        btn_demo.setOnClickListener(this);
//        btn_demoa= (Button) findViewById(R.id.btn_demoa);
//        btn_demoa.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_demo:
//                startActivity(new Intent(this,Main2Activity.class));
//                EventBus.getDefault().post(new MessageEvent(12580));
//                break;
//            case R.id.btn_demoa:
//                startActivity(new Intent(this,Main3Activity.class));
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }
//}
