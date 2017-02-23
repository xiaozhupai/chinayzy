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

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/16 16:52
 * Class GoodsDetailGridAdpter 商品详情页相关商品适配器
 */
public class GoodsDetailGridAdpter extends BaseAdapter implements View.OnClickListener {
    /**
     * 商品详情页
     */
    public final static String CLICK_GOODS="GoodsDetailGridAdpter_goods";

    private RelatedGoodsModel mModel;
    private ViewHoder mHoder;
    private Context mContext;
    private boolean isInit = false;

    public GoodsDetailGridAdpter(Context context) {
        mContext = context;
    }

    public void setData(RelatedGoodsModel model) {
        mModel = model;
    }

    @Override
    public int getCount() {
        if (isInit) {
            return mModel.getData().size();
        }
        return mModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        if (isInit) {
            return mModel.getData().get(position);
        }
        return mModel.getData().get(position);
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
            mHoder.mView=view.findViewById(R.id.view_Goods);
            view.findViewById(R.id.iv_addCart).setVisibility(View.GONE);
            mHoder.mName = (TextView) view.findViewById(R.id.tv_goodsName);
            mHoder.mPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
            view.setTag(mHoder);
        } else {
            view = convertView;
            mHoder = (ViewHoder) view.getTag();
        }
        mHoder.mView.setTag(mModel.getData().get(position).getGoodsid());
        mHoder.mView.setOnClickListener(this);
        Glide.with(mContext)
                .load(mModel.getData().get(position).getIcon())
                .into(mHoder.mIocn);
        mHoder.mName.setText(mModel.getData().get(position).getGname());
        mHoder.mPrice.setText(mModel.getData().get(position).getPrice());
        return view;
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,CLICK_GOODS,v.getTag()));
    }

    class ViewHoder {
        View mView;
        ImageView mIocn;
        TextView mName;
        TextView mPrice;
    }
}
