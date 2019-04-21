package com.jiyun.geeknews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jiyun.geeknews.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by $sl on 2019/4/17 18:39.
 */
public class MyDailyNewsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> list;

    public MyDailyNewsPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
