package com.example.bj.superdemo.ui.customview.gallery_picture;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.customview.common.DividerItemDecoration;
import com.example.bj.superdemo.ui.customview.common.MyRecycleItemDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */

public class WaterFallFragment2 extends Fragment {
    private RecyclerView rv_water_fall;
    private List<Integer> mLists;
    private int drawableId;
    private WaterFallFragment2.WaterFallAdatper mAdapter;
    private MyRecycleItemDecoder myRecycleItemDecoder;
    private LinearLayout ll_show_loading;
    private Handler mHandler;
    public static Fragment newInstance(int page) {
        Fragment fragment = new WaterFallFragment();
        return fragment;
    }

    private Runnable LOAD_DATA = new Runnable() {
        @Override
        public void run() {
            //在这里数据内容加载到Fragment上
            ll_show_loading.setVisibility(View.GONE);
            showList();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mHandler=new Handler();
        return inflater.inflate(R.layout.fragment_water_fall2, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDrawableResouces(view);
        rv_water_fall = (RecyclerView) view.findViewById(R.id.rv_water_fall);
        ll_show_loading= (LinearLayout) view.findViewById(R.id.ll_show_loading);
        ll_show_loading.setVisibility(View.VISIBLE);
        mHandler.postDelayed(LOAD_DATA,3000);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser)
        mHandler.removeCallbacks(LOAD_DATA);
    }

    private void showList() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv_water_fall.setLayoutManager(layoutManager);

        rv_water_fall.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rv_water_fall.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

//        rv_water_fall.addItemDecoration(new MyRecycleItemDecoder(this.getActivity()));
        mAdapter = new WaterFallFragment2.WaterFallAdatper(this.getActivity(), mLists);
//        rv_water_fall.setItemAnimator(new DefaultItemAnimator());
//        rv_water_fall.addItemDecoration(new RecyclerView.ItemDecoration() {});
        rv_water_fall.setAdapter(mAdapter);
    }

    private void setDrawableResouces(View view) {
        mLists = new ArrayList<>();
        for (int y = 0; y < 68; y++) {
            drawableId = this.getResources().getIdentifier("x" + y, "drawable", "com.example.bj.superdemo");
            mLists.add(drawableId);
        }
        rv_water_fall = (RecyclerView) view.findViewById(R.id.rv_water_fall);
    }

    class WaterFallAdatper extends RecyclerView.Adapter<WaterFallFragment2.MyViewHolder> {
        private List<Integer> mHeightSizes;
        private Context mContext;
        private List<Integer> mList;

        public WaterFallAdatper(Context context, List<Integer> list) {
            this.mContext = context;
            this.mList = list;
            mHeightSizes = new ArrayList<>();
            for (int x = 0; x < list.size(); x++) {
                mHeightSizes.add((int) ((Math.random() + 0.5) * 400));
            }
        }

        @Override
        public WaterFallFragment2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return WaterFallFragment2.MyViewHolder.newInstance(mContext, parent, mHeightSizes);
        }

        @Override
        public void onBindViewHolder(WaterFallFragment2.MyViewHolder holder, int position) {
//            holder.image.
//            holder.image.setImageResource(mList.get(position));
//            MyImageLoader.showImage("drawable://" + mList.get(position), holder.image);
            Glide.with(mContext).load(mList.get(position)).into(holder.image);
//            GlideImageLoader.i
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_image_icon);
        }

        private ImageView image;

        public static WaterFallFragment2.MyViewHolder newInstance(Context context, ViewGroup parent, List<Integer> mLists) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = (int) ((Math.random() + 0.5) * 400);
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
//            view.setPadding(5, 5, 5, 5);
            view.setPadding(0, 0, 0, 0);
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.WHITE);
            return (new WaterFallFragment2.MyViewHolder(view));
        }
    }
}
