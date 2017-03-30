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
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.entity.request.CommentGoodsModel;
import com.chinayiz.chinayzy.entity.response.OrderDetailModel;
import com.chinayiz.chinayzy.net.Commons;
import com.orhanobut.logger.Logger;

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
    private String orderID;
    private CommentGoodsModel model;
    public OrderDetailAdapter( Fragment fragment,String orderID) {
        mFragment = fragment;
        this.orderID=orderID;
    }

    public void setDetailModel(OrderDetailModel detailModel) {
        this.mDetailModel=null;
        this.goodsList=null;
        this.orderState=null;
        this.mDetailModel = detailModel;
        this.goodsList=detailModel.getData().getOmessages();
        this.orderState=detailModel.getData().getState();
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
        model=new CommentGoodsModel();
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
                model.setOrderdetailid(goodsList.get(position).getOrderdetailid()+"");
                model.setOrderid(orderID);
                model.setgPic(goodsList.get(position).getStanderpic());
                model.setGoodsName(goodsList.get(position).getGname());
                model.setGoodsCount("x"+goodsList.get(position).getGoodscount());
                model.setsName(goodsList.get(position).getStandardname());
                model.setPice("￥"+goodsList.get(position).getPrice());

                mHolder.btn_action1.setTag(R.id.tag_orderInfo,model);
                mHolder.btn_action2.setTag(R.id.tag_orderInfo,model);
                mHolder.btn_action1.setText("售后");
                if (goodsList.get(position).getIscomment().equals("0")) {
                    mHolder.btn_action2.setTextColor(Color.rgb(255,57,81));
                    mHolder.btn_action2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape_pre));
                    mHolder.btn_action2.setText("去评价");
                    mHolder.btn_action2.setOnClickListener(this);
                }else {
                    mHolder.btn_action2.setVisibility(View.GONE);
                }
                mHolder.btn_action1.setOnClickListener(this);
                break;
            case "5"://已完成
                mHolder.btn_action1.setVisibility(View.GONE);
                mHolder.btn_action2.setTag(R.id.tag_orderInfo,model);
                mHolder.btn_action2.setTextColor(Color.rgb(11,27,1));
                mHolder.btn_action2.setBackground(mFragment.getResources().getDrawable(R.drawable.btn_shape));
                mHolder.btn_action2.setText("售后");
                mHolder.btn_action2.setOnClickListener(this);
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

    switch (v.getId()){

        case R.id.btn_action1:
            model= (CommentGoodsModel) v.getTag(R.id.tag_orderInfo);
            Logger.i("btn_action1售后");
            String url=new String(Commons.API+Commons.AFTER_RESQUEST+
                    "?userid="+ APP.sUserid+"&orderdetailid="+model.getOrderdetailid());
            Skip.toWebPage(mFragment.getActivity(),url,"申请售后");
            break;
        case R.id.btn_action2:
            model= (CommentGoodsModel) v.getTag(R.id.tag_orderInfo);
            Skip.toCommentGoods(mFragment.getActivity(),model);
            Button button= (Button) v;
            button.setClickable(false);
            button.setVisibility(View.GONE);
            Logger.i("btn_action2评价" );
            break;
    }
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
