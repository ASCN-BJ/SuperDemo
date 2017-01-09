package com.example.bj.superdemo.ui.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.bean.DouBanBean;
import com.example.bj.superdemo.ui.net.retrofittest.MyService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * retrofit小demo
 *
 * @author bj
 */
public class RetrofitActivity extends BaseActivity {
    private Button btn_get;
    private RecyclerView rcl_show_content;
    private TextView tv_set_retrofit_content;
    private Retrofit ret;
    private ImageView iv_show_ret_picture;

    @Override
    public void initData() {
        setContentView(R.layout.activity_retrofit);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_get.setOnClickListener(this);
        tv_set_retrofit_content = (TextView) findViewById(R.id.tv_set_retrofit_content);
        ret = new Retrofit.Builder().baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iv_show_ret_picture = (ImageView) findViewById(R.id.iv_show_ret_picture);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                tv_set_retrofit_content.setText("");
                MyService myservice = ret.create(MyService.class);
                Call<DouBanBean> call = myservice.getSearchCallBack("小王子", "", 0, 3);
                call.enqueue(new Callback<DouBanBean>() {
                    @Override
                    public void onResponse(Call<DouBanBean> call, Response<DouBanBean> response) {
//                        tv_set_retrofit_content.setText(response.body().getBooks().get(0).toString());
                        Glide.with(context).load(response.body().getBooks().get(0).getImage()).into(iv_show_ret_picture);
                    }

                    @Override
                    public void onFailure(Call<DouBanBean> call, Throwable t) {
                        System.out.println("");
                    }
                });
                break;
            case R.id.iv_show_ret_picture:

                break;
            default:
                break;
        }

    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
