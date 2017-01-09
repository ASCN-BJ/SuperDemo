package com.example.bj.superdemo.ui.net.retrofittest;

import com.example.bj.superdemo.ui.bean.DouBanBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bj on 2017-1-9.
 * Description：
 */

public interface MyService {
    /**
     * Query 代表GET请求 用于接口后面拼接参数
     *
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return
     */
    @GET("book/search")
    Call<DouBanBean> getSearchCallBack(@Query("q") String name,
                                       @Query("tag") String tag, @Query("start") int start,
                                       @Query("count") int count);
}
