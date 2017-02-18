package com.chinayiz.chinayzy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 */

public class PagerAdaphter extends FragmentPagerAdapter {
    private List<Fragment> lists;
    public PagerAdaphter(FragmentManager fm,List<Fragment> lists) {
        super(fm);
        this.lists=lists;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists==null?0:lists.size();
    }

    public void setData(List<Fragment> lists){
        this.lists=lists;
        notifyDataSetChanged();
    }




}
