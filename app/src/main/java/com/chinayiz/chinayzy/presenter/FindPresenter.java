package com.chinayiz.chinayzy.presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.chinayiz.chinayzy.adapter.PagerAdaphter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.FindTypeModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.find.FindFragment;
import com.chinayiz.chinayzy.ui.fragment.find.FindListFragment;
import com.chinayiz.chinayzy.utils.magicindicator.ViewPagerHelper;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.UIUtil;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.chinayiz.chinayzy.utils.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class FindPresenter  extends BasePresenter<FindFragment>{
    private Net net=Net.getNet();
    private List<String> titles=new ArrayList<>();
    @Override
    public void onCreate() {
        net.getFindType();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getDataType() == Commons.FINDTYPE) {//网络请求回调消息
            Logger.i("网络请求回调消息" + message.toString());
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            FindTypeModel model= (FindTypeModel) message.getData();
              titles.clear();
            List <Fragment> lists=new ArrayList<>();
            for (FindTypeModel.DataBean bean: model.getData()){
                FindListFragment fragment=new FindListFragment(bean.getType());
                lists.add(fragment);
                titles.add(bean.getTypename());
            }
            CommonNavigator commonNavigator = new CommonNavigator(mView.getActivity());
            commonNavigator.setAdjustMode(true);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {

                @Override
                public int getCount() {
                    return titles.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                    SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                    simplePagerTitleView.setTextSize(14);
                    simplePagerTitleView.setNormalColor(Color.parseColor("#0b1b01"));
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#6db430"));
                    simplePagerTitleView.setText(titles.get(index));

                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                         mView.vp_find.setCurrentItem(index);
                        }
                    });
                    badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                    return badgePagerTitleView;
                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);

                    linePagerIndicator.setColors(Color.parseColor("#6db430"));
                    return linePagerIndicator;
                }
            });
           mView.magic_indicator.setNavigator(commonNavigator);
            LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
            titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            titleContainer.setDividerDrawable(new ColorDrawable() {
                @Override
                public int getIntrinsicWidth() {
                    return UIUtil.dip2px(mView.getActivity(), 15);
                }
            });
            ViewPagerHelper.bind(mView.magic_indicator,mView.vp_find);

            mView.vp_find.setAdapter(new PagerAdaphter(mView.getChildFragmentManager(),lists));
            mView.vp_find.setOffscreenPageLimit(lists.size());
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }
}
