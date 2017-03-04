package com.chinayiz.chinayzy.ui.fragment;

import android.annotation.SuppressLint;
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
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.views.MyGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/16 16:42
 * Class GoodsListFragment
 */
public class GoodsListFragment extends Fragment {
    private MyGridView mGoodsList;
    private String mTypeCode="1";
    private GoodsDetailGridAdpter mAdapter;

    public GoodsListFragment(String typeCode) {
        mTypeCode = typeCode;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goodslist, container, false);
        initView(view);
        EventBus.getDefault().register(this);
        return view;
    }

    private void initView(View view) {
        mGoodsList = (MyGridView) view.findViewById(R.id.gv_goodsList);
        mAdapter = new GoodsDetailGridAdpter(getActivity());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(EventMessage message) {
        if (EventMessage.NET_EVENT == message.getEventType()) {
            switch (message.getDataType()){
                case Commons.GOODS_RELATED:{
                    RelatedGoodsModel model = (RelatedGoodsModel) message.getData();
                    mAdapter.setData(model);
                    mGoodsList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    break;
                }

            }

        }
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
        EventBus.getDefault().unregister(this);
        mGoodsList = null;
        mTypeCode = null;

    }
}
