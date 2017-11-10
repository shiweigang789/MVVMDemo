package com.swg.mvvmdemo.manager;

import com.swg.mvvmdemo.bean.ExpressInfo;
import com.swg.mvvmdemo.net.RetrofitHelper;
import com.swg.mvvmdemo.net.RetrofitService;

import io.reactivex.Observable;

/**
 * 数据处理
 * Created by swg on 2017/11/10.
 */

public class DataManager {

    private static DataManager dataManager;
    private RetrofitService retrofitService;

    public static DataManager getInstance() {
        return dataManager == null ? dataManager = new DataManager() : dataManager;
    }

    private DataManager() {
        retrofitService = RetrofitHelper.getInstance().getRetrofitService();
    }

    /**
     * 获取快递信息
     *
     * @param type   快递类型
     * @param postid 快递单号
     * @return Observable<ExpressInfo>
     */
    public Observable<ExpressInfo> getExpressInfo(String type, String postid) {
        return retrofitService.getExpressInfoRx(type, postid);
    }

}
