package com.chinayiz.chinayzy.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.database.UserSeeion;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.presenter.MinePresenter;
import com.chinayiz.chinayzy.views.CircleImageView;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.orhanobut.logger.Logger;


/**
 * 个人中心
 */

public class MineFragment extends BaseFragment<MinePresenter> implements View.OnClickListener {
    public CircleImageView iv_mine_user_logo;
    public ImageView iv_mine_user_sex;
    public TextView tv_user_username,tv_has_user;
    public ImageView iv_arrow_right;
    public RelativeLayout rl_user_all_order;
    public RelativeLayout lv_wait_pay;
    public RelativeLayout lv_wait_goods;
    public RelativeLayout lv_wait_accept_goods;
    public RelativeLayout lv_after_sale;
    public LinearLayout lv_mine_keep;
    public LinearLayout lv_mine_content_keep;
    public LinearLayout lv_mine_step;
    public LinearLayout lv_mine_shop_car;
    public LinearLayout lv_mine_scores;
    public LinearLayout lv_mine_server;
    public LinearLayout lv_mine_suggest;
    public LinearLayout lv_mine_setting;
    public LinearLayout layout_content;
    public PullToRefreshLayout pullToRefreshLayout;
    public LinearLayout lv_user;
    public TextView tv_wait_pay_count,tv_wait_goods_count,tv_wait_accept_goods_count,tv_after_sale_count,tv_recommend;

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mIvBackButton.setVisibility(View.GONE);
        activity.mTvActionBarTitle.setText("个人中心");
//        activity.mTvActionBarTitle.setTextColor(Color.WHITE);
        activity.mActionBar.setBackgroundColor(Color.parseColor("#f52a44"));
        activity.mIvActionBarMore.setVisibility(View.VISIBLE);
        activity.mIvActionBarMore.setImageResource(R.mipmap.btn_bg_set);
        activity.mIvActionBarMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip.toSetting(getActivity());
            }
        });
    }

    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_mine,null);
        layout_content= (LinearLayout)view. findViewById(R.id.layout_content);
        iv_mine_user_logo = (CircleImageView)view. findViewById(R.id.iv_mine_user_logo);
        iv_mine_user_sex = (ImageView)view. findViewById(R.id.iv_mine_user_sex);
        tv_user_username = (TextView)view. findViewById(R.id.tv_user_username);
        iv_arrow_right = (ImageView)view. findViewById(R.id.iv_arrow_right);
        rl_user_all_order = (RelativeLayout)view. findViewById(R.id.rl_user_all_order);
        lv_wait_pay = (RelativeLayout) view.findViewById(R.id.lv_wait_pay);
        lv_wait_goods = (RelativeLayout) view.findViewById(R.id.lv_wait_goods);
        lv_wait_accept_goods = (RelativeLayout)view. findViewById(R.id.lv_wait_accept_goods);
        lv_after_sale = (RelativeLayout) view.findViewById(R.id.lv_after_sale);
        lv_mine_keep = (LinearLayout)view. findViewById(R.id.lv_mine_keep);
        lv_mine_content_keep = (LinearLayout)view. findViewById(R.id.lv_mine_content_keep);
        lv_mine_step = (LinearLayout) view.findViewById(R.id.lv_mine_step);
        lv_mine_shop_car = (LinearLayout) view.findViewById(R.id.lv_mine_shop_car);
        lv_mine_scores = (LinearLayout)view. findViewById(R.id.lv_mine_scores);
        lv_mine_server = (LinearLayout) view.findViewById(R.id.lv_mine_server);
        lv_mine_suggest = (LinearLayout) view.findViewById(R.id.lv_mine_suggest);
        lv_mine_setting = (LinearLayout)view. findViewById(R.id.lv_mine_setting);
        tv_recommend= (TextView) view.findViewById(R.id.tv_recommend);
        tv_has_user= (TextView) view.findViewById(R.id.tv_has_user);
        tv_wait_pay_count= (TextView) view.findViewById(R.id.tv_wait_pay_count);
        tv_wait_goods_count= (TextView) view.findViewById(R.id.tv_wait_goods_count);
        tv_wait_accept_goods_count= (TextView)view. findViewById(R.id.tv_wait_accept_goods_count);
        tv_after_sale_count= (TextView)view. findViewById(R.id.tv_after_sale_count);
        lv_user = (LinearLayout)view. findViewById(R.id.lv_user);
        lv_user.setOnClickListener(this);
        pullToRefreshLayout= (PullToRefreshLayout)view. findViewById(R.id.pullrefresh);
        tv_has_user.setOnClickListener(this);
        rl_user_all_order.setOnClickListener(this);
        lv_wait_pay.setOnClickListener(this);
        lv_wait_goods.setOnClickListener(this);
        lv_wait_accept_goods.setOnClickListener(this);
        lv_after_sale.setOnClickListener(this);
        lv_mine_keep.setOnClickListener(this);
        lv_mine_step.setOnClickListener(this);
        lv_mine_shop_car.setOnClickListener(this);
        lv_mine_scores.setOnClickListener(this);
        lv_mine_server.setOnClickListener(this);
        lv_mine_suggest.setOnClickListener(this);
        lv_mine_setting.setOnClickListener(this);
        lv_mine_content_keep.setOnClickListener(this);


        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mPresenter.getData();


            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
        return view;
    }

    @Override
    public MinePresenter initPresenter() {
        return new MinePresenter();
    }


    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user_all_order: //所有 订单
                Skip.toOrder(getActivity(),0);
                break;
            case R.id.lv_wait_pay:  //待付款
                Skip.toOrder(getActivity(),1);
                break;
            case R.id.lv_wait_goods:   //待发货
                Skip.toOrder(getActivity(),2);
                break;
            case R.id.lv_wait_accept_goods:   //待收货
                Skip.toOrder(getActivity(),3);
                break;
            case R.id.lv_after_sale:   //售后
                Skip.toWebPage(getActivity(), Commons.API+Commons.AFTER_LIST+"?userid="+APP.sUserid,"售后");
                break;
            case R.id.lv_mine_keep:   //充值

                if (UserSeeion.isMember(getActivity())){
                    Skip.toDeposit(getActivity());  //充值
                }else {
                    if (UserSeeion.getSys_auth(getActivity()).equals("1")){
                        Skip.toPerfestData(getActivity());  //完善资料
                    }else {
                        Skip.toDeposit(getActivity());  //充值
                    }
                }

                break;
            case R.id.lv_mine_step:   //我的足迹
                Skip.toMyStep(getActivity());
                break;
            case R.id.lv_mine_shop_car:
                Logger.i("分享二维码");
                if (UserSeeion.isMember(getActivity())){
                    Skip.toWebPage(getActivity(), Commons.API + "/h5/tuijianma?userid=" + APP.sUserid + "&devicetype=android","分享二维码");
                }
                break;
            case R.id.lv_mine_scores:   //我的积分
                Intent intent =new Intent(getActivity(),GoldActivity.class);
                startActivity(intent);
                break;
            case R.id.lv_mine_server:   //消息
                Skip.toItemMenu(getActivity(),"-1");
                break;
            case R.id.lv_mine_suggest:  //我的建议
                Skip.toSuggest(getActivity());
                break;
            case R.id.lv_mine_setting:
                Skip.toWebPage(getActivity(),Commons.API+Commons.XJJUAN+"?userid="+APP.sUserid+"&devicetype=android","现金券");
                break;
            case R.id.lv_mine_content_keep:  //内容收藏
                Skip.toContentCollection(getActivity());
                break;
            case R.id.lv_user:
                Skip.toPerson(getActivity());
                break;
            case R.id.tv_has_user:
                Skip.toWebPage(getActivity(),Commons.API+Commons.FINDRECOMMENDCOUNT+"?userid="+APP.sUserid,"");
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
       mPresenter.getData();
    }
}
