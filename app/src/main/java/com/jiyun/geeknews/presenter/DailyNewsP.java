package com.jiyun.geeknews.presenter;

import android.util.Log;

import com.jiyun.geeknews.base.BaseMainPresenter;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.jiyun.geeknews.callback.CallBack;
import com.jiyun.geeknews.model.DailyNewsM;
import com.jiyun.geeknews.view.DailyNewsV;

/**
 * Created by $sl on 2019/4/17 17:06.
 */
public class DailyNewsP extends BaseMainPresenter<DailyNewsV> implements CallBack<DailyNewsBean> {

    private static final String TAG = "DailyNewsP----";

    private DailyNewsM dailyNewsM;

    @Override
    protected void initModel() {
        dailyNewsM = new DailyNewsM();
    }

    public void getData(){
        dailyNewsM.getData(this);
    }

    public void getOldData(String data){
        dailyNewsM.getOldData(new CallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                baseMainView.setData(dailyNewsBean);
            }

            @Override
            public void onFailed(String v) {
                Log.e(TAG, "onFailed: " + v);
            }
        }, data);
    }
    @Override
    public void onSuccess(DailyNewsBean dailyNewsBean) {
        baseMainView.setData(dailyNewsBean);
    }

    @Override
    public void onFailed(String v) {

    }
}
