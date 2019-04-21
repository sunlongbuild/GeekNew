package com.jiyun.geeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by $sl on 2019/4/3 11:00.
 */
public abstract class BaseActivity<V extends BaseMainView, P extends BaseMainPresenter> extends AppCompatActivity   {
    protected P presenter;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.bind((V) this);
        }
        initView();
        initListener();
        initData();
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    protected void initView() {

    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();
}
