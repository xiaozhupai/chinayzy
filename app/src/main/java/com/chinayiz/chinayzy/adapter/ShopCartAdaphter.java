package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.Spinner;
import android.widget.TextView;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.orhanobut.logger.Logger;
import java.util.List;

/**  购物车Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopCartAdaphter extends BaseInectAdaphter<ShopCartModel> {
    public static final int HEAD=0;
    public static final int ITEM=1;
    public ShopCartAdaphter(Context context, List<ShopCartModel> list) {
        this.context=context;
        this.lists=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            if (i==0){
                Logger.i("position=0");
                view=HeadView(view);
            }else {
                if (!lists.get(i).getSname().equals(lists.get(i-1).getSname())){
                    view=HeadView(view);
                    Logger.i("头部视图");
                }else {
                    view=ItemView(view);
                    Logger.i("body视图");
                }
            }
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return HEAD;
        }else {
            if (!lists.get(position).getSname().equals(lists.get(position-1).getSname())) {
                return HEAD;
            }else {
                return ITEM;
            }
        }

    }

    public View ItemView(View view){
        view = View.inflate(context, R.layout.shopcart_item_layout, null);
        return view;
    }

    public View  HeadView(View view){
        view=View.inflate(context, R.layout.shopcart_head_layout, null);
        return view;
    }


//
//    @Override
//    public Object[] getSections() {
//        return null;
//    }
//
//    @Override
//    public int getPositionForSection(int sectionIndex) {
////        for (int i = 0; i < lists.size(); i++) {
////            String l = stringArray.get(i);
////            char firstChar = l.toUpperCase().charAt(0);
////            if (firstChar == sectionIndex) {
////                return i;
////            }
////        }
////        return -1;
//        return 0;
//    }
//
//    @Override
//    public int getSectionForPosition(int position) {
//        return 0;
//    }


    public static class ViewHolder {
        public View rootView;
        public ImageView iv_shopcart_item_radio;
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

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopcart_item_radio = (ImageView) rootView.findViewById(R.id.iv_shopcart_item_radio);
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
        }

    }

}
