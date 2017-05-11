package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.utils.Utility;
import com.orhanobut.logger.Logger;
import java.util.List;


/**
 * 结算订单Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ResultAdaphter extends BaseAdapter {
    private static final int TYPE_CATEGORY_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private Context context;
    private List<ResultModel.DataBean.GoodmessageBean> goods_list;
    private List<ResultModel.DataBean.CarriagesBean> carriages_list;



    public ResultAdaphter(Context context, List<ResultModel.DataBean.GoodmessageBean> goods_list, List<ResultModel.DataBean.CarriagesBean> carriages_list) {
        this.goods_list = goods_list;
        this.carriages_list = carriages_list;
        this.context = context;
    }

    public void setData(List<ResultModel.DataBean.GoodmessageBean> goods_list, List<ResultModel.DataBean.CarriagesBean> carriages_list) {
        this.carriages_list = carriages_list;
        this.goods_list = goods_list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return goods_list == null ? 0 : goods_list.size();
    }

    @Override
    public ResultModel.DataBean.GoodmessageBean getItem(int position) {

        return goods_list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = ItemView(view);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ResultModel.DataBean.GoodmessageBean goodmessageBean=getItem(i);
        ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean bean=goodmessageBean.getGoodmessagelist().get(0);
        Glide.with(context).load(bean.getPic()).into(viewHolder.iv_result_head);
        viewHolder.tv_result_head.setText(bean.getSname());
        ResultModel.DataBean.CarriagesBean carriagesBean=carriages_list.get(i);
        viewHolder.tv_goods_total.setText("¥"+carriagesBean.getShopTatalPrice());
        viewHolder.tv_cost.setText("¥"+carriagesBean.getCarriage());

        ResultItemAdaphter   adaphter=new ResultItemAdaphter(context,goodmessageBean.getGoodmessagelist());

        viewHolder.lv_list.setAdapter(adaphter);
        Utility.setListViewHeightBasedOnChildren(viewHolder.lv_list);
        Logger.i("ResultAdaphter"+i);

        return view;
    }

    public View ItemView(View view) {
        view = View.inflate(context, R.layout.result_item_head, null);
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_result_head;
        public TextView tv_result_head;
        public ListView lv_list;
        public TextView tv_goods_total;
        public TextView tv_cost;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_result_head = (ImageView) rootView.findViewById(R.id.iv_result_head);
            this.tv_result_head = (TextView) rootView.findViewById(R.id.tv_result_head);
            this.lv_list = (ListView) rootView.findViewById(R.id.lv_list);
            this.tv_goods_total = (TextView) rootView.findViewById(R.id.tv_goods_total);
            this.tv_cost = (TextView) rootView.findViewById(R.id.tv_cost);
        }
    }
}
