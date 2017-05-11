package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.HomeGoodsListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 16:30
 * Class HomeListRecylAdapter  首页横向滚动商品集合适配器
 */

public class HomeListRecylAdapter extends RecyclerView.Adapter {
    /**
     * 商品数据
     */
    public static final int ITEM_GOODS=1;
    /**
     * 查看全部
     */
    public static final int ITEM_ALL=2;
    List<HomeGoodsListModel.DataBean> mBeanList=new ArrayList<>();
    Fragment mFragment;
    onItemClickListener mItemClickListener;
    public HomeListRecylAdapter(List<HomeGoodsListModel.DataBean> beanList, Fragment fragment) {
        mBeanList = beanList;
        mFragment = fragment;
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ITEM_GOODS :
                return new ListItemGoods(getItemView(parent,R.layout.home_item_list_goods));
            case ITEM_ALL:
                return new ListItemAll(getItemView(parent,R.layout.home_item_list_all));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position==getItemCount()-1){
          if (holder instanceof ListItemAll){
              ListItemAll itemAll= (ListItemAll) holder;
              itemAll.root.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (mItemClickListener!=null){
                          mItemClickListener.onClickItem(ListItemAll.CLICK_ALL);
                      }
                  }
              });
          }
        }else {
            if (holder instanceof ListItemGoods){
                ListItemGoods listItemGoods= (ListItemGoods) holder;
                listItemGoods.setData(mBeanList.get(position),mFragment);
                listItemGoods.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener!=null){
                            mItemClickListener.onClickItem(v.getTag(R.id.tag_click).toString());
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==getItemCount()-1){
            return ITEM_ALL;
        }else {
            return ITEM_GOODS;
        }
    }

    @Override
    public int getItemCount() {
        return mBeanList.size()+1;
    }
    /**
     * 填充不同类型 item 布局
     *
     * @param parent
     * @param resource R资源
     * @return
     */
    private View getItemView(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }
    /**
     * item点击监听器
     */
    public  interface  onItemClickListener{
        void onClickItem(String goodsId);
    }
    /**
     * 商品类型item
     */
    public class ListItemGoods extends RecyclerView.ViewHolder{
        View root;
        ImageView goodsPic;
        TextView goodsName,goodsPrice;
        public ListItemGoods(View itemView) {
            super(itemView);
            root=itemView;
            goodsPic= (ImageView) itemView.findViewById(R.id.iv_goodsPic);
            goodsName= (TextView) itemView.findViewById(R.id.tv_goodsName);
            goodsPrice= (TextView) itemView.findViewById(R.id.tv_goodsPrice);
        }
        public void setData(HomeGoodsListModel.DataBean data, Fragment fragment){
            Glide.with(fragment)
                    .load(data.getIcon())
                    .into(goodsPic);
            goodsName.setText(data.getGname());
            goodsPrice.setText("\t"+data.getPrice());
            root.setTag(R.id.tag_click,data.getGoodsid());
        }
    }
    /**
     * 查看全部商品item
     */
    public class ListItemAll extends RecyclerView.ViewHolder{
        /**
         * 点击查看所有
         */
        public static final String CLICK_ALL="ListItem_all";
        View root;
        public ListItemAll(View itemView) {
            super(itemView);
            root=itemView;
        }
    }
}
