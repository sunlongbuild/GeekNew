package com.jiyun.geeknews.api;

import com.jiyun.geeknews.bean.WechatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by $sl on 2019/4/19 11:26.
 */
public interface WechatApiServer {
    String url = "http://api.tianapi.com/wxnew/";

    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1")
    Observable<WechatBean> getList();
}
