package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.GoodsCollectModel;
import com.chinayiz.chinayzy.net.User.UserNet;

import java.util.List;

/**宝贝收藏
 * Created by Administrator on 2017/1/12.
 */

public class GoodsKeepAdaphter extends BaseInectAdaphter {
    public GoodsKeepAdaphter(Context context, List <GoodsCollectModel.DataBean> lists) {
        this.context=context;
        this.lists=lists;
    }



    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.goodskeep_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        GoodsCollectModel.DataBean bean= (GoodsCollectModel.DataBean) lists.get(i);
        Glide.with(context).load(bean.getIcon()).into( viewHolder.iv_goodskeep_imag);
        viewHolder.tv_goodskeep_title.setText(bean.getGoodsdesc());
        viewHolder.tv_goodskeep_price.setText(bean.getPrice());
        return view;
    }

    @Override
    public void onGetData(int pageindex) {
        UserNet.getNet().getshowGoodsCollect(pageindex+"","10");
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_goodskeep_imag;
        public TextView tv_goodskeep_title;
        public TextView tv_goodskeep_price;
        public LinearLayout lv_center;
        public ImageView iv_arrow_right;


        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_goodskeep_imag = (ImageView) rootView.findViewById(R.id.iv_goodskeep_imag);
            this.tv_goodskeep_title = (TextView) rootView.findViewById(R.id.tv_goodskeep_title);
            this.tv_goodskeep_price = (TextView) rootView.findViewById(R.id.tv_goodskeep_price);
            this.lv_center = (LinearLayout) rootView.findViewById(R.id.lv_center);
            this.iv_arrow_right = (ImageView) rootView.findViewById(R.id.iv_arrow_right);
        }
    }

}
