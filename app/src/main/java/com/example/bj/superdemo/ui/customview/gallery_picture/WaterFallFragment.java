package com.example.bj.superdemo.ui.customview.gallery_picture;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.customview.common.DividerItemDecoration;
import com.example.bj.superdemo.ui.customview.common.MyRecycleItemDecoder;
import com.example.bj.superdemo.ui.utils.viewutil.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bj on 2016/10/8.
 * description：瀑布流
 */
public class WaterFallFragment extends Fragment {

    private RecyclerView rv_water_fall;
    private List<Integer> mLists;
    private int drawableId;
    private WaterFallAdatper mAdapter;
    private MyRecycleItemDecoder myRecycleItemDecoder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_water_fall, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDrawableResouces(view);
        rv_water_fall = (RecyclerView) view.findViewById(R.id.rv_water_fall);
//        myRecycleItemDecoder = new MyRecycleItemDecoder(this.getActivity());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rv_water_fall.setLayoutManager(layoutManager);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 5);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rv_water_fall.setLayoutManager(layoutManager);

//        rv_water_fall.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        rv_water_fall.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        rv_water_fall.addItemDecoration(new MyRecycleItemDecoder(this.getActivity()));
        mAdapter = new WaterFallAdatper(this.getActivity(), mLists);
        rv_water_fall.setItemAnimator(new DefaultItemAnimator());
        rv_water_fall.addItemDecoration(new RecyclerView.ItemDecoration() {
        });
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

    class WaterFallAdatper extends RecyclerView.Adapter<MyViewHolder> {
        private Context mContext;
        private List<Integer> mList;

        public WaterFallAdatper(Context context, List<Integer> list) {
            this.mContext = context;
            this.mList = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return MyViewHolder.newInstance(mContext, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
//            holder.image.
//            holder.image.setImageResource(mList.get(position));
            MyImageLoader.showImage("drawable://" + mList.get(position), holder.image);
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

        public static MyViewHolder newInstance(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = 650;
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.parseColor("#3300ff66"));
            return (new MyViewHolder(view));
        }
    }
}
