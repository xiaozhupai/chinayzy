package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeDetailModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * 红包专场recycleview适配器
 * Created by Administrator on 2017/7/31.
 */

public class RedpacketRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    /**
     * 头条图片
     */
    public static final int ITEM_PIC = 0;
    /**
     * goodsdetail
     */
    public static final int ITEM_GOOODSDETAIL = 1;

    private int page = 1;
    private int index;
    private String itemcode;
    private String pic;
    private Context context;
    private List<ShowClassifyCodeDetailModel.DataBean> list;

    public SmartRefreshLayout mSmartRefresh;
    private Fragment mFragment;
    private MyItemClickListener mItemClickListener = null;


    public RedpacketRecycleAdapter(Context context, List<ShowClassifyCodeDetailModel.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public List<ShowClassifyCodeDetailModel.DataBean> getList() {
        return list;
    }

    public void setList(List<ShowClassifyCodeDetailModel.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
        Logger.i("itemcode" + itemcode);
        notifyDataSetChanged();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
        notifyDataSetChanged();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        notifyDataSetChanged();
    }

    public boolean isHeader(int position) {
        return position == ITEM_PIC;
    }

    //添加数据  上拉加载使用
    public void AddData(List<ShowClassifyCodeDetailModel.DataBean> lists) {
        this.list.addAll(lists);
        notifyDataSetChanged();
    }

    //下拉刷新
    public void onRefresh() {
        this.page = 1;
        list.clear();
        onGetData(1, itemcode);
    }

    //上拉加载
    public void LoadMore() {
        page++;
        onGetData(page, itemcode);
    }

    //清空数据
    public void clearData(){
        list.clear();
        notifyDataSetChanged();
    }

    //调用数据
    public void onGetData(int pageindex, String itemcode) {
        //每次加载6条数据
        int size = 6;
        CommonRequestUtils.getRequestUtils().showClassifyCodeDetail(pageindex, size, itemcode);
    }

    //结束加载 刷新
    public void onResult(List<ShowClassifyCodeDetailModel.DataBean> list) {
        if (page > 1) {  //上拉加载
            AddData(list);
        } else {  //下拉刷新
            setList(list);
        }
        if (list.size() < 6) {
            mSmartRefresh.setEnableLoadmore(false);
        } else {
            mSmartRefresh.setEnableLoadmore(true);
        }
        mSmartRefresh.finishRefresh();
        mSmartRefresh.finishLoadmore();
    }

    //设置下拉刷新布局

    public void setRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        this.mSmartRefresh = smartRefreshLayout;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_PIC:
                View view = LayoutInflater.from(context).inflate(R.layout.classify_gridhead, parent, false);
                PicViewHolder viewHolder1 = new PicViewHolder(view);
                return viewHolder1;
            case ITEM_GOOODSDETAIL:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_showclassifycode_detail, parent, false);
                MyViewHolder viewHolder = new MyViewHolder(view1);
                view1.setOnClickListener(this);
                return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder mHolder = (MyViewHolder) holder;
            index = position - 1;
            ShowClassifyCodeDetailModel.DataBean model = list.get(index);
            mHolder.tv_goodsName.setText(model.getGname());
            mHolder.tv_price.setText(model.getPrice());
            mHolder.tv_commentCount.setText("好评率:"+model.getPraise());
            mHolder.tv_goodsComment.setText("评论："+model.getCommenttotal());
            Glide.with(context)
                    .load(model.getIcon())
                    .into(mHolder.iv_goodsPic);

            //将position保存在itemView的Tag中，以便点击时进行获取
            mHolder.itemView.setTag(position);

        }
        if (holder instanceof PicViewHolder) {
            PicViewHolder pHolder = (PicViewHolder) holder;
            Glide.with(context)
                    .load(pic)
                    .into(pHolder.iv_classifyHead);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_PIC;
        }
        return ITEM_GOOODSDETAIL;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    //声明MyItemClickListener这个接口
    public  interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    //红包专场自营商品
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_goodsName, tv_price, tv_commentCount, tv_goodsComment;
        public ImageView iv_goodsPic;
        public LinearLayout item_goods;

        public MyViewHolder(View rootView) {
            super(rootView);
            tv_goodsName = (TextView) rootView.findViewById(R.id.tv_goodsName);
            tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            tv_commentCount = (TextView) rootView.findViewById(R.id.tv_commentCount);
            tv_goodsComment = (TextView) rootView.findViewById(R.id.tv_goodsComment);
            iv_goodsPic = (ImageView) rootView.findViewById(R.id.iv_goodsPic);
            item_goods = (LinearLayout) rootView.findViewById(R.id.item_goods);
        }
    }

    //红包专场pic
    public static class PicViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_classifyHead;

        public PicViewHolder(View itemView) {
            super(itemView);
            iv_classifyHead = (ImageView) itemView.findViewById(R.id.iv_classifyHead);
        }
    }

    @Override
    public void onClick(View v) {
        //如果mListener不为空，就实现接口中的方法onItemClick其中getPosition()是得到被点击位置的position
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, (int)v.getTag());
        }
    }

}
