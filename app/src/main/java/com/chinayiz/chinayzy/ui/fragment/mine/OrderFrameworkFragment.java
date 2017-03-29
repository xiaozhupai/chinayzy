package com.chinayiz.chinayzy.ui.fragment.mine;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.OderPagerAdapter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.OrderListModel;
import com.chinayiz.chinayzy.presenter.OrderFrameworkPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/15 16:55
 * Class OrderFrameworkFragment
 */

public class OrderFrameworkFragment extends BaseFragment<OrderFrameworkPresenter> implements RadioGroup.OnCheckedChangeListener
        , ViewPager.OnPageChangeListener{
    public List<OrderFragment> mFragments;
    private OderPagerAdapter mPagerAdapter;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    public int orderType;
    private RadioButton rb_orderAll;
    private RadioButton rb_order1;
    public RadioButton rb_order2;
    private RadioButton rb_order3;
    private RadioButton rb_order4;
    private RadioGroup rg_orderState;
    private ViewPager vp_orderContent;
    @Override
    public void isNightMode(boolean isNight) {

    }
    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("我的订单");
    }
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_order,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFragments = new ArrayList<>(5);
        mFragments.add(OrderFragment.newInstance("0"));
        mFragments.add(OrderFragment.newInstance("1"));
        mFragments.add(OrderFragment.newInstance("2"));
        mFragments.add(OrderFragment.newInstance("3"));
        mFragments.add(OrderFragment.newInstance("4"));

        rg_orderState = (RadioGroup) view.findViewById(R.id.rg_orderState);
        rg_orderState.setOnCheckedChangeListener(this);
        rb_orderAll = (RadioButton) rg_orderState.findViewById(R.id.rb_orderAll);
        rb_order1 = (RadioButton) rg_orderState.findViewById(R.id.rb_order1);
        rb_order2 = (RadioButton) rg_orderState.findViewById(R.id.rb_order2);
        rb_order3 = (RadioButton) rg_orderState.findViewById(R.id.rb_order3);
        rb_order4 = (RadioButton) rg_orderState.findViewById(R.id.rb_order4);
        mPagerAdapter = new OderPagerAdapter(getFragmentManager(), mFragments);
        vp_orderContent = (ViewPager) view.findViewById(R.id.vp_orderContent);
        vp_orderContent.addOnPageChangeListener(this);
        vp_orderContent.setAdapter(mPagerAdapter);
        mActivity.mActionBar.setBackground(getResources().getDrawable(R.color.Background));
        switch (orderType){
            case 0:
                rb_orderAll.setChecked(true);
                break;
            case 1:
                rb_order1.setChecked(true);
                break;
            case 2:
                rb_order2.setChecked(true);
                break;
            case 3:
                rb_order3.setChecked(true);
                break;
            case 4:
                rb_order4.setChecked(true);
                break;
        }
    }

    @Override
    public void onInintData(Bundle bundle) {
        orderType=bundle.getInt("orderType",-1);
    }

    @Override
    public OrderFrameworkPresenter initPresenter() {
        return new OrderFrameworkPresenter();
    }

    public void showDilog(int type, final OrderListModel.Order.Goods goods){
        builder = new AlertDialog.Builder(getActivity());
        switch (type){
            case 0:
                alert = null;
                alert = builder
                        .setMessage("确定删除订单？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.mRequestUtils.deleteOrder(String.valueOf(goods.getOrderid()));
                                mPresenter.doGteOrderList(String.valueOf(mPresenter.state),mPresenter.state);
                                Logger.i("删除订单成功");
                            }
                        }).create();

                alert.show();
                break;
            case 1:
                alert = null;
                alert = builder
                        .setMessage("确定取消订单？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.mRequestUtils.cancelOrder(String.valueOf(goods.getOrderid()));
                                mPresenter.doGteOrderList(String.valueOf(mPresenter.state),mPresenter.state);
                                Logger.i("取消订单成功");
                            }
                        }).create();

                alert.show();
                break;
            case 2:
                alert = null;
                alert = builder
                        .setTitle("确认收货")
                        .setMessage("您要确认已经收到此订单中的商品？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.mRequestUtils.recognizelOrder(String.valueOf(goods.getOrderid()));
                                mPresenter.doGteOrderList(String.valueOf(mPresenter.state),mPresenter.state);
                            }
                        }).create();

                alert.show();
                break;
        }
    }
    @Override
    protected void onVisible() {

    }
    @Override
    protected void onInvisible() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        mActivity.mTvActionBarTitle.setTextColor(Color.BLACK);
        switch (checkedId){
            case R.id.rb_orderAll:
                vp_orderContent.setCurrentItem(0);
                mFragments.get(0).onVisible();
                mPresenter.doGteOrderList("0",0);
                break;
            case R.id.rb_order1:
                vp_orderContent.setCurrentItem(1);
                mFragments.get(1).onVisible();
                mPresenter.doGteOrderList("1", 1);
                break;
            case R.id.rb_order2:
                vp_orderContent.setCurrentItem(2);
                mFragments.get(2).onVisible();
                mPresenter.doGteOrderList("2", 2);
                break;
            case R.id.rb_order3:
                vp_orderContent.setCurrentItem(3);
                mFragments.get(3).onVisible();
                mPresenter.doGteOrderList("3", 3);
                break;
            case R.id.rb_order4:
                vp_orderContent.setCurrentItem(4);
                mFragments.get(4).onVisible();
                mPresenter.doGteOrderList("4", 4);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rb_orderAll.setChecked(true);
                break;
            case 1:
                rb_order1.setChecked(true);
                break;
            case 2:
                rb_order2.setChecked(true);
                break;
            case 3:
                rb_order3.setChecked(true);
                break;
            case 4:
                rb_order4.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
