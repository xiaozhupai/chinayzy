package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.SearchFarmModel;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */

public class SearchResultAdaphter extends BaseInectAdaphter<SearchFarmModel.DataBean> {
    private int type;
    public static final int SIMPLE1 = 1;  //列表布局
    public static final int SIMPLE2 = 2;   //九宫格布局
    public static final String JOINCART = "JOINCART";


    public SearchResultAdaphter(List<SearchFarmModel.DataBean> lists, int type, Context context) {
        this.type = type;
        this.lists = lists;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            if (type == SIMPLE1) {
                view = View.inflate(context, R.layout.searchresult_item1, null);
            } else {
                view = View.inflate(context, R.layout.searchresult_item2, null);
            }
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }


        final SearchFarmModel.DataBean bean = lists.get(i);
        String price=bean.getPrice().toString();
        Logger.i(bean.getIcon());
        Glide.with(context).load(bean.getIcon()).into(viewHolder.iv_image);
        viewHolder.tv_price_pre.setText(price.substring(0,price.length()-3));
        viewHolder.tv_price_after.setText(price.substring(price.length()-3,price.length()));
        viewHolder.tv_title.setText(bean.getGname());
        if (bean.getIsself()!=null){
            if (bean.getIsself().equals("1")) {   //是否自营
                viewHolder.iv_oneselft.setVisibility(View.VISIBLE);
            } else {
                viewHolder.iv_oneselft.setVisibility(View.GONE);
            }
        }else {
            viewHolder.iv_oneselft.setVisibility(View.GONE);
        }

        viewHolder.iv_join_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserSeeion.isLogin(context)){
                    EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,JOINCART,bean));
                }
            }
        });
        viewHolder.tv_evaluate.setText(bean.getCommenttotal()+"条评价");
        if (bean.getCommenttotal().equals("0")){
            viewHolder.tv_good_evaluate.setVisibility(View.GONE);
        }else {
            viewHolder.tv_good_evaluate.setVisibility(View.VISIBLE);
            viewHolder.tv_good_evaluate.setText(bean.getPraise()+"好评");
        }


        return view;
    }


    public void AddData(List<SearchFarmModel.DataBean> list, int type) {
        this.lists.addAll(list);
        this.type = type;
        notifyDataSetChanged();

    }

    public void setData(List<SearchFarmModel.DataBean> lists, int type) {
        this.lists = lists;
        this.type = type;
        notifyDataSetChanged();
    }


    public static class ViewHolder {
        public View rootView;
        public ImageView iv_image;
        public TextView tv_title;
        public TextView tv_price_pre;
        public TextView tv_price_after;
        public ImageView iv_oneselft;
        public TextView tv_evaluate;
        public TextView tv_good_evaluate;
        public ImageView iv_join_cart;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_image = (ImageView) rootView.findViewById(R.id.iv_image);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_price_pre = (TextView) rootView.findViewById(R.id.tv_price_pre);
            this.tv_price_after = (TextView) rootView.findViewById(R.id.tv_price_after);
            this.iv_oneselft = (ImageView) rootView.findViewById(R.id.iv_oneselft);
            this.tv_evaluate = (TextView) rootView.findViewById(R.id.tv_evaluate);
            this.tv_good_evaluate = (TextView) rootView.findViewById(R.id.tv_good_evaluate);
            this.iv_join_cart = (ImageView) rootView.findViewById(R.id.iv_join_cart);
        }

    }
}
