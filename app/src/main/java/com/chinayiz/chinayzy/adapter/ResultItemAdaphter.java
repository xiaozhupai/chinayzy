package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ResultModel;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */

public class ResultItemAdaphter extends BaseInectAdaphter<ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean> {
    public ResultItemAdaphter(Context context, List<ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean> list){
        this.lists=list;
        this.context=context;
    }

    public void setData(List<ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean> list){
        this.lists=list;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.result_item_layout, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
          viewHolder= (ViewHolder) view.getTag();
        }
       ResultModel.DataBean.GoodmessageBean.GoodmessagelistBean bean=lists.get(i);
        viewHolder.lv_before.setVisibility(View.VISIBLE);
        viewHolder.tv_result_item_num.setText("×" + bean.getNum());
        viewHolder.tv_result_item_price.setText("¥" + bean.getPrice());
        viewHolder.tv_result_item_title.setText(bean.getGname());
        viewHolder.tv_result_item_kind.setText(bean.getStandardname());
        Glide.with(context).load(bean.getIcon()).into(viewHolder.iv_result_item_img);
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
}
