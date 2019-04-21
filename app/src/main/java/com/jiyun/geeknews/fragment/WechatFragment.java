package com.jiyun.geeknews.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MyWechatAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.WechatBean;
import com.jiyun.geeknews.presenter.WechatPresenter;
import com.jiyun.geeknews.view.WechatView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class WechatFragment extends BaseFragment<WechatView, WechatPresenter> implements WechatView {
    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<WechatBean.NewslistBean> list;
    private MyWechatAdapter adapter;
    private static final String TAG = "WechatFragment----";

    @Override
    protected WechatPresenter initPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new MyWechatAdapter(getContext(), list);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        presenter.getData();
    }

    @Override
    public void onSuccess(WechatBean wechatBean) {
        list.addAll(wechatBean.getNewslist());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String string) {
        Log.e(TAG, "onFailed: " + string);
    }
}
