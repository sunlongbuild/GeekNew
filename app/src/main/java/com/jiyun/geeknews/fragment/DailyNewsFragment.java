package com.jiyun.geeknews.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MyDailyNewAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.DailyNewsBean;
import com.jiyun.geeknews.presenter.DailyNewsP;
import com.jiyun.geeknews.view.DailyNewsV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class DailyNewsFragment extends BaseFragment<DailyNewsV, DailyNewsP> implements DailyNewsV {
    private String data;

    public void setSt(String data) {
        this.data = data;
        Log.e(TAG, "setSt: " + data);
        initData();
    }

    private static final String TAG = "DailyNewsFragment----";
    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans;
    private ArrayList<DailyNewsBean.StoriesBean> storiesBeans;
    private MyDailyNewAdapter adapter;

    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {

        topStoriesBeans = new ArrayList<>();
        storiesBeans = new ArrayList<>();
        adapter = new MyDailyNewAdapter(getActivity(), topStoriesBeans, storiesBeans);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        if (data != null) {
            //在p层里在写一个方法用来调用往期日报
            presenter.getOldData(data);
            Log.e(TAG, "initData: " + data);
        } else {
            presenter.getData(); //如果你data没值的话走这个 就是说你没点击日历
        }

    }

    @Override
    public void setData(DailyNewsBean data) {
        topStoriesBeans.clear();
        storiesBeans.clear();
        List<DailyNewsBean.TopStoriesBean> top_stories = data.getTop_stories();
        if (top_stories!=null){
            topStoriesBeans.addAll(data.getTop_stories());
        }
        storiesBeans.addAll(data.getStories());
        adapter.notifyDataSetChanged();
        adapter.setData(data);
    }


}
