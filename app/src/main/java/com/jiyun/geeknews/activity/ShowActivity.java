package com.jiyun.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MyGoldShowAdapter;
import com.jiyun.geeknews.base.BaseActivity;
import com.jiyun.geeknews.base.Constants;
import com.jiyun.geeknews.bean.GoldTitlesBean;
import com.jiyun.geeknews.presenter.EmptyPresenter;
import com.jiyun.geeknews.view.EmptyView;
import com.jiyun.geeknews.weidget.SmipleItemTouchCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by $sl on 2019/4/18 18:29.
 */
public class ShowActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {
    @BindView(R.id.lv)
    RecyclerView lv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    private ArrayList<GoldTitlesBean> titles;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        toolBar.setTitle("特殊展示");
        toolBar.setNavigationIcon(R.mipmap.btn_back_normal);
        setSupportActionBar(toolBar);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        //获取到Title
        Intent intent = getIntent();
        titles = (ArrayList<GoldTitlesBean>) intent.getSerializableExtra(Constants.DATA);
        MyGoldShowAdapter adapter = new MyGoldShowAdapter(this, titles);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //左滑拖拽和上下拖动
        SmipleItemTouchCallBack smipleItemTouchCallBack = new SmipleItemTouchCallBack(adapter);
        smipleItemTouchCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(smipleItemTouchCallBack);
        helper.attachToRecyclerView(lv);
    }

    private void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, titles);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }
}
