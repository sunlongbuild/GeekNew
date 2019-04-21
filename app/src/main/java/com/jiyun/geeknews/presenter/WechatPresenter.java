package com.jiyun.geeknews.presenter;

import com.jiyun.geeknews.api.WechatApiServer;
import com.jiyun.geeknews.base.BaseMainPresenter;
import com.jiyun.geeknews.bean.WechatBean;
import com.jiyun.geeknews.callback.CallBack;
import com.jiyun.geeknews.model.WechatModel;
import com.jiyun.geeknews.utils.RxUtils;
import com.jiyun.geeknews.view.WechatView;
import com.jiyun.geeknews.view.ZhiHuDailyNewsView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by $sl on 2019/4/3 16:14.
 */
public class WechatPresenter extends BaseMainPresenter<WechatView> implements CallBack<WechatBean> {

    private WechatModel wechatModel;

    @Override
    protected void initModel() {
        wechatModel = new WechatModel();
    }
    public void getData(){
        wechatModel.initModel(this);
    }


    @Override
    public void onSuccess(WechatBean wechatBean) {
        baseMainView.onSuccess(wechatBean);
    }

    @Override
    public void onFailed(String v) {
        baseMainView.onFailed(v);
    }
}
