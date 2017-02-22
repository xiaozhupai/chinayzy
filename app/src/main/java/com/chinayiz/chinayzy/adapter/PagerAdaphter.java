package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
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
