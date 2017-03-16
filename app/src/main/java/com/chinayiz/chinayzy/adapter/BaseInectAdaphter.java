package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class BaseInectAdaphter<T> extends BaseAdapter implements AdapterView.OnItemClickListener {
    public List<T> lists;
    public Context context;
    public ListView listView;

    public PullToRefreshLayout pullrefresh;
    public int page=1;

    public List<T> getData(){
        return lists;
    }

    public   void  setData(List<T> lists){
        this.lists=lists;
        notifyDataSetChanged();
    }
    public void AddData(List<T> lists){
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists==null?0:lists.size();
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

    public void getdata(int pageindex){
        this.page=1;
        EventBus.getDefault().register(this);
        onGetData(pageindex);
    }

    public void onDestory(){
        EventBus.getDefault().unregister(this);
    }

    public void onGetData(int pageindex){
    }

    public void onRefresh(){
        this.page=1;
        onGetData(1);
    }
    //上拉加载
    public void LoadMore(){
        this.page++;
        onGetData(page);
    }


    public  void setListview(ListView listview){
        this.listView=listView;
        listview.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onItemClick(i);
    }

    /**
     * 设置item点击事件
     * @param position
     */
    public void onItemClick(int position){

    }

    //结束加载 刷新
    public void onResult(List<T> lists){
          if (page>1){  //上拉加载
              AddData(lists);
          }else {  //下拉刷新
              setData(lists);
          }
        if (lists.size()<10){
            pullrefresh.loadmoreView.setVisibility(View.GONE);
            pullrefresh.setLoadMoreVisiable(false);
        }else {
            pullrefresh.loadmoreView.setVisibility(View.VISIBLE);
            pullrefresh.setLoadMoreVisiable(true);
        }
        pullrefresh.refreshFinish(PullToRefreshLayout.SUCCEED);
        pullrefresh.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    public void setRefreshLayout(PullToRefreshLayout pullrefresh) {
        this.pullrefresh=pullrefresh;
    }
}
