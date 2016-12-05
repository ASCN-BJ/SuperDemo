package com.example.bj.superdemo.ui;


import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.utils.viewutil.math.OperationUtil;

public class SplashActivity extends BaseActivity {
    private EditText user, pwd;
    private Button login;

    @Override
    public void initData() {
        setContentView(R.layout.activity_splash);
        user = (EditText) findViewById(R.id.user);
        pwd = (EditText) findViewById(R.id.user);
        login = (Button) findViewById(R.id.login);
        user.setOnClickListener(this);
        pwd.setOnClickListener(this);
        login.setOnClickListener(this);
        System.out.println("resutl--》"+OperationUtil.mutilAdd(3.1,4.1,9.0,30.1));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {
//            if (user.getText().toString().trim().equals("123")&&pwd.getText().toString().trim().equals("123")){
//                startActivity(new Intent(this,MainActivity.class));
//            }
            startActivity(new Intent(this, Main3Activity.class));
            WindowManager wm = this.getWindowManager();
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            Toast.makeText(this, width + "--" + height, Toast.LENGTH_SHORT).show();
        }
    }
}
