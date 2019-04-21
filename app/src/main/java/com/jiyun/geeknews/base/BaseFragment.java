package com.jiyun.geeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.geeknews.presenter.GoldPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $sl on 2019/4/3 13:05.
 */
public abstract class BaseFragment<V extends BaseMainView,P extends BaseMainPresenter>
        extends Fragment implements BaseMainView{

    private Unbinder bind;
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,false);
        bind = ButterKnife.bind(this, view);
        presenter = initPresenter();
        if(presenter!=null){
            presenter.bind((V)this);
        }
        initView();
        initListener();
        initData();
        return view;
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    protected void initView() {
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        presenter = null;
    }
}
