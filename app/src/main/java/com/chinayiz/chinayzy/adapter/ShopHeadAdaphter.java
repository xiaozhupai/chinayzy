package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

/**     购物车头部Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopHeadAdaphter extends BaseInjectHeadAdaphter {
    private Context context;
    public ShopHeadAdaphter(Context context){
        this.context=context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.shopcart_head_layout, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
           viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_shopcart_head_radio;
        public TextView iv_shopcart_head_title;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopcart_head_radio = (ImageView) rootView.findViewById(R.id.iv_shopcart_head_radio);
            this.iv_shopcart_head_title = (TextView) rootView.findViewById(R.id.iv_shopcart_head_title);
        }

    }
}
