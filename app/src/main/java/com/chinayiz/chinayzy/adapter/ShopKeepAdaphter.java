package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

import java.util.List;

/**   店铺收藏Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopKeepAdaphter extends BaseInectAdaphter {
    public ShopKeepAdaphter(Context context, List list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.shop_keep_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
           viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_shopkeep_list_image;
        public TextView tv_shopkeep_title;
        public TextView tv_shopkeep_num;
        public TextView tv_shopkeep_evaluate;
        public LinearLayout lv_center;
        public ImageView iv_arrow_right;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopkeep_list_image = (ImageView) rootView.findViewById(R.id.iv_shopkeep_list_image);
            this.tv_shopkeep_title = (TextView) rootView.findViewById(R.id.tv_shopkeep_title);
            this.tv_shopkeep_num = (TextView) rootView.findViewById(R.id.tv_shopkeep_num);
            this.tv_shopkeep_evaluate = (TextView) rootView.findViewById(R.id.tv_shopkeep_evaluate);
            this.lv_center = (LinearLayout) rootView.findViewById(R.id.lv_center);
            this.iv_arrow_right = (ImageView) rootView.findViewById(R.id.iv_arrow_right);
        }

    }
}
