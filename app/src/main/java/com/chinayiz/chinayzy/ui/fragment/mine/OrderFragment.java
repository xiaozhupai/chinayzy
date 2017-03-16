package com.chinayiz.chinayzy.ui.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.OrderListAdapter;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.presenter.FrgOrderPresenter;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/14 9:17
 * Class OrderFragment  订单
 */

public class OrderFragment extends BaseFragment<FrgOrderPresenter> {
    private PullableListView mLvOrder;
    public static final String GET_DATA="OrderFragment_GET";
    private PullToRefreshLayout mPullrefresh;
    private View mLlProgress,mNullOrder;
    private OrderListModel mOrderListModel;
    private OrderListAdapter mOrderListAdapter;
    private String orderType ;
    public static OrderFragment newInstance(String type) {
        return new OrderFragment(type);
    }
    private OrderFragment(String str) {
        orderType=str;
    }
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mLvOrder = (PullableListView) view.findViewById(R.id.lv_order);
        mPullrefresh = (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        mPullrefresh.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
               Logger.i("准备刷新");
                mPullrefresh=pullToRefreshLayout;
                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,GET_DATA,orderType));
            }
            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
        mLlProgress = view.findViewById(R.id.ll_progress);
        mNullOrder=view.findViewById(R.id.view_nullOrder);
    }
    public void setOrderListModel(OrderListModel orderListModel) {
        if (mPullrefresh!=null) {
            mPullrefresh.refreshFinish(PullToRefreshLayout.SUCCEED);
        }
        mOrderListModel = orderListModel;
        mLlProgress.setVisibility(View.GONE);
        if (mOrderListModel.getOrderList().size()==0){
            mNullOrder.setVisibility(View.VISIBLE);
            Logger.i("没有相关订单");
            return;
        }
        mOrderListAdapter=new OrderListAdapter(orderListModel.getOrderList(),this);
        mLvOrder.setAdapter(mOrderListAdapter);
    }
    @Override
    public FrgOrderPresenter initPresenter() {
        return new FrgOrderPresenter();
    }

    @Override
    public void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
