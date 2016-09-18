package com.example.bj.superdemo.ui.customview.view_ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.customview.subject.DragLeftDeleteListViewA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DragLeftDeleteListActivity extends BaseActivity {

    private DragLeftDeleteListViewA ddlv_list;
    private List<String> mList;
    private ArrayAdapter mAdapter;

    @Override
    public void initData() {
        setContentView(R.layout.activity_drag_left_delete_list);
        ddlv_list = (DragLeftDeleteListViewA) findViewById(R.id.ddlv_list);
        mList = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        ddlv_list.setAdapter(mAdapter);
        ddlv_list.setDelButtonClickListener(new DragLeftDeleteListViewA.DelButtonClickListener() {
            @Override
            public void clickHappend(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
