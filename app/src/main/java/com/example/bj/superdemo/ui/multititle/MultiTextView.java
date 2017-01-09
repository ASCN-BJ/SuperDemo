package com.example.bj.superdemo.ui.multititle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bj on 2017-1-4.
 * Description：
 */

public class MultiTextView extends TextView {
    private Context mContext;

    public MultiTextView(Context context) {
        this(context, null);
    }

    public MultiTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {//响应点击事件
//        if (event.getAction() != MotionEvent.ACTION_DOWN) {
//            if (mContext != null) {
//                Toast.makeText(mContext, this.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//            }
//        }
//        return true;
//    }
}


