package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.entity.response.OrderListModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/15 9:14
 * Class OrderItemAdapter  订单商品适配器
 */

public class OrderItemAdapter extends BaseAdapter implements View.OnClickListener {
   private List<OrderListModel.Order.Goods> goodsList;
    private Fragment mFragment;
    private ViewHolder mHolder;

    public OrderItemAdapter(List<OrderListModel.Order.Goods> goodsList, Fragment fragment) {
        this.goodsList = goodsList;
        mFragment = fragment;
    }

    @Override
    public int getCount() {
        return goodsList==null? 0:goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList==null? null:goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            mHolder=new ViewHolder();
            convertView=View.inflate(mFragment.getActivity(), R.layout.order_item,null);
            mHolder.mRootView=convertView;
            mHolder.iv_goodsPic = (ImageView) convertView.findViewById(R.id.iv_goodsPic);
            mHolder.tv_goodsPrice = (TextView) convertView.findViewById(R.id.tv_goodsPrice);
            mHolder.tv_goodsName = (TextView) convertView.findViewById(R.id.tv_goodsName);
            mHolder.tv_goodsCount = (TextView) convertView.findViewById(R.id.tv_goodsCount);
            mHolder.tv_goodsGroup = (TextView) convertView.findViewById(R.id.tv_goodsGroup);
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

        mHolder.mRootView.setClickable(true);
        mHolder.mRootView.setTag(R.id.tag_click,goodsList.get(position).getOrderid());
        mHolder.mRootView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Skip.toOrderDetail(mFragment.getActivity(),v.getTag(R.id.tag_click).toString());
    }

    public static class ViewHolder {
        public View mRootView;
        public ImageView iv_goodsPic;
        public TextView tv_goodsPrice;
        public TextView tv_goodsName;
        public TextView tv_goodsCount;
        public TextView tv_goodsGroup;

    }
}
