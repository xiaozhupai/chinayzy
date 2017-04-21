package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter.CLICK_GOODS;

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
        Holder holder;
       for (int i=0;i< themelistBeen.size();i++ ){
           Glide.with(fragment)
                   .load(themelistBeen.get(i).getPic())
                   .into(mRecommendViews.get(i));
           mRecommendViews.get(i).setTag(themelistBeen.get(i).getDetaillink());
            if ("1".equals(themelistBeen.get(i).getType())){// 1为主题

            }else if ("2".equals(themelistBeen.get(i).getType())){// 2为商品

            }
       }
        for (int i=0;i< recommentlistBeen.size();i++ ){
            holder=mComgoods.get(i);
            Glide.with(fragment)
                    .load(recommentlistBeen.get(i).getIcon())
                    .into(holder.icon);
            holder.name.setText(recommentlistBeen.get(i).getGname());
            String price=recommentlistBeen.get(i).getPrice();
            if (price.contains("-")) {
                String [] strings=price.split("-");
                holder.price.setText(" "+strings[0]);
            }else {
                holder.price.setText(" "+price);
            }
            if ("0".equals(recommentlistBeen.get(i).getIsself())) {
                holder.price.setCompoundDrawables(null,null,null,null);
            }
            //设置商品ID
            holder.view.setTag(recommentlistBeen.get(i).getGoodsid());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_recommend_Item1:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend_Item2:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend_Item3:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend_Item4:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend_Item5:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.lv_comgoods1:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.lv_comgoods2:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.lv_comgoods3:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
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
