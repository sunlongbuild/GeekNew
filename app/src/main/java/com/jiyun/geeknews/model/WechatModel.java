package com.jiyun.geeknews.model;

import com.jiyun.geeknews.api.WechatApiServer;
import com.jiyun.geeknews.base.BaseMainModel;
import com.jiyun.geeknews.bean.WechatBean;
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
 * Created by $sl on 2019/4/19 11:25.
 */
public class WechatModel extends BaseMainModel {
    public void initModel(final CallBack<WechatBean> callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WechatApiServer.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WechatApiServer server = retrofit.create(WechatApiServer.class);
        Observable<WechatBean> observable = server.getList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WechatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatBean wechatBean) {
                        callBack.onSuccess(wechatBean);
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
