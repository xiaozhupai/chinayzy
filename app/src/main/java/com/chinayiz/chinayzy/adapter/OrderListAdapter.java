package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.views.GlideRoundTransform;

import java.util.List;

import static com.chinayiz.chinayzy.R.id.btn_order1;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/14 16:49
 * Class OrderListAdapter 订单列表适配器
 */

public class OrderListAdapter extends BaseAdapter {
    private List<OrderListModel.Order> orderList;
    private ViewHolder mHolder;
    private Fragment mFragment;
    private OrderItemAdapter mItemAdapter;
    private int itemHeight=0;
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
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = View.inflate(mFragment.getActivity(), R.layout.order_list, null);
            mHolder.iv_storeLogo = (ImageView) convertView.findViewById(R.id.iv_storeLogo);
            mHolder.tv_storeName = (TextView) convertView.findViewById(R.id.tv_storeName);
            mHolder.lv_orderGoods = (ListView) convertView.findViewById(R.id.lv_orderGoods);
            mHolder.tv_orderNum = (TextView) convertView.findViewById(R.id.tv_orderNum);
            mHolder.tv_orderMyone = (TextView) convertView.findViewById(R.id.tv_orderMyone);
            mHolder.tv_orderSpay = (TextView) convertView.findViewById(R.id.tv_orderSpay);
            mHolder.tv_orderState = (TextView) convertView.findViewById(R.id.tv_orderState);
            mHolder.btn_order1 = (Button) convertView.findViewById(btn_order1);
            mHolder.btn_order2 = (Button) convertView.findViewById(R.id.btn_order2);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mFragment)
                .load(order.getGoodsList().get(0).getPic())
                .bitmapTransform(new GlideRoundTransform(mFragment.getActivity()))
                .into(mHolder.iv_storeLogo);
        mHolder.tv_storeName.setText(order.getGoodsList().get(0).getSname() + "  ");
        mHolder.tv_orderNum.setText("共" + order.getGoodsList().size() + "件商品  总计：");
        mHolder.tv_orderMyone.setText("￥" + order.getGoodsList().get(0).getTotalmoney());
        mHolder.tv_orderSpay.setText("实付款：￥" + order.getGoodsList().get(0).getMoney());
        setOrderAction(order.getGoodsList().get(0).getState());
        mItemAdapter=new OrderItemAdapter(order.getGoodsList(),mFragment);
        mHolder.lv_orderGoods.setAdapter(mItemAdapter);

        View view=mItemAdapter.getView(0,null,mHolder.lv_orderGoods);
        view.measure(0, 0);
        int itrmHeights= view.getMeasuredHeight();

        convertView.measure(0,0);
        int baseHeight=convertView.getMeasuredHeight();
        int height=itrmHeights*(order.getGoodsList().size()-1);

        ViewGroup.LayoutParams layoutParams=new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,height+baseHeight);
        convertView.setLayoutParams(layoutParams);
        return convertView;
    }

    private void setOrderAction(String state) {
        switch (state) {
            case "1"://待付款
                mHolder.tv_orderState.setText("待付款");
                mHolder.btn_order1.setText("取消订单");
                mHolder.btn_order2.setText("付 款");
                break;
            case "2"://待发货
                mHolder.tv_orderState.setText("待发货");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setText("提醒发货");
                break;
            case "3"://待收货
                mHolder.tv_orderState.setText("待收货");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setText("确认收货");
                break;
            case "4"://待评价
                mHolder.tv_orderState.setText("待评价");
                mHolder.btn_order1.setText("删除订单");
                mHolder.btn_order2.setText("去评价");
                break;
            case "5"://取消订单
                mHolder.tv_orderState.setText("取消订单");
                mHolder.btn_order1.setVisibility(View.GONE);
                mHolder.btn_order2.setVisibility(View.GONE);
                break;
        }
    }

    public static class ViewHolder {
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
