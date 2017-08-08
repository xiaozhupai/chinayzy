package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.views.GlideRoundTransform;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.chinayiz.chinayzy.R.id.btn_order1;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/14 16:49
 * Class OrderListAdapter 订单列表适配器
 */

public class OrderListAdapter extends BaseAdapter implements View.OnClickListener {
    /**
     * 订单列表适配器点击事件
     */
    public static final String ADAPTER_CLICK = "OrderListAdapter_CLICK";
    /**
     * 进入店铺
     */
    public static final String CLICK_STORE = "OrderListAdapter_STORE";

    /**
     * 删除订单
     */
    public static final String DELETE_ORDER = "CLICK_-1";
    /**
     * 快速支付
     */
    public static final String PAY_ORDER = "CLICK_0";
    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER = "CLICK_1";
    /**
     * 提醒发货
     */
    public static final String WARN_ORDER = "CLICK_2";
    /**
     * 确认收货
     */
    public static final String CONFIRM_ORDER = "CLICK_3";
    /**
     * 去评论
     */
    public static final String COMMENT_ORDER = "CLICK_4";


    private List<OrderListModel.Order> orderList;
    private ViewHolder mHolder;
    private Fragment mFragment;
    private OrderItemAdapter mItemAdapter;
    private int itemHeight = 0;

    public OrderListAdapter(List<OrderListModel.Order> orderList, Fragment fragment) {
        this.orderList = orderList;
        mFragment = fragment;
    }

