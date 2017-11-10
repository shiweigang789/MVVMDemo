package com.swg.mvvmdemo.net;

import com.swg.mvvmdemo.bean.ExpressInfo;
import com.swg.mvvmdemo.constant.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求参数接口
 * Created by swg on 2017/11/10.
 */

public interface RetrofitService {

    /**
     * 获取快递信息
     *
     * @param type   快递类型
     * @param postid 快递单号
     * @return
     */
    @GET(Constant.UrlOrigin.get_express_info)
    Observable<ExpressInfo> getExpressInfoRx(@Query("type") String type, @Query("postid") String postid);

}
