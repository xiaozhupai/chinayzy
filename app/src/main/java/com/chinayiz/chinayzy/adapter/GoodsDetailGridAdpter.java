package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/16 16:52
 * Class GoodsDetailGridAdpter 商品详情页相关商品适配器
 */
public class GoodsDetailGridAdpter extends BaseAdapter implements View.OnClickListener {
    /**
     * 商品详情页相关商品点击
     */
    public final static String CLICK_GOODS="GoodsDetailGridAdpter_goods";

    private List<RelatedGoodsModel.DataBean> mBeanList;
    private ViewHoder mHoder;
    private Context mContext;


    public GoodsDetailGridAdpter(Context context) {
        mContext = context;
    }

    public void setData(RelatedGoodsModel model) {
        mBeanList=model.getData();
        notifyDataSetChanged();
    }
    public void setData(List<RelatedGoodsModel.DataBean> beanList ) {
        mBeanList=beanList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mBeanList==null? 0:mBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeanList==null? null:mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (null == convertView) {
            view = View.inflate(mContext, R.layout.goodslist_item, null);
            mHoder = new ViewHoder();
            mHoder.mIocn = (ImageView) view.findViewById(R.id.iv_goodsPic);
            view.findViewById(R.id.iv_addCart).setVisibility(View.GONE);
            mHoder.mName = (TextView) view.findViewById(R.id.tv_goodsName);
            mHoder.mPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
            mHoder.mView=view.findViewById(R.id.view_Goods);
            view.setTag(R.id.tag_view,mHoder);
        } else {
            view = convertView;
            mHoder = (ViewHoder) view.getTag(R.id.tag_view);
        }
        mHoder.mView.setTag(R.id.tag_click,mBeanList.get(position).getGoodsid());
        mHoder.mView.setOnClickListener(this);
        Glide.with(mContext)
                .load(mBeanList.get(position).getIcon())
                .into(mHoder.mIocn);
        mHoder.mName.setText(mBeanList.get(position).getGname());

        if (mBeanList.get(position).getPrice().contains("-")){
            String[] prices=mBeanList.get(position).getPrice().split("-");
            mHoder.mPrice.setText(prices[0]);
        }else {
            mHoder.mPrice.setText(mBeanList.get(position).getPrice());
        }
        if ("0".equals(mBeanList.get(position).getIsself())){
            mHoder.mPrice.setCompoundDrawables(null,null,null,null);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag(R.id.tag_click)));
    }

    class ViewHoder {
        View mView;
        ImageView mIocn;
        TextView mName;
        TextView mPrice;
    }
}
