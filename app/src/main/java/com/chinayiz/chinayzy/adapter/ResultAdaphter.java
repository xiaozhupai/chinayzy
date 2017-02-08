package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 结算订单Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ResultAdaphter extends BaseInectAdaphter<ShopCartModel.ShopCartBean> {
    public static final int HEAD = 0;
    public static final int ITEM = 1;
    private ImageView iv_result_item_img;
    private TextView tv_result_item_title;
    private TextView tv_result_item_kind;
    private TextView tv_result_item_price;
    private TextView tv_result_item_num;
    private LinearLayout lv_before;

    public ResultAdaphter(Context context, List<ShopCartModel.ShopCartBean> list) {
        this.lists = list;
        this.context = context;
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
         ShopCartModel.ShopCartBean shopCartModel=lists.get(i);
        if (shopCartModel.isHead()){
            viewHolder.iv_head_title.setVisibility(View.VISIBLE);
            viewHolder.iv_head_title.setText(shopCartModel.getSname());
        }else {
            viewHolder.iv_head_title.setVisibility(View.GONE);
        }
        viewHolder.tv_result_item_price.setText("￥"+shopCartModel.getPrice()+"");
        viewHolder.tv_result_item_num.setText("×"+shopCartModel.getNum());
        viewHolder.tv_result_item_kind.setText(shopCartModel.getStandardname());
        viewHolder.tv_result_item_title.setText(shopCartModel.getGname());
//        viewHolder.iv_result_item_img.
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
        public TextView iv_head_title;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_result_item_img = (ImageView) rootView.findViewById(R.id.iv_result_item_img);
            this.tv_result_item_title = (TextView) rootView.findViewById(R.id.tv_result_item_title);
            this.tv_result_item_kind = (TextView) rootView.findViewById(R.id.tv_result_item_kind);
            this.tv_result_item_price = (TextView) rootView.findViewById(R.id.tv_result_item_price);
            this.tv_result_item_num = (TextView) rootView.findViewById(R.id.tv_result_item_num);
            this.lv_before = (LinearLayout) rootView.findViewById(R.id.lv_before);
            this.iv_head_title = (TextView) rootView.findViewById(R.id.iv_head_title);
        }
    }
}
