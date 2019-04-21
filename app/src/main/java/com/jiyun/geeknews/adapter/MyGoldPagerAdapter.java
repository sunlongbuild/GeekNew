package com.jiyun.geeknews.adapter;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jiyun.geeknews.base.BaseFragment;
import com.jiyun.geeknews.bean.GoldTitlesBean;

import java.util.ArrayList;

/**
 * Created by $sl on 2019/4/18 16:52.
 */
public class MyGoldPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> list;
//
    public MyGoldPagerAdapter(FragmentManager fm,ArrayList<BaseFragment> list) {
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
