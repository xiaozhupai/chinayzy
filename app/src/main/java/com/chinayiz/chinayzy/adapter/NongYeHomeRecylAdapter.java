package com.chinayiz.chinayzy.adapter;


import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeBanner;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeFeature;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeRecommend;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_HomeTypeMenu;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_Home_EatItem;
import com.chinayiz.chinayzy.adapter.viewHolder.NY_Home_EatTheme;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.NY_EatItemModel;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.NY_FeatureModel;
import com.chinayiz.chinayzy.entity.response.NY_RecommentModel;

import java.util.HashMap;
import java.util.Map;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 15:57
 * Class NongYe_HomeRecyl_Adapter 农业板块首页RecycleView适配器
 */
public class NongYeHomeRecylAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 点击商品flag
     */
    public static final String CLICK_GOODS = "HomeRecylAdapter_CLICK";
    /**
     * 点击主题flag
     */
    public static final String CLICK_THEME = "HomeRecylAdapter_THEME";
    /**
     * 点击分类菜单flag
     */
    public static final String CLICK_MENU = "HomeRecylAdapter_MENU";
    /**
     * 动态获取数据flag
     */
    public static final String GTE_DATA = "GETDATA";
    /**
     * 获取数据成功标志flag
     */
    public static final String REFRESH = "REFRESH_DATA";
    private Fragment mFragment;
    private boolean isSetBanner = true;
    private static final int BANNER_TYPE = 0;//轮播图
    private static final int MENU_TYPE = 1;//类型菜单
    private static final int RECOMMEND_TYPE = 2;//精选推荐
    private static final int FEATURE_TYPE = 3;//特色购
    private static final int LOVE_ATE_TYPE = 4;//爱吃主题
    private static final int LOVE_ITEM_TYPE = 5;//爱吃item

    /**
     * RecyclerView 填充数据
     */
    private Map<Integer, Object> mDates = new HashMap<>();
    private NY_HomeBanner mBanner;
    private NY_HomeRecommend mRecommend;
    private NY_HomeFeature mFeature1;
    private NY_HomeFeature mFeature2;
    private NY_Home_EatTheme mLoveEat;
    private NY_Home_EatItem mEatItem;

    /**
     * 构造适配器
     */
    public NongYeHomeRecylAdapter(Fragment fragment) {
        this.mFragment = fragment;
        mDates.put(0,10);
    }

    public void setData(int key, Object object) {
        mDates.put(key,object);
        if(key>10){
            mDates.put(0,key+1);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER_TYPE://返回banner图
                return new NY_HomeBanner(getViewType(parent, R.layout.nongye_home_banner));
            case MENU_TYPE://返回类型菜单
                return new NY_HomeTypeMenu(getViewType(parent, R.layout.nongye_home_teypmenu));
            case RECOMMEND_TYPE://返回精选推荐
                return new NY_HomeRecommend(getViewType(parent, R.layout.nongye_home_recommend));
            case FEATURE_TYPE://返回特色购
                return new NY_HomeFeature(getViewType(parent, R.layout.nongye_home_feature));
            case LOVE_ATE_TYPE://返回爱吃主题
                return new NY_Home_EatTheme(getViewType(parent, R.layout.nongye_home_loveeat));
            default://返回爱吃item
                return new NY_Home_EatItem(getViewType(parent, R.layout.nongye_home_loveeat_item));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            switch (position) {
                case 0://banner图
                    if (mDates.containsKey(1) && isSetBanner) {
                        mBanner = (NY_HomeBanner) holder;
                        mBanner.setData((NY_BannerModel) mDates.get(1));
                        isSetBanner = false;
                    }
                    break;
                case 1://类型菜单
                    break;
                case 2://精选推荐
                    if (mDates.containsKey(2)) {
                        mRecommend = (NY_HomeRecommend) holder;
                        NY_RecommentModel model = (NY_RecommentModel) mDates.get(2);
                        mRecommend.setData(model, mFragment);
                    }
                    break;
                case 3://特色购
                    if (mDates.containsKey(3)) {
                        mFeature1 = (NY_HomeFeature) holder;
                        mFeature1.setData((NY_FeatureModel) mDates.get(3), mFragment, true);
                    }
                    break;
                case 4://特色购
                    if (mDates.containsKey(3)) {
                        mFeature2 = (NY_HomeFeature) holder;
                        mFeature2.setData((NY_FeatureModel) mDates.get(3), mFragment, false);
                    }
                    break;
                case 5://爱吃主题
                    if (mDates.containsKey(4)) {
                        mLoveEat = (NY_Home_EatTheme) holder;
                        mLoveEat.setData((NY_EatThemeModel) mDates.get(4), mFragment);
                    }
                    break;
                default://爱吃item
                    if (mDates.containsKey(position)) {
                        mEatItem = (NY_Home_EatItem) holder;
                        mEatItem.setData((NY_EatItemModel.DataBean) mDates.get(position), mFragment);
                    }
                    break;
            }

        }catch (Exception e){
            new RuntimeException("首页数据异常");
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0://返回banner图
                return BANNER_TYPE;
            case 1://返回类型菜单
                return MENU_TYPE;
            case 2://返回精选推荐
                return RECOMMEND_TYPE;
            case 3://返回特色购
                return FEATURE_TYPE;
            case 4://返回特色购
                return FEATURE_TYPE;
            case 5://返回爱吃主题
                return LOVE_ATE_TYPE;
            default://返回爱吃item
                return LOVE_ITEM_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return Integer.valueOf(mDates.get(0).toString());
    }

    /**
     * 填充不同类型 item 布局
     *
     * @param parent
     * @param resource R资源
     * @return
     */
    private View getViewType(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }

}
