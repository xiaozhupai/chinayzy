package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.TeaListModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/4 16:25
 * Class StoreGoodsAdapter  两列型商品宫格布局适配器
 */
public class GoodsGridAdapter extends BaseAdapter {
    private List<TeaListModel.DataBean> mGoodsList;
    private ItemAddCartListener mAddCartListener;
    private ViewHoder mHoder;
    private Context mContext;

    public GoodsGridAdapter(Context context, List<TeaListModel.DataBean> goodsList) {
        this.mContext = context;
        this.mGoodsList = goodsList;
    }

    @Override
    public int getCount() {
        return mGoodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsList.get(position);
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
            mHoder.mName = (TextView) view.findViewById(R.id.tv_goodsName);
            mHoder.mPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
            mHoder.mAddCart= (ImageView) view.findViewById(R.id.iv_addCart);
            view.setTag(mHoder);
        } else {
            view = convertView;
            mHoder = (ViewHoder) view.getTag();
        }
        Glide.with(mContext)
                .load(mGoodsList.get(position).getIcon())
                .into(mHoder.mIocn);
        mHoder.mName.setText(mGoodsList.get(position).getGname());
        mHoder.mPrice.setText(mGoodsList.get(position).getPrice());
        mHoder.mAddCart.setVisibility(View.VISIBLE);
        mHoder.mAddCart.setTag(mGoodsList.get(position).getGoodsid()+"");
        mHoder.mAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddCartListener!=null){
                    mAddCartListener.addCart(v.getTag().toString());
                }
            }
        });
        return view;
    }
    public void setAddCartListener(ItemAddCartListener addCartListener) {
        mAddCartListener = addCartListener;
    }
    class ViewHoder {
        ImageView mAddCart;
        ImageView mIocn;
        TextView mName;
        TextView mPrice;
    }
    interface ItemAddCartListener{
       void addCart(String goodsID);
    }
}
