package com.chinayiz.chinayzy.adapter.viewHolder;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 16:54
 * Class NongYe_HomeRecommend 生态农业首页精选推荐holder
 */
public class NY_HomeRecommend extends RecyclerView.ViewHolder implements View.OnClickListener {
    private List<ImageView> mRecommendViews = new ArrayList<>(5);
    private List<Holder> mComgoods = new ArrayList<>(3);

    public NY_HomeRecommend(View itemView) {
        super(itemView);
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend_Item1));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend_Item2));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend_Item3));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend_Item4));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend_Item5));
        mRecommendViews.get(0).setOnClickListener(this);
        mRecommendViews.get(1).setOnClickListener(this);
        mRecommendViews.get(2).setOnClickListener(this);
        mRecommendViews.get(3).setOnClickListener(this);
        mRecommendViews.get(4).setOnClickListener(this);


        mComgoods.add(new Holder(itemView.findViewById(R.id.lv_comgoods1)
                , (ImageView) itemView.findViewById(R.id.iv_comgoods1_icon)
                , (TextView) itemView.findViewById(R.id.tv_comgoods1_title)
                , (TextView) itemView.findViewById(R.id.tv_comgoods1_price)));
        mComgoods.get(0).view.setOnClickListener(this);

        mComgoods.add(new Holder(itemView.findViewById(R.id.lv_comgoods2)
                , (ImageView) itemView.findViewById(R.id.iv_comgoods2_icon)
                , (TextView) itemView.findViewById(R.id.tv_comgoods2_title)
                , (TextView) itemView.findViewById(R.id.tv_comgoods2_price)));
        mComgoods.get(1).view.setOnClickListener(this);

        mComgoods.add(new Holder(itemView.findViewById(R.id.lv_comgoods3)
                , (ImageView) itemView.findViewById(R.id.iv_comgoods3_icon)
                , (TextView) itemView.findViewById(R.id.tv_comgoods3_title)
                , (TextView) itemView.findViewById(R.id.tv_comgoods3_price)));
        mComgoods.get(2).view.setOnClickListener(this);

    }

    public void setData(NY_RecommentModel data, Fragment fragment) {
        List<NY_RecommentModel.DataBean.ThemelistBean> themelistBeen=data.getData().getThemelist();
        List<NY_RecommentModel.DataBean.RecommentlistBean> recommentlistBeen=data.getData().getRecommentlist();
        Logger.i("推荐主题图有："+themelistBeen.size());
        Logger.i("推荐商品有："+recommentlistBeen.size());
        Holder holder;
       for (int i=0;i< data.getData().getThemelist().size();i++ ){
           Glide.with(fragment)
                   .load(themelistBeen.get(i).getPic())
                   .into(mRecommendViews.get(i));
       }
        for (int i=0;i< data.getData().getRecommentlist().size();i++ ){
            holder=mComgoods.get(i);
            Glide.with(fragment)
                    .load(recommentlistBeen.get(i).getIcon())
                    .into(holder.icon);
            holder.name.setText(recommentlistBeen.get(i).getGname());
            holder.price.setText(recommentlistBeen.get(i).getPrice());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_recommend_Item1:
                Logger.i("点击了item1");
                break;
            case R.id.iv_recommend_Item2:
                Logger.i("点击了item2");
                break;
            case R.id.iv_recommend_Item3:
                Logger.i("点击了item3");
                break;
            case R.id.iv_recommend_Item4:
                Logger.i("点击了item4");
                break;
            case R.id.iv_recommend_Item5:
                Logger.i("点击了item5");
                break;
            case R.id.lv_comgoods1:
                Logger.i("点击了item6");
                break;
            case R.id.lv_comgoods2:
                Logger.i("点击了item7");
                break;
            case R.id.lv_comgoods3:
                Logger.i("点击了item8");
                break;
        }
    }
    private class Holder {
        public Holder(View view, ImageView icon, TextView name, TextView price) {
            this.view = view;
            this.icon = icon;
            this.name = name;
            this.price = price;
        }
        View view;
        ImageView icon;
        TextView name;
        TextView price;
    }
}