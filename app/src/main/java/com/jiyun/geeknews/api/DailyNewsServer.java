package com.jiyun.geeknews.api;

import com.jiyun.geeknews.bean.DailyNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by $sl on 2019/4/17 16:15.
 */
public interface DailyNewsServer {
    String url = "http://news-at.zhihu.com/api/4/news/";

    @GET("latest")
    Observable<DailyNewsBean> getDailyList();


    @GET("before/{date}")
    Observable<DailyNewsBean> getOldList(@Path("date") String date);
}
