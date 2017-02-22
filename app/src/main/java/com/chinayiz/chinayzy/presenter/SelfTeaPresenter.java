package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.chinayiz.chinayzy.adapter.GoodsGridAdapter;
import com.chinayiz.chinayzy.adapter.MyNavigatorAdapter;
import com.chinayiz.chinayzy.adapter.SelfTeaPagerAdapter;
import com.chinayiz.chinayzy.adapter.viewHolder.CreateBannerHolder;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.TeaListModel;
import com.chinayiz.chinayzy.entity.response.TypeListModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.chinayiz.chinayzy.ui.fragment.SelfTeaFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/9 11:58
 * Class SelfTeaPresenter 自营茶叶Presenter
 */
public class SelfTeaPresenter extends BasePresenter<SelfTeaFragment> implements
        PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private int mCurrentType;
    private static int mSum=0;
    private List<GridView> mGridViews;
    public SelfTeaPagerAdapter mPagerAdapter;
    private MyNavigatorAdapter mNavigatorAdapter;
    private TypeListModel mTypeListModel;
    private TeaListModel mTeaListModel;
    public Net mNet = Net.getNet();
    public PullToRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate() {
        mPagerAdapter=new SelfTeaPagerAdapter();
        mNet.getBanner(Contants.TEA_BANNER);
        mNet.getTeaCode();
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Contants.TEA_BANNER: {
                NY_BannerModel model = (NY_BannerModel) message.getData();
                List<String> mUrls = new ArrayList<>();
                List<NY_BannerModel.Data> mBanner = model.getData();
                for (NY_BannerModel.Data data : mBanner) {
                    mUrls.add(data.getShowlink());
                }
                mView.mBannerSelfTea.setPages(new CreateBannerHolder(), mUrls);
                mView.mBannerSelfTea.startTurning(1500);
                break;
            }
            case Contants.TEA_TYPELIST: {
                mView.mVpTeaList.addOnPageChangeListener(mView);
                mTypeListModel = (TypeListModel) message.getData();
                mView.mNavigator.setAdjustMode(true);
                mNavigatorAdapter=new MyNavigatorAdapter(mTypeListModel.getData());
                mNavigatorAdapter.setTitleViewClickListener(mView);
                mView.mNavigator.setAdapter(mNavigatorAdapter);
                mView.mIndxMagicIndicator.setNavigator(mView.mNavigator);
                loaderPager(mTypeListModel.getData());
                break;
            }
            case Contants.TEA_TEALIST: {
                mTeaListModel = (TeaListModel) message.getData();
                if (mSum<mCurrentType){
                    Logger.i("设置适配器：="+mSum);
                    mGridViews.get(mSum)
                            .setAdapter(new GoodsGridAdapter(mView.getContext(), mTeaListModel.getData()));
                    mPagerAdapter.setGridViews(mGridViews);
                    mPagerAdapter.notifyDataSetChanged();
                    mView.mVpTeaList.setAdapter(mPagerAdapter);
                    mSum++;
                }
                break;
            }
            case "onRefresh": {
                Logger.i("刷新成功！");
                mRefreshLayout = (PullToRefreshLayout) message.getData();
                mRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                break;
            }
        }
    }

    private void loaderPager(List<TypeListModel.DataBean> typeList) {
        if (typeList.size() == 0) return;
        mGridViews = new ArrayList<>(typeList.size());
        mCurrentType = typeList.size();
        for (int i = 0; i < typeList.size(); i++) {
            mGridViews.add(buildGridView());
        }
        for (int x=0;x<typeList.size();x++){
            Logger.i("类型"+typeList.get(x).getItemcode());
            mNet.getTeaList("1", "10", typeList.get(x).getItemcode());
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }
    private GridView buildGridView() {
        GridView gridView = new GridView(mView.getActivity());
        gridView.setLayoutParams(new ViewGroup.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        gridView.setOnItemClickListener(this);
        gridView.setNumColumns(2);
        gridView.setHorizontalSpacing(2);
        gridView.setVerticalSpacing(2);
        return gridView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Logger.i("申请刷新");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT, "onRefresh", pullToRefreshLayout));
            }
        }).start();
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Logger.i("申请加载");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT, "onRefresh", pullToRefreshLayout));

            }
        }).start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    @Override
    protected void onDestroy() {
        mNet = null;
    }
}
