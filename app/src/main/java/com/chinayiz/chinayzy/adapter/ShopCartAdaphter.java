package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.Spinner;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  购物车Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopCartAdaphter extends BaseInectAdaphter<ShopCartModel> {
    public static final int HEAD=0;
    public static final int ITEM=1;
    private Map<String,ShopCartModel> map=new HashMap<>();
    public ShopCartAdaphter(Context context, List<ShopCartModel> list) {
        this.context=context;
        this.lists=list;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            view = ItemView(view);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        if (i==0){
            Logger.i("position=0");
            viewHolder.lv_shophead.setVisibility(View.VISIBLE);
        }else {
            if (!lists.get(i).getSname().equals(lists.get(i - 1).getSname())) {
                viewHolder.lv_shophead.setVisibility(View.VISIBLE);
                Logger.i("头部视图");
            } else {
                viewHolder.lv_shophead.setVisibility(View.GONE);
                Logger.i("body视图");
            }
        }
       ShopCartModel model=lists.get(i);
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.iv_shopcart_head_radio.setOnClickListener(new View.OnClickListener() { //头部点击事件
            @Override
            public void onClick(View v) {
                  if (finalViewHolder.iv_shopcart_head_radio.isCheck){
                      finalViewHolder.iv_shopcart_head_radio.setCheck(false);
                      HeadUpdate(i,false);
                  }else {
                      finalViewHolder.iv_shopcart_head_radio.setCheck(true);
                      HeadUpdate(i,true);
                  }
            }
        });

        viewHolder.iv_shopcart_item_radio.setOnClickListener(new View.OnClickListener() {  //ITEM点击事件
            @Override
            public void onClick(View v) {
                if (finalViewHolder.iv_shopcart_item_radio.isCheck){
                    finalViewHolder.iv_shopcart_item_radio.setCheck(false);
                    ItemUpdate(i,finalViewHolder);
                }else {
                    finalViewHolder.iv_shopcart_item_radio.setCheck(true);
                    ItemUpdate(i,finalViewHolder);
                }
            }
        });

        if (model.isChecked()){
            viewHolder.iv_shopcart_item_radio.setImageResource(R.mipmap.radio_selected);
        }else {
            viewHolder.iv_shopcart_item_radio.setImageResource(R.mipmap.radio_normal);
        }
        return view;
    }

    public void HeadUpdate(int positon,boolean isChecked){
         ShopCartModel  shopCartModel=lists.get(positon);
        for (int i=0;i<lists.size();i++){
            if (lists.get(i).getSname().equals(lists.get(positon).getSname())){
                lists.get(i).setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
    }

    public void ItemUpdate(int position,ViewHolder viewHolder){
        ShopCartModel  shopCartModel=lists.get(position);
        if (shopCartModel.isChecked()){  //已经选择
            for (int i=0;i<lists.size();i++){
                if (lists.get(i).getSname().equals(lists.get(position).getSname()) && viewHolder.lv_shophead.getVisibility()==View.VISIBLE){
                    viewHolder.iv_shopcart_head_radio.setImageResource(R.mipmap.radio_normal);
                    viewHolder.iv_shopcart_item_radio.setCheck(false);
                }
            }
            lists.get(position).setChecked(false);
        }else {  //没有选择
            List <ShopCartModel> list=new ArrayList<>();
            List<ShopCartModel> list_selected=new ArrayList<>();
            for (int i=0;i<lists.size();i++){
                if (lists.get(i).getSname().equals(lists.get(position).getSname())){
                     list.add(lists.get(i));
                }
            }
            for (int j=0;j<list.size();j++){
                if (list.get(j).isChecked()){
                    list_selected.add(list.get(j));
                }
            }
            if (list.size()==(list_selected.size()+1)){
                viewHolder.iv_shopcart_head_radio.setImageResource(R.mipmap.radio_selected);
                viewHolder.iv_shopcart_item_radio.setCheck(true);
            }
            lists.get(position).setChecked(true);
        }
         notifyDataSetChanged();
    }



    public View ItemView(View view){
        view = View.inflate(context, R.layout.shopcart_item_layout, null);
        return view;
    }


    }
//
//    public View  HeadView(View view){
//        view=View.inflate(context, R.layout.shopcart_head_layout, null);
//        return view;
//    }





    class ViewHolder {
        public View rootView;
        public CheckImageView iv_shopcart_item_radio;
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
        public LinearLayout lv_shophead;
        public CheckImageView iv_shopcart_head_radio;
        public TextView iv_shopcart_head_titlel;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopcart_item_radio = (CheckImageView) rootView.findViewById(R.id.iv_shopcart_item_radio);
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
            this.lv_shophead= (LinearLayout) rootView.findViewById(R.id.lv_shophead);
            this.iv_shopcart_head_radio= (CheckImageView) rootView.findViewById(R.id.iv_shopcart_head_radio);
            this.iv_shopcart_head_titlel= (TextView) rootView.findViewById(R.id.iv_shopcart_head_title);

        }



}
