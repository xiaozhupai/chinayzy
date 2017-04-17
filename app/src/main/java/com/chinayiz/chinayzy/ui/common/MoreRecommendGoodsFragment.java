package com.chinayiz.chinayzy.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.presenter.MoreRecommendGoodsPresent;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/4/15 16:52
 * Class MoreRecommendGoodsFragment
 */

public class MoreRecommendGoodsFragment extends BaseFragment<MoreRecommendGoodsPresent> {
    private GridView gv_goodsList;
    private boolean RelatGoodsisSetData = true;
    private RelatedGoodsModel mRelatedGoodslist;
    public String itemCode;


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_recommend, container, false);
        gv_goodsList = (GridView) view.findViewById(R.id.gv_goodsList);
        return view;
    }

    public void setRelatGoodsList(RelatedGoodsModel relatedGoodsModel) {
        if (!RelatGoodsisSetData) {
            return;
        }
        RelatGoodsisSetData = false;
        mRelatedGoodslist = relatedGoodsModel;
        GoodsDetailGridAdpter detailGridAdpter = new GoodsDetailGridAdpter(getActivity());
        detailGridAdpter.setData(mRelatedGoodslist);
        gv_goodsList.setAdapter(detailGridAdpter);
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("为你推荐");
        activity.mIvActionBarMore.setVisibility(View.GONE);
        super.onInitActionBar(activity);
    }

    @Override
    public void onInintData(Bundle bundle) {
        itemCode = bundle.getString("itemCode", "-1");
    }

    @Override
    public MoreRecommendGoodsPresent initPresenter() {
        return new MoreRecommendGoodsPresent();
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {
    }
}
