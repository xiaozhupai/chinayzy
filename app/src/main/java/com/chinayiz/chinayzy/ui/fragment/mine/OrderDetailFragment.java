package com.chinayiz.chinayzy.ui.fragment.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.OrderDetailAdapter;
import com.chinayiz.chinayzy.adapter.OrderListAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.OrderDetailModel;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.OrderDetailPresenter;
import com.chinayiz.chinayzy.views.GlideRoundTransform;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.chinayiz.chinayzy.R.id.lv_orderGoods;


/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/15 18:09
 * Class OrderDetailFragment  订单详情
 */
@SuppressLint("ValidFragment")
public class OrderDetailFragment extends BaseFragment<OrderDetailPresenter>
        implements View.OnClickListener, OrderDetailAdapter.OnOrderClicListener {
    public ViewHolder mViewHolder;
    private List<OrderDetailModel.DataBean.OmessagesBean> mGoodsList;
    public OrderDetailModel mOrderDetailModel;
    public OrderDetailAdapter mAdapter;
    public boolean isLoad = true;
    public  OrderListModel.Order.Goods goods;
    private View headerView, FooderView;
    public String orderid;
    public String orderState;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        headerView = inflater.inflate(R.layout.order_detail_header, null);
        FooderView = inflater.inflate(R.layout.order_detail_footer, null);
        mViewHolder = new ViewHolder();
        initViews(view, headerView, FooderView);
        mAdapter = new OrderDetailAdapter(this,orderid);
        return view;
    }
    private void initViews(View view, View headerView, View fooderView) {
        mViewHolder.tv_orderId = (TextView) headerView.findViewById(R.id.tv_orderId);
        mViewHolder.tv_orderState = (TextView) headerView.findViewById(R.id.tv_orderState);
        mViewHolder.tv_expressId = (TextView) headerView.findViewById(R.id.tv_expressId);
        mViewHolder.tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        mViewHolder.tv_address = (TextView) headerView.findViewById(R.id.tv_address);
        mViewHolder.iv_storeLogo = (ImageView) headerView.findViewById(R.id.iv_storeLogo);
        mViewHolder.tv_storeName = (TextView) headerView.findViewById(R.id.tv_storeName);
        mViewHolder.btn_copyId = (Button) headerView.findViewById(R.id.btn_copyId);

        mViewHolder.mPregress = view.findViewById(R.id.ll_progress);
        mViewHolder.btn_order1 = (Button) view.findViewById(R.id.btn_order1);
        mViewHolder.btn_order2 = (Button) view.findViewById(R.id.btn_order2);
        mViewHolder.lv_orderGoods = (ListView) view.findViewById(lv_orderGoods);

        mViewHolder.view_coupons=fooderView.findViewById(R.id.view_coupons);
        mViewHolder.tv_coupons= (TextView) fooderView.findViewById(R.id.tv_coupons);
        mViewHolder.tv_yunfei = (TextView) fooderView.findViewById(R.id.tv_yunfei);
        mViewHolder.tv_sumGolds = (TextView) fooderView.findViewById(R.id.tv_sumGolds);
        mViewHolder.tv_orderNum = (TextView) fooderView.findViewById(R.id.tv_orderNum);
        mViewHolder.tv_orderMyone = (TextView) fooderView.findViewById(R.id.tv_orderMyone);
        mViewHolder.tv_orderSpay = (TextView) fooderView.findViewById(R.id.tv_orderSpay);
        mViewHolder.tv_createDate = (TextView) fooderView.findViewById(R.id.tv_createDate);

        mViewHolder.btn_copyId.setOnClickListener(this);
        mViewHolder.btn_order1.setOnClickListener(this);
        mViewHolder.btn_order2.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!"-1".equals(orderid)) {
            if (isLoad) {
                mPresenter.mRequestUtils.getOrderDetail(orderid);
            }
        }
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("订单详情");
    }

    @Override
    public void onInintData(Bundle bundle) {
        orderid = bundle.getString("orderid", "-1");
    }

    /**
     * 设置数据
     *
     * @param model
     */
    public void setData(OrderDetailModel model) {

        goods=new OrderListModel.Order.Goods();
        isLoad = false;
        mOrderDetailModel = model;
        mGoodsList = mOrderDetailModel.getData().getOmessages();
        mViewHolder.tv_orderId.setText("订单编号：" + model.getData().getCodeX());
        orderState = model.getData().getState();
        setOrderAction(orderState);
//        mViewHolder.tv_expressId.setText("物流单号：" + model.getData().getCodeX());
        mViewHolder.tv_name.setText(model.getData().getConsignee() + "\t\t\t\t" + model.getData().getPhone());
        mViewHolder.tv_address.setText(model.getData().getArea() + model.getData().getAddress());
        Glide.with(this)
                .load(model.getData().getPic())
                .bitmapTransform(new GlideRoundTransform(getActivity()))
                .into(mViewHolder.iv_storeLogo);
        mViewHolder.tv_storeName.setText(model.getData().getSname() + "  ");

        mViewHolder.lv_orderGoods.addHeaderView(headerView);
        mViewHolder.lv_orderGoods.addFooterView(FooderView);

        mAdapter.setDetailModel(model);
        mViewHolder.lv_orderGoods.setAdapter(mAdapter);
        mAdapter.setOrderClicListener(this);

        mViewHolder.tv_yunfei.setText("￥\t " + model.getData().getCarriage());
        mViewHolder.tv_sumGolds.setText("\t"+model.getData().getPoint());
        mViewHolder.tv_orderNum.setText("共" + mGoodsList.size() + "件商品   总计：");
        mViewHolder.tv_orderMyone.setText("\t" + model.getData().getTotalmoney());
        if (model.getData().getCouponprice()==null||model.getData().getCouponprice().equals("0")){
            mViewHolder.view_coupons.setVisibility(View.GONE);
        }else {
            mViewHolder.tv_coupons.setText("\t"+model.getData().getCouponprice());
        }
        mViewHolder.tv_orderSpay.setText("实付: ￥\t" + model.getData().getMoney());
        mViewHolder.tv_createDate.setText("下单时间：" + model.getData().getOrdertime());
        mViewHolder.btn_copyId.setTag(R.id.tag_click, model.getData().getCodeX());
        mViewHolder.btn_copyId.setOnClickListener(this);
        mViewHolder.mPregress.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_copyId:
                String url=new String(Commons.API+Commons.LOGISTICS_INFO+"?orderid="+orderid);
                Skip.toWebPage(getActivity(),url,"物流信息");
                break;
            case R.id.btn_order1:
                goods.setOrderid(mOrderDetailModel.getData().getOrderid());
                goods.setTotalmoney(mOrderDetailModel.getData().getTotalmoney());
                switch (orderState) {
                    case "1"://待付款
                        v.setTag(R.id.click_type,OrderListAdapter.CANCEL_ORDER);
                        v.setTag(R.id.tag_orderInfo,goods);
                        EventBus.getDefault()
                                .post(new EventMessage(BaseMessage.NET_EVENT,OrderListAdapter.ADAPTER_CLICK, v));
                        break;
                }
                break;
            case R.id.btn_order2:
                goods.setOrderid(mOrderDetailModel.getData().getOrderid());
                goods.setTotalmoney(mOrderDetailModel.getData().getTotalmoney());
                switch (orderState) {
                    case "1"://待付款
                        v.setTag(R.id.click_type,OrderListAdapter.PAY_ORDER);
                        v.setTag(R.id.tag_orderInfo,goods);
                        EventBus.getDefault()
                                .post(new EventMessage(BaseMessage.NET_EVENT,OrderListAdapter.ADAPTER_CLICK, v));
                        break;
                    case "2"://待发货

                        BaseActivity.showToast(getActivity(), "提醒成功");
                        break;
                    case "3"://待收货
                        v.setTag(R.id.click_type,OrderListAdapter.CONFIRM_ORDER);
                        v.setTag(R.id.tag_orderInfo,goods);
                        EventBus.getDefault()
                                .post(new EventMessage(BaseMessage.NET_EVENT,OrderListAdapter.ADAPTER_CLICK, v));
                        break;
                    case "4"://待评价
                    case "5"://已完成
                    case "6"://取消订单
                        v.setTag(R.id.click_type,OrderListAdapter.DELETE_ORDER);
                        v.setTag(R.id.tag_orderInfo,goods);
                        EventBus.getDefault()
                                .post(new EventMessage(BaseMessage.NET_EVENT,OrderListAdapter.ADAPTER_CLICK, v));
                        break;
                }
                break;
        }
    }

    private void setOrderAction(String state) {
        switch (state) {
            case "1"://待付款
                mViewHolder.btn_copyId.setVisibility(View.GONE);
                mViewHolder.tv_expressId.setVisibility(View.GONE);
                mViewHolder.tv_orderState.setText("待付款");
                mViewHolder.btn_order1.setText("取消订单");
                mViewHolder.btn_order2.setTextColor(Color.rgb(255,57,81));
                mViewHolder.btn_order2.setBackground(this.getResources().getDrawable(R.drawable.btn_shape_pre));
                mViewHolder.btn_order2.setText("付 款");
                break;
            case "2"://待发货
                mViewHolder.tv_expressId.setVisibility(View.GONE);
                mViewHolder.btn_copyId.setVisibility(View.GONE);
                mViewHolder.tv_orderState.setText("待发货");
                mViewHolder.btn_order1.setVisibility(View.GONE);
                mViewHolder.btn_order2.setTextColor(Color.rgb(255,57,81));
                mViewHolder.btn_order2.setBackground(this.getResources().getDrawable(R.drawable.btn_shape_pre));
                mViewHolder.btn_order2.setText("提醒发货");
                break;
            case "3"://待收货
                mViewHolder.tv_orderState.setText("待收货");
                mViewHolder.btn_order1.setVisibility(View.GONE);
                mViewHolder.btn_order2.setTextColor(Color.rgb(255,57,81));
                mViewHolder.btn_order2.setBackground(this.getResources().getDrawable(R.drawable.btn_shape_pre));
                mViewHolder.btn_order2.setText("确认收货");
                break;
            case "4"://待评价
                mViewHolder.tv_orderState.setText("待评价");
                mViewHolder.btn_order1.setVisibility(View.GONE);
                mViewHolder.btn_order2.setTextColor(Color.rgb(11,27,1));
                mViewHolder.btn_order2.setBackground(this.getResources().getDrawable(R.drawable.btn_shape));
                mViewHolder.btn_order2.setText("删除订单");
                break;
            case "5"://已完成
                mViewHolder.tv_orderState.setText("已完成");
                mViewHolder.btn_order1.setVisibility(View.GONE);
                mViewHolder.btn_order2.setTextColor(Color.rgb(11,27,1));
                mViewHolder.btn_order2.setBackground(this.getResources().getDrawable(R.drawable.btn_shape));
                mViewHolder.btn_order2.setText("删除订单");
                break;
            case "6"://取消订单
                mViewHolder.tv_orderState.setText("取消订单");
                mViewHolder.btn_order1.setVisibility(View.GONE);
                mViewHolder.btn_order2.setTextColor(Color.rgb(11,27,1));
                mViewHolder.btn_order2.setBackground(this.getResources().getDrawable(R.drawable.btn_shape));
                mViewHolder.btn_order2.setText("删除订单");
                break;
        }
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public OrderDetailPresenter initPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void onClicOrder(String goodsId) {
      Skip.toNewGoodsDetail(getActivity(),goodsId);
    }

    public class ViewHolder {
        public View mPregress;
        public TextView tv_orderId;
        public TextView tv_orderState;
        public TextView tv_expressId;
        public Button btn_copyId;
        public TextView tv_name;
        public TextView tv_address;
        public ImageView iv_storeLogo;
        public TextView tv_storeName;
        public ListView lv_orderGoods;
        public TextView tv_yunfei;
        public View view_coupons;
        public TextView tv_coupons;
        public TextView tv_sumGolds;
        public TextView tv_orderNum;
        public TextView tv_orderMyone;
        public TextView tv_orderSpay;
        public TextView tv_createDate;
        public Button btn_order1;
        public Button btn_order2;

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
