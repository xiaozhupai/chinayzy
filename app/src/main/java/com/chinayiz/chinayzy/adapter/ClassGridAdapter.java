package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.ClassifyHeadHolder;
import com.chinayiz.chinayzy.adapter.viewHolder.ClassifyItemHolder;
import com.chinayiz.chinayzy.entity.response.ClassifyCodesModel;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/28 11:36
 * Class ClassGridAdapter 生态农业首页三级分类code宫格适配器
 */
public class ClassGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    /**
     * 头类型视图
     */
    public static final int HEAD_VIEW = 0;
    /**
     * item类型视图
     */
    public static final int ITEM_VIEW = 1;
    private ClassifyCodesModel mModel;
    private ClassifyHeadHolder mHeadHolder;
    private ClassifyItemHolder mItemHolder;
    private String themeUri;
    private Fragment mFragment;

    public ClassGridAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    public void setModel(ClassifyCodesModel model,String themeUrl) {
        this.mModel = model;
        themeUri=themeUrl;
        notifyDataSetChanged();
    }
    public boolean isHeader(int position) {
        return position == HEAD_VIEW;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType==HEAD_VIEW ?
                new ClassifyHeadHolder(getViewType(parent, R.layout.classify_gridhead))
                :new ClassifyItemHolder(getViewType(parent,R.layout.classify_griditem));
    }
    @Override
    public int getItemViewType(int position) {
        return position==HEAD_VIEW? HEAD_VIEW:ITEM_VIEW;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position==HEAD_VIEW){
            mHeadHolder= (ClassifyHeadHolder) holder;
            if (mModel!=null){
                mHeadHolder.setDatas(mFragment,themeUri);
            }
        }else {
            mItemHolder= (ClassifyItemHolder) holder;
            if (mModel!=null){
            mItemHolder.setDatas(mFragment,mModel.getData().get(position-1));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mModel==null? 0:mModel.getData().size()+1;
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
