package com.jiyun.geeknews.model;

import com.jiyun.geeknews.api.DailyNewsServer;
import com.jiyun.geeknews.api.HotApiServer;
import com.jiyun.geeknews.base.BaseMainModel;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.jiyun.geeknews.bean.HotBean;
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
 * Created by $sl on 2019/4/17 20:51.
 */
public class HotM extends BaseMainModel{
    public void getData(final CallBack<HotBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HotApiServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HotApiServer server = retrofit.create(HotApiServer.class);
        Observable<HotBean> observable = server.getList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        callBack.onSuccess(hotBean);
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
