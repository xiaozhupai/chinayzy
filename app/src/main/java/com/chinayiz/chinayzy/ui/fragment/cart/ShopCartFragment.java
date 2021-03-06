package com.chinayiz.chinayzy.ui.fragment.cart;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.ShopCartAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.presenter.ShopCartPresenter;
import com.chinayiz.chinayzy.ui.common.GoodsMainFragment;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.chinayiz.chinayzy.views.pullable.PullableListView;
import com.chinayiz.chinayzy.widget.GoodsStandardPopuWindow;
import com.chinayiz.chinayzy.widget.LoadlingDialog;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 购物车
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ShopCartFragment extends BaseFragment<ShopCartPresenter> implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    //红包购物车
    public static final int REDPACKET = 0;
    //普通购物车
    public static final int COMMON = 1;
    public int cartType = COMMON;
    public RelativeLayout rl_shopcart;
    private PullableListView listv_shopcart;
    public CheckImageView iv_shopcart_radio;
    public TextView tv_shopcart_price;
    public TextView tv_shopcart_submit;
    public LinearLayout lv_boom, ll_no_goods;
    public SmartRefreshLayout smartRefreshLayout;
    public ShopCartAdaphter adaphter;
    public TextView tv_shopcart_all;
    public boolean isClick = true;
    public GoodsStandardPopuWindow popuWindow;
    public int index;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_EDITER = 1;
    public LoadlingDialog loadlingDialog;
    public boolean isCheck;

    public ShopCartFragment() {
    }

    public ShopCartFragment(int type) {
        this.cartType = type;
    }

    public static ShopCartFragment getInstance(int type) {
        return new ShopCartFragment(type);
    }

    @Override
    public void onInintData(Bundle bunbdle) {
        this.cartType = bunbdle.getInt("cartType", COMMON);
    }

    @Override
    protected void onVisible() {
        mPresenter.getData();
        Logger.i("ShopCartFragment 可见");
    }

    @Override
    protected void onInvisible() {
        Logger.i("ShopCartFragment 不可见");
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onInitActionBar(final BaseActivity activity) {
        activity.mTvActionBarTitle.setText("购物车");
        activity.mIvActionBarMore.setVisibility(View.GONE);
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("编辑");
        activity.mCbActionBarEdit.setOnCheckedChangeListener(this);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shop_cart, null);
        rl_shopcart = (RelativeLayout) view.findViewById(R.id.rl_shopcart);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.pullrefresh);
        listv_shopcart = (PullableListView) view.findViewById(R.id.listv_shopcart);
        iv_shopcart_radio = (CheckImageView) view.findViewById(R.id.iv_shopcart_radio);
        tv_shopcart_price = (TextView) view.findViewById(R.id.tv_shopcart_price);
        tv_shopcart_submit = (TextView) view.findViewById(R.id.tv_shopcart_submit);
        lv_boom = (LinearLayout) view.findViewById(R.id.lv_boom);
        tv_shopcart_all = (TextView) view.findViewById(R.id.tv_shopcart_all);
        ll_no_goods = (LinearLayout) view.findViewById(R.id.ll_no_goods);
        tv_shopcart_submit.setOnClickListener(this);
        iv_shopcart_radio.setOnClickListener(this);
        listv_shopcart.setOnItemClickListener(this);
//        view.findViewById(R.id.loadlayout).setVisibility(View.GONE);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getData();
            }
        });
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));

        adaphter = new ShopCartAdaphter(getActivity(), null, iv_shopcart_radio, tv_shopcart_price, tv_shopcart_all, 0);
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
    public void onClick(View v) {
        switch (v.getId()) {
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
        if (!isCheck) {
            ShopCartModel.DataBean.ShoplistBean bean = (ShopCartModel.DataBean.ShoplistBean) adapterView.getItemAtPosition(i);
            Skip.toNewGoodsDetail(getActivity(), bean.getGoodsid() + "", GoodsMainFragment.COMMON);
        }
    }


    /**
     * 更新adaphter
     *
     * @param type 0编辑前  1编辑后
     */
    public void UpdateUi(int type) {
        mPresenter.type = type;
        adaphter.setData(mPresenter.list, type);
        if (type == TYPE_NORMAL) {
            adaphter.UpdateTotal();
        }
    }

    //更新购物车
    public void UpdateShopCart() {
        StringBuilder sb = new StringBuilder();
        for (ShopCartModel.DataBean data : mPresenter.list) {
            for (int i = 0; i < data.getShoplist().size(); i++) {
                ShopCartModel.DataBean.ShoplistBean bean = data.getShoplist().get(i);
                sb.append(bean.getCarid());
                sb.append("/");
                sb.append(bean.getNum() + "");
                sb.append("/");
                sb.append(bean.getGoodsstandardid());
                sb.append(",");

            }
        }
        if (loadlingDialog == null) {
            loadlingDialog = new LoadlingDialog(getActivity());
        }
        loadlingDialog.show();
        if (cartType==COMMON){
            CommonRequestUtils.getRequestUtils().getUpdateCart(sb.toString());
        } else if (cartType==REDPACKET) {
            CommonRequestUtils.getRequestUtils().updateRedPacketCar(sb.toString());
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.isCheck = isChecked;
        if (isChecked) {
            buttonView.setText("完成");
            tv_shopcart_submit.setText("删除");
            tv_shopcart_price.setVisibility(View.GONE);
            isClick = false;
            UpdateUi(1);
        } else {
            buttonView.setText("编辑");
            tv_shopcart_submit.setText("结算");
            tv_shopcart_price.setVisibility(View.VISIBLE);
            isClick = true;
            UpdateUi(0);
            UpdateShopCart();
        }
    }
}
