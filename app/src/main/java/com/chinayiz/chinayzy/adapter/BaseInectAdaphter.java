package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class BaseInectAdaphter<T> extends BaseAdapter implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    public List<T> lists;
    public Context context;
    public ListView listView;
    public BaseFragment fragment;
    public SmartRefreshLayout mSmartRefresh;
    public int page = 1;

    public List<T> getData() {
        return lists;
    }

    //设置数据
    public void setData(List<T> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    //添加数据  上拉刷新使用
    public void AddData(List<T> lists) {
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    //注册EventBus调用的数据请求
    public void getdata(int pageindex) {
        this.page = 1;
        EventBus.getDefault().register(this);
        onGetData(pageindex);
    }


    public void onDestory() {
        EventBus.getDefault().unregister(this);
    }


    //调用数据
    public void onGetData(int pageindex) {
    }

    //下拉刷新
    public void onRefresh() {
        this.page = 1;
        onGetData(1);
    }

    //上拉加载
    public void LoadMore() {
        this.page++;
        onGetData(page);
    }


    //设置listview
    public void setListview(ListView listview) {
        this.listView = listView;
        listview.setOnItemClickListener(this);

        listview.setOnItemLongClickListener(this);
    }

    //点击事件
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onItemClick(i);
    }

    /**
     * 设置item点击事件
     *
     * @param position
     */
    public void onItemClick(int position) {
        Logger.e("地址列表点击=" + lists.get(position).toString());
    }

    //结束加载 刷新
    public void onResult(List<T> lists) {
        if (page > 1) {  //上拉加载
            AddData(lists);
        } else {  //下拉刷新
            setData(lists);
        }
        if (lists.size() < 10) {
            mSmartRefresh.setEnableLoadmore(false);
        } else {
            mSmartRefresh.setEnableLoadmore(true);

        }
        mSmartRefresh.finishRefresh();
        mSmartRefresh.finishLoadmore();

        ListFragment listFragment = null;
        if (fragment instanceof ListFragment) {
            listFragment = (ListFragment) fragment;
        }
        if (lists.size() == 0) {
            listFragment.ll_none.setVisibility(View.VISIBLE);
            onNone(listFragment);
        } else {
            if (listFragment != null) {
                listFragment.ll_none.setVisibility(View.GONE);

            }
        }
    }

    //没有数据的时候
    public void onNone(ListFragment fragment) {

    }

    //dismiss刷新界面
    public void pullResult() {
        if (mSmartRefresh != null) {
            mSmartRefresh.finishRefresh();
        }
    }

    //设置下拉刷新布局

    public void setRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        this.mSmartRefresh = smartRefreshLayout;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return true;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }
}
