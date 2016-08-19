package com.example.bj.superdemo.ui;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bj.superdemo.R;

public class MainActivity extends BaseActivity {
    private View mMeanuView;
    private TextView tv_username, tv_id;
    private ImageView user_image;

    @Override
    public void initData() {
        setContentView(R.layout.activity_main);
        mMeanuView = View.inflate(context, R.layout.header_just_username, null);
        tv_username = (TextView) mMeanuView.findViewById(R.id.tv_username);
        tv_id = (TextView) mMeanuView.findViewById(R.id.tv_id);
        user_image = (ImageView) mMeanuView.findViewById(R.id.user_image);

    }

    @Override
    public void onClick(View view) {

    }
}
