package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

import java.util.List;

/**           我的足迹Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class MyStepAdaphter extends BaseInectAdaphter {
    public MyStepAdaphter(Context context, List list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.mystep_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
        viewHolder= (ViewHolder) view.getTag();
        }

        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_mystep_image;
        public TextView tv_mystep_title;
        public TextView tv_mystep_price;
        public ImageView iv_mystep_delete;
        public ImageView iv_mystep_share;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_mystep_image = (ImageView) rootView.findViewById(R.id.iv_mystep_image);
            this.tv_mystep_title = (TextView) rootView.findViewById(R.id.tv_mystep_title);
            this.tv_mystep_price = (TextView) rootView.findViewById(R.id.tv_mystep_price);
            this.iv_mystep_delete = (ImageView) rootView.findViewById(R.id.iv_mystep_delete);
            this.iv_mystep_share = (ImageView) rootView.findViewById(R.id.iv_mystep_share);
        }

    }
}
