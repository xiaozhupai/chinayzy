package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.ClassifyHeadHolder;
import com.chinayiz.chinayzy.adapter.viewHolder.ClassifyItemHolder;
import com.chinayiz.chinayzy.adapter.viewHolder.GoodsSetItemHolder;
import com.chinayiz.chinayzy.entity.response.ClassifyCodesModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/28 11:36
 * Class ClassGridAdapter 生态农业首页三级分类code宫格适配器
 */
public class ClassGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 头类型视图
     */
    public static final int HEAD_VIEW = 0;
    /**
     * item类型视图
     */
    public static final int ITEM_VIEW = 1;
    /**
     * 商品类型
     */
    public static final int ITEM_GOODS = 2;
    private int viewsType;
    private ClassifyCodesModel mModel;
    private List<NY_EatItemModel.DataBean> mDataBeanList;
    private ClassifyHeadHolder mHeadHolder;
    private ClassifyItemHolder mItemHolder;
    private GoodsSetItemHolder mGoodsHolder;
    private String themeUri;
    private Fragment mFragment;

    public ClassGridAdapter(Fragment fragment, int type) {
        mFragment = fragment;
        viewsType = type;
    }

    public void setDataBeanList(List<NY_EatItemModel.DataBean> dataBeanList, String themeUrl) {
        mDataBeanList = dataBeanList;
        themeUri = themeUrl;
        notifyDataSetChanged();
    }

    public void setModel(ClassifyCodesModel model, String themeUrl) {
        this.mModel = model;
        themeUri = themeUrl;
        notifyDataSetChanged();
    }

    public boolean isHeader(int position) {
        return position == HEAD_VIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD_VIEW:
                return new ClassifyHeadHolder(getViewType(parent, R.layout.classify_gridhead));
            case ITEM_VIEW:
                if (viewsType == ITEM_VIEW) {//三级菜单
                    return new ClassifyItemHolder(getViewType(parent, R.layout.classify_griditem));
                } else {//四级商品
                    return new GoodsSetItemHolder(getViewType(parent, R.layout.item_grid_goods));
                }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return position == HEAD_VIEW ? HEAD_VIEW : ITEM_VIEW;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == HEAD_VIEW) {
            mHeadHolder = (ClassifyHeadHolder) holder;
            mHeadHolder.setDatas(mFragment, themeUri);
        } else {
            if (viewsType == ITEM_VIEW) {//三级菜单
                mItemHolder = (ClassifyItemHolder) holder;
                if (mModel != null) {
                    mItemHolder.setDatas(mFragment, mModel.getData().get(position - 1));
                }
            } else {//四级商品
                mGoodsHolder = (GoodsSetItemHolder) holder;
                if (mDataBeanList != null) {
                    mGoodsHolder.setData(mDataBeanList.get(position - 1), mFragment);
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        if (viewsType == ITEM_VIEW) {//三级菜单
            if (mModel == null) return 0;
            return mModel.getData().size() + 1;
        } else {//四级商品
            if (mDataBeanList == null) return 0;
            return mDataBeanList.size() + 1;
        }
    }

    /**
     * 填充不同类型 item 布局
     *
     * @param parent
     * @param resource R资源
     * @return
     */
    private View getViewType(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }
}
