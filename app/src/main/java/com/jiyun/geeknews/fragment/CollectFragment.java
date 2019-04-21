package com.jiyun.geeknews.fragment;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.presenter.AboutPresenter;
import com.jiyun.geeknews.presenter.CollectPresenter;
import com.jiyun.geeknews.presenter.GoldPresenter;
import com.jiyun.geeknews.view.CollectView;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class CollectFragment extends BaseFragment<CollectView,CollectPresenter> implements CollectView{
    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
