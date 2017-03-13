package com.chinayiz.chinayzy.ui.fragment.find;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.FindAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.presenter.FindListPresenter;
import com.chinayiz.chinayzy.ui.fragment.SearchFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SexFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableGridView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/** 发现列表
 * A simple {@link Fragment} subclass.
 */
public class FindListFragment extends BaseFragment<FindListPresenter> implements AdapterView.OnItemClickListener {
    public PullableGridView gd_find_list;
    public FindAdaphter adaphter;
    public PullToRefreshLayout pullToRefreshLayout;
    public static final String DATA_TYPE="DATA_TYPE";
    public static final String TO_FINDDETAIL="TO_FINDDETAIL";
    public String type;
    public PullableGridView lv_list;
    public FindListFragment(String type){
        this.type=type;
    }


    @Override
    protected void onVisible() {
        if (isInit){
          getData();
        }
        isInit=false;
    }

    @Override
    protected void onInvisible() {

    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=initView(inflater,container,savedInstanceState);

        ViewGroup parent= (ViewGroup) view.getParent();
        if (parent!=null){
            parent.removeView(view);
            Logger.i("FindListFragment onCreateView");
        }
        return view;
    }

    public void getData(){
        Net.getNet().getFindBlogByType(type);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find_list,container,false);
        lv_list= (PullableGridView) view.findViewById(R.id.lv_list);
        pullToRefreshLayout= (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        List list=new ArrayList();
        adaphter=new FindAdaphter(getActivity(),list);
        lv_list.setAdapter(adaphter);
        lv_list.setOnItemClickListener(this);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
             getData();

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                Toast.makeText(getActivity(),"refresh",Toast.LENGTH_SHORT).show();
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
        return view;
    }

    @Override
    public FindListPresenter initPresenter() {
        return new FindListPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      List <FindListModel.DataBean> lists=mPresenter.lists;
      FindListModel.DataBean dataBean=lists.get(position);
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,TO_FINDDETAIL,dataBean));
    }
}
