package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;

import java.util.List;

/**
 * 结算订单Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ResultAdaphter extends BaseAdapter {
    private static final int TYPE_CATEGORY_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private Context context;
    private List<ResultModel.DataBean.GoodmessageBean> list;


    public ResultAdaphter(Context context, List<ResultModel.DataBean.GoodmessageBean> list) {
        this.list = list;
        this.context = context;
    }

    public void setData(List<ResultModel.DataBean.GoodmessageBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if (null == list || position < 0 || position > getCount()) {
            return TYPE_ITEM;
        }
        int categroyFirstIndex = 0;
        for (ResultModel.DataBean.GoodmessageBean category : list) {
            int size = category.getGoodmessagelist().size() + 1;
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            if (categoryIndex == 0) {
                return TYPE_CATEGORY_ITEM;
            }
            categroyFirstIndex += size;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != TYPE_CATEGORY_ITEM;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != list) {
            //  所有分类中item的总和是ListVIew  Item的总个数
            for (ResultModel.DataBean.GoodmessageBean groups : list) {
                count += groups.getGoodmessagelist().size() + 1;
            }
        }
        return count;
    }

    @Override
    public ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean getItem(int position) {

        // 异常情况处理
        if (null == list || position < 0 || position > getCount()) {
            return null;
        }

        // 同一分类内，第一个元素的索引值
        int categroyFirstIndex = 0;

        for (ResultModel.DataBean.GoodmessageBean category : list) {
            int size = category.getGoodmessagelist().size() + 1;
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            // item在当前分类内
            if (categoryIndex < size) {

                return category.getItem(categoryIndex);
            }

            // 索引移动到当前分类结尾，即下一个分类第一个元素索引
            categroyFirstIndex += size;
        }

        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        final ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean bean = getItem(i);
        switch (type) {
            case TYPE_ITEM:   //条目视图
               ViewHolder viewHolder = null;
                if (view == null) {
                    view = ItemView(view);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }

                viewHolder.lv_before.setVisibility(View.VISIBLE);
                viewHolder.tv_result_item_num.setText("×" + bean.getNum());
                viewHolder.tv_result_item_price.setText("￥" + bean.getPrice());
                viewHolder.tv_result_item_title.setText(bean.getGname());
                viewHolder.tv_result_item_kind.setText(bean.getStandardname());

                Glide.with(context).load(bean.getIcon()).into(viewHolder.iv_result_item_img);
                break;
            case TYPE_CATEGORY_ITEM: //头部视图
                ViewHolderHead viewHolderHead = null;
                if (null == view) {
                    view = View.inflate(context, R.layout.result_item_head, null);
                    viewHolderHead = new ViewHolderHead(view);
                    view.setTag(viewHolderHead);
                } else {
                    viewHolderHead = (ViewHolderHead) view.getTag();
                }
                viewHolderHead.tv_result_head.setText(bean.getSname());
                Glide.with(context).load(bean.getPic()).into(viewHolderHead.iv_result_head);
                break;
        }
        return view;
    }


    public View ItemView(View view) {
        view = View.inflate(context, R.layout.result_item_layout, null);
        return view;
    }




    public static class ViewHolder {
        public View rootView;
        public ImageView iv_result_item_img;
        public TextView tv_result_item_title;
        public TextView tv_result_item_kind;
        public TextView tv_result_item_price;
        public TextView tv_result_item_num;
        public LinearLayout lv_before;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_result_item_img = (ImageView) rootView.findViewById(R.id.iv_result_item_img);
            this.tv_result_item_title = (TextView) rootView.findViewById(R.id.tv_result_item_title);
            this.tv_result_item_kind = (TextView) rootView.findViewById(R.id.tv_result_item_kind);
            this.tv_result_item_price = (TextView) rootView.findViewById(R.id.tv_result_item_price);
            this.tv_result_item_num = (TextView) rootView.findViewById(R.id.tv_result_item_num);
            this.lv_before = (LinearLayout) rootView.findViewById(R.id.lv_before);
        }
    }

    public static class ViewHolderHead {
        public View rootView;
        public ImageView iv_result_head;
        public TextView tv_result_head;

        public ViewHolderHead(View rootView) {
            this.rootView = rootView;
            this.iv_result_head = (ImageView) rootView.findViewById(R.id.iv_result_head);
            this.tv_result_head = (TextView) rootView.findViewById(R.id.tv_result_head);
        }

    }
}
