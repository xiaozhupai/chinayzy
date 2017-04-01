package com.chinayiz.chinayzy.adapter.viewHolder;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 17:01
 * Class NongYe_Home_LoveEat 生态农业首页爱吃主题
 */
public class NY_Home_EatTheme extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView mIvFeatureIcon;

    public NY_Home_EatTheme(View itemView) {
        super(itemView);
        mIvFeatureIcon = (ImageView) itemView.findViewById(R.id.iv_themeIcon);
        mIvFeatureIcon.setOnClickListener(this);
    }
    public void setData(NY_EatThemeModel data, Fragment fragment) {
        Glide.with(fragment).load(data.getData().get(0).getPic())
                .into(mIvFeatureIcon);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new EventMessage(BaseMessage.INFORM_EVENT, NongYeHomeRecylAdapter.CLICK_GOODS,"46"));
    }
}
