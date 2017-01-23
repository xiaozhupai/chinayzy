package com.chinayiz.chinayzy.adapter.viewHolder;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.NY_FeatureModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 16:55
 * Class NongYe_HomeFeature 生态农业首页特色购holder
 */
public class NY_HomeFeature extends RecyclerView.ViewHolder implements View.OnClickListener {
    private List<Holder> mGoods = new ArrayList<>(3);
    public NY_HomeFeature(View itemView) {
        super(itemView);
        mGoods.add(new Holder(null,(ImageView) itemView.findViewById(R.id.iv_themeIcon),null,null));
        mGoods.get(0).Icon.setOnClickListener(this);

        mGoods.add(new Holder(itemView.findViewById(R.id.lv_comgoods1)
                , (ImageView) itemView.findViewById(R.id.iv_comgoods1_icon)
                , (TextView) itemView.findViewById(R.id.tv_comgoods1_title)
                , (TextView) itemView.findViewById(R.id.tv_comgoods1_price)));
        mGoods.get(1).view.setOnClickListener(this);

        mGoods.add(new Holder(itemView.findViewById(R.id.lv_comgoods2)
                , (ImageView) itemView.findViewById(R.id.iv_comgoods2_icon)
                , (TextView) itemView.findViewById(R.id.tv_comgoods2_title)
                , (TextView) itemView.findViewById(R.id.tv_comgoods2_price)));
        mGoods.get(2).view.setOnClickListener(this);

        mGoods.add(new Holder(itemView.findViewById(R.id.lv_comgoods3)
                , (ImageView) itemView.findViewById(R.id.iv_comgoods3_icon)
                , (TextView) itemView.findViewById(R.id.tv_comgoods3_title)
                , (TextView) itemView.findViewById(R.id.tv_comgoods3_price)));
        mGoods.get(3).view.setOnClickListener(this);

    }

    public void setData(NY_FeatureModel ny_featureModel, Fragment fragment ,boolean isLoad) {
        List<NY_FeatureModel.DataBean> datas = ny_featureModel.getData();
        Logger.i("特色商品有："+datas.size());
        NY_FeatureModel.DataBean dataBean;
        Holder holder;
        if (isLoad){
            Logger.i("第一次加载AAAAAAAAAAAA");
            for (int i = 0; i < 4 ; i++) {
                holder = mGoods.get(i);
                dataBean = datas.get(i);
                if (dataBean.getType().equals("1")) {//主题类型
                    Glide.with(fragment).load(dataBean.getThemepic()).into(mGoods.get(0).Icon);
                } else if (i!=0){//商品类型
                    holder.Title.setText(dataBean.getGname());
                    holder.Price.setText(dataBean.getPrice());
                    Glide.with(fragment).load(dataBean.getIcon()).into(holder.Icon);
                }
            }
        }else {
            Logger.i("第二次加载BBBBBBBBBBBBB");
            for (int i = 4; i<datas.size() ; i++) {
                dataBean = datas.get(i);
                holder = mGoods.get(i%4);
                if (dataBean.getType().equals("1")) {//主题类型
                    Glide.with(fragment).load(dataBean.getThemepic()).into(mGoods.get(0).Icon);
                } else if (i!=0){//商品类型
                    holder.Title.setText(dataBean.getGname());
                    holder.Price.setText(dataBean.getPrice());
                    Glide.with(fragment).load(dataBean.getIcon()).into(holder.Icon);
                }
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_themeIcon://特色购主题宣传图
                Logger.i("特色购主题宣传图");
                break;
            case R.id.lv_comgoods1://商品1
                Logger.i("商品1");
                break;
            case R.id.lv_comgoods2://商品2
                Logger.i("商品2");
                break;
            case R.id.lv_comgoods3://商品3
                Logger.i("商品3");
                break;
        }
    }

    private class Holder {
        public View view;
        public ImageView Icon;
        public TextView Title;
        public TextView Price;

        public Holder(View view, ImageView icon, TextView title, TextView price) {
            this.view = view;
            Icon = icon;
            Title = title;
            Price = price;
        }
    }
}
