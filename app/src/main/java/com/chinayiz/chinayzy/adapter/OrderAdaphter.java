package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

import java.util.List;

/**       我的订单Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class OrderAdaphter extends BaseInectAdaphter {
    public OrderAdaphter(Context context, List lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.order_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(view);
        } else {
           viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_order_image;
        public TextView tv_order_title;
        public TextView tv_order_norm;
        public TextView tv_order_price;
        public TextView tv_order_num;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_order_image = (ImageView) rootView.findViewById(R.id.iv_order_image);
            this.tv_order_title = (TextView) rootView.findViewById(R.id.tv_order_title);
            this.tv_order_norm = (TextView) rootView.findViewById(R.id.tv_order_norm);
            this.tv_order_price = (TextView) rootView.findViewById(R.id.tv_order_price);
            this.tv_order_num = (TextView) rootView.findViewById(R.id.tv_order_num);
        }

    }
}
