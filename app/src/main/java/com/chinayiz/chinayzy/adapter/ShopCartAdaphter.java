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
import com.chinayiz.chinayzy.entity.response.ShopCartModel.ShopCartBean;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  购物车Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopCartAdaphter extends BaseInectAdaphter<ShopCartBean> {
    public static final int HEAD=0;
    public static final int ITEM=1;
    private static  final String TAG="ShopCartAdaphter";
    private CheckImageView iv_all;
    private TextView tv_shopcart_all;
    private Map<String,ShopCartBean> map=new HashMap<>();

    public ShopCartAdaphter(Context context, List<ShopCartBean> list,CheckImageView iv_all,TextView tv_shopcart_all) {
        this.context=context;
        this.lists=list;
        this.iv_all=iv_all;
        this.tv_shopcart_all=tv_shopcart_all;
    }

    public void setData(List<ShopCartBean> list){
        this.lists=list;
        for (int i=0;i<list.size();i++){
            if (i==0){
                list.get(i).setHead(true);
            }else {
                if (!list.get(i).getSname().equals(list.get(i - 1).getSname())) {
                    list.get(i).setHead(true);
                    Logger.i("头部视图");
                } else {
                    list.get(i).setHead(false);
                    Logger.i("body视图");
                }
            }
        }
        notifyDataSetChanged();
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
        final ShopCartBean shopCartModel=lists.get(i);
        if (shopCartModel.isHead()){  //是否是头部
            if (shopCartModel.isHeadChecked()){   //头部是否被选择
                viewHolder.iv_shopcart_head_radio.setImageResource(R.mipmap.radio_selected);
            }else {
                viewHolder.iv_shopcart_head_radio.setImageResource(R.mipmap.radio_normal);
            }
            viewHolder.lv_shophead.setVisibility(View.VISIBLE);
            viewHolder.iv_shopcart_head_titlel.setText(shopCartModel.getSname());
        }else {
            viewHolder.lv_shophead.setVisibility(View.GONE);
        }


        viewHolder.iv_shopcart_head_radio.setOnClickListener(new View.OnClickListener() { //头部点击事件
            @Override
            public void onClick(View v) { //头部radio事件
                if (shopCartModel.isHeadChecked()){
                    HeadUpdate(i,false);
                }else {
                    HeadUpdate(i,true);
                }
            }
        });

        viewHolder.iv_shopcart_item_radio.setOnClickListener(new View.OnClickListener() {  //ITEM点击事件
            @Override
            public void onClick(View v) {//ITEM radio事件
                if (shopCartModel.isChecked()){
                    ItemUpdate(i);
                }else {
                    ItemUpdate(i);
                }
            }
        });
        if (shopCartModel.isChecked()){  //判断item是否被选择
            viewHolder.iv_shopcart_item_radio.setImageResource(R.mipmap.radio_selected);
        }else {
            viewHolder.iv_shopcart_item_radio.setImageResource(R.mipmap.radio_normal);
        }
        viewHolder.tv_shopcart_item_num.setText("×"+shopCartModel.getNum());
        viewHolder.tv_shopcart_item_price.setText("￥"+shopCartModel.getPrice());
        viewHolder.tv_shopcart_item_title.setText(shopCartModel.getGname());
        viewHolder.tv_shopcart_item_kind.setText(shopCartModel.getStandardname());
//        viewHolder.iv_shopcart_item_img
        return view;
    }

    //头部更新
    /**
     *
     * @param positon   当前的位置
     * @param isChecked  这个头部是否被选择
     */
    public void HeadUpdate(int positon,boolean isChecked){
        ShopCartBean  shopCartModel=lists.get(positon);
        shopCartModel.setHeadChecked(isChecked);
        for (int i=0;i<lists.size();i++){
            if (lists.get(i).getSname().equals(lists.get(positon).getSname())){
                lists.get(i).setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
        AllUpdate(isChecked);
        double total=UpdateTotal();
        tv_shopcart_all.setText(total+"");
    }

    public double UpdateTotal(){
        double total=0.00;
        for (ShopCartBean bean:lists){
            if (bean.isChecked()){
                total+=bean.getPrice()*bean.getNum();
            }
        }
        return total;
    }

    //将已经选择的放在一个集合里面
    private void AllUpdate(boolean isChecked){
        if (isChecked){
            List <ShopCartBean> list_isChecked=new ArrayList<>();
            for (ShopCartBean bean:lists){
                if (bean.isChecked()){
                    list_isChecked.add(bean);
                }
            }
            if (list_isChecked.size()==lists.size()){
                iv_all.setCheck(true);
                return;
            }
        }
        iv_all.setCheck(false);

    }

    //ITEM更新
    public void ItemUpdate(int position){
        ShopCartBean  shopCartModel=lists.get(position);
        if (shopCartModel.isChecked()){  //已经选择
            for (int i=0;i<lists.size();i++){
                if (lists.get(i).getSname().equals(lists.get(position).getSname()) && lists.get(i).isHead()){
                    lists.get(i).setHeadChecked(false);
                }
            }
            lists.get(position).setChecked(false);
            iv_all.setCheck(false);
        }else {  //没有选择
            List <ShopCartBean> list=new ArrayList<>();  //将这个子类相同的组放到一个集合
            List<ShopCartBean> list_selected=new ArrayList<>();     //获得这个组子类所有被选择的对象
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
                for (int i=0;i<lists.size();i++){
                    if (lists.get(i).getSname().equals(lists.get(position).getSname()) && lists.get(i).isHead()){
                        lists.get(i).setHeadChecked(true);
                    }
                }
            }
            lists.get(position).setChecked(true);
        }
        notifyDataSetChanged();
        AllUpdate(true);
        double total=UpdateTotal();
        tv_shopcart_all.setText(total+"");
    }



    public View ItemView(View view){
        view = View.inflate(context, R.layout.shopcart_item_layout, null);
        return view;
    }

}

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
