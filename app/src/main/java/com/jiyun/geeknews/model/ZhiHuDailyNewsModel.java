package com.jiyun.geeknews.model;

import com.jiyun.geeknews.api.DailyNewsServer;
import com.jiyun.geeknews.base.BaseMainModel;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.jiyun.geeknews.callback.CallBack;
import com.jiyun.geeknews.utils.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by $sl on 2019/4/17 15:47.
 */
public class ZhiHuDailyNewsModel extends BaseMainModel {
    public void getData(final CallBack callBack){
        DailyNewsServer server = HttpUtils.getInstance().getApiserver(DailyNewsServer.url, DailyNewsServer.class);
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
}
