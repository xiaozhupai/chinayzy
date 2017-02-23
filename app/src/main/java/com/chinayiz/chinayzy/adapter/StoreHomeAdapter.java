package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.StoreHomeHead;
import com.chinayiz.chinayzy.adapter.viewHolder.StoreHomeItem;
import com.chinayiz.chinayzy.entity.model.StoreInfo;
import com.chinayiz.chinayzy.entity.response.StoreGoodsListModel;

import java.util.Collections;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/6 9:54
 * Class StoreHomeAdapter 商铺主页数据适配器
 */
public class StoreHomeAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    public static final int HEAD_VIEW = 0;
    public static final int ITEM_VIEW = 1;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<StoreGoodsListModel.DataBean> mDataList;
    private StoreInfo mStoreInfo;
    private Context mContext;
    private StoreHomeHead mHomeHead;
    private StoreHomeItem mHomeItem;
    public void setData(List<StoreGoodsListModel.DataBean> beanList, StoreInfo storeInfo) {
        this.mDataList = beanList;
        this.mStoreInfo = storeInfo;
    }

    public void setData(List<StoreGoodsListModel.DataBean> beanList) {
        this.mDataList = beanList;
    }

    public StoreHomeHead getHomeHead() {
        return mHomeHead;
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    /**
     * 智能排序...
     */
    public void noopsycheSort(){
        if (mDataList!=null){
            Collections.shuffle(mDataList);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        mContext = parent.getContext();
        if (viewType == HEAD_VIEW) {
            view=getViewType(parent, R.layout.store_headview);
            return new StoreHomeHead(view);
        }
        view=getViewType(parent, R.layout.goodslist_item);
        view.setOnClickListener(this);
        return new StoreHomeItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == HEAD_VIEW) {
            mHomeHead = (StoreHomeHead) holder;
            mHomeHead.setData(mContext, mStoreInfo);
            return;
        }
        mHomeItem = (StoreHomeItem) holder;
        mHomeItem.setData(mContext, mDataList.get(position - 1));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD_VIEW;
        }
        return ITEM_VIEW;
    }

    @Override
    public int getItemCount() {
        return mDataList==null? 1:mDataList.size() + 1;
    }
    /**
     * 填充不同类型 item 布局
     * @param parent
     * @param resource R资源
     * @return
     */
    private View getViewType(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }
    public boolean isHeader(int position) {
        return position == HEAD_VIEW;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v,v.getTag().toString());
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        /**
         * @param view 被点击的视图
         * @param goodsID 商品ID
         */
        void onItemClick(View view , String goodsID);
    }
}
