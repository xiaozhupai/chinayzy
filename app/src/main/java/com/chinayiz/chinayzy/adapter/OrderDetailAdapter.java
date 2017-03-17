package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.OrderDetailModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/16 11:53
 * Class OrderDetailAdapter
 */

public class OrderDetailAdapter extends BaseAdapter implements View.OnClickListener {
    /**
     * 订单售后服务
     */
    public static final String ORDER_SERVER="OrderDetailAdapter_SERVER";
    /**
     * 订单评论
     */
    public static final String ORDER_COMMENT="OrderDetailAdapter_COMMENT";

    private OrderDetailModel mDetailModel;

    private List<OrderDetailModel.DataBean.OmessagesBean> goodsList;
    private Fragment mFragment;
    private ViewHolder mHolder;
    private String orderState;
    public OrderDetailAdapter( Fragment fragment) {
        mFragment = fragment;
    }

    public void setDetailModel(OrderDetailModel detailModel) {
        this.mDetailModel = detailModel;
        goodsList=detailModel.getData().getOmessages();
        orderState=detailModel.getData().getState();
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return goodsList==null? 0:goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return  goodsList==null? null:goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            mHolder=new ViewHolder();
            convertView=View.inflate(mFragment.getActivity(), R.layout.order_detail_item,null);
            mHolder.iv_goodsPic = (ImageView) convertView.findViewById(R.id.iv_goodsPic);
            mHolder.tv_goodsPrice = (TextView) convertView.findViewById(R.id.tv_goodsPrice);
            mHolder.tv_goodsName = (TextView) convertView.findViewById(R.id.tv_goodsName);
            mHolder.tv_goodsCount = (TextView) convertView.findViewById(R.id.tv_goodsCount);
            mHolder.tv_goodsGroup = (TextView) convertView.findViewById(R.id.tv_goodsGroup);
            mHolder.btn_action1= (Button) convertView.findViewById(R.id.btn_action1);
            mHolder.btn_action2= (Button) convertView.findViewById(R.id.btn_action2);
            convertView.setTag(mHolder);
        }else {
            mHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(mFragment)
                .load(goodsList.get(position).getStanderpic())
                .into(mHolder.iv_goodsPic);
        mHolder.tv_goodsName.setText(goodsList.get(position).getGname());
        mHolder.tv_goodsCount.setText("x"+goodsList.get(position).getGoodscount());
        mHolder.tv_goodsPrice.setText("￥"+goodsList.get(position).getPrice());
        mHolder.tv_goodsGroup.setText(goodsList.get(position).getStandardname());
        switch (orderState) {
            case "1"://待付款
                mHolder.btn_action1.setVisibility(View.GONE);
                mHolder.btn_action2.setVisibility(View.GONE);
                break;
            case "2"://待发货
                mHolder.btn_action1.setVisibility(View.GONE);
                mHolder.btn_action2.setVisibility(View.GONE);
                break;
            case "3"://待收货
                mHolder.btn_action1.setVisibility(View.GONE);
                mHolder.btn_action2.setVisibility(View.GONE);
                break;
            case "4"://待评价
                mHolder.btn_action1.setTag(R.id.tag_orderInfo,mDetailModel.getData().getOrderid());
                mHolder.btn_action1.setTag(R.id.tag_detailId,goodsList.get(position).getOrderdetailid());
                mHolder.btn_action2.setTag(R.id.tag_orderInfo,mDetailModel.getData().getOrderid());
                mHolder.btn_action2.setTag(R.id.tag_detailId,goodsList.get(position).getOrderdetailid());
                mHolder.btn_action1.setText("售后");
                mHolder.btn_action2.setTextColor(Color.rgb(255,57,81));
                mHolder.btn_action2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape_pre));
                mHolder.btn_action2.setText("去评价");
                break;
            case "5"://已完成
                mHolder.btn_action1.setVisibility(View.GONE);
                mHolder.btn_action2.setTag(R.id.tag_orderInfo,mDetailModel.getData().getOrderid());
                mHolder.btn_action2.setTag(R.id.tag_detailId,goodsList.get(position).getOrderdetailid());
                mHolder.btn_action2.setTextColor(Color.rgb(11,27,1));
                mHolder.btn_action2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape));
                mHolder.btn_action2.setText("售后");
                break;
            case "6"://取消订单
                mHolder.btn_action1.setVisibility(View.GONE);
                mHolder.btn_action2.setVisibility(View.GONE);
                break;
        }
        return convertView;
    }
    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder {
        public ImageView iv_goodsPic;
        public TextView tv_goodsPrice;
        public TextView tv_goodsName;
        public TextView tv_goodsCount;
        public TextView tv_goodsGroup;
        public Button btn_action1,btn_action2;
    }
}
