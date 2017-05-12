package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.viewHolder.CreateBannerHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.entity.response.HomeGoodsListModel;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.entity.response.HomeMenusModel;
import com.chinayiz.chinayzy.entity.response.HomeThemesModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.WebPowerFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 11:54
 * Class MainHomeRecylAdapter
 */

public class MainHomeRecylAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 首页广告banner图
     */
    public static final int ITEM_BANNER = 10;
    /**
     * 板块菜单
     */
    public static final int ITEM_MENU = 11;
    /**
     * 主题图片
     */
    public static final int ITEM_THEME = 12;
    /**
     * 主题商品列表
     */
    public static final int ITEM_LIST = 13;
    /**
     * 热销主题title
     */
    public static final int ITEM_TITLE = 14;
    /**
     * 热销板块商品
     */
    public static final int ITEM_GOODS = 15;
    private Fragment mFragment;
    /**
     * RecyclerView 填充数据
     */
    private Map<String, Object> mDates = new HashMap<>();
    private HomeHotGoodsModel mHotGoodsModel;
    private int mPositions;

    public void addDate(String key, Object object) {
        mDates.put(key, object);
        notifyDataSetChanged();
    }

    public MainHomeRecylAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_BANNER:
                return new HomeBanner(getItemView(parent, R.layout.home_item_banner));
            case ITEM_MENU:
                return new HomeMenu(getItemView(parent, R.layout.home_item_menu));
            case ITEM_THEME:
                return new HomenTheme(getItemView(parent, R.layout.home_item_theme));
            case ITEM_LIST:
                return new HomeList(getItemView(parent, R.layout.home_item_list));
            case ITEM_TITLE:
                return new HomeTitle(getItemView(parent, R.layout.home_item_thriving_title));
            case ITEM_GOODS:
                return new HomeGoods(getItemView(parent, R.layout.home_item_thriving_goods));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return ITEM_BANNER;
            case 1:
                return ITEM_MENU;
            case 2:
                return ITEM_THEME;
            case 3:
                return ITEM_LIST;
            case 4:
                return ITEM_THEME;
            case 5:
                return ITEM_LIST;
            case 6:
                return ITEM_TITLE;
            default:
                return ITEM_GOODS;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
                if (holder instanceof HomeBanner) {
                    if (mDates.containsKey(Commons.MAIN_BANNER)) {
                        HomeBanner banner = (HomeBanner) holder;
                        banner.setData((NY_BannerModel) mDates.get(Commons.MAIN_BANNER));
                    }
                }
                break;
            case 1:
                if (holder instanceof HomeMenu) {
                    if (mDates.containsKey(Commons.HOME_MODEL)) {
                        HomeMenu menu = (HomeMenu) holder;
                        HomeMenusModel model = (HomeMenusModel) mDates.get(Commons.HOME_MODEL);
                        menu.setData(model, mFragment);
                    }
                }
                break;
            case 2:
                if (holder instanceof HomenTheme) {
                    if (mDates.containsKey(Commons.HOME_THEME1)) {
                        HomenTheme theme = (HomenTheme) holder;
                        HomeThemesModel model = (HomeThemesModel) mDates.get(Commons.HOME_THEME1);
                        theme.setData(model, mFragment, 1);
                    }
                }
                break;
            case 3:
                if (holder instanceof HomeList) {
                    if (mDates.containsKey(Commons.HOME_LIST1)) {
                        HomeList list = (HomeList) holder;
                        HomeGoodsListModel model = (HomeGoodsListModel) mDates.get(Commons.HOME_LIST1);
                        list.setData(mFragment, model.getData(), 1);
                    }
                }
                break;
            case 4:
                if (holder instanceof HomenTheme) {
                    Logger.i("商品主题2");
                    if (mDates.containsKey(Commons.HOME_THEME2)) {
                        HomenTheme theme = (HomenTheme) holder;
                        HomeThemesModel model = (HomeThemesModel) mDates.get(Commons.HOME_THEME2);
                        theme.setData(model, mFragment, 2);
                    }
                }
                break;
            case 5:
                if (holder instanceof HomeList) {
                    Logger.i("商品集合2");
                    if (mDates.containsKey(Commons.HOME_LIST2)) {
                        HomeList list = (HomeList) holder;
                        HomeGoodsListModel model = (HomeGoodsListModel) mDates.get(Commons.HOME_LIST2);
                        list.setData(mFragment, model.getData(), 2);
                    }
                }
                break;
            case 6:
                if (holder instanceof HomeTitle) {
                    Logger.i("热销标题");
                }
                break;
            default:
                if (holder instanceof HomeGoods) {
                    mPositions = position % 7;
                    if (mDates.containsKey(Commons.HOME_REXIAO)) {
                        HomeGoods goods = (HomeGoods) holder;
                        if (mHotGoodsModel == null) {
                            mHotGoodsModel = (HomeHotGoodsModel) mDates.get(Commons.HOME_REXIAO);
                        }
                        Logger.i("热销商品位置=" + mPositions);
                        goods.setData(mFragment, new HomeHotGoodsModel.DataBean[]{mHotGoodsModel.getData().get(mPositions)
                                , mHotGoodsModel.getData().get(mPositions + 1)});
                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    /**
     * 填充不同类型 item 布局
     *
     * @param parent
     * @param resource R资源
     * @return
     */
    private View getItemView(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }

    /**
     * banner图
     */
    public class HomeBanner extends RecyclerView.ViewHolder implements OnItemClickListener {
        ConvenientBanner mBannerNongyeHome;
        List<String> mUrls = new ArrayList<>();
        NY_BannerModel mModel;

        public HomeBanner(View itemView) {
            super(itemView);
            mBannerNongyeHome = (ConvenientBanner) itemView.findViewById(R.id.home_banner);
            //设置指示器（小圆点）
            mBannerNongyeHome.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
            mBannerNongyeHome.setOnItemClickListener(this);
            mBannerNongyeHome.startTurning(3000);
        }

        public void setData(NY_BannerModel model) {
            mModel = model;
            mUrls.clear();
            for (NY_BannerModel.Data data : mModel.getData()) {
                mUrls.add(data.getShowlink());
            }
            mBannerNongyeHome.setPages(new CreateBannerHolder(), mUrls);
        }

        @Override
        public void onItemClick(int position) {
            if (position == 0) {
                String uri = Commons.API + "/h5/activity?devicetype=android&userid=" + APP.sUserid;
                Skip.toWebPage(mFragment.getActivity(), uri, "活动中心");
                return;
            }
            if (mModel == null) {
                return;
            }
            if ("5".equals(mModel.getData().get(position).getType())) {
                Skip.toNewGoodsDetail(mFragment.getActivity(), mModel.getData().get(position).getDetaillink());
            }
        }
    }

    /**
     * 板块菜单
     */
    public class HomeMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<ImageView> menus = new ArrayList<>(4);

        public HomeMenu(View itemView) {
            super(itemView);
            menus.add((ImageView) itemView.findViewById(R.id.home_menu1));
            menus.add((ImageView) itemView.findViewById(R.id.home_menu2));
            menus.add((ImageView) itemView.findViewById(R.id.home_menu3));
            menus.add((ImageView) itemView.findViewById(R.id.home_menu4));
        }

        public void setData(HomeMenusModel data, Fragment fragment) {
            ImageView img;
            for (int i = 0; i < data.getData().size(); i++) {
                img = menus.get(i);
                Glide.with(fragment)
                        .load(data.getData().get(i).getSypic())
                        .into(img);
                img.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            Logger.i("模块点击");
            switch (v.getId()) {
                case R.id.home_menu1:
                    Skip.toNongYeHome(mFragment.getActivity());
                    break;
                case R.id.home_menu2:
                    Skip.toMail(mFragment.getActivity(),"");
                    break;
                case R.id.home_menu3:
                    Logger.i("关于我们");
                    shareUser(Commons.API + "/h5/aboutus", "关于我们");
                    break;
                case R.id.home_menu4:
                    Logger.i("我的二维码");
                    if (UserSeeion.isLogin(mFragment.getActivity())) {
                        shareUser(Commons.API + "/h5/tuijianma?userid=" + APP.sUserid + "&devicetype=android", WebPowerFragment.SHARE);
                    } else {
                        BaseActivity.showToast(mFragment.getActivity(), "请先登录");
                    }
                    break;
            }
        }
    }

    private void shareUser(String url, String titel) {
        Skip.toWebPage(mFragment.getActivity(), url, titel);
    }

    /**
     * 主题图
     */
    public class HomenTheme extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mThemeImage;
        int mPosetion = 0;

        public HomenTheme(View itemView) {
            super(itemView);
            mThemeImage = (ImageView) itemView.findViewById(R.id.home_themeIcon);
        }

        public void setData(HomeThemesModel model, Fragment fragment, int posetion) {
            mPosetion = posetion;
            Glide.with(fragment)
                    .load(model.getData().get(0).getShowlink())
                    .into(mThemeImage);

            mThemeImage.setOnClickListener(this);
            mThemeImage.setTag(R.id.tag_click, model.getData().get(0).getDetaillink());
        }

        @Override
        public void onClick(View v) {
            switch (mPosetion) {
                case 0: {
                    BaseActivity.showToast(mFragment.getActivity(), "未知错误，请重试");
                    break;
                }
                case 1: {
                    Skip.toNongYeHome(mFragment.getActivity());
                    break;
                }
                case 2: {
                    Skip.toMail(mFragment.getActivity(),"");
                    break;
                }
            }
        }
    }

    /**
     * 横向商品集合
     */
    public class HomeList extends RecyclerView.ViewHolder implements HomeListRecylAdapter.onItemClickListener {
        RecyclerView mRecyclerView;
        HomeListRecylAdapter mAdapter;
        int is=0;

        public HomeList(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.home_goodslist);
        }

        public void setData(Fragment fragment, List<HomeGoodsListModel.DataBean> beanList, int posetion) {
            is = posetion;
            LinearLayoutManager layoutManager = new LinearLayoutManager(fragment.getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new HomeListRecylAdapter(beanList, fragment);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setItemClickListener(this);
        }

        @Override
        public void onClickItem(String goodsId) {
            Logger.i("点击了横向商品="+goodsId);
            if (HomeListRecylAdapter.ListItemAll.CLICK_ALL.equals(goodsId)) {
                switch (is) {
                    case 0: {
                        BaseActivity.showToast(mFragment.getActivity(), "未知错误，请重试");
                        break;
                    }
                    case 1: {
                        Skip.toNongYeHome(mFragment.getActivity());
                        break;
                    }
                    case 2: {
                        Skip.toMail(mFragment.getActivity(),"");
                        break;
                    }
                }
            }else {
            Skip.toNewGoodsDetail(mFragment.getActivity(), goodsId);
            }
        }
    }

    /**
     * 畅销商品title
     */
    public class HomeTitle extends RecyclerView.ViewHolder {
        public HomeTitle(View itemView) {
            super(itemView);
        }
    }

    /**
     * 畅销商品（两个一组）
     */
    public class HomeGoods extends RecyclerView.ViewHolder implements View.OnClickListener {
        View root, root1;
        ImageView goodsPic, goodsPic1;
        TextView goodsName, goodsName1;
        TextView goodsPrice, goodsDubPrice;
        TextView goodsPrice1, goodsDubPrice1;
        View isSelf, isSelf1;
        TextView commentCount, commentCount1;
        TextView goodComments, goodComments1;
        Fragment mContext;

        public HomeGoods(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_goods);
            root1 = itemView.findViewById(R.id.item_goods1);

            goodsPic = (ImageView) itemView.findViewById(R.id.iv_goodsPic);
            goodsName = (TextView) itemView.findViewById(R.id.tv_goodsName);
            goodsPrice = (TextView) itemView.findViewById(R.id.tv_price);
            goodsDubPrice = (TextView) itemView.findViewById(R.id.tv_dobPrice);
            isSelf = itemView.findViewById(R.id.view_isSelf);
            commentCount = (TextView) itemView.findViewById(R.id.tv_commentCount);
            goodComments = (TextView) itemView.findViewById(R.id.tv_goodsComment);

            goodsPic1 = (ImageView) itemView.findViewById(R.id.iv_goodsPic1);
            goodsName1 = (TextView) itemView.findViewById(R.id.tv_goodsName1);
            goodsPrice1 = (TextView) itemView.findViewById(R.id.tv_price1);
            goodsDubPrice1 = (TextView) itemView.findViewById(R.id.tv_dobPrice1);
            isSelf1 = itemView.findViewById(R.id.view_isSelf1);
            commentCount1 = (TextView) itemView.findViewById(R.id.tv_commentCount1);
            goodComments1 = (TextView) itemView.findViewById(R.id.tv_goodsComment1);
        }

        public void setData(Fragment fragment, HomeHotGoodsModel.DataBean[] datas) {
            mContext = fragment;
            Glide.with(fragment).load(datas[0].getIcon()).into(goodsPic);
            goodsName.setText(datas[0].getGname());
            if (datas[0].getPrice().contains(".")) {
                String[] prices = datas[0].getPrice().split("\\.");
                goodsPrice.setText(prices[0] + ".");
                goodsDubPrice.setText(prices[1]);
            } else {
                goodsPrice.setText(datas[0].getPrice() + ".");
                goodsDubPrice.setText("00");
            }
            if ("0".equals(datas[0].getIsself())) {
                isSelf.setVisibility(View.GONE);
            }
            if (!"0".equals(datas[0].getCommenttotal())) {
                commentCount.setText(datas[0].getCommenttotal() + "条评论");
            }
            goodComments.setText(datas[0].getPraise() + "好评");

            Glide.with(fragment).load(datas[1].getIcon()).into(goodsPic1);
            goodsName1.setText(datas[1].getGname());
            if (datas[1].getPrice().contains(".")) {
                String[] prices = datas[1].getPrice().split("\\.");
                goodsPrice1.setText(prices[0] + ".");
                goodsDubPrice1.setText(prices[1]);
            } else {
                goodsPrice1.setText(datas[1].getPrice() + ".");
                goodsDubPrice1.setText("00");
            }
            if ("0".equals(datas[1].getIsself())) {
                isSelf1.setVisibility(View.GONE);
            }
            if (!"0".equals(datas[1].getCommenttotal())) {
                commentCount1.setText(datas[1].getCommenttotal() + "条评论");
            }
            goodComments1.setText(datas[1].getPraise() + "好评");

            root.setTag(R.id.tag_click, datas[0].getGoodsid());
            root1.setTag(R.id.tag_click, datas[1].getGoodsid());
            root.setOnClickListener(this);
            root1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Skip.toNewGoodsDetail(mContext.getActivity(), v.getTag(R.id.tag_click).toString());
        }
    }
}
