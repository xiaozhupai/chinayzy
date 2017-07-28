package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.content.Context;
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
import com.chinayiz.chinayzy.entity.response.HomeActivitysModel;
import com.chinayiz.chinayzy.entity.response.HomeGoodsListModel;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.entity.response.HomeMainActivitysModel;
import com.chinayiz.chinayzy.entity.response.HomeMenusModel;
import com.chinayiz.chinayzy.entity.response.HomeNewsModel;
import com.chinayiz.chinayzy.entity.response.HomeThemesModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.orhanobut.logger.Logger;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.iwgang.countdownview.CountdownView;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/9 11:54
 * Class MainHomeRecylAdapter
 */

public class MainHomeRecylAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 爱时尚
     */
    public static final String TYPE_LOVEFS = "aishishang";
    /**
     * 世界硒都
     */
    public static final String TYPE_XIINFO = "guanyuxi";
    /**
     * 奢侈品
     */
    public static final String TYPE_LUXURY = "shechiping";
    /**
     * 分类title
     */
    public static final String TYPE_TITLE = "typefenlei";
    /**
     * 首页广告banner图
     */
    public static final int ITEM_BANNER = 10;
    /**
     * 主要主题
     */
    public static final int ITEM_MAIN_THEME = 16;
    /**
     * 板块菜单
     */
    public static final int ITEM_MENU = 11;
    /**
     * 亿众快讯
     */
    public static final int ITEM_NEWS = 17;
    /**
     * 抢购活动
     */
    public static final int ITEM_ACTIVITYS = 18;
    /**
     * 横向主题
     */
    public static final int ITEM_THEME = 12;
    /**
     * 横向商品列表
     */
    public static final int ITEM_LIST = 13;
    /**
     * 主题title
     */
    public static final int ITEM_TITLE = 14;
    /**
     * 主题商品商品
     */
    public static final int ITEM_GOODS = 15;

    private int itemSize = 18;
    /**
     * RecyclerView 填充数据
     */
    public Map<String, Object> mDates = new HashMap<>();
    private HomeHotGoodsModel mHotGoodsModel;
    private HomeMenusModel mMenusModel;
    private Context mContext;
    private Fragment mFragment;

    public void addDate(String key, Object object) {
        mDates.put(key, object);
        notifyDataSetChanged();
    }

    public void loadData(HomeHotGoodsModel data) {
        itemSize = itemSize + data.getData().size() / 2;
        if (mHotGoodsModel == null) {
            mHotGoodsModel = data;
        } else {
            mHotGoodsModel.getData().addAll(data.getData());
        }
        notifyDataSetChanged();
    }

    public MainHomeRecylAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
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
            case ITEM_ACTIVITYS:
                return new Acvivitys(getItemView(parent, R.layout.home_item_actititys));
            case ITEM_NEWS:
                return new News(getItemView(parent, R.layout.home_item_news));
            case ITEM_MAIN_THEME:
                return new PicTheme(getItemView(parent, R.layout.home_item_img));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0://banner图
                return ITEM_BANNER;
            case 1://主要主题
                return ITEM_MAIN_THEME;
            case 2://板块按钮
                return ITEM_MENU;
            case 3://亿众头条新闻
                return ITEM_NEWS;
            case 4://倒计时活动
                return ITEM_ACTIVITYS;
            case 5://横向商品主题1
                return ITEM_THEME;
            case 6://横向商品1
                return ITEM_LIST;
            case 7://横向商品主题2
                return ITEM_THEME;
            case 8://横向商品2
                return ITEM_LIST;
            case 9://吃货必备
                return ITEM_TITLE;
            case 13://爱时尚
                return ITEM_TITLE;
            case 17://为你推荐
                return ITEM_TITLE;
            default://两个横向商品
                return ITEM_GOODS;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeBanner) {
            if (mDates.containsKey(Commons.MAIN_BANNER)) {
                HomeBanner banner = (HomeBanner) holder;
                banner.setData((NY_BannerModel) mDates.get(Commons.MAIN_BANNER));
            } else {
                Logger.i("没有首页banner图=" + position);
            }
            return;
        }
        if (holder instanceof PicTheme) {
            if (mDates.containsKey(Commons.HONE_ACTIVITYS)) {
                PicTheme picTheme = (PicTheme) holder;
                picTheme.setData((HomeMainActivitysModel) mDates.get(Commons.HONE_ACTIVITYS), mContext);
            } else {
                Logger.i("没有首页主要（新人）活动图=" + position);
            }
            return;
        }
        if (holder instanceof HomeMenu) {
            if (mDates.containsKey(Commons.HOME_MODEL)) {
                HomeMenu menu = (HomeMenu) holder;
                mMenusModel= (HomeMenusModel) mDates.get(Commons.HOME_MODEL);
                menu.setData(mMenusModel, mContext);

            } else {
                Logger.i("没有首页板块菜单=" + position);
            }
            return;
        }
        if (holder instanceof News) {
            if (mDates.containsKey(Commons.HOME_NEWS)) {
                News news = (News) holder;
                news.setData((HomeNewsModel) mDates.get(Commons.HOME_NEWS));
            } else {
                Logger.i("没有亿众头条新闻=" + position);
            }
            return;
        }
        if (holder instanceof Acvivitys) {
            if (mDates.containsKey(Commons.HOME_ZHONGCHOU)) {
                Acvivitys zhongchou = (Acvivitys) holder;
                zhongchou.setData((HomeActivitysModel) mDates.get(Commons.HOME_ZHONGCHOU), mContext);
            } else {
                Logger.i("没有抢购众筹活动=" + position);
            }

            return;
        }
        if (holder instanceof HomenTheme) {
            if (position == 5) {
                if (mDates.containsKey(Commons.HOME_THEME1)) {
                    HomenTheme theme = (HomenTheme) holder;
                    Glide.with(mContext).load(mMenusModel.getData().getLanweilist().get(0).getSypic()).into(theme.mThemeTitle);
                    HomeThemesModel model = (HomeThemesModel) mDates.get(Commons.HOME_THEME1);
                    theme.setData(model, mContext, 1);
                } else {
                    Logger.i("没有首页第一个主题=" + position);
                    CommonRequestUtils.getRequestUtils().getHomeTheme(Commons.HOME_THEME1);
                }
            } else {
                if (mDates.containsKey(Commons.HOME_THEME2)) {
                    HomenTheme theme = (HomenTheme) holder;
                    Glide.with(mContext).load(mMenusModel.getData().getLanweilist().get(1).getSypic()).into(theme.mThemeTitle);
                    HomeThemesModel model = (HomeThemesModel) mDates.get(Commons.HOME_THEME2);
                    theme.setData(model, mContext, 2);
                } else {
                    Logger.i("没有首页第二个主题=" + position);
                    CommonRequestUtils.getRequestUtils().getHomeTheme(Commons.HOME_THEME2);
                }
            }
            return;
        }
        if (holder instanceof HomeList) {
            if (position == 6) {
                if (mDates.containsKey(Commons.HOME_LIST1)) {
                    HomeList list = (HomeList) holder;
                    HomeGoodsListModel model = (HomeGoodsListModel) mDates.get(Commons.HOME_LIST1);
                    list.setData(mContext, model.getData(), 1);
                } else {
                    Logger.i("没有首页第一个横向商品集合=" + position);
                    CommonRequestUtils.getRequestUtils().getHomeList(Commons.HOME_LIST1);
                }
            } else {
                if (mDates.containsKey(Commons.HOME_LIST2)) {
                    HomeList list = (HomeList) holder;
                    HomeGoodsListModel model = (HomeGoodsListModel) mDates.get(Commons.HOME_LIST2);
                    list.setData(mContext, model.getData(), 2);
                } else {
                    Logger.i("没有首页第二个横向商品集合=" + position);
                    CommonRequestUtils.getRequestUtils().getHomeList(Commons.HOME_LIST2);
                }
            }
            return;
        }
        if (holder instanceof HomeTitle) {
            HomeTitle hometitle = (HomeTitle) holder;
            switch (position) {
                case 9: {//吃货必备
                    Glide.with(mContext).load(mMenusModel.getData().getLanweilist().get(2).getSypic()).into(hometitle.mImageView);
                    break;
                }
                case 13: {//爱时尚
                    Glide.with(mContext).load(mMenusModel.getData().getLanweilist().get(3).getSypic()).into(hometitle.mImageView);
                    break;
                }
                case 17: {//为你推荐
                    Glide.with(mContext).load(mMenusModel.getData().getLanweilist().get(4).getSypic()).into(hometitle.mImageView);
                    break;
                }
            }
            return;
        }
        if (holder instanceof HomeGoods) {
            HomeGoods goods;
            goods = (HomeGoods) holder;
            HomeHotGoodsModel goodsModel = null;
            int index = 0;
            switch (position) {
                case 10:
                case 11:
                case 12: {//吃货必备
                    if (mDates.containsKey(TYPE_LOVEFS)) {
                        index = position % 10;
                        goodsModel = (HomeHotGoodsModel) mDates.get(TYPE_LOVEFS);
                    } else {
                        CommonRequestUtils.getRequestUtils().getHomeThemeGoods(TYPE_LOVEFS);
                        Logger.i("没有首页爱时尚商品集合=" + position);
                    }
                    break;
                }
                case 14:
                case 15:
                case 16: {//爱时尚
                    if (mDates.containsKey(TYPE_XIINFO)) {
                        index = position % 14;
                        goodsModel = (HomeHotGoodsModel) mDates.get(TYPE_XIINFO);
                    } else {
                        CommonRequestUtils.getRequestUtils().getHomeThemeGoods(TYPE_XIINFO);
                        Logger.i("没有首页世界硒都商品集合=" + position);
                    }
                    break;
                }
//                case 18:
//                case 19:
//                case 20:
//                case 21: {//奢侈品
//                    if (mDates.containsKey(TYPE_LUXURY)) {
//                        index = position % 18;
//                        goodsModel = (HomeHotGoodsModel) mDates.get(TYPE_LUXURY);
//                    } else {
//                        CommonRequestUtils.getRequestUtils().getHomeThemeGoods(TYPE_LUXURY);
//                        Logger.i("没有首页奢侈品商品集合=" + position);
//                    }
//                    break;
//                }
                default: {//为你推荐
                    if (mHotGoodsModel != null) {
                        index = position % 18;
                        goodsModel = mHotGoodsModel;
                        Logger.i("推荐商品集合=" + goodsModel.getData().size());
                    } else {
                        Logger.i("没有首页推荐商品集合=" + position);
                    }
                }
            }
            int sta = index * 2;
            int end = sta + 2;
            if (goodsModel != null) {
                List<HomeHotGoodsModel.DataBean> datas = goodsModel.getData().subList(sta, end);
                Logger.i("本次角标为=" + position + "\t\t\t容量为=" + goodsModel.getData().size());
                goods.setData(mContext, datas);
            } else {
                Logger.i("橱窗两组商品数据异常");
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemSize;
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
            mBannerNongyeHome.setPageIndicator(new int[]{R.mipmap.pager_indx1, R.mipmap.pager_indx2});
            mBannerNongyeHome.setOnItemClickListener(this);
            mBannerNongyeHome.startTurning(3000);
        }

        public void setData(NY_BannerModel model) {
            mModel = model;
            mUrls.clear();
            if (mModel != null) {
                for (NY_BannerModel.Data data : mModel.getData()) {
                    mUrls.add(data.getShowlink());
                }
                mBannerNongyeHome.setPages(new CreateBannerHolder(), mUrls);
            }
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

        public void setData(HomeMenusModel data, Context fragment) {
            ImageView img;
            for (int i = 0; i < data.getData().getTubiaolist().size(); i++) {
                img = menus.get(i);
                Glide.with(fragment)
                        .load(data.getData().getTubiaolist().get(i).getSypic())
                        .into(img);
                img.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_menu1:
                    Skip.toNongYeHome(mFragment.getActivity());
                    break;
                case R.id.home_menu2:
                    Skip.toMail(mFragment.getActivity(), "");
                    break;
                case R.id.home_menu3:
                    Logger.i("关于我们");
                    shareUser(Commons.API + "/h5/aboutus", "关于我们");
                    break;
                case R.id.home_menu4:
                    Logger.i("我的二维码");
                    if (UserSeeion.isLogin(mFragment.getActivity())) {
                        if (UserSeeion.getSys_auth(mFragment.getActivity()).equals("1")) {   //已经完善资料
                            if (UserSeeion.isMember(mFragment.getActivity())) {
                                Skip.toWebPage(mFragment.getActivity(), Commons.API + "/h5/tuijianma?userid=" + APP.sUserid + "&devicetype=android", "分享二维码");
                            }
                        } else {
                            Skip.toPerfestData(mFragment.getActivity());
                        }
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
     * 横向主题图
     */
    public class HomenTheme extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mThemeImage;
        ImageView mThemeTitle;
        int mPosetion = -1;

        public HomenTheme(View itemView) {
            super(itemView);
            mThemeImage = (ImageView) itemView.findViewById(R.id.home_themeIcon);
            mThemeTitle = (ImageView) itemView.findViewById(R.id.iv_themeTitle);
        }

        public void setData(HomeThemesModel model, Context fragment, int posetion) {
            mPosetion = posetion;
            switch (mPosetion) {
                case -1: {
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
            }
            Glide.with(fragment)
                    .load(model.getData().get(0).getShowlink())
                    .into(mThemeImage);
            mThemeImage.setOnClickListener(this);
            mThemeImage.setTag(R.id.tag_click, model.getData().get(0).getDetaillink());
        }

        @Override
        public void onClick(View v) {
            switch (mPosetion) {
                case -1: {
                    BaseActivity.showToast(mFragment.getActivity(), "未知错误，请重试");
                    break;
                }
                case 1: {
//                    Skip.toNongYeHome(mFragment.getActivity());
                    //富硒农业
                    Skip.toItemGoosd(mFragment.getActivity(),"015");
                    break;
                }
                case 2: {
//                    Skip.toMail(mFragment.getActivity(), "");
                    //有机农业
                    Skip.toItemMenu(mFragment.getActivity(), "1");
                    break;
                }
                default: {
//                    Skip.toNongYeHome(mFragment.getActivity());
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
        int is = -1;

        public HomeList(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.home_goodslist);
        }

        public void setData(Context fragment, List<HomeGoodsListModel.DataBean> beanList, int posetion) {
            is = posetion;
            LinearLayoutManager layoutManager = new LinearLayoutManager(fragment);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new HomeListRecylAdapter(beanList);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setItemClickListener(this);
        }

        @Override
        public void onClickItem(String goodsId) {
            Logger.i("点击了横向商品=" + goodsId);
            if (HomeListRecylAdapter.ListItemAll.CLICK_ALL.equals(goodsId)) {
                switch (is) {
                    case -1: {
                        BaseActivity.showToast(mFragment.getActivity(), "未知错误，请重试");
                        break;
                    }
                    case 1: {
                        //富硒农业
                        Skip.toItemGoosd(mFragment.getActivity(),"015");
                        break;
                    }
                    case 2: {
                        //有机农业
                        Skip.toItemMenu(mFragment.getActivity(), "1");
                        break;
                    }
                }
            } else {
                Skip.toNewGoodsDetail(mFragment.getActivity(), goodsId);
            }
        }
    }

    /**
     * 主要主题（单个）
     */
    public class PicTheme extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        String type="";
        public PicTheme(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_themePic);
        }
        @Override
        public void onClick(View v) {
            Logger.i("点击了主要主题="+v.getTag(R.id.tag_click)+"\t\t\t类型="+type);
            if ("1".equals(type)){
                Skip.gotoRegister(mFragment.getActivity());
            }else {
                StringBuffer url=new StringBuffer();
           url.append(Commons.API)
                   .append("/")
                   .append(v.getTag(R.id.tag_click))
                   .append("userid="+APP.sUserid)
                   .append("&devicetye=android");
                Skip.toWebPage(mFragment.getActivity(), url.toString(), "会员招募");
            }
        }

        public void setData(HomeMainActivitysModel homeMainActivitysModel, Context context) {
            HomeMainActivitysModel.DataBean data = null;
            for (int i = 0; i < homeMainActivitysModel.getData().size(); i++) {
                if (homeMainActivitysModel.getData().get(i).getIsshow().equals("1")) {
                    data = homeMainActivitysModel.getData().get(i);
                }
            }
            if (data != null) {
                type=data.getType();
                mImageView.setTag(R.id.tag_click,data.getLink());
                Glide.with(context).load(data.getPic()).into(mImageView);
                mImageView.setOnClickListener(this);
            }
        }
    }

    /**
     * 亿众新闻快讯
     */
    public class News extends RecyclerView.ViewHolder implements MarqueeView.OnItemClickListener {
        HomeNewsModel mNewsModel;
        MarqueeView mMarqueeView;
        List<String> mNews = new ArrayList<>();

        public News(View itemView) {
            super(itemView);
            mMarqueeView = (MarqueeView) itemView.findViewById(R.id.marqueeView);
            mMarqueeView.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(int position, TextView textView) {
            HomeNewsModel.DataBean dataBean=mNewsModel.getData().get(position);
            Skip.toWebPage(mContext,dataBean.getLink(),"亿众快讯");
        }
        public void setData(HomeNewsModel homeNewsModel) {
            mNewsModel=homeNewsModel;
            mNews.clear();
            for (HomeNewsModel.DataBean bean : mNewsModel.getData()) {
                mNews.add(bean.getTheme());
            }
            mMarqueeView.startWithList(mNews);
        }


    }

    /**
     * 众筹活动
     */
    public class Acvivitys extends RecyclerView.ViewHolder implements HomeActivitysRecylAdapter.onItemClickListener, View.OnClickListener {
        CountdownView mCountdownView;
        RecyclerView mRecyclerView;
        HomeActivitysRecylAdapter mAdapter;

        public Acvivitys(View itemView) {
            super(itemView);
            mCountdownView = (CountdownView) itemView.findViewById(R.id.countDownView);
            itemView.findViewById(R.id.iv_more_activity).setOnClickListener(this);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.activity_list);

        }

        public void setData(HomeActivitysModel homeActivitysModel, Context context) {
            mCountdownView.setTag("test");
            Random random = new Random();
            int x = random.nextInt(10);
            long time = x * 60 * 1000;
            mCountdownView.start(time);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mFragment.getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new HomeActivitysRecylAdapter(homeActivitysModel.getData(), context);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setItemClickListener(this);
        }

        @Override
        public void onClickItem(String goodsId) {
            Logger.i("点击抢购众筹商品事件=" + goodsId);
            if (HomeActivitysRecylAdapter.ListItemAll.CLICK_ALL.equals(goodsId)) {//全部活动跳转
                clickEvent();
            } else {
                StringBuffer url=new StringBuffer();
                url.append(Commons.API)
                        .append("/h5/crowdfunddetail?")
                        .append("userid="+APP.sUserid)
                        .append("&crowdfid="+goodsId)
                        .append("&devicetye=android");
                Logger.i("活动详情地址="+url.toString());
                Skip.toWebPage(mFragment.getActivity(), url.toString(), "分享活动");
            }
        }
        @Override
        public void onClick(View v) {//更多活动跳转
            clickEvent();
        }
        private void clickEvent() {
            StringBuffer url=new StringBuffer();
            url.append(Commons.API)
                    .append("/h5/crowdfundlist?")
                    .append("userid="+APP.sUserid)
                    .append("&devicetye=android");
            Skip.toWebPage(mFragment.getActivity(), url.toString(), "分享活动");
        }
    }

    /**
     * 畅销商品title
     */
    class HomeTitle extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public HomeTitle(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_themeTitles);
        }

    }

    /**
     * 畅销商品（两个一组）
     */
    public class HomeGoods extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<GridGoodsHolder> mGoodsHolders = new ArrayList<>(2);
        Context mContext;

        public HomeGoods(View itemView) {
            super(itemView);
            GridGoodsHolder viewHolder1 = new GridGoodsHolder();
            GridGoodsHolder viewHolder2 = new GridGoodsHolder();
            viewHolder1.root_goods = itemView.findViewById(R.id.item_goods);
            viewHolder1.iv_goodsPic = (ImageView) itemView.findViewById(R.id.iv_goodsPic);
            viewHolder1.tv_goodsName = (TextView) itemView.findViewById(R.id.tv_goodsName);
            viewHolder1.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            viewHolder1.tv_dobPrice = (TextView) itemView.findViewById(R.id.tv_dobPrice);
            viewHolder1.view_isSelf = itemView.findViewById(R.id.view_isSelf);
            viewHolder1.tv_commentCount = (TextView) itemView.findViewById(R.id.tv_commentCount);
            viewHolder1.tv_goodsComment = (TextView) itemView.findViewById(R.id.tv_goodsComment);

            viewHolder2.root_goods = itemView.findViewById(R.id.item_goods1);
            viewHolder2.iv_goodsPic = (ImageView) itemView.findViewById(R.id.iv_goodsPic1);
            viewHolder2.tv_goodsName = (TextView) itemView.findViewById(R.id.tv_goodsName1);
            viewHolder2.tv_price = (TextView) itemView.findViewById(R.id.tv_price1);
            viewHolder2.tv_dobPrice = (TextView) itemView.findViewById(R.id.tv_dobPrice1);
            viewHolder2.view_isSelf = itemView.findViewById(R.id.view_isSelf1);
            viewHolder2.tv_commentCount = (TextView) itemView.findViewById(R.id.tv_commentCount1);
            viewHolder2.tv_goodsComment = (TextView) itemView.findViewById(R.id.tv_goodsComment1);
            mGoodsHolders.add(viewHolder1);
            mGoodsHolders.add(viewHolder2);
        }

        public void setData(Context fragment, List<HomeHotGoodsModel.DataBean> datas) {
            mContext = fragment;
            GridGoodsHolder viewholder;
            for (int i = 0; i < datas.size(); i++) {
                viewholder = mGoodsHolders.get(i);
                Glide.with(fragment).load(datas.get(i).getIcon()).into(viewholder.iv_goodsPic);
                viewholder.tv_goodsName.setText(datas.get(i).getGname());
                if (datas.get(i).getPrice().contains(".")) {
                    String[] prices = datas.get(i).getPrice().split("\\.");
                    viewholder.tv_price.setText(prices[0] + ".");
                    viewholder.tv_dobPrice.setText(prices[1]);
                } else {
                    viewholder.tv_price.setText(datas.get(i).getPrice() + ".");
                    viewholder.tv_dobPrice.setText("00");
                }
                if ("0".equals(datas.get(i).getIsself())) {
                    viewholder.view_isSelf.setVisibility(View.GONE);
                }
                if (!"0".equals(datas.get(i).getCommenttotal())) {
                    viewholder.tv_commentCount.setText(datas.get(i).getCommenttotal() + "条评论");
                }
                viewholder.tv_goodsComment.setText(datas.get(i).getPraise() + "好评");
                viewholder.root_goods.setTag(R.id.tag_click, datas.get(i).getGoodsid());
                viewholder.root_goods.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View v) {
            Skip.toNewGoodsDetail(mContext, v.getTag(R.id.tag_click).toString());
        }
    }

    /**
     * 畅销商品的item（单个）
     */
    public class GridGoodsHolder {
        public View root_goods;
        public ImageView iv_goodsPic;
        public TextView tv_goodsName;
        public TextView tv_price;
        public TextView tv_dobPrice;
        public View view_isSelf;
        public TextView tv_commentCount;
        public TextView tv_goodsComment;

    }
}