    @Override
    public int getCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList == null ? null : orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderListModel.Order order = orderList.get(position);
        OrderListModel.Order.Goods goods=order.getGoodsList().get(0);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = View.inflate(mFragment.getActivity(), R.layout.order_list, null);
            mHolder.store = convertView.findViewById(R.id.ll_store);
            mHolder.iv_storeLogo = (ImageView) convertView.findViewById(R.id.iv_storeLogo);
            mHolder.tv_storeName = (TextView) convertView.findViewById(R.id.tv_storeName);
            mHolder.lv_orderGoods = (ListView) convertView.findViewById(R.id.lv_orderGoods);
            mHolder.tv_orderNum = (TextView) convertView.findViewById(R.id.tv_orderNum);
            mHolder.tv_orderMyone = (TextView) convertView.findViewById(R.id.tv_orderMyone);
            mHolder.tv_orderSpay = (TextView) convertView.findViewById(R.id.tv_orderSpay);
            mHolder.tv_orderState = (TextView) convertView.findViewById(R.id.tv_orderState);
            mHolder.btn_order1 = (Button) convertView.findViewById(btn_order1);
            mHolder.btn_order2 = (Button) convertView.findViewById(R.id.btn_order2);
            mHolder.btn_order1.setTag(R.id.tag_position,position);
            mHolder.btn_order2.setTag(R.id.tag_position,position);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mFragment)
                .load(goods.getPic())
                .bitmapTransform(new GlideRoundTransform(mFragment.getActivity()))
                .into(mHolder.iv_storeLogo);
        mHolder.tv_storeName.setText(goods.getSname() + "  ");

        mHolder.tv_orderNum.setText("共" + order.getGoodsList().size() + "件商品  总计：");
        mHolder.tv_orderMyone.setText("￥" + goods.getTotalmoney());
        mHolder.tv_orderSpay.setText("实付款：￥" + goods.getMoney());
        setOrderAction(goods.getState());
        mHolder.btn_order1.setOnClickListener(this);
        mHolder.btn_order2.setOnClickListener(this);

        if ("1".equals(goods.getState())) {
            mHolder.btn_order2.setTag(R.id.click_pay, goods.getTotalmoney());
        }

        /**
         * 设置订单信息
         */
        mHolder.btn_order1.setTag(R.id.tag_orderInfo,goods);
        mHolder.btn_order2.setTag(R.id.tag_orderInfo,goods);

        mHolder.store.setTag(R.id.tag_click, String.valueOf(goods.getShopid()));
        mHolder.store.setOnClickListener(this);
        mItemAdapter = new OrderItemAdapter(order.getGoodsList(), mFragment);
        mHolder.lv_orderGoods.setAdapter(mItemAdapter);

        //动态计算内部list高度
        View view = mItemAdapter.getView(0, null, mHolder.lv_orderGoods);
        view.measure(0, 0);
        int itrmHeights = view.getMeasuredHeight();

        convertView.measure(0, 0);
        int baseHeight = convertView.getMeasuredHeight();
        int height = itrmHeights * (order.getGoodsList().size() - 1);

        ViewGroup.LayoutParams layoutParams = new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , height + baseHeight);
        convertView.setLayoutParams(layoutParams);

        return convertView;
    }

    private void setOrderAction(String state) {
        switch (state) {
            case "1"://待付款
                mHolder.tv_orderState.setText("待付款");
                mHolder.btn_order1.setTag(R.id.click_type, CANCEL_ORDER);
                mHolder.btn_order1.setText("取消订单");
                mHolder.btn_order2.setTextColor(Color.rgb(255, 57, 81));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape_pre));
                mHolder.btn_order2.setTag(R.id.click_type, PAY_ORDER);
                mHolder.btn_order2.setText("付 款");
                break;
            case "2"://待发货
                mHolder.tv_orderState.setText("待发货");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setTextColor(Color.rgb(255, 57, 81));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape_pre));
                mHolder.btn_order2.setTag(R.id.click_type, WARN_ORDER);
                mHolder.btn_order2.setText("提醒发货");
                break;
            case "3"://待收货
                mHolder.tv_orderState.setText("待收货");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setTextColor(Color.rgb(255, 57, 81));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape_pre));
                mHolder.btn_order2.setTag(R.id.click_type, CONFIRM_ORDER);
                mHolder.btn_order2.setText("确认收货");
                break;
            case "4"://待评价
                mHolder.tv_orderState.setText("待评价");
                mHolder.btn_order1.setTag(R.id.click_type, DELETE_ORDER);
                mHolder.btn_order1.setText("删除订单");
                mHolder.btn_order2.setTextColor(Color.rgb(255, 57, 81));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape_pre));
                mHolder.btn_order2.setTag(R.id.click_type, COMMENT_ORDER);
                mHolder.btn_order2.setText("去评价");
                break;
            case "5"://已完成
                mHolder.tv_orderState.setText("已完成");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setTextColor(Color.rgb(11, 27, 1));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape));
                mHolder.btn_order2.setTag(R.id.click_type, DELETE_ORDER);
                mHolder.btn_order2.setText("删除订单");
                break;
            case "6"://已取消
                mHolder.tv_orderState.setText("已取消");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setTextColor(Color.rgb(11, 27, 1));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape));
                mHolder.btn_order2.setTag(R.id.click_type, DELETE_ORDER);
                mHolder.btn_order2.setText("删除订单");
                break;
            case "7"://已分红
                mHolder.tv_orderState.setText("已分红");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setTextColor(Color.rgb(11, 27, 1));
                mHolder.btn_order2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape));
                mHolder.btn_order2.setTag(R.id.click_type, DELETE_ORDER);
                mHolder.btn_order2.setText("删除订单");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_store:
                EventBus.getDefault()
                        .post(new EventMessage(BaseMessage
                                .INFORM_EVENT, CLICK_STORE, v.getTag(R.id.tag_click)));
                break;
            default:
                EventBus.getDefault()
                        .post(new EventMessage(EventMessage.NET_EVENT,ADAPTER_CLICK,v));
//                switch (v.getTag(R.id.click_type).toString()) {
//                    case OrderListAdapter.DELETE_ORDER:
//                        Logger.i("删除订单");
//                        orderList.remove(Integer.parseInt(v.getTag(R.id.tag_position).toString()));
//                        notifyDataSetChanged();
//                        break;
//                }
                break;
        }
    }

    public static class ViewHolder {
        public View store;
        public ImageView iv_storeLogo;
        public TextView tv_storeName;
        public ListView lv_orderGoods;
        public TextView tv_orderNum;
        public TextView tv_orderMyone;
        public TextView tv_orderSpay;
        public TextView tv_orderState;
        public Button btn_order1;
        public Button btn_order2;

    }
}
