package com.jiyun.geeknews.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.activity.CalendarActivity;
import com.jiyun.geeknews.adapter.MyDailyNewsPagerAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.base.Constants;
import com.jiyun.geeknews.presenter.ZhiHuDailyNewsPresenter;
import com.jiyun.geeknews.utils.CircularAnimUtil;
import com.jiyun.geeknews.utils.DateUtil;
import com.jiyun.geeknews.view.ZhiHuDailyNewsView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.jiyun.geeknews.utils.CircularAnimUtil.PERFECT_MILLS;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class ZhiHuDailyNewsFragment extends BaseFragment<ZhiHuDailyNewsView, ZhiHuDailyNewsPresenter> implements ZhiHuDailyNewsView {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.floatButton)
    FloatingActionButton floatButton;
    private ArrayList<BaseFragment> list;
    private long durationMills = PERFECT_MILLS;
    private DailyNewsFragment dailyNewsFragment;

    @Override
    protected ZhiHuDailyNewsPresenter initPresenter() {
        return new ZhiHuDailyNewsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_news;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        dailyNewsFragment = new DailyNewsFragment();
        list.add(dailyNewsFragment);
        list.add(new SessionsFragment());
        list.add(new HotFragment());

        tab.addTab(tab.newTab().setText("日报"));
        tab.addTab(tab.newTab().setText("专栏"));
        tab.addTab(tab.newTab().setText("热门"));


        MyDailyNewsPagerAdapter adapter = new MyDailyNewsPagerAdapter(getChildFragmentManager(), list);
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
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(getContext(), CalendarActivity.class);
                startActivityForResult(it, 100);
            }
        });
    }

    private static final String TAG = "DailyNewsFragment";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 20) {
            String s = data.getStringExtra("date");
            dailyNewsFragment.setSt(s);
        }
    }


}
