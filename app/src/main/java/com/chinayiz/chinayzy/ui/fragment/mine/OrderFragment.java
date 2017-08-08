package com.chinayiz.chinayzy.ui.fragment.mine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.OrderListAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.presenter.FrgOrderPresenter;
import com.chinayiz.chinayzy.views.refreshView.PullableListView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.greenrobot.eventbus.EventBus;


/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/14 9:17
 * Class OrderFragment  订单状态
 */
@SuppressLint("ValidFragment")
public class OrderFragment extends BaseFragment<FrgOrderPresenter> {
    private PullableListView mLvOrder;
    /**
     * 订单页请求订单数据
     */
    public static final String GET_DATA="OrderFragment_GET";
    private SmartRefreshLayout mSmartRefresh;
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
//        mPullrefresh = (PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        mSmartRefresh = (SmartRefreshLayout) view.findViewById(R.id.pullrefresh);

        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mSmartRefresh= (SmartRefreshLayout) refreshlayout;
                EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT,GET_DATA,orderType));
            }
        });
        mSmartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(0);
            }
        });
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefresh.setEnableLoadmore(false);
        mLlProgress = view.findViewById(R.id.ll_progress);
        mNullOrder=view.findViewById(R.id.view_nullOrder);
    }
    public void setOrderListModel(OrderListModel orderListModel) {

        if (mSmartRefresh!=null) {
            mSmartRefresh.finishRefresh();
        }
        mOrderListModel = orderListModel;
        mLlProgress.setVisibility(View.GONE);
        if (mOrderListModel.getOrderList().size()==0){
            mNullOrder.setVisibility(View.VISIBLE);
            mLvOrder.setVisibility(View.GONE);
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

    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("我的订单");
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
