package com.chinayiz.chinayzy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.StoreTabRecylAdapter;
import com.chinayiz.chinayzy.base.ViewPagerFragment;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.StoreHomeTabModel;
import com.chinayiz.chinayzy.net.Commons;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/6/7 9:37
 * Class StoreTabFragment  商城页面
 */

public class StoreTabFragment extends ViewPagerFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    public static Gson mGson = new Gson();
    public static final String TAB_HOME_NAME = "首页";
    /**
     * 首页类型
     */
    public static final int TAB_TYPE_HOME = 66;
    /**
     * 主题类型
     */
    public static final int TAB_TYPE_THEME = 77;

    private RecyclerView recycleView;
    private BGARefreshLayout refresh_layout;
    private static StoreHomeTabModel.DataBean mMenuDatas;
    private StoreTabRecylAdapter mAdapter;
    private View ll_progress;

    private int tabType = -1;
    private String tabCode = "";
    private String tabName = "";
    private int currentPage = 0;

    private StoreTabFragment(int tabType, String itemCode, int currentPage, StoreHomeTabModel.DataBean data) {
        this.tabType = tabType;
        this.currentPage = currentPage;
        this.mMenuDatas = data;
        String[] strings = itemCode.split(",");
        this.tabCode = strings[0];
        this.tabName = strings[1];
    }

    public static StoreTabFragment getInstance(int tabType, String itemCode, int currentPage, StoreHomeTabModel.DataBean data) {
        return new StoreTabFragment(tabType, itemCode, currentPage, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_stotr_tab, container, false);
            initViews(rootView);
        }
        return rootView;
    }

    private void initViews(View rootView) {
        recycleView = (RecyclerView) rootView.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        recycleView.setLayoutManager(layoutManager);
        refresh_layout = (BGARefreshLayout) rootView.findViewById(R.id.refresh_layout);
        ll_progress = rootView.findViewById(R.id.ll_progress);
        refresh_layout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        // 设置下拉刷新和上拉加载更多的风格
        refresh_layout.setRefreshViewHolder(refreshViewHolder);

    }

    private void initData() {
        if (tabType == TAB_TYPE_HOME) {
            initHomeData();
        } else {
            initTabData();
        }
    }

    private void initHomeData() {
        mAdapter = new StoreTabRecylAdapter(this, tabType);
        mAdapter.addViewHolderType(0, 1, 2, 3, 3, 3, 3, 3);
        mAdapter.addVieHolderData(Commons.STORE_HOME_TABS, mMenuDatas.getTubiaolist());
        recycleView.setAdapter(mAdapter);
        getBanner();
        getMenuIcon();
        getTheme();
        getGoodsSet(1, "10");
    }

    private void initTabData() {
        mAdapter = new StoreTabRecylAdapter(this, tabType);
        mAdapter.addViewHolderType(4, 5, 3, 3, 3, 3, 3, 3);
        //后面数据为两个string类型 用 ，号连接，取值时需要进行分割
        String datas = mMenuDatas.getToplist().get(currentPage).getTypepic() + "," + mMenuDatas.getToplist().get(currentPage).getDetaillink();
        mAdapter.addVieHolderData(Commons.STORE_HOME_TABS, datas);
        recycleView.setAdapter(mAdapter);
        getTheme();
        getGoodsSet(1, "10");
    }

    private void getGoodsSet(int page, String size) {
        OkGo.post(Commons.API + Commons.STORE_HOME_GOODSS)
                .params("page", page)
                .params("size", size)
                .params("itemcode", tabCode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.i(s);
                        try {
                            mAdapter.addVieHolderData(Commons.STORE_HOME_GOODSS, mGson.fromJson(s, HomeHotGoodsModel.class));
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    private void getTheme() {
        OkGo.post(Commons.API + Commons.STORE_HOME_THEME)
                .params("itemcode", tabCode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Logger.i(s);
                        try {
                            mAdapter.addVieHolderData(Commons.STORE_HOME_THEME, mGson.fromJson(s, NY_EatThemeModel.class));
                            ll_progress.setVisibility(View.GONE);
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    private void getBanner() {
        OkGo.post(Commons.API + Commons.STORE_HOME_BANNER)
                .params("itemcode", tabCode)
                .execute(new com.chinayiz.chinayzy.utils.StrCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Logger.i(s);
                        try {
                            NY_BannerModel model = new Gson().fromJson(s, NY_BannerModel.class);
                            mAdapter.addVieHolderData(Commons.STORE_HOME_BANNER, model);
                        } catch (Exception e) {
                            onError(null, response, e);
                        }
                    }
                });
    }

    private void getMenuIcon() {
        mAdapter.addVieHolderData(Commons.STORE_HOME_TABS, mMenuDatas.getTubiaolist());
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refreshLayout.endRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        Logger.i("当前页面" + tabName + "是否显示=" + isVisible);
        if (isVisible) {
            if (mMenuDatas!=null) initData();
        }
    }


}
