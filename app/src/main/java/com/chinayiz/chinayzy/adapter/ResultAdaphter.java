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

public class ResultAdaphter extends BaseInectAdaphter<ShopCartModel> {
    public static final int HEAD = 0;
    public static final int ITEM = 1;
    private ImageView iv_result_item_img;
    private TextView tv_result_item_title;
    private TextView tv_result_item_kind;
    private TextView tv_result_item_price;
    private TextView tv_result_item_num;
    private LinearLayout lv_before;

    public ResultAdaphter(Context context, List<ShopCartModel> list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShopCartAdaphter.ViewHolder viewHolder = null;
        if (view == null) {
            if (i == 0) {
                Logger.i("position=0");
                view = HeadView(view);
            } else {
                if (!lists.get(i).getSname().equals(lists.get(i - 1).getSname())) {
                    view = HeadView(view);
                    Logger.i("头部视图");
                } else {
                    view = ItemView(view);
                    Logger.i("body视图");
                }
            }
            viewHolder = new ShopCartAdaphter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ShopCartAdaphter.ViewHolder) view.getTag();
        }

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return HEAD;
        }else {
            if (!lists.get(position).getSname().equals(lists.get(position-1).getSname())) {
                return HEAD;
            }else {
                return ITEM;
            }
        }

    }

    public View ItemView(View view) {
        view = View.inflate(context, R.layout.result_item_layout, null);
        return view;
    }

    public View HeadView(View view) {
        view = View.inflate(context, R.layout.result_head_layout, null);
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
