package com.jiyun.geeknews.fragment;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.presenter.AboutPresenter;
import com.jiyun.geeknews.presenter.GankPresenter;
import com.jiyun.geeknews.presenter.GoldPresenter;
import com.jiyun.geeknews.view.GankView;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class GankFragment extends BaseFragment<GankView,GankPresenter> implements GankView{
    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}
