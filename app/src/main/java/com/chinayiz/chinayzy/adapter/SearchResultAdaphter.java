package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */

public class SearchResultAdaphter extends BaseInectAdaphter<SearchFarmModel.DataBean> {
    private int type;
    public static final int SIMPLE1 = 1;
    public static final int SIMPLE2 = 2;

    public SearchResultAdaphter(List <SearchFarmModel.DataBean> lists, int type, Context context) {
        this.type = type;
        this.lists = lists;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            if (type == SIMPLE1) {
                view = View.inflate(context, R.layout.searchresult_item1, null);
            } else {
                view = View.inflate(context, R.layout.searchresult_item2, null);
            }
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
         viewHolder= (ViewHolder) view.getTag();
        }
      SearchFarmModel.DataBean bean=lists.get(i);
        viewHolder.tv_price.setText(bean.getPrice());
        viewHolder.tv_title.setText(bean.getGname());
        viewHolder.iv_join_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("加入购物车");

            }
        });
        return view;
    }

    public void setData(List<SearchFarmModel.DataBean> lists,int type){
        this.lists=lists;
        this.type=type;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_image;
        public TextView tv_title;
        public TextView tv_price;
        public ImageView iv_join_cart;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_image = (ImageView) rootView.findViewById(R.id.iv_image);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.iv_join_cart = (ImageView) rootView.findViewById(R.id.iv_join_cart);
        }

    }
}
