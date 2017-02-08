package com.chinayiz.chinayzy.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 16:52
 * Class NongYeBanner 生态农业首页Banner图holder
 */
public class NY_HomeBanner extends RecyclerView.ViewHolder implements View.OnClickListener, OnItemClickListener {
    public ImageView mIvSearch;
    public ConvenientBanner mBannerNongyeHome;
    public List<String> mUrls = new ArrayList<>();
    public static final String CLICK_SEARCH="NY_HomeBanner";

    public NY_HomeBanner(View itemView) {
        super(itemView);
        mIvSearch = (ImageView) itemView.findViewById(R.id.iv_search);
        mIvSearch.setOnClickListener(this);
        mBannerNongyeHome = (ConvenientBanner)
                itemView.findViewById(R.id.banner_nongyeHome);
        //设置指示器（小圆点）
        mBannerNongyeHome.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        mBannerNongyeHome.setOnItemClickListener(this);
    }

    public void setData(NY_BannerModel model) {
        Logger.i("广告图有："+model.getData().size());
        for (NY_BannerModel.Data data : model.getData()) {
            mUrls.add(data.getShowlink());
        }
        mBannerNongyeHome.setPages(new CreateBannerHolder(), mUrls);
        mBannerNongyeHome.startTurning(1500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                Logger.i("搜索");
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_SEARCH,""));
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
        Logger.i("点击了页面" + position);
    }
}
