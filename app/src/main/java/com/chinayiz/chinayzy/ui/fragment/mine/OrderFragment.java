package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.OrderPresenter;

/**我的订单
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment<OrderPresenter> implements View.OnClickListener {


    private TextView tv_order_all;
    private TextView tv_order_wait_pay;
    private TextView tv_order_wait_send_goods;
    private TextView tv_order_accept_goods;
    private TextView tv_order_wait_evaluate;
    private View v_order_slide;
    private ViewPager vp_order;

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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, null);
        tv_order_all = (TextView) view.findViewById(R.id.tv_order_all);
        tv_order_all.setOnClickListener(this);
        tv_order_wait_pay = (TextView) view.findViewById(R.id.tv_order_wait_pay);
        tv_order_wait_pay.setOnClickListener(this);
        tv_order_wait_send_goods = (TextView) view.findViewById(R.id.tv_order_wait_send_goods);
        tv_order_wait_send_goods.setOnClickListener(this);
        tv_order_accept_goods = (TextView) view.findViewById(R.id.tv_order_accept_goods);
        tv_order_accept_goods.setOnClickListener(this);
        tv_order_wait_evaluate = (TextView) view.findViewById(R.id.tv_order_wait_evaluate);
        tv_order_wait_evaluate.setOnClickListener(this);
        v_order_slide = (View) view.findViewById(R.id.v_order_slide);
        v_order_slide.setOnClickListener(this);
        vp_order = (ViewPager) view.findViewById(R.id.vp_order);
        vp_order.setOnClickListener(this);
        return view;

    }

    @Override
    public OrderPresenter initPresenter() {
        return new OrderPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.fragment_order, null);
        initView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_all:

                break;
            case R.id.tv_order_wait_pay:

                break;
            case R.id.tv_order_wait_send_goods:

                break;
            case R.id.tv_order_accept_goods:

                break;
            case R.id.tv_order_wait_evaluate:

                break;
        }
    }
}
