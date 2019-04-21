package com.jiyun.geeknews.fragment;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.presenter.GoldPresenter;
import com.jiyun.geeknews.presenter.SettingPresenter;
import com.jiyun.geeknews.view.SettingView;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class SettingFragment extends BaseFragment<SettingView,SettingPresenter> implements SettingView {
    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }
}
