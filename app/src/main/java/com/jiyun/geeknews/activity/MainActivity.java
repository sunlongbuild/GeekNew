package com.jiyun.geeknews.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseActivity;
import com.jiyun.geeknews.fragment.AboutFragment;
import com.jiyun.geeknews.fragment.CollectFragment;
import com.jiyun.geeknews.fragment.GankFragment;
import com.jiyun.geeknews.fragment.GoldFragment;
import com.jiyun.geeknews.fragment.SettingFragment;
import com.jiyun.geeknews.fragment.VtexFragment;
import com.jiyun.geeknews.fragment.WechatFragment;
import com.jiyun.geeknews.fragment.ZhiHuDailyNewsFragment;
import com.jiyun.geeknews.presenter.MyPresenter;
import com.jiyun.geeknews.view.MyView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MyView, MyPresenter> implements MyView, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.drawer_nv)
    NavigationView drawer_nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> titles;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTING = 6;
    private final int TYPE_ABOUT = 7;

    //上一次显示的fragmnet的索引
    private int LastFragmentPosition = 0;
    private MenuItem searchItem;

    @Override
    protected MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar();
        initFragments();
        initTitles();
        initZhiHuDailyNewsFragment();
    }


    private void initTitles() {
        titles = new ArrayList<>();
        titles.add(R.id.zhihu);
        titles.add(R.id.wechat);
        titles.add(R.id.gank);
        titles.add(R.id.gold);
        titles.add(R.id.vtex);
        titles.add(R.id.collect);
        titles.add(R.id.setting);
        titles.add(R.id.about);
    }

    //新进入应用默认显示第一个
    private void initZhiHuDailyNewsFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl, fragments.get(0));
        transaction.commit();

        toolbar.setTitle(titles.get(0));

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ZhiHuDailyNewsFragment());
        fragments.add(new WechatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new VtexFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new AboutFragment());
    }

    private void initToolBar() {
        manager = getSupportFragmentManager();
        toolbar.setTitleTextColor(getResources().getColor(R.color.c_white));
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.app_name, R.string.app_name);
        //设置侧滑开关的颜色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.c_white));
        dl.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void initListener() {
        drawer_nv.setNavigationItemSelectedListener(this);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //查询文本提交时
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //查询文本改变时
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展示时

            }

            @Override
            public void onSearchViewClosed() {
                //搜索框隐藏时
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        toolbar.setTitle(menuItem.getTitle());
        //如果不等于资讯和选项，就可以点击
        if (itemId != R.id.itme1 && itemId != R.id.itme2) {
            menuItem.setChecked(true);
            switch (itemId) {
                case R.id.zhihu:
                    switchFragments(TYPE_ZHIHU);
                    break;
                case R.id.wechat:
                    switchFragments(TYPE_WECHAT);
                    break;
                case R.id.gank:
                    switchFragments(TYPE_GANK);
                    break;
                case R.id.gold:
                    switchFragments(TYPE_GOLD);
                    break;
                case R.id.vtex:
                    switchFragments(TYPE_V2EX);
                    break;
                case R.id.collect:
                    switchFragments(TYPE_COLLECT);
                    break;
                case R.id.setting:
                    switchFragments(TYPE_SETTING);
                    break;
                case R.id.about:
                    switchFragments(TYPE_ABOUT);
                    break;
            }
            dl.closeDrawer(Gravity.LEFT);
        } else {
            menuItem.setChecked(false);
        }

        return false;
    }

    private void switchFragments(int type) {
        //本质显示一个framgnet,隐藏一个
        //要显示的fragment
        Fragment fragment = fragments.get(type);
        //要隐藏的碎片
        Fragment hideFragment = fragments.get(LastFragmentPosition);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl, fragment);
        }
        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        LastFragmentPosition = type;

        if (type == TYPE_WECHAT || type == TYPE_GANK) {
            searchItem.setVisible(true);
        } else {
            searchItem.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);
        searchView.setMenuItem(searchItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
