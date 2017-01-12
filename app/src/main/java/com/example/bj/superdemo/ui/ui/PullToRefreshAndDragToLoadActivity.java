package com.example.bj.superdemo.ui.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJ on 2016/11/15.
 */

public class PullToRefreshAndDragToLoadActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView rl_all;
    private MyAdapter myAdapter;
    private List<Integer> mAllDrawables;
    private List<String> mAllUrls;
    private SwipeRefreshLayout srl_refresh;
    private List<Integer> mAllDrawablesAll;
    private final int normalType = 1;
    private final int footerType = 2;

    @Override
    public void initData() {
        setContentView(R.layout.activity_pull_to_refresh_and_drag_to_load);
        mAllDrawablesAll = new ArrayList<>();
        setDrawables();
        LinearLayoutManager layoutManager = new LinearLayoutManager(PullToRefreshAndDragToLoadActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAdapter = new MyAdapter(PullToRefreshAndDragToLoadActivity.this, mAllDrawables, true);
        rl_all = (RecyclerView) findViewById(R.id.rl_all);
        rl_all.setAdapter(myAdapter);
        rl_all.setLayoutManager(layoutManager);
        srl_refresh = (SwipeRefreshLayout) findViewById(R.id.srl_refresh);
        srl_refresh.setColorSchemeColors(context.getResources().getColor(R.color.bg_dark_green));
        srl_refresh.setOnRefreshListener(this);
    }

    private void setDrawables() {
        mAllDrawables = new ArrayList<>();
        int drawableId;
        for (int y = 0; y < 68; y++) {
            drawableId = this.getResources().getIdentifier("x" + y, "drawable", "com.example.bj.superdemo");
            mAllDrawables.add(drawableId);
        }
    }

    private void setUrls() {
        mAllUrls = new ArrayList<>();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                srl_refresh.setRefreshing(false);//刷新结束
            }
        }, 3000);
    }

    private boolean canLoadMore() {
//        RecyclerView.canScrollVertically(1)  的值表示是否能向上滚动，false表示已经滚动到底部
//        RecyclerView.canScrollVertically(-1) 的值表示是否能向下滚动，false表示已经滚动到顶部
        if (rl_all.canScrollVertically(1)) {
            return true;
        }
        return false;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolde> {
        private Context mContext;
        private List<String> mList;
        private List<Integer> mDrawableLists;
        private boolean isDrawable = false;

        public MyAdapter(Context mContext, List<String> mList) {
            this.mContext = mContext;
            this.mList = mList;
        }

        public MyAdapter(Context mContext, List<Integer> mDrawableLists, boolean isDrawable) {
            this.mContext = mContext;
            this.mDrawableLists = mDrawableLists;
            this.isDrawable = true;
//            System.out.println(mDrawableLists.size() + "mDrawableLists");
        }

        @Override
        public ViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == footerType) {//判断是否是滑到底部
                return new ViewHolde(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_footer, parent, false), viewType);
            }
            ViewHolde viewHolde = new ViewHolde(LayoutInflater.from(mContext).inflate(R.layout.item_base_recycler, parent, false), viewType);
            return viewHolde;
        }

        @Override
        public void onBindViewHolder(ViewHolde holder, int position) {
            if (getItemViewType(position) == normalType) {//normal
                holder.tv_content.setText("图描述测试");
                if (isDrawable) {
                    Glide.with(mContext).load(mDrawableLists.get(position)).thumbnail(0.1f).crossFade().into(holder.iv_header);
                } else {
                    Glide.with(mContext).load(mList.get(position)).thumbnail(0.5f).into(holder.iv_header);
                }
            } else {
//                Log.e("onBindViewHolder", "onBindViewHolder: " + canLoadMore());
//                if (canLoadMore()) {
//                    holder.tv_load_more.setText("加载更多...");
//                } else {
//                    holder.pb_load_more.setVisibility(View.VISIBLE);
//                    holder.tv_load_more.setText("正在加载...");
//                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position < getItemCount() - 1) {
                //normal
                return normalType;
            } else {
                return footerType;
            }
        }

        @Override
        public int getItemCount() {
            if (isDrawable) {
                return mDrawableLists.size() + 1;
            } else {
                return mList.size() + 1;
            }
        }

        class ViewHolde extends RecyclerView.ViewHolder {
            private ImageView iv_header;
            private TextView tv_content;
            private ProgressBar pb_load_more;
            private TextView tv_load_more;

            public ViewHolde(View itemView, int ViewType) {
                super(itemView);
                if (ViewType == footerType) {
                    pb_load_more = (ProgressBar) itemView.findViewById(R.id.pb_load_more);
                    tv_load_more = (TextView) itemView.findViewById(R.id.tv_load_more);
                } else {
                    tv_content = (TextView) itemView.findViewById(R.id.tv_content);
                    iv_header = (ImageView) itemView.findViewById(R.id.iv_header);
                }
            }
        }
    }
}
