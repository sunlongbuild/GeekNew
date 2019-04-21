package com.jiyun.geeknews.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MySectionsAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.SectionsBean;
import com.jiyun.geeknews.presenter.AboutPresenter;
import com.jiyun.geeknews.presenter.SectionsP;
import com.jiyun.geeknews.view.AboutView;
import com.jiyun.geeknews.view.SectionsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class SessionsFragment extends BaseFragment<SectionsV, SectionsP> implements SectionsV {
    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<SectionsBean.DataBean> list;
    private MySectionsAdapter adapter;

    @Override
    protected SectionsP initPresenter() {
        return new SectionsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sessions;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new MySectionsAdapter(getContext(), list);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    protected void initData() {
        presenter.getData();
    }

    @Override
    public void setData(SectionsBean sectionsBean) {
        list.addAll(sectionsBean.getData());
        adapter.notifyDataSetChanged();
    }
}
