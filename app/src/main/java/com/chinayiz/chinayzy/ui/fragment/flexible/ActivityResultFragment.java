package com.chinayiz.chinayzy.ui.fragment.flexible;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ResultAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.ResultModel;
import com.chinayiz.chinayzy.presenter.ActivityResultPresenter;
import com.chinayiz.chinayzy.ui.activity.CommonActivity;
import com.chinayiz.chinayzy.views.CheckImageView;

import java.util.List;

/** 活动支付页面
 * A simple {@link Fragment} subclass.
 */
public class ActivityResultFragment extends BaseFragment<ActivityResultPresenter> implements View.OnClickListener {

    public TextView tv_result_price,tv_titel,tv_price;
    public TextView tv_result_submit;
    public RelativeLayout result_list;
    public ResultAdaphter adaphter;
    public TextView tv_goods_total;
    public TextView tv_cost;

    public RelativeLayout rl_payway_boom;
    public CheckImageView iv_pay_ali,iv_pay_yzb;
    public CheckImageView iv_pay_wechat;
    public LinearLayout lv_payway;
    public TextView tv_pay_way;
    public List<ResultModel.DataBean.GoodmessageBean> list;
    public String params;
    public ImageView iv_pic;


    public int index;
    public ImageView iv_luckly_money;
    public CommonActivity activity;
    public static final int WECHAR_BACK=1;
    public String crowdfid;
    public RelativeLayout rl_yzb;

public ActivityResultFragment(){

}
    @Override
    public void onInintData(Bundle bundle) {
        crowdfid=bundle.getString("crowdfid");


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
        View foot = inflater.inflate(R.layout.fragment_activity_result, null);
        tv_result_price = (TextView) foot.findViewById(R.id.tv_result_price);
        tv_result_submit = (TextView) foot.findViewById(R.id.tv_result_submit);
        tv_result_submit.setOnClickListener(this);
        result_list = (RelativeLayout) foot.findViewById(R.id.result_list);
        tv_pay_way= (TextView) foot.findViewById(R.id.tv_pay_way);
        tv_goods_total = (TextView) foot.findViewById(R.id.tv_goods_total);
        tv_cost = (TextView) foot.findViewById(R.id.tv_cost);
        rl_payway_boom = (RelativeLayout) foot.findViewById(R.id.rl_payway_boom);
        iv_pay_ali = (CheckImageView) foot.findViewById(R.id.iv_ali_pay);
        iv_pay_wechat = (CheckImageView) foot.findViewById(R.id.iv_wechat_pay);
        lv_payway = (LinearLayout) foot.findViewById(R.id.lv_payway);
        iv_pic= (ImageView) foot.findViewById(R.id.iv_pic);
        tv_titel= (TextView) foot.findViewById(R.id.tv_title);
        tv_price= (TextView) foot.findViewById(R.id.tv_price);
        rl_yzb= (RelativeLayout) foot.findViewById(R.id.rl_yzb);


        iv_pay_yzb= (CheckImageView) foot.findViewById(R.id.iv_yzb_pay);

        iv_pay_ali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //支付宝支付
                tv_pay_way.setText("支付宝");
                if (!iv_pay_ali.isCheck){
                    iv_pay_ali.setCheck(true);
                    iv_pay_wechat.setCheck(false);
                    iv_pay_yzb.setCheck(false);
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
                    iv_pay_yzb.setCheck(false);
                }
            }
        });
        iv_pay_yzb.setOnClickListener(new View.OnClickListener() {  //亿众币支付
            @Override
            public void onClick(View v) {
                tv_pay_way.setText("亿众币");
                if (!iv_pay_yzb.isCheck){
                    iv_pay_ali.setCheck(false);
                    iv_pay_wechat.setCheck(false);
                    iv_pay_yzb.setCheck(true);
                }
            }
        });



        rl_payway_boom.setOnClickListener(this);
        iv_pay_ali.setCheck(true);
        iv_pay_wechat.setCheck(false);

        adaphter = new ResultAdaphter(getActivity(), null,null);

        return foot;

    }

    @Override
    public ActivityResultPresenter initPresenter() {
        return new ActivityResultPresenter();
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
