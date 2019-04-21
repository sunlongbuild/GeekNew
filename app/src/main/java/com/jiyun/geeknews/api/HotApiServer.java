package com.jiyun.geeknews.api;

import com.jiyun.geeknews.bean.HotBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by $sl on 2019/4/17 20:49.
 */
public interface HotApiServer {
    String url = "http://news-at.zhihu.com/api/4/";

    @GET("news/hot")
    Observable<HotBean> getList();
}
