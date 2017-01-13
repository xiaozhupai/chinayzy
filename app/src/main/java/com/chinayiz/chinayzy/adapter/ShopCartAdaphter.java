package com.chinayiz.chinayzy.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.Spinner;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;


/**  购物车Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopCartAdaphter extends BaseInectAdaphter  {
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.shopcart_item_layout, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }

        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public ImageView iv_shopcart_item_radio;
        public ImageView iv_shopcart_item_img;
        public TextView tv_shopcart_item_title;
        public TextView tv_shopcart_item_kind;
        public TextView tv_shopcart_item_price;
        public TextView tv_shopcart_item_num;
        public LinearLayout lv_before;
        public ImageView iv_left;
        public ImageView iv_right;
        public Spinner sp_list;
        public LinearLayout lv_after;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopcart_item_radio = (ImageView) rootView.findViewById(R.id.iv_shopcart_item_radio);
            this.iv_shopcart_item_img = (ImageView) rootView.findViewById(R.id.iv_shopcart_item_img);
            this.tv_shopcart_item_title = (TextView) rootView.findViewById(R.id.tv_shopcart_item_title);
            this.tv_shopcart_item_kind = (TextView) rootView.findViewById(R.id.tv_shopcart_item_kind);
            this.tv_shopcart_item_price = (TextView) rootView.findViewById(R.id.tv_shopcart_item_price);
            this.tv_shopcart_item_num = (TextView) rootView.findViewById(R.id.tv_shopcart_item_num);
            this.lv_before = (LinearLayout) rootView.findViewById(R.id.lv_before);
            this.iv_left = (ImageView) rootView.findViewById(R.id.iv_left);
            this.iv_right = (ImageView) rootView.findViewById(R.id.iv_right);
            this.sp_list = (Spinner) rootView.findViewById(R.id.sp_list);
            this.lv_after = (LinearLayout) rootView.findViewById(R.id.lv_after);
        }

    }
}
