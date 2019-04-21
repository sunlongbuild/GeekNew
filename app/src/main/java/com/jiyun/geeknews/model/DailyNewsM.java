package com.jiyun.geeknews.model;

import com.jiyun.geeknews.api.DailyNewsServer;
import com.jiyun.geeknews.base.BaseMainModel;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.jiyun.geeknews.callback.CallBack;

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
public class DailyNewsM extends BaseMainModel {
    public void getData(final CallBack<DailyNewsBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyNewsServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DailyNewsServer server = retrofit.create(DailyNewsServer.class);
        Observable<DailyNewsBean> observable = server.getDailyList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
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

    public void getOldData(final CallBack<DailyNewsBean> callBack,String date){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyNewsServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DailyNewsServer server = retrofit.create(DailyNewsServer.class);
        Observable<DailyNewsBean> observable = server.getOldList(date);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
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
