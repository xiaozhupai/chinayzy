package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsSetItemHolder;
import com.chinayiz.chinayzy.entity.response.GoodsSteModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/24 15:14
 * Class GoodsSetAdapter
 */

public class GoodsSetAdapter extends RecyclerView.Adapter<GoodsSetItemHolder> {
    public List<GoodsSteModel.DataBean> mDataList;
    private Fragment mFragment;
    private int mViewType;
    /**
     * 列表布局
     */
    public static final int LIST_ITEM = 1;
    /**
     * 宫格布局
     */
    public static final int GRID_ITEM = 2;

    public GoodsSetAdapter(List<GoodsSteModel.DataBean> dataList, Fragment fragment, int viewType) {
        mDataList = dataList;
        mFragment = fragment;
        this.mViewType = viewType;
    }
    @Override
    public GoodsSetItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (mViewType==LIST_ITEM){
            view=getViewType(parent, R.layout.item_list_goods);
        }else {
            view=getViewType(parent, R.layout.item_grid_goods);
        }
        return new GoodsSetItemHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsSetItemHolder holder, int position) {
        GoodsSteModel.DataBean data=mDataList.get(position);
        NY_EatItemModel.DataBean dataBean=new NY_EatItemModel.DataBean(data.getIcon(),data.getGname(),data.getIsself(),data.getBrand()
        ,data.getPrice(),data.getGoodsid(),data.getBrand(),data.getBrand(),data.getPraise(),data.getCommenttotal());
        holder.setData(dataBean,mFragment);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
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
}
