package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.CouponModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CouponAdaphter extends BaseInectAdaphter {
    public CouponAdaphter(Context context, List list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.dialog_coupon_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
         viewHolder= (ViewHolder) view.getTag();
        }
        CouponModel.DataBean dataBean= (CouponModel.DataBean) lists.get(i);
        viewHolder.mTvTime.setText(dataBean.getEndtime());
        viewHolder.mTvTotal.setText((int)dataBean.getCouponprice()+"");
        viewHolder.mTitle.setText(dataBean.getCoupontitle());
        viewHolder.mTvContent.setText(dataBean.getCouponcontent());
        viewHolder.mTvTotalInfo.setText(dataBean.getCoupontitle());
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvTotal;
        public TextView mTvTotalInfo;
        public TextView mTitle;
        public TextView mTvContent;
        public TextView mTvTime;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvTotal = (TextView) rootView.findViewById(R.id.tv_total);
            this.mTvTotalInfo = (TextView) rootView.findViewById(R.id.tv_total_info);
            this.mTitle = (TextView) rootView.findViewById(R.id.title);
            this.mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
            this.mTvTime = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
