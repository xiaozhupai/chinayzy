package com.chinayiz.chinayzy.adapter;

import android.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.viewHolder.CreateBannerHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.response.HomeHotGoodsModel;
import com.chinayiz.chinayzy.entity.response.NY_BannerModel;
import com.chinayiz.chinayzy.entity.response.NY_EatThemeModel;
import com.chinayiz.chinayzy.entity.response.StoreHomeTabModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.ui.fragment.StoreTabFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.chinayiz.chinayzy.net.Commons.STORE_HOME_TABS;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/6/7 11:40
 * Class StoreTabRecylAdapter  商城TAB页数据适配器
 */

public class StoreTabRecylAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 滚动广告图 0
     */
    public static final int VIEW_HOLDER_BANNER = 0;
    /**
     * 类型菜单 1
     */
    public static final int VIEW_HOLDER_MENU = VIEW_HOLDER_BANNER + 1;
    /**
     * Home页主题活动 2
     */
    public static final int VIEW_HOLDER_THEME_HOME = VIEW_HOLDER_MENU + 1;
    /**
     * 商品列表 3
     */
    public static final int VIEW_HOLDER_GOODS = VIEW_HOLDER_THEME_HOME + 1;

    /**
     * 固定主题 4
     */
    public static final int VIEW_HOLDER_IMG = VIEW_HOLDER_GOODS + 1;
    /**
     * Tab页主题活动 5
     */
    public static final int VIEW_HOLDER_THEME_TAB = VIEW_HOLDER_IMG + 1;

    List<Integer> viewHolderTypes = new ArrayList<>();
    private Fragment mFragment;
    SparseArrayCompat<Integer> titleIndexs = new SparseArrayCompat<>();
    private HashMap<String, Object> datas = new HashMap<>();
    private int tabType = -1;

    public StoreTabRecylAdapter(Fragment fragment, int tbType) {
        this.tabType = tbType;
        this.mFragment = fragment;
    }

    public void addVieHolderData(String key, Object valeu) {
        datas.put(key, valeu);
        notifyDataSetChanged();
    }

    public void addViewHolderType(int... type) {
        for (int i = 0; i < type.length; i++) {
            viewHolderTypes.add(type[i]);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Logger.i("获取具体的ViewHolder" + viewType);
        switch (viewType) {
            case VIEW_HOLDER_BANNER:
                return new ViewHolderBanner(getViewType(parent, R.layout.home_item_banner));
            case VIEW_HOLDER_MENU:
                return new ViewHolderMenu(getViewType(parent, R.layout.item_store_tab_menu));
            case VIEW_HOLDER_THEME_HOME:
                return new ViewHolderHomeTheme(getViewType(parent, R.layout.item_store_tab_theme_home));
            case VIEW_HOLDER_GOODS:
                return new ViewHolderGoods(getViewType(parent, R.layout.home_item_thriving_goods));
            case VIEW_HOLDER_IMG:
                return new ViewHolderImgTheme(getViewType(parent, R.layout.item_store_tab_img));
            case VIEW_HOLDER_THEME_TAB:
                return new ViewHolderTabTheme(getViewType(parent, R.layout.item_store_tab_theme_tab));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Logger.i("绑定ViewHolder的数据" + position);
        switch (position) {
            case VIEW_HOLDER_BANNER: {// 0
                if (tabType == StoreTabFragment.TAB_TYPE_HOME) {
                    if (datas.containsKey(Commons.STORE_HOME_BANNER)) {
                        if (holder instanceof ViewHolderBanner) {
                            ViewHolderBanner viewHolderBanner = (ViewHolderBanner) holder;
                            viewHolderBanner.setData((NY_BannerModel) datas.get(Commons.STORE_HOME_BANNER));
                        }
                    }
                } else {
                    if (datas.containsKey(STORE_HOME_TABS)) {
                        if (holder instanceof ViewHolderImgTheme) {
                            ViewHolderImgTheme viewHolderImgTheme = (ViewHolderImgTheme) holder;
                            String[] url_goodsis = ((String) datas.get(STORE_HOME_TABS)).split(",");
                            viewHolderImgTheme.setData(mFragment, url_goodsis[0], url_goodsis[1]);
                        }
                    }
                }
                break;
            }
            case VIEW_HOLDER_MENU: {// 1
                if (tabType == StoreTabFragment.TAB_TYPE_HOME) {
                    if (datas.containsKey(Commons.STORE_HOME_TABS)) {
                        if (holder instanceof ViewHolderMenu) {
                            ViewHolderMenu viewHolderMenu = (ViewHolderMenu) holder;
                            List<StoreHomeTabModel.DataBean.TubiaolistBean> tubiaolist =
                                    (List<StoreHomeTabModel.DataBean.TubiaolistBean>) datas.get(STORE_HOME_TABS);
                            viewHolderMenu.setData(mFragment, tubiaolist);
                        }
                    }
                } else {
                    if (datas.containsKey(Commons.STORE_HOME_TABS)) {
                        if (tabType == StoreTabFragment.TAB_TYPE_HOME) {
                            if (holder instanceof ViewHolderTabTheme) {
                                ViewHolderTabTheme viewHolderTabTheme = (ViewHolderTabTheme) holder;
                                NY_EatThemeModel model = (NY_EatThemeModel) datas.get(Commons.STORE_HOME_TABS);
                                viewHolderTabTheme.setData(mFragment, model.getData());
                            }
                        }

                    }
                }
                break;
            }
            case VIEW_HOLDER_THEME_HOME: {// 2
                if (tabType == StoreTabFragment.TAB_TYPE_HOME) {
                    if (datas.containsKey(Commons.STORE_HOME_THEME)) {
                        if (tabType == StoreTabFragment.TAB_TYPE_HOME) {
                            if (holder instanceof ViewHolderHomeTheme) {
                                ViewHolderHomeTheme viewHolderHomeTheme = (ViewHolderHomeTheme) holder;
                                NY_EatThemeModel model = (NY_EatThemeModel) datas.get(Commons.STORE_HOME_THEME);
                                viewHolderHomeTheme.setData(mFragment, model.getData());
                            }
                        }
                    }
                } else {
                    if (datas.containsKey(Commons.STORE_HOME_GOODSS)) {
                        if (holder instanceof ViewHolderGoods) {
                            ViewHolderGoods viewHolderGoods = (ViewHolderGoods) holder;
                            HomeHotGoodsModel model = (HomeHotGoodsModel) datas.get(Commons.STORE_HOME_GOODSS);
                            Logger.i("商品集合数量="+model.getData().size());
                            int index = position % 2;
                            int sta = index * 2;
                            int end = sta + 2;
                            List<HomeHotGoodsModel.DataBean> datas = model.getData().subList(sta, end);
                            viewHolderGoods.setData(mFragment, datas);
                        }
                    }
                }
                break;
            }
            default: {// 3
                if (datas.containsKey(Commons.STORE_HOME_GOODSS)) {
                    if (holder instanceof ViewHolderGoods) {
                        ViewHolderGoods viewHolderGoods = (ViewHolderGoods) holder;
                        HomeHotGoodsModel model = (HomeHotGoodsModel) datas.get(Commons.STORE_HOME_GOODSS);
                        int index = position % 3;
                        int sta = index * 2;
                        int end = sta + 2;
                        List<HomeHotGoodsModel.DataBean> datas = model.getData().subList(sta, end);
                        viewHolderGoods.setData(mFragment, datas);
                    }
                }
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        Logger.i("获取适配器数据量" + viewHolderTypes.size());
        return viewHolderTypes.size();
    }

    @Override
    public int getItemViewType(int position) {
        Logger.i("获取item类型" + position);
        return viewHolderTypes.get(position);
    }


    /**
     * 填充不同类型 item 布局
     *
     * @param parent
     * @param resource R资源
     * @return
     */
    public static View getViewType(ViewGroup parent, int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }

    /**
     * 滚动广告图 0
     */
    public class ViewHolderBanner extends RecyclerView.ViewHolder implements OnItemClickListener {
        ConvenientBanner mBannerNongyeHome;
        List<String> mUrls = new ArrayList<>();

        public ViewHolderBanner(View itemView) {
            super(itemView);
            mBannerNongyeHome = (ConvenientBanner) itemView.findViewById(R.id.home_banner);
            //设置指示器（小圆点）
            mBannerNongyeHome.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
            mBannerNongyeHome.setOnItemClickListener(this);
            mBannerNongyeHome.startTurning(3000);
        }

        public void setData(NY_BannerModel model) {
            mUrls.clear();
            if (model != null) {
                for (NY_BannerModel.Data data : model.getData()) {
                    mUrls.add(data.getShowlink());
                }
                mBannerNongyeHome.setPages(new CreateBannerHolder(), mUrls);
            }
        }

        @Override
        public void onItemClick(int position) {
            Logger.i("点击了广告图" + position);
        }

    }

    /**
     * 类型菜单 1
     */
    public class ViewHolderMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<ImageView> menus = new ArrayList<>(8);

        public ViewHolderMenu(View itemView) {
            super(itemView);
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu1));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu2));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu3));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu4));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu5));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu6));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu7));
            menus.add((ImageView) itemView.findViewById(R.id.iv_tab_menu8));
        }


        public void setData(Fragment fram, List<StoreHomeTabModel.DataBean.TubiaolistBean> icons) {
            StoreHomeTabModel.DataBean.TubiaolistBean bean = null;
            ImageView img = null;
            for (int i = 0; i < icons.size(); i++) {
                bean = icons.get(i);
                img = menus.get(i);
                if (img != null) {
                    Glide.with(fram).load(bean.getTypepic()).into(img);
                    img.setTag(R.id.tag_click, bean.getItemcode());
                    img.setOnClickListener(this);
                }
            }
        }

        @Override
        public void onClick(View v) {
            Logger.i("点击了商城首页按钮菜单=" + v.getTag(R.id.tag_click).toString());
        }
    }

    /**
     * Home页主题活动 2
     */
    public class ViewHolderHomeTheme extends RecyclerView.ViewHolder {
        List<ImageView> themes = new ArrayList<>(3);

        public ViewHolderHomeTheme(View itemView) {
            super(itemView);
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme1));
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme2));
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme3));
        }


        public void setData(Fragment fragment, List<NY_EatThemeModel.DataBean> datas) {
            ImageView img;
            for (int i = 0; i < datas.size(); i++) {
                img = themes.get(i);
                if (img == null) return;
                Glide.with(fragment)
                        .load(datas.get(i).getPic())
                        .into(img);
            }
        }
    }

    /**
     * 商品列表 3
     */
    public class ViewHolderGoods extends RecyclerView.ViewHolder implements View.OnClickListener {
        List<GridGoodsHolder> mGoodsHolders = new ArrayList<>(2);
        Fragment mContext;

        public ViewHolderGoods(View itemView) {
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
                Logger.i("打印价格="+datas.get(i).getPrice());
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
            Logger.i("点击了商城商品" + v.getTag(R.id.tag_click));
            BaseActivity.showToast(mContext.getActivity(), "点击了商品" + v.getTag(R.id.tag_click));
        }
    }

    /**
     * 固定主题 4
     */
    public class ViewHolderImgTheme extends RecyclerView.ViewHolder {
        private ImageView theme;

        public ViewHolderImgTheme(View itemView) {
            super(itemView);
            theme = (ImageView) itemView.findViewById(R.id.iv_tab_theme);
        }


        /**
         * 设置图片Tab主题信息
         *
         * @param fragment
         * @param url      主题图片
         * @param data     数据
         */
        public void setData(Fragment fragment, String url, String data) {
            Glide.with(fragment)
                    .load(url)
                    .into(theme);
        }
    }

    /**
     * Tab页主题活动 5
     */
    public class ViewHolderTabTheme extends RecyclerView.ViewHolder {
        List<ImageView> themes = new ArrayList<>(4);

        public ViewHolderTabTheme(View itemView) {
            super(itemView);
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme1));
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme2));
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme3));
            themes.add((ImageView) itemView.findViewById(R.id.iv_theme4));
        }

        public void setData(Fragment fragment, List<NY_EatThemeModel.DataBean> datas) {
            ImageView img;
            for (int i = 0; i < datas.size(); i++) {
                img = themes.get(i);
                if (img == null) return;
                Glide.with(fragment)
                        .load(datas.get(i).getPic())
                        .into(img);
            }
        }
    }

    /**
     * 畅销商品的item（单个）
     */
    public static class GridGoodsHolder {
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
