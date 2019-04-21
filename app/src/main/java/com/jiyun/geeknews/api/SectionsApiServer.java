package com.jiyun.geeknews.api;

import com.jiyun.geeknews.bean.SectionsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by $sl on 2019/4/17 20:26.
 */
public interface SectionsApiServer {
    String url = "http://news-at.zhihu.com/api/4/";

    @GET("sections")
    Observable<SectionsBean> getList();
}
