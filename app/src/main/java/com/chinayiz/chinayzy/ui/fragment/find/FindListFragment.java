package com.chinayiz.chinayzy.ui.fragment.find;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.BaseInectAdaphter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.presenter.FindListPresenter;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableGridView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 发现列表
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FindListFragment extends BaseFragment<FindListPresenter> implements AdapterView.OnItemClickListener {
    public PullableGridView gd_find_list;
    public SmartRefreshLayout smartRefreshLayout;
    public static final String DATA_TYPE = "DATA_TYPE";
    public static final String TO_FINDDETAIL = "TO_FINDDETAIL";
    public String type;
    public PullableGridView lv_list;
    public BaseInectAdaphter adaphter;

    public FindListFragment(String type, BaseInectAdaphter adaphter) {
        this.type = type;
        this.adaphter = adaphter;
    }


    @Override
    protected void onVisible() {
        if (isInit) {
            getData();
        }
        isInit = false;
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
        View view = initView(inflater, container, savedInstanceState);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
            Logger.i("FindListFragment onCreateView");
        }
        return view;
    }

    public void getData() {
        adaphter.onGetData(1);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_list, container, false);
        lv_list = (PullableGridView) view.findViewById(R.id.lv_list);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh_view);
//        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
//        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        adaphter.setRefreshLayout(smartRefreshLayout);
        lv_list.setAdapter(adaphter);
        lv_list.setOnItemClickListener(this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
                adaphter.onRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                adaphter.LoadMore();
            }
        });
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
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
        List<FindListModel.DataBean> lists = adaphter.getData();
        FindListModel.DataBean dataBean = lists.get(position);
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, TO_FINDDETAIL, dataBean));
    }
}
