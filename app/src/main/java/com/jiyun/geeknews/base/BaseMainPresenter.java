package com.jiyun.geeknews.base;

/**
 * Created by $sl on 2019/4/3 11:00.
 */
public abstract class BaseMainPresenter<V extends BaseMainView> {
    protected V baseMainView;

    public BaseMainPresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(V view) {
        this.baseMainView = view;
    }
}
