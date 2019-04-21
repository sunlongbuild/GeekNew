package com.jiyun.geeknews.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MyHotAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.HotBean;
import com.jiyun.geeknews.presenter.AboutPresenter;
import com.jiyun.geeknews.presenter.HotP;
import com.jiyun.geeknews.view.AboutView;
import com.jiyun.geeknews.view.HotV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class HotFragment extends BaseFragment<HotV, HotP> implements HotV {
    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<HotBean.RecentBean> list;
    private MyHotAdapter adapter;

    @Override
    protected HotP initPresenter() {
        return new HotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new MyHotAdapter(getContext(), list);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        presenter.getData();
    }

    @Override
    public void setData(HotBean hotBean) {
        list.addAll(hotBean.getRecent());
        adapter.notifyDataSetChanged();
    }
}
