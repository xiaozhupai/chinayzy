package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.RedpacketListViewAdapter;
import com.chinayiz.chinayzy.adapter.RedpacketRecycleAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeDetailModel;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeModel;
import com.chinayiz.chinayzy.presenter.RedpacketPrecenter;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * 红包专场
 * A simple {@link Fragment} subclass. NewClassifyFragment
 */
public class RedpacketFragment extends BaseFragment<RedpacketPrecenter> implements View.OnClickListener, RedpacketListViewAdapter.OnItemSeletdListener {

    public ListView red_packet_listview;
    public RecyclerView red_packet_recyclerview;
    private RelativeLayout rl_pay_record;
    private Button bt_red_packet_shopcart;
    private View view;
    public RedpacketListViewAdapter mLvAdapter;
    public SmartRefreshLayout rv_refresh;
    public String pic;
    private boolean isFirst = true;

    public RedpacketRecycleAdapter mRvAdapter;
    public GridLayoutManager mGridLayoutManager;

    public RedpacketFragment() {
    }

    @Override
    public RedpacketPrecenter initPresenter() {
        return new RedpacketPrecenter();
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("红包专场");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_redpacket_goods, container, false);
        init();
        return view;
    }

    private void init() {
        red_packet_listview = (ListView) view.findViewById(R.id.red_packet_listview);
        red_packet_recyclerview = (RecyclerView) view.findViewById(R.id.red_packet_recyclerview);
        rv_refresh = (SmartRefreshLayout) view.findViewById(R.id.rv_refresh);
        rl_pay_record = (RelativeLayout) view.findViewById(R.id.rl_pay_record);
        bt_red_packet_shopcart = (Button) view.findViewById(R.id.bt_red_packet_shopcart);
        rl_pay_record.setOnClickListener(this);
        bt_red_packet_shopcart.setOnClickListener(this);

        //listview
        mLvAdapter = new RedpacketListViewAdapter(getActivity(), null);
        mLvAdapter.setItemSeletdListener(this);
        red_packet_listview.setAdapter(mLvAdapter);

        //recycleview
        mRvAdapter = new RedpacketRecycleAdapter(getActivity(), null);
        mRvAdapter.setRefreshLayout(rv_refresh);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //添加pic核心代码
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果位置是0，那么这个条目将占用SpanCount()这么多的列数，在此也就是2
                //而如果不是0，则说明不是Header，就占用1列即可
                return mRvAdapter.isHeader(position) ? mGridLayoutManager.getSpanCount() : 1;
            }
        });

        red_packet_recyclerview.setLayoutManager(mGridLayoutManager);
        red_packet_recyclerview.setAdapter(mRvAdapter);

        rv_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mRvAdapter.onRefresh();
            }
        });
        rv_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mRvAdapter.LoadMore();
            }
        });
        rv_refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        rv_refresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        mRvAdapter.setOnItemClickListener(new RedpacketRecycleAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Skip.toNewGoodsDetail(getActivity(), mRvAdapter.getList().get(postion - 1).getGoodsid() + "", GoodsMainFragment.REDPACKET);
            }
        });
    }


    @Override
    public void onSeletItem(int position, ShowClassifyCodeModel.DataBean data) {
        if (!isFirst) {
            mRvAdapter.clearData();
        }
        isFirst = false;
        ShowClassifyCodeModel.DataBean dataBean = data;
        //头条图片
        pic = dataBean.getPic();
        mRvAdapter.setPic(pic);
        mLvAdapter.changeSelected(position);
        //入参
        mRvAdapter.setItemcode(dataBean.getItemcode());
        mRvAdapter.setPage(1);
        //网络请求
        mPresenter.getGoods(dataBean.getItemcode());

    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pay_record: //消费记录
                Skip.toRedpacketRecord(getActivity());
                break;
            case R.id.bt_red_packet_shopcart://红包专场购物车
                Skip.toShopCart(getActivity(), ShopCartFragment.REDPACKET);
                break;
        }
    }
}
