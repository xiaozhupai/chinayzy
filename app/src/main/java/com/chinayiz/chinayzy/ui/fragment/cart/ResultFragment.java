package com.chinayiz.chinayzy.ui.fragment.cart;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.ui.fragment.mine.AddressListFragment;
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import java.util.List;

/**
 * 结算订单
 * A simple {@link Fragment} subclass.
 */

public class ResultFragment extends BaseFragment<ResultPresenter> implements View.OnClickListener, IWXAPIEventHandler {
    public ListView lv_result;
    public TextView tv_result_price,tv_no_address;
    public TextView tv_result_submit;
    public RelativeLayout result_list;
    public ResultAdaphter adaphter;
    public TextView tv_goods_total;
    public TextView tv_cost;
    public CheckBox cb_check;
    public RelativeLayout rl_payway_boom;
    public CheckImageView iv_pay_ali;
    public CheckImageView iv_pay_wechat;
    public LinearLayout lv_payway;
    public TextView tv_pay_way;
    public List<ResultModel.DataBean.GoodmessageBean> list;
    public String params;
    public TextView tv_address_name,tv_address_phone,tv_address_text,tv_deducpoint;
    public int index;
    public CommonActivity activity;


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
        iv_pay_ali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //支付宝支付
                tv_pay_way.setText("支付宝");
                if (iv_pay_wechat.isCheck){
                    iv_pay_wechat.setCheck(false);
                    iv_pay_ali.setCheck(true);
                }
            }
        });
        iv_pay_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //微信支付
                tv_pay_way.setText("微信");
                if (iv_pay_ali.isCheck){
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        return view;
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
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i("ResultFragment", "onPayFinish, errCode = " + baseResp.errCode);
//              if (baseResp.getType()==ConstantsAPI.)


//        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(baseResp.errCode)));
//            builder.show();
//        }
    }
}
