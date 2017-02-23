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
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.views.MyGridView;
import com.orhanobut.logger.Logger;

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
    private String mTypeCode;
    private GoodsDetailGridAdpter mAdapter;
    private CommonRequestUtils mReques = CommonRequestUtils.getRequestUtils();

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

    @Override
    public void onResume() {
        super.onResume();
        mReques.getRelatedGoods(mTypeCode, "1", "14");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(EventMessage message) {
        if (EventMessage.NET_EVENT == message.getEventType() && Commons.GOODS_RELATED.equals(message.getDataType())) {
            RelatedGoodsModel model = (RelatedGoodsModel) message.getData();
            Logger.i("相关商品数据返回=" + model.getData().size());
            mAdapter.setData(model);
            mGoodsList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 动态计算GridView滑动的位置
     * @return
     */
    public int getScrllY() {
        View c = mGoodsList.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = mGoodsList.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mReques = null;
        mGoodsList = null;
        mTypeCode = null;

    }
}
