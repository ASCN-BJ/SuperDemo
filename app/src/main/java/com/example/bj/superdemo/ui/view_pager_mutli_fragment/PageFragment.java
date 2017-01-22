package com.example.bj.superdemo.ui.view_pager_mutli_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bj.superdemo.R;

/**
 * Created by Administrator on 2017/1/22.
 */

public class PageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);
        TextView textView = (TextView) view.findViewById(R.id.tv_content);
        textView.setText("第"+mPage+"页");
        return view;
    }
}
