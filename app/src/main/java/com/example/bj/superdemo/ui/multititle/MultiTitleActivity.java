package com.example.bj.superdemo.ui.multititle;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

public class MultiTitleActivity extends BaseActivity {
    private MultiTitleLayout mtl_list;
    private String[] datas = {"西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人", "这是一个长的测试哈哈哈哈哈哈哈哈", "真尼玛闲",
            "西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人",  "真尼玛闲",
            "西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人", "这是一个长的测试哈哈哈哈哈哈哈哈", "真尼玛闲",
            "西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人", "这是一个长的测试哈哈哈哈哈哈哈哈", "真尼玛闲",
            "西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人", "这是一个长的测试哈哈哈哈哈哈哈哈", "真尼玛闲",
            "西游记", "红楼梦", "三国演义", "水浒传", "追风筝的人", "这是一个长的测试哈哈哈哈哈哈哈哈", "真尼玛闲"};
//    private String[] datas = {"西游记", "红楼梦", "三国演义"};

    @Override
    public void initData() {
        setContentView(R.layout.activity_multi_title);
        mtl_list = (MultiTitleLayout) findViewById(R.id.mtl_list);
        for (int i = 0; i < datas.length; i++) {
            final MultiTextView text = new MultiTextView(context);
            text.setPadding(20,20,20,20);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams lpmargin = new ViewGroup.MarginLayoutParams(lp);
            lpmargin.leftMargin = 20;
            lpmargin.rightMargin = 20;
            lpmargin.topMargin = 20;
            lpmargin.bottomMargin = 20;
            text.setLayoutParams(lpmargin);
            text.setText(datas[i]);
            text.setGravity(Gravity.CENTER);
            text.setBackgroundResource(R.color.lightgoldenrodyellow);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, text.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            mtl_list.addView(text);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
