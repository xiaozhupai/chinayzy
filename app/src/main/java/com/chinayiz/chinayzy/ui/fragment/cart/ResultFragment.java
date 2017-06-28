package com.chinayiz.chinayzy.ui.fragment.cart;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ResultAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.presenter.ResultPresenter;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 结算订单
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ResultFragment extends BaseFragment<ResultPresenter> implements View.OnClickListener{
    public ListView lv_result;
    public TextView tv_result_price,tv_no_address;
    public TextView tv_result_submit;
    public RelativeLayout result_list;
    public ResultAdaphter adaphter;
    public TextView tv_goods_total;
    public TextView tv_cost,tv_luckly_money;
    public CheckBox cb_check,cb_luckey_money;
    public RelativeLayout rl_payway_boom;
    public CheckImageView iv_pay_ali,iv_pay_yzb;
    public CheckImageView iv_pay_wechat;
    public LinearLayout lv_payway;
    public TextView tv_pay_way;
    public List<ResultModel.DataBean.GoodmessageBean> list;
    public String params;
    public TextView tv_address_name,tv_address_phone,tv_address_text,tv_deducpoint;
    public int index;
    public ImageView iv_luckly_money;
    public CommonActivity activity;
    public static final int WECHAR_BACK=1;


    @Override
    public void onInintData(Bundle bundle) {
        this.params=bundle.getString("params");


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
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("结算订单");

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, null);
        lv_result = (ListView) view.findViewById(R.id.lv_result);
        tv_result_price = (TextView) view.findViewById(R.id.tv_result_price);
        tv_result_submit = (TextView) view.findViewById(R.id.tv_result_submit);
        tv_result_submit.setOnClickListener(this);
        result_list = (RelativeLayout) view.findViewById(R.id.result_list);
        View head = View.inflate(getActivity(), R.layout.result_head, null);
        tv_address_name= (TextView) head.findViewById(R.id.tv_address_name);
        tv_address_phone= (TextView) head.findViewById(R.id.tv_address_phone);
        tv_address_text= (TextView) head.findViewById(R.id.tv_address_text);
        tv_no_address= (TextView) head.findViewById(R.id.tv_no_address);
        lv_result.addHeaderView(head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.addFragment(new AddressListFragment());
            }
        });
        View foot = View.inflate(getActivity(), R.layout.result_foot, null);
        tv_pay_way= (TextView) foot.findViewById(R.id.tv_pay_way);
        tv_goods_total = (TextView) foot.findViewById(R.id.tv_goods_total);
        tv_deducpoint= (TextView) foot.findViewById(R.id.tv_deducpoint);
        tv_cost = (TextView) foot.findViewById(R.id.tv_cost);
        cb_check = (CheckBox) foot.findViewById(R.id.cb_check);
        rl_payway_boom = (RelativeLayout) foot.findViewById(R.id.rl_payway_boom);
        iv_pay_ali = (CheckImageView) foot.findViewById(R.id.iv_ali_pay);
        iv_pay_wechat = (CheckImageView) foot.findViewById(R.id.iv_wechat_pay);
        lv_payway = (LinearLayout) foot.findViewById(R.id.lv_payway);
        tv_luckly_money= (TextView) foot.findViewById(R.id.tv_luckly_money);
        cb_luckey_money= (CheckBox) foot.findViewById(R.id.cb_luckey_money);
        iv_luckly_money= (ImageView) foot.findViewById(R.id.iv_luckly_money);

        iv_luckly_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("优惠券信息");
                if (mPresenter.resultModel.getData()!=null){
                    if (mPresenter.resultModel.getData().getCoupon()!=null){
                        MessageDialog dialog=new MessageDialog(getActivity());
                            dialog.message.setText(mPresenter.resultModel.getData().getCoupon().getCouponremark());
                            dialog.message.setGravity(Gravity.LEFT);
                            dialog.vButton1.setVisibility(View.GONE);
                            dialog.vTitle.setVisibility(View.VISIBLE);
                            dialog.vTitle.setText("现金券使用规则");

                        dialog.show();
                    }                    }

            }
        });
        iv_pay_ali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //支付宝支付
                tv_pay_way.setText("支付宝");
                if (!iv_pay_ali.isCheck){
                    iv_pay_ali.setCheck(true);
                    iv_pay_wechat.setCheck(false);

                }
            }
        });
        iv_pay_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //微信支付
                tv_pay_way.setText("微信");
                if (!iv_pay_wechat.isCheck){
                    iv_pay_ali.setCheck(false);
                    iv_pay_wechat.setCheck(true);

                }
            }
        });


        cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPresenter.ChangeDeducpoint(b);
            }
        });

        rl_payway_boom.setOnClickListener(this);
        iv_pay_ali.setCheck(true);
        iv_pay_wechat.setCheck(false);
        lv_result.addFooterView(foot);
        adaphter = new ResultAdaphter(getActivity(), null,null);
        lv_result.setAdapter(adaphter);
        return view;

    }

    @Override
    public ResultPresenter initPresenter() {
        return new ResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_result_submit:  //确定支付
                mPresenter.submit();
                break;
            case R.id.rl_payway_boom:    //底部支付方式
                if (lv_payway.getVisibility()==View.VISIBLE){
                    lv_payway.setVisibility(View.GONE);
                }else {
                    lv_payway.setVisibility(View.VISIBLE);
                    lv_result.setSelection(lv_result.getBottom());
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mPresenter.status==1){
            mPresenter.success();
        }
    }
}
