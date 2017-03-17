package com.chinayiz.chinayzy.ui.common;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.chinayiz.chinayzy.adapter.viewHolder.GoodsHolder;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.presenter.GoodsPresenter;
import com.chinayiz.chinayzy.ui.fragment.CommentsFragment;
import com.chinayiz.chinayzy.ui.fragment.GoodsListFragment;
import com.chinayiz.chinayzy.views.goodsParticular.BotContentPage;
import com.chinayiz.chinayzy.views.goodsParticular.McoySnapPageLayout;
import com.chinayiz.chinayzy.views.goodsParticular.TopDetailInfoPage;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/17 19:20
 * Class GoodsFragment
 */

public class GoodsFragment extends BaseFragment<GoodsPresenter> {
    public final static String REFRESH = "GoodsActivity_refresh";
    private GoodsListFragment mGoodssFragment;
    private CommentsFragment mCommentFragment;
    private PartWebFragment mPartWebFragment;
    private CommonRequestUtils mRequestUtils;
    private McoySnapPageLayout mMlMcoySnapPageLayout = null;
    private FragmentManager mFragmentManager;
    private TopDetailInfoPage mTopPage = null;
    private BotContentPage mBottomPage = null;
    private GoodsHolder mGoodsHolder = null;
    private List<String> urls = new ArrayList<>();
    private boolean isFirst = true;
    private boolean isShowComments;
    private boolean isRefresh=false;
    private CheckBox mRbFavorite;
    private GoodsDetailModel model = null;
    private ImageView mIvBackBtn;
    private int sumComment = 0;
    private View mPregress;
    private String goodsID, storeID;
    private int comitsID = 0;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onInintData(Bundle bundle) {

    }

    @Override
    public void onInitActionBar(BaseActivity activity) {

    }

    @Override
    public GoodsPresenter initPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
