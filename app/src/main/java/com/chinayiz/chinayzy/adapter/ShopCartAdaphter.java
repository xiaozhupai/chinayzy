package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopCartAdaphter extends BaseAdapter implements SectionIndexer {
    private static final int TYPE_CATEGORY_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private static final String TAG = "ShopCartAdaphter";
    private CheckImageView iv_all;
    private TextView tv_shopcart_price;
    private Context context;
    private List<ShopCartModel.DataBean> lists;
    private TextView tv_shopcart_all;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_EDITER = 1;
    public int layout_type;
    public static final String POPUWINDOW="POPUWINDOW";
    public static final String UPDATE="ShopCartAdaphter";
    public  double total;


    public ShopCartAdaphter(Context context, List<ShopCartModel.DataBean> list, CheckImageView iv_all, TextView tv_shopcart_price, TextView tv_shopcart_all, int layout_type) {
        this.context = context;
        this.lists = list;
        this.iv_all = iv_all;
        this.tv_shopcart_price = tv_shopcart_price;
        this.tv_shopcart_all = tv_shopcart_all;
        this.layout_type = layout_type;
    }

    public void setData(List<ShopCartModel.DataBean> list,int layout_type) {
        this.lists = list;
        this.layout_type=layout_type;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != lists) {
            //  所有分类中item的总和是ListVIew  Item的总个数
            for (ShopCartModel.DataBean groups : lists) {
                count += groups.getShoplist().size() + 1;
            }
        }
        return count;
    }

    @Override
    public ShopCartModel.DataBean.ShoplistBean getItem(int position) {

        // 异常情况处理
        if (null == lists || position < 0 || position > getCount()) {
            return null;
        }

        // 同一分类内，第一个元素的索引值
        int categroyFirstIndex = 0;

        for (ShopCartModel.DataBean category : lists) {
            int size = category.getShoplist().size() + 1;
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            // item在当前分类内
            if (categoryIndex < size) {

                return category.getItem(categoryIndex);
            }

            // 索引移动到当前分类结尾，即下一个分类第一个元素索引
            categroyFirstIndex += size;
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if (null == lists || position < 0 || position > getCount()) {
            return TYPE_ITEM;
        }
        int categroyFirstIndex = 0;
        for (ShopCartModel.DataBean category : lists) {
            int size = category.getShoplist().size() + 1;
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            if (categoryIndex == 0) {
                return TYPE_CATEGORY_ITEM;
            }
            categroyFirstIndex += size;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != TYPE_CATEGORY_ITEM;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        final ShopCartModel.DataBean.ShoplistBean bean = getItem(i);
        switch (type) {
            case TYPE_ITEM:   //条目视图
                ViewHolder viewHolder = null;
                if (view == null) {
                    view = ItemView(view);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.iv_shopcart_item_radio.setOnClickListener(new View.OnClickListener() {  //ITEM点击事件
                    @Override
                    public void onClick(View v) {//ITEM radio事件
                        ItemUpdate(i);
                    }
                });
                if (bean.isChecked()) {  //判断item是否被选择
                    viewHolder.iv_shopcart_item_radio.setImageResource(R.mipmap.radio_selected);
                } else {
                    viewHolder.iv_shopcart_item_radio.setImageResource(R.mipmap.radio_normal);
                }

                if (bean.getNum()>= bean.getRepertory()){
                    viewHolder.iv_left_b.setBackgroundResource(R.mipmap.img_bg_right_unclickable);
                }else {
                    viewHolder.iv_left_b.setBackgroundResource(R.mipmap.img_bg_right);
                }
                if (layout_type == TYPE_EDITER) { //编辑后页面
                    viewHolder.lv_before.setVisibility(View.GONE);
                    viewHolder.lv_after.setVisibility(View.VISIBLE);
                    viewHolder.tv_standard.setText(bean.getStandardname()+bean.getStandardvalue());
                    viewHolder.tv_center.setText(bean.getNum()+"");
                    Logger.i("num-----------------------"+bean.getNum());

                    final ViewHolder finalViewHolder = viewHolder;
                    if (bean.getNum()==1){
                        finalViewHolder.iv_left.setBackgroundResource(R.mipmap.img_bg_left_unclickable);
                    }else {
                        finalViewHolder.iv_left.setBackgroundResource(R.mipmap.img_bg_left);
                    }
                    if (bean.getNum()>= bean.getRepertory()){
                        finalViewHolder.iv_right.setBackgroundResource(R.mipmap.img_bg_right_unclickable);
                    }else {
                        finalViewHolder.iv_right.setBackgroundResource(R.mipmap.img_bg_right);
                    }
                    viewHolder.iv_left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (finalViewHolder.iv_left.isClickable()){
                                if (bean.getNum()==1){
                                    BaseActivity.showToast(context,"宝贝数量不能低于一个");
                                    return;
                                }
                                if (bean.getNum()==2){
                                    finalViewHolder.iv_left.setBackgroundResource(R.mipmap.img_bg_left_unclickable);
                                    finalViewHolder.iv_left.setClickable(false);
                                }
                                if (bean.getNum()==bean.getRepertory()){
                                    finalViewHolder.iv_right.setBackgroundResource(R.mipmap.img_bg_right);
                                }
                                Logger.i("数量减少");
                                bean.setNum(bean.getNum()-1);
                                finalViewHolder.tv_center.setText(bean.getNum()+"");
                                Logger.i("num-----------------------"+bean.getNum());
                            }
                        }

                    });
                    viewHolder.iv_right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bean.getNum()>=bean.getRepertory()){
                                BaseActivity.showToast(context,"库存不足");
                                return;
                            }
                            if (bean.getNum()==1){
                                finalViewHolder.iv_left.setBackgroundResource(R.mipmap.img_bg_left);
                                finalViewHolder.iv_left.setClickable(true);
                            }
                            if (bean.getNum()==(bean.getRepertory()-1)){
                                finalViewHolder.iv_right.setBackgroundResource(R.mipmap.img_bg_right_unclickable);
//                                finalViewHolder.iv_left.setClickable(false);
                            }

                            Logger.i("数量增加");
                            bean.setNum(bean.getNum()+1);
                            finalViewHolder.tv_center.setText(bean.getNum()+"");
                            Logger.i("num-----------------------"+bean.getNum());
                        }
                    });
                    viewHolder.rl_standard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,POPUWINDOW,bean));
                            Logger.i("修改规格");

                        }
                    });
                } else { //编辑前页面
                    if (bean.getNum()==1){
                        viewHolder.iv_left_b.setBackgroundResource(R.mipmap.img_bg_left_unclickable);
                    }else {
                        viewHolder.iv_left_b.setBackgroundResource(R.mipmap.img_bg_left);
                    }
                    if (bean.getNum()>= bean.getRepertory()){
                        viewHolder.iv_right_b.setBackgroundResource(R.mipmap.img_bg_right_unclickable);
                    }else {
                        viewHolder.iv_right_b.setBackgroundResource(R.mipmap.img_bg_right);
                    }
                    viewHolder.lv_before.setVisibility(View.VISIBLE);
                    viewHolder.lv_after.setVisibility(View.GONE);
                    viewHolder.et_center_b.setText(bean.getNum()+"");
//                    viewHolder.tv_shopcart_item_num.setText("×" + bean.getNum());
                    viewHolder.tv_shopcart_item_price.setText("¥" + bean.getPrice());
                    viewHolder.tv_shopcart_item_title.setText(bean.getGname());
                    viewHolder.tv_shopcart_item_kind.setText(bean.getStandardname()+bean.getStandardvalue());
                    final ViewHolder finalViewHolder1 = viewHolder;
                    viewHolder.iv_left_b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (finalViewHolder1.iv_left_b.isClickable()){
                                if (bean.getNum()==1){
                                    BaseActivity.showToast(context,"宝贝数量不能低于一个");
                                    return;
                                }
                                if (bean.getNum()==2){
                                    finalViewHolder1.iv_left_b.setBackgroundResource(R.mipmap.img_bg_left_unclickable);
                                    finalViewHolder1.iv_left_b.setClickable(false);
                                }
                                if (bean.getNum()==bean.getRepertory()){
                                    finalViewHolder1.iv_right_b.setBackgroundResource(R.mipmap.img_bg_right);
                                }
                                Logger.i("数量减少");
                                bean.setNum(bean.getNum()-1);
                                finalViewHolder1.et_center_b.setText(bean.getNum()+"");
                                Logger.i("num-----------------------"+bean.getNum());
                                UpdateTotal();
                                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,UPDATE,""));
                            }
                        }
                    });
                    viewHolder.iv_right_b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bean.getNum()>=bean.getRepertory()){
                                BaseActivity.showToast(context,"库存不足");
                                return;
                            }
                            if (bean.getNum()==1){
                                finalViewHolder1.iv_left_b.setBackgroundResource(R.mipmap.img_bg_left);
                                finalViewHolder1.iv_left_b.setClickable(true);
                            }
                            if (bean.getNum()==(bean.getRepertory()-1)){
                                finalViewHolder1.iv_right_b.setBackgroundResource(R.mipmap.img_bg_right_unclickable);
//                                finalViewHolder.iv_left.setClickable(false);
                            }
                            Logger.i("数量增加");
                            bean.setNum(bean.getNum()+1);
                            finalViewHolder1.et_center_b.setText(bean.getNum()+"");

                            Logger.i("num-----------------------"+bean.getNum());
                            UpdateTotal();
                            EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,UPDATE,""));
                        }
                    });
                    finalViewHolder1.et_center_b.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus){
                               Logger.i("开始编辑数量");
                            }else {
                                Logger.i("取消编辑数量");
                            }
                        }
                    });
                    finalViewHolder1.et_center_b.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            finalViewHolder1.et_center_b.clearComposingText();
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Logger.i("正在修改数量="+s);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Logger.i("修改数量完成="+s);
                        }
                    });
                }

                Glide.with(context).load(bean.getIcon()).into(viewHolder.iv_shopcart_item_img);
                break;
            case TYPE_CATEGORY_ITEM: //头部视图
                ViewHolderHead viewHolderHead = null;
                if (null == view) {
                    view = View.inflate(context, R.layout.shopcart_head_layout, null);
                    viewHolderHead = new ViewHolderHead(view);
                    view.setTag(viewHolderHead);
                } else {
                    viewHolderHead = (ViewHolderHead) view.getTag();
                }
                 if (i!=0){
                     viewHolderHead.v_diver.setVisibility(View.VISIBLE);
                 }else {
                     viewHolderHead.v_diver.setVisibility(View.GONE);
                 }
                if (bean.isHeadChecked()) {
                    viewHolderHead.iv_shopcart_head_radio.setImageResource(R.mipmap.radio_selected);
                } else {
                    viewHolderHead.iv_shopcart_head_radio.setImageResource(R.mipmap.radio_normal);
                }
                viewHolderHead.iv_shopcart_head_title.setText(bean.getSname());
                viewHolderHead.iv_shopcart_head_radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HeadUpdate(i, !bean.isHeadChecked());
                    }
                });

                break;
        }
        return view;
    }

    public int getAllHead(){   //获得选中头部的个数
        List<ShopCartModel.DataBean.ShoplistBean> list_selected = new ArrayList<>();
        for (ShopCartModel.DataBean data : lists) {
            for (ShopCartModel.DataBean.ShoplistBean bean : data.getShoplist()) {
                if (bean.isHeadChecked()) {
                    list_selected.add(bean);
                }
            }
        }
        return  list_selected.size();
    }

    //ITEM更新
    public void ItemUpdate(int position) {
        ShopCartModel.DataBean.ShoplistBean shopCartModel = getItem(position);
        int section = getSectionForPosition(position);
        ShopCartModel.DataBean head = lists.get(section);
        List<ShopCartModel.DataBean.ShoplistBean> list_item = head.getShoplist();
        if (shopCartModel.isChecked()) {  //已经选择
            list_item.get(0).setHeadChecked(false);
            shopCartModel.setChecked(false);
            iv_all.setCheck(false);
        } else {  //没有选择
            List<ShopCartModel.DataBean.ShoplistBean> list_selected = new ArrayList<>();     //获得这个组子类所有被选择的对象
            for (int j = 0; j < list_item.size(); j++) {
                if (list_item.get(j).isChecked()) {
                    list_selected.add(list_item.get(j));
                }
            }
            if (list_item.size() == (list_selected.size() + 1)) {
                list_item.get(0).setHeadChecked(true);
            }
            shopCartModel.setChecked(true);
        }
        notifyDataSetChanged();
        UpdateBoomlayout(true);
    }

    //头部更新

    /**
     * @param positon   当前的位置
     * @param isChecked 这个头部是否被选择
     */
    public void HeadUpdate(int positon, boolean isChecked) {
        int section = getSectionForPosition(positon);
        ShopCartModel.DataBean data = lists.get(section);
        ShopCartModel.DataBean.ShoplistBean headbean = data.getShoplist().get(0);
        headbean.setHeadChecked(isChecked);
        for (ShopCartModel.DataBean.ShoplistBean bean : data.getShoplist()) {
            bean.setChecked(isChecked);
        }
        notifyDataSetChanged();
        UpdateBoomlayout(isChecked);
    }

    public void UpdateBottomLayout(){
        double total;
        List<ShopCartModel.DataBean.ShoplistBean> list_selected = new ArrayList<>();
        for (ShopCartModel.DataBean data : lists) {
            for (ShopCartModel.DataBean.ShoplistBean bean : data.getShoplist()) {
                if (bean.isChecked()) {
                    list_selected.add(bean);
                }
            }
        }
           UpdateTotal();
        tv_shopcart_all.setText("全选(" + list_selected.size() + ")");
    }

    //更新底部布局
    public void UpdateBoomlayout(boolean isChecked) {
        AllUpdate(isChecked);
        UpdateTotal();

        List<ShopCartModel.DataBean.ShoplistBean> list_selected = new ArrayList<>();
        for (ShopCartModel.DataBean data : lists) {
            for (ShopCartModel.DataBean.ShoplistBean bean : data.getShoplist()) {
                if (bean.isChecked()) {
                    list_selected.add(bean);
                }
            }
        }
        tv_shopcart_all.setText("全选(" + list_selected.size() + ")");
    }

    //更新总价格
    public void UpdateTotal() {
        double total = 0.00;
        for (ShopCartModel.DataBean data : lists) {
            for (ShopCartModel.DataBean.ShoplistBean bean : data.getShoplist()) {
                if (bean.isChecked()) {
                    total += bean.getPrice() * bean.getNum();
                    Logger.i("价格="+bean.getPrice()+"数量="+bean.getNum());
                }
            }
        }
        tv_shopcart_price.setText("￥"+String.format("%.2f",total));

    }

    //更新所有的check
    private void AllUpdate(boolean isChecked) {
        if (isChecked) {
            List<ShopCartModel.DataBean.ShoplistBean> list_isChecked = new ArrayList<>();
            for (ShopCartModel.DataBean data : lists) {
                for (ShopCartModel.DataBean.ShoplistBean bean : data.getShoplist()) {
                    if (bean.isChecked()) {
                        list_isChecked.add(bean);
                    }
                }
            }
            int size = 0;
            for (ShopCartModel.DataBean data : lists) {
                size += data.getShoplist().size();
            }
            if (list_isChecked.size() == size) {
                iv_all.setCheck(true);
                return;
            }
        }
        iv_all.setCheck(false);
    }



    public View ItemView(View view) {
        view = View.inflate(context, R.layout.shopcart_item_layout, null);
        return view;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    //通过组来获得位置
    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    //通过位置获得组
    @Override
    public int getSectionForPosition(int position) {
        int group = 0;
        int FirstPostion = 0;
        int LastPosition = 0;
        for (ShopCartModel.DataBean groups : lists) {
            int size = groups.getShoplist().size() + 1;
            FirstPostion=LastPosition;
            LastPosition += size;
            if (position < LastPosition) {
                if (position > FirstPostion || position == FirstPostion) {
                    return group;
                }
            }
            group++;
        }
        return 0;
    }

    class ViewHolderHead {
        public View rootView;
        public CheckImageView iv_shopcart_head_radio;
        public TextView iv_shopcart_head_title;
        public View v_diver;

        public ViewHolderHead(View rootView) {
            this.rootView = rootView;
            this.iv_shopcart_head_radio = (CheckImageView) rootView.findViewById(R.id.iv_shopcart_head_radio);
            this.iv_shopcart_head_title = (TextView) rootView.findViewById(R.id.iv_shopcart_head_title);
            this.v_diver=rootView.findViewById(R.id.v_diver);
        }

    }

    public static class ViewHolder {
        public View rootView;
        public CheckImageView iv_shopcart_item_radio;
        public ImageView iv_shopcart_item_img;
        public TextView tv_shopcart_item_title;
        public TextView tv_shopcart_item_kind;
        public TextView tv_shopcart_item_price;
//        public TextView tv_shopcart_item_num;
        public LinearLayout lv_before;
        public ImageView iv_left;
        public TextView tv_center;
        public ImageView iv_right;
        public TextView tv_standard;
        public RelativeLayout rl_standard;
        public LinearLayout lv_after;
        public ImageView iv_left_b;
        public ImageView iv_right_b;
        public EditText et_center_b;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopcart_item_radio = (CheckImageView) rootView.findViewById(R.id.iv_shopcart_item_radio);
            this.iv_shopcart_item_img = (ImageView) rootView.findViewById(R.id.iv_shopcart_item_img);
            this.tv_shopcart_item_title = (TextView) rootView.findViewById(R.id.tv_shopcart_item_title);
            this.tv_shopcart_item_kind = (TextView) rootView.findViewById(R.id.tv_shopcart_item_kind);
            this.tv_shopcart_item_price = (TextView) rootView.findViewById(R.id.tv_shopcart_item_price);
//            this.tv_shopcart_item_num = (TextView) rootView.findViewById(R.id.tv_shopcart_item_num);
            this.lv_before = (LinearLayout) rootView.findViewById(R.id.lv_before);
            this.iv_left = (ImageView) rootView.findViewById(R.id.iv_left);
            this.tv_center = (TextView) rootView.findViewById(R.id.tv_center);
            this.iv_right = (ImageView) rootView.findViewById(R.id.iv_right);
            this.tv_standard = (TextView) rootView.findViewById(R.id.tv_standard);
            this.rl_standard = (RelativeLayout) rootView.findViewById(R.id.rl_standard);
            this.lv_after = (LinearLayout) rootView.findViewById(R.id.lv_after);
         this.iv_left_b= (ImageView) rootView.findViewById(R.id.iv_left_b);
         this.iv_right_b= (ImageView) rootView.findViewById(R.id.iv_right_b);
          this.et_center_b= (EditText) rootView.findViewById(R.id.et_center_b);
        }

    }
}



