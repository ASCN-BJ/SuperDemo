package com.example.bj.superdemo.ui.customview.gallery_picture;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.utils.viewutil.MyImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bj on 2016/10/8.
 * description：
 */
public class GalleryFragment extends Fragment {
    private GalleryRecycleView rv_gallery;
    private List<Integer> mDatas;
    private MyGalleryAdapter myGalleryAdapter;
    private ImageView iv_gallery_main_img;
    private LinearLayout fl_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatas();
        iv_gallery_main_img = (ImageView) view.findViewById(R.id.iv_gallery_main_img);
        rv_gallery = (GalleryRecycleView) view.findViewById(R.id.rv_gallery);
        fl_container = (LinearLayout) view.findViewById(R.id.fl_container);
        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_gallery.setLayoutManager(manager);
//        iv_gallery_main_img.setImageResource((int)mDatas.get(0));
        rv_gallery.setOnRecycleChangeListner(new GalleryRecycleView.OnRecycleChangeListner() {
            @Override
            public void change(View view, int i) {
                iv_gallery_main_img.setImageResource((int) mDatas.get(i));
//                iv_gallery_main_img.postInvalidate();
            }
        });
        rv_gallery.setAdapter(myGalleryAdapter = new MyGalleryAdapter(GalleryFragment.this.getActivity(), mDatas));
        myGalleryAdapter.setOnItemClickLitener(new MyGalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GalleryFragment.this.getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                MyImageLoader.showImage("drawable://" + mDatas.get(position), iv_gallery_main_img);
            }
        });

    }

    private void initDatas() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.bg,
                R.drawable.bizhi, R.drawable.bizhia, R.drawable.dvq, R.drawable.fengjing1,
                R.drawable.fenjing2, R.drawable.forest, R.drawable.libra, R.drawable.bizhi));
    }

    static class MyGalleryAdapter extends RecyclerView.Adapter<MyGalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;

        public interface OnItemClickLitener {
            void onItemClick(View view, int position);
        }

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        public MyGalleryAdapter(Context context, List<Integer> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;

        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
//                LayoutInf later inflater = LayoutInflater.from(context);
//                View v = inflater.inflate(R.layout.item_gallery, (ViewGroup) arg0, true);
            }

            ImageView iv_image_icon;
            TextView tv_icon_name;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = mInflater.inflate(R.layout.item_gallery, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            viewHolder.iv_image_icon = (ImageView) v.findViewById(R.id.iv_image_icon);
            viewHolder.tv_icon_name = (TextView) v.findViewById(R.id.iv_tv_icon_name);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
//            holder.iv_image_icon.setImageResource(mDatas.get(position));
            MyImageLoader.showImage("drawable://" + mDatas.get(position), holder.iv_image_icon);
            holder.tv_icon_name.setText("图片");
            //如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(holder.iv_image_icon, position);
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }
}
