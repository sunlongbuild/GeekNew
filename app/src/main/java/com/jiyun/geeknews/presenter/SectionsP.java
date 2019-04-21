package com.jiyun.geeknews.presenter;


import com.jiyun.geeknews.base.BaseMainPresenter;
import com.jiyun.geeknews.bean.SectionsBean;
import com.jiyun.geeknews.callback.CallBack;
import com.jiyun.geeknews.model.SectionsM;
import com.jiyun.geeknews.view.SectionsV;

/**
 * Created by $sl on 2019/4/17 17:06.
 */
public class SectionsP extends BaseMainPresenter<SectionsV> implements CallBack<SectionsBean> {

    private SectionsM sectionsM;

    public void getData(){
        if(sectionsM!=null){
            sectionsM.getData(this);
        }

    }
    @Override
    protected void initModel() {
        sectionsM = new SectionsM();
    }

    @Override
    public void onSuccess(SectionsBean sectionsBean) {
        baseMainView.setData(sectionsBean);
    }

    @Override
    public void onFailed(String v) {

    }
}
