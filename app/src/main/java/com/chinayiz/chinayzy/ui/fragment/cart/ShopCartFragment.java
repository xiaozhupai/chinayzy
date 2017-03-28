package com.chinayiz.chinayzy.ui.fragment.cart;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.ShopCartAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.ActionBarControlModel;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.presenter.ShopCartPresenter;
import com.chinayiz.chinayzy.ui.activity.NongYeMainActivity;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.pullable.PullableListView;
import com.chinayiz.chinayzy.widget.GoodsStandardPopuWindow;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment<ShopCartPresenter> implements View.OnClickListener, AdapterView.OnItemClickListener {
    public RelativeLayout rl_shopcart;
    private PullableListView listv_shopcart;
    public CheckImageView iv_shopcart_radio;
    public TextView tv_shopcart_price;
    private TextView tv_shopcart_submit;
    public LinearLayout lv_boom,ll_no_goods;
    public PullToRefreshLayout pullToRefreshLayout;
    public ShopCartAdaphter adaphter;
    public List<ShopCartModel.DataBean> list=new ArrayList<>();
    public TextView tv_shopcart_all;
    public boolean isClick=true;
    public GoodsStandardPopuWindow popuWindow;
    public int index;


    @Override
    public void onInintData(Bundle bundle) {
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onInitActionBar(final BaseActivity  activity) {
        activity.mTvActionBarTitle.setText("购物车");
        activity.mIvActionBarMore.setVisibility(View.GONE);
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("编辑");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick){  //编辑后
                    activity.mCbActionBarEdit.setText("完成");
                    tv_shopcart_submit.setText("删除");
                    tv_shopcart_price.setVisibility(View.GONE);
                    isClick=false;
                    mPresenter.UpdateUi(1);
                }else {   //编辑前
                    activity.mCbActionBarEdit.setText("编辑");
                    tv_shopcart_submit.setText("结算");
                    tv_shopcart_price.setVisibility(View.VISIBLE);
                    isClick=true;
                    mPresenter.UpdateUi(0);
                    mPresenter.UpdateShopCart();
                }
            }
        });
    }



    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shop_cart, null);
        rl_shopcart= (RelativeLayout) view.findViewById(R.id.rl_shopcart);
        pullToRefreshLayout=(PullToRefreshLayout) view.findViewById(R.id.pullrefresh);
        listv_shopcart = (PullableListView) view.findViewById(R.id.listv_shopcart);
        iv_shopcart_radio = (CheckImageView) view.findViewById(R.id.iv_shopcart_radio);
        tv_shopcart_price = (TextView) view.findViewById(R.id.tv_shopcart_price);
        tv_shopcart_submit = (TextView) view.findViewById(R.id.tv_shopcart_submit);
        lv_boom = (LinearLayout) view.findViewById(R.id.lv_boom);
        tv_shopcart_all= (TextView) view.findViewById(R.id.tv_shopcart_all);
        ll_no_goods= (LinearLayout) view.findViewById(R.id.ll_no_goods);
        tv_shopcart_submit.setOnClickListener(this);
        iv_shopcart_radio.setOnClickListener(this);
        listv_shopcart.setOnItemClickListener(this);
        view.findViewById(R.id.loadlayout).setVisibility(View.GONE);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                             mPresenter.getData();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
        adaphter=new ShopCartAdaphter(getActivity(),list,iv_shopcart_radio,tv_shopcart_price,tv_shopcart_all,0);
        listv_shopcart.setAdapter(adaphter);

        return view;
    }

    @Override
    public ShopCartPresenter initPresenter() {
        return new ShopCartPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_shopcart_submit:  //结算或者删除

                mPresenter.submit();
                break;
            case R.id.iv_shopcart_radio:   //是否全选
                mPresenter.UpdateAll();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ShopCartModel.DataBean.ShoplistBean bean= (ShopCartModel.DataBean.ShoplistBean) adapterView.getItemAtPosition(i);
        Skip.toGoodsDetail(getActivity(),bean.getGoodsid()+"");
    }

    @Override
    public void onResume() {
        EventBus.getDefault().post(new EventMessage(BaseMessage.NET_EVENT,
                NongYeMainActivity.NYMAIN_ACTIONBAR, new ActionBarControlModel(NongYeMainActivity.SHOW_ALL, "购物车", 1, 0, 1, 0)));
        super.onResume();

    }
}
