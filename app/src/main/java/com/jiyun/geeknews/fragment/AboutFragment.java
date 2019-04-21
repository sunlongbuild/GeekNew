package com.jiyun.geeknews.fragment;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.presenter.AboutPresenter;
import com.jiyun.geeknews.presenter.GoldPresenter;
import com.jiyun.geeknews.view.AboutView;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class AboutFragment extends BaseFragment<AboutView, AboutPresenter> implements AboutView{
    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
