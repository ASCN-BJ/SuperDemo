package com.example.bj.superdemo.ui.ui;


import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJava3Activity extends BaseActivity {
    private RecyclerView rcl_show_picture;
    private List<Bitmap> mFileList = new ArrayList<>();
    private MyRecyclerAdapter myAdapter;
    private Button btn_display_pictures;
    private Integer[] imageIds = new Integer[]{R.drawable.x0, R.drawable.x1, R.drawable.x2, R.drawable.x3, R.drawable.x4, R.drawable.x5, R.drawable.x6, R.drawable.x7, R.drawable.x8, R.drawable.x9, R.drawable.x10, R.drawable.x11, R.drawable.x12, R.drawable.x14, R.drawable.x15, R.drawable.x16, R.drawable.x17, R.drawable.x18, R.drawable.x0, R.drawable.x1, R.drawable.x2, R.drawable.x3, R.drawable.x4, R.drawable.x5, R.drawable.x6, R.drawable.x7, R.drawable.x8, R.drawable.x9, R.drawable.x10, R.drawable.x11, R.drawable.x12, R.drawable.x14, R.drawable.x15, R.drawable.x16, R.drawable.x17, R.drawable.x18};
    private List<Integer> mImgList = new ArrayList<>();
    private Subscriber<Bitmap> subscriber;

    @Override
    public void initData() {
//        LauncherActivity
        setContentView(R.layout.activity_rx_java3);
        btn_display_pictures = (Button) findViewById(R.id.btn_display_pictures);
        btn_display_pictures.setOnClickListener(this);
        rcl_show_picture = (RecyclerView) findViewById(R.id.rcl_show_picture);
        myAdapter = new MyRecyclerAdapter(context, mFileList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);//需要设置manager否则 No layout manager attached; skipping layout
        rcl_show_picture.setLayoutManager(manager);
        rcl_show_picture.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_display_pictures:
                subscriber = new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mFileList.add(bitmap);
                    }
                };

                Observable.from(imageIds).flatMap(new Func1<Integer, Observable<Bitmap>>() {
                    @Override
                    public Observable<Bitmap> call(Integer integer) {//内存溢出
                        return Observable.from(new Bitmap[]{BitmapFactory.decodeResource(context.getResources(), integer)});//读写文件操作
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
                break;
            default:
                break;
        }
    }

    /*适配器*/
    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Context mContext;
        private List<Bitmap> mPictureList;

        public MyRecyclerAdapter(Context mContext, List<Bitmap> mPictureList) {
            this.mContext = mContext;
            this.mPictureList = mPictureList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_base_recycler, parent, false);
            return new MyViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ImageView iv = holder.iv_header;
            iv.setImageBitmap(mPictureList.get(position));
        }

        @Override
        public int getItemCount() {
            if (!mPictureList.isEmpty()) {
                return mPictureList.size();
            } else {
                return 0;
            }
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_header;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView != null) {
                iv_header = (ImageView) itemView.findViewById(R.id.iv_header);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
