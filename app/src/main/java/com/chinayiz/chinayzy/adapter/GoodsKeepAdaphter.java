package com.chinayiz.chinayzy.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

/**宝贝收藏
 * Created by Administrator on 2017/1/12.
 */

public class GoodsKeepAdaphter extends BaseInectAdaphter {
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.goodskeep_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }




    public static class ViewHolder {
        public View rootView;
        public ImageView iv_goodskeep_imag;
        public TextView tv_goodskeep_title;
        public TextView tv_goodskeep_price;
        public LinearLayout lv_center;
        public ImageView iv_arrow_right;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_goodskeep_imag = (ImageView) rootView.findViewById(R.id.iv_goodskeep_imag);
            this.tv_goodskeep_title = (TextView) rootView.findViewById(R.id.tv_goodskeep_title);
            this.tv_goodskeep_price = (TextView) rootView.findViewById(R.id.tv_goodskeep_price);
            this.lv_center = (LinearLayout) rootView.findViewById(R.id.lv_center);
            this.iv_arrow_right = (ImageView) rootView.findViewById(R.id.iv_arrow_right);
        }

    }
}
