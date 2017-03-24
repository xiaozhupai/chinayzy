package com.chinayiz.chinayzy.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.GoodsDetailGridAdpter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.RelatedGoodsModel;
import com.chinayiz.chinayzy.views.goodsDetail.NoScrollGridView;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/16 16:42
 * Class GoodsListFragment 相关商品
 */
public class GoodsListFragment extends Fragment {
    private NoScrollGridView mGoodsList;
    private GoodsDetailGridAdpter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goodslist, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        mGoodsList = (NoScrollGridView) view.findViewById(R.id.gv_goodsList);
        mAdapter = new GoodsDetailGridAdpter(getActivity());
    }

    public void setData(EventMessage message) {
        RelatedGoodsModel model = (RelatedGoodsModel) message.getData();
        mAdapter.setData(model);
        mGoodsList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public static GoodsListFragment newInstance() {
        Bundle args = new Bundle();
        GoodsListFragment fragment = new GoodsListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * 动态计算GridView滑动的位置
     * @return
     */
    public int getScrllY() {
        if (mGoodsList!=null){
            View c = mGoodsList.getChildAt(0);
            if (c == null) {
                return 0;
            }
            int firstVisiblePosition = mGoodsList.getFirstVisiblePosition();
            int top = c.getTop();
            return -top + firstVisiblePosition * c.getHeight();
        }
        return  0;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoodsList = null;
    }
}
