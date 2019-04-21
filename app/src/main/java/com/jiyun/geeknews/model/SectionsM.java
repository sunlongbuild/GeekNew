package com.jiyun.geeknews.model;

import com.jiyun.geeknews.api.DailyNewsServer;
import com.jiyun.geeknews.api.SectionsApiServer;
import com.jiyun.geeknews.base.BaseMainModel;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.jiyun.geeknews.bean.SectionsBean;
import com.jiyun.geeknews.callback.CallBack;
import com.jiyun.geeknews.utils.BaseObserver;
import com.jiyun.geeknews.utils.HttpUtils;
import com.jiyun.geeknews.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by $sl on 2019/4/17 17:06.
 */
public class SectionsM extends BaseMainModel {
    public void getData(final CallBack<SectionsBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SectionsApiServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SectionsApiServer server = retrofit.create(SectionsApiServer.class);
        Observable<SectionsBean> observable = server.getList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SectionsBean sectionsBean) {
                        callBack.onSuccess(sectionsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
