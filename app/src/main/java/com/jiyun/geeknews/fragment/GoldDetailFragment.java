package com.jiyun.geeknews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.base.Constants;
import com.jiyun.geeknews.presenter.EmptyPresenter;
import com.jiyun.geeknews.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $sl on 2019/4/18 16:57.
 */
public class GoldDetailFragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.txt)
    TextView txt;

    public static GoldDetailFragment getDetailFragment(String text) {
        GoldDetailFragment goldDetailFragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        txt.setText(data);
    }
}
