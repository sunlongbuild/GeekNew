package com.jiyun.geeknews.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.adapter.MyVtexAdapter;
import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.VtexBean;
import com.jiyun.geeknews.presenter.EmptyPresenter;
import com.jiyun.geeknews.view.EmptyView;

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
public class VtexItemFragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {
    @BindView(R.id.lv)
    RecyclerView lv;
    private String link;

    private String url = "https://www.v2ex.com";
    private static final String TAG = "VtexItemFragment----";
    private ArrayList<VtexBean.ItemBean> list;
    private MyVtexAdapter adapter;

    @SuppressLint("ValidFragment")
    public VtexItemFragment(String link) {
        this.link = link;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vtex_item;
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();
        adapter = new MyVtexAdapter(getActivity(), list);
        lv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);

    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {

            private Element lastDicuss;
            private Element author;

            @Override
            public void run() {

                try {
                    final List<VtexBean.ItemBean> itemList = new ArrayList<>();
                    Document doc = Jsoup.connect(url + link).get();
                    //item数据
                    Elements items = doc.select("div.cell.item");
                    for (Element item : items) {
                        //图片
                        Element image = item.select("table tr td a > img.avatar").first();
                        String src = image.attr("src");

                        //评论数量和评论链接地址
                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null) {
                            String href = comment.attr("href");
                            String text = comment.text();
                            //Log.d(TAG, "评论: "+",链接:"+href+",数量:"+text);
                        }
                        //标题
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        String text = title.text();
                        Log.d(TAG, "标题: " + text);

                        //topic_info
                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secondaryTab = topic.select("a.node").first();
                        String secTab = secondaryTab.text();
                        Log.d(TAG, "secTab: " + secTab);

                        String topicText = topic.text();
                        Log.d(TAG, "topicText: " + topicText);


                        Elements people = topic.select("strong > a");
                        if (people.size() > 0) {
                            //作者
                            author = people.get(0);
                            Log.d(TAG, "作者: " + author.text());
                        }

                        if (people.size() > 1) {
                            //最后的评论者
                            lastDicuss = people.get(1);
                            Log.d(TAG, "最后的评论者: " + lastDicuss.text());
                        }
                        itemList.add(new VtexBean.ItemBean(src,text,author.text(),lastDicuss.text(),secTab));

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(itemList);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }).start();
}
}
