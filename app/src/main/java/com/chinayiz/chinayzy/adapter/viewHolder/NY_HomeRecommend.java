package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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
    private List<ImageView> mRecommendViews = new ArrayList<>(6);

    public NY_HomeRecommend(View itemView) {
        super(itemView);
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend1));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend2));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend3));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend4));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend5));
        mRecommendViews.add((ImageView) itemView.findViewById(R.id.iv_recommend6));
    }
    public void setData(NY_RecommentModel data, Fragment fragment) {
        List<NY_RecommentModel.DataBean.ThemelistBean> themelistBeen=data.getData().getThemelist();
        for (int i = 0; i < themelistBeen.size(); i++) {
            Glide.with(fragment)
                    .load(themelistBeen.get(i).getPic())
                    .into(mRecommendViews.get(i));
            mRecommendViews.get(i).setTag(themelistBeen.get(i).getDetaillink());
            mRecommendViews.get(i).setOnClickListener(this);
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_recommend1:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend2:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend3:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend4:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend5:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;
            case R.id.iv_recommend6:
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_GOODS,v.getTag()));
                break;

        }
    }

}
