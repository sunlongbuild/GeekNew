package com.jiyun.geeknews.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MyGoldPagerAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.VtexBean;
import com.jiyun.geeknews.presenter.VtexPresenter;
import com.jiyun.geeknews.view.VtexView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by $sl on 2019/4/3 16:13.
 */
public class VtexFragment extends BaseFragment<VtexView, VtexPresenter> implements VtexView {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    @SuppressLint("HandlerLeak")
    private ArrayList<BaseFragment> list;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                List<VtexBean.TabBean> tabBeans = (List<VtexBean.TabBean>) msg.obj;
                for (int i = 0; i < tabBeans.size(); i++) {
                    tab.addTab(tab.newTab().setText(tabBeans.get(i).getTab()));
                    list.add(new VtexItemFragment(tabBeans.get(i).getLink()));
                }
                MyGoldPagerAdapter adapter = new MyGoldPagerAdapter(getChildFragmentManager(), list);
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

        }
    };
    private String url = "https://www.v2ex.com/";
    private static final String TAG = "VtexFragment----";



    @Override
    protected VtexPresenter initPresenter() {
        return new VtexPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vtex;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();

    }

    @Override
    protected void initData() {
        new Thread() {


            @Override
            public void run() {
                super.run();
                try {
                    //获取Vtex的网页结构
                    Document doc = Jsoup.connect(url).get();
                    //获取tabs的div标签结构
                    Element tabs = doc.select("div#Tabs").first();
                    //通过tab获取到里面的tab元素（a标签）
                    Elements allTabs = tabs.select("a[href]");

                    List<VtexBean.TabBean> tabList = new ArrayList<>();

                    for (Element element : allTabs) {
                        //获取href属性
                        String linkHref = element.attr("href");
                        //获取标签里面的文本
                        String linkText = element.text();
                        Log.d(TAG, "linkHref: " + linkHref + ",tab:" + linkText);
                        tabList.add(new VtexBean.TabBean(linkHref, linkText));
                    }


                    Log.e(TAG, "run: " + tabList);

                    Message message = Message.obtain();
                    message.obj = tabList;
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
