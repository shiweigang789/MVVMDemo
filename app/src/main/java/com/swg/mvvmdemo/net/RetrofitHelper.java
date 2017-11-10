package com.swg.mvvmdemo.net;

import com.swg.mvvmdemo.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取RetrofitService实例
 * Created by swg on 2017/11/10.
 */

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private RetrofitService retrofitService;

    public static RetrofitHelper getInstance() {
        return retrofitHelper == null ? retrofitHelper = new RetrofitHelper() : retrofitHelper;
    }

    private RetrofitHelper() {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

}
