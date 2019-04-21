package com.jiyun.geeknews.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.activity.ShowActivity;
import com.jiyun.geeknews.adapter.MyGoldPagerAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.base.Constants;
import com.jiyun.geeknews.bean.GoldTitlesBean;
import com.jiyun.geeknews.presenter.GoldPresenter;
import com.jiyun.geeknews.view.GoldView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.TlsVersion;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class GoldFragment extends BaseFragment<GoldView, GoldPresenter> implements GoldView {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.img)
    ImageView img;

    private ArrayList<GoldTitlesBean> titles;
    private ArrayList<BaseFragment> fragments;
    private static final String TAG = "GoldFragment----";

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitles();//设置Tab的标题

        setFragments();
    }

    private void setFragments() {
        fragments = new ArrayList<>();
        tab.removeAllTabs();
        for (int i = 0; i < titles.size(); i++) {
            GoldTitlesBean goldTitlesBean = titles.get(i);
            if (goldTitlesBean.isChecked) {//如果标题被选中就给Fragment设置内容
                fragments.add(GoldDetailFragment.getDetailFragment(goldTitlesBean.title));
                tab.addTab(tab.newTab().setText(goldTitlesBean.title));
            }
        }
        MyGoldPagerAdapter adapter = new MyGoldPagerAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(adapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add(new GoldTitlesBean("Android", true));
        titles.add(new GoldTitlesBean("iOS", true));
        titles.add(new GoldTitlesBean("设计", true));
        titles.add(new GoldTitlesBean("工具资源", true));
        titles.add(new GoldTitlesBean("产品", true));
        titles.add(new GoldTitlesBean("阅读", true));
        titles.add(new GoldTitlesBean("前端", true));
        titles.add(new GoldTitlesBean("后端", true));
    }


    @OnClick(R.id.img)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,titles);
        startActivityForResult(intent,100);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            titles = (ArrayList<GoldTitlesBean>) data.getSerializableExtra(Constants.DATA);
            Log.e(TAG, "onActivityResult: " + titles.get(0));
            setFragments();
        }
    }
}
