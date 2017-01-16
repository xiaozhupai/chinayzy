package com.chinayiz.chinayzy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.NongYe_HomeBanner;
import com.chinayiz.chinayzy.adapter.viewHolder.NongYe_HomeFeature;
import com.chinayiz.chinayzy.adapter.viewHolder.NongYe_HomeRecommend;
import com.chinayiz.chinayzy.adapter.viewHolder.NongYe_HomeTypeMenu;
import com.chinayiz.chinayzy.adapter.viewHolder.NongYe_Home_EatItem;
import com.chinayiz.chinayzy.adapter.viewHolder.NongYe_Home_LoveEat;

import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 15:57
 * Class NongYe_HomeRecyl_Adapter 农业板块首页RecycleView适配器
 */
public class NongYe_HomeRecyl_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int BANNER_TYPE = 0;//轮播图
    private static final int MENU_TYPE = 1;//类型菜单
    private static final int RECOMMEND_TYPE = 2;//精选推荐
    private static final int FEATURE_TYPE = 3;//特色购
    private static final int LOVE_ATE_TYPE = 4;//爱吃主题
    private static final int LOVE_ITEM_TYPE = 5;//爱吃item
    private List<Object> mDates;
    /**
     * @param date 数据
     */
    public NongYe_HomeRecyl_Adapter(List<Object> date) {
        mDates=date;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case BANNER_TYPE://返回banner图
                return new NongYe_HomeBanner(getViewType(parent,R.layout.nongye_home_banner));
            case MENU_TYPE://返回类型菜单
                return new NongYe_HomeTypeMenu(getViewType(parent,R.layout.nongye_home_teypmenu));
            case RECOMMEND_TYPE://返回精选推荐
                return new NongYe_HomeRecommend(getViewType(parent,R.layout.nongye_home_recommend));
            case FEATURE_TYPE://返回特色购
                return new NongYe_HomeFeature(getViewType(parent,R.layout.nongye_home_feature));
            case LOVE_ATE_TYPE://返回爱吃主题
                return new NongYe_Home_LoveEat(getViewType(parent,R.layout.nongye_home_loveeat));
            default://返回爱吃item
                return new NongYe_Home_EatItem(getViewType(parent,R.layout.nongye_home_loveeat_item));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position){
            case 0://返回banner图

                break;
            case 1://返回类型菜单

                break;
            case 2://返回精选推荐

                break;
            case 3://返回特色购

                break;
            case 4://返回特色购

                break;
            case 5://返回爱吃主题

                break;
            default://返回爱吃item

                break;
        }
    }
    @Override
    public int getItemCount(){
        return mDates.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return BANNER_TYPE;//返回banner图
            case 1:
                return MENU_TYPE;//返回类型菜单
            case 2:
                return RECOMMEND_TYPE;//返回精选推荐
            case 3:
                return FEATURE_TYPE;//返回特色购
            case 4:
                return FEATURE_TYPE;//返回特色购
            case 5:
                return LOVE_ATE_TYPE;//返回爱吃主题
        }
        return LOVE_ITEM_TYPE;//返回爱吃item
    }

    /**
     * 填充不同类型布局
     * @param parent
     * @param resource R资源
     * @return
     */
    private View getViewType(ViewGroup parent, int resource ){
        return LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
    }

}
