package com.jiyun.geeknews.presenter;

import com.jiyun.geeknews.base.BaseMainPresenter;
import com.jiyun.geeknews.bean.HotBean;
import com.jiyun.geeknews.callback.CallBack;
import com.jiyun.geeknews.model.HotM;
import com.jiyun.geeknews.view.HotV;

/**
 * Created by $sl on 2019/4/17 20:53.
 */
public class HotP extends BaseMainPresenter<HotV> implements CallBack<HotBean> {

    private HotM hotM;

    @Override
    protected void initModel() {
        hotM = new HotM();
    }
    public void getData(){
        hotM.getData(this);
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        baseMainView.setData(hotBean);
    }

    @Override
    public void onFailed(String v) {

    }
}
