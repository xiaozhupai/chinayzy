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
    public Map<String, Object> mDates = new HashMap<>();
    private HomeHotGoodsModel mHotGoodsModel;
    private int size = 7;

    public void addDate(String key, Object object) {
        if (key.equals(Commons.HOME_REXIAO)) {
            mHotGoodsModel = (HomeHotGoodsModel) object;
            notifyDataSetChanged();
        }
        mDates.put(key, object);
        notifyDataSetChanged();
    }

    public void loadData(HomeHotGoodsModel data) {
    if (mHotGoodsModel==null){
        mHotGoodsModel=data;
    }else {
        mHotGoodsModel.getData().addAll(data.getData());
    }
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
                    if (mDates.containsKey(Commons.HOME_THEME2)) {
                        HomenTheme theme = (HomenTheme) holder;
                        HomeThemesModel model = (HomeThemesModel) mDates.get(Commons.HOME_THEME2);
                        theme.setData(model, mFragment, 2);
                    }
                }
                break;
            case 5:
                if (holder instanceof HomeList) {
                    if (mDates.containsKey(Commons.HOME_LIST2)) {
                        HomeList list = (HomeList) holder;
                        HomeGoodsListModel model = (HomeGoodsListModel) mDates.get(Commons.HOME_LIST2);
                        list.setData(mFragment, model.getData(), 2);
                    }
                }
                break;
            case 6:

                break;
            default:
                if (holder instanceof HomeGoods) {
                    int index = position % 7;
                    int sta = index * 2;
                    int end = sta + 2;
                    HomeGoods goods;
                    goods = (HomeGoods) holder;
                    List<HomeHotGoodsModel.DataBean> datas = mHotGoodsModel.getData().subList(sta, end);
                    goods.setData(mFragment, datas);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mHotGoodsModel == null||mHotGoodsModel.getData()==null ? size : size + (mHotGoodsModel.getData().size() / 2);
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
            if (mModel!=null) {
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
                        if (UserSeeion.isMember(mFragment.getActivity())){
                            shareUser(Commons.API + "/h5/tuijianma?userid=" + APP.sUserid + "&devicetype=android", WebPowerFragment.SHARE);
                        }else {

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
//                    Skip.toNongYeHome(mFragment.getActivity());
                    Skip.toNewGoodsDetail(mFragment.getActivity(),v.getTag(R.id.tag_click).toString());
                    break;
                }
                case 2: {
                    Skip.toMail(mFragment.getActivity(), "");
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
        int is = 0;

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
            Logger.i("点击了横向商品=" + goodsId);
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
                        Skip.toMail(mFragment.getActivity(), "");
                        break;
                    }
                }
            } else {
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
        List<GridGoodsHolder> mGoodsHolders = new ArrayList<>(2);
        Fragment mContext;

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

        public void setData(Fragment fragment, List<HomeHotGoodsModel.DataBean> datas) {
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
            Skip.toNewGoodsDetail(mContext.getActivity(), v.getTag(R.id.tag_click).toString());
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
