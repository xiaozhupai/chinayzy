package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.presenter.MinePresenter;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.PersonFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SettingFragment;
import com.chinayiz.chinayzy.ui.fragment.mine.SuggestFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.views.CircleImageView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


/**
 * 个人中心
 */

public class MineActivity extends BaseActivity<MinePresenter> implements View.OnClickListener {
    public CircleImageView iv_mine_user_logo;
    public ImageView iv_mine_user_sex;
    public TextView tv_user_username;
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
    public TextView tv_wait_pay_count,tv_wait_goods_count,tv_wait_accept_goods_count,tv_after_sale_count;
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager=getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int backStackEntryCount=mFragmentManager.getBackStackEntryCount();
                if (backStackEntryCount==0){
                    mTvActionBarTitle.setText("个人中心");
                  return;
                }
                if (backStackEntryCount>0){
                    FragmentManager.BackStackEntry backStack=mFragmentManager.getBackStackEntryAt(backStackEntryCount-1);
                    String name=backStack.getName();
                 BaseFragment fragment= (BaseFragment) mFragmentManager.findFragmentByTag(name);
                    fragment.onInitActionBar(MineActivity.this);
                    Logger.i("OnBackPressed---------------------------------------------------"+name);
                }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine);
        setStatuBarColor(MineActivity.this, Color.rgb(255, 255, 255));
        initView();
    }

    public void initView() {

        findViewById(R.id.loadlayout).setVisibility(View.GONE);
        //actionbar
        mActionBar=findViewById(R.id.rl_ActionBar);
        mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
        mIvActionBarMore = (ImageView) findViewById(R.id.iv_more_button);
        mIvActionBarCart= (ImageView) findViewById(R.id.iv_shopcart);
        mCbActionBarEdit= (CheckBox) findViewById(R.id.cb_edit_button);
        mTvActionBarTitle.setText("个人中心");
        mIvActionBarMore.setVisibility(View.GONE);
        mTvActionBarTitle.setTextColor(getResources().getColor(R.color.white));
        mIvBackButton.setImageResource(R.mipmap.back_arrow);
        mActionBar.setBackgroundColor(Color.parseColor("#ff3951"));
        mIvBackButton.setOnClickListener(this);

        layout_content= (LinearLayout) findViewById(R.id.layout_content);
        iv_mine_user_logo = (CircleImageView) findViewById(R.id.iv_mine_user_logo);
        iv_mine_user_sex = (ImageView) findViewById(R.id.iv_mine_user_sex);
        tv_user_username = (TextView) findViewById(R.id.tv_user_username);
        iv_arrow_right = (ImageView) findViewById(R.id.iv_arrow_right);
        rl_user_all_order = (RelativeLayout) findViewById(R.id.rl_user_all_order);
        lv_wait_pay = (RelativeLayout) findViewById(R.id.lv_wait_pay);
        lv_wait_goods = (RelativeLayout) findViewById(R.id.lv_wait_goods);
        lv_wait_accept_goods = (RelativeLayout) findViewById(R.id.lv_wait_accept_goods);
        lv_after_sale = (RelativeLayout) findViewById(R.id.lv_after_sale);
        lv_mine_keep = (LinearLayout) findViewById(R.id.lv_mine_keep);
        lv_mine_content_keep = (LinearLayout) findViewById(R.id.lv_mine_content_keep);
        lv_mine_step = (LinearLayout) findViewById(R.id.lv_mine_step);
        lv_mine_shop_car = (LinearLayout) findViewById(R.id.lv_mine_shop_car);
        lv_mine_scores = (LinearLayout) findViewById(R.id.lv_mine_scores);
        lv_mine_server = (LinearLayout) findViewById(R.id.lv_mine_server);
        lv_mine_suggest = (LinearLayout) findViewById(R.id.lv_mine_suggest);
        lv_mine_setting = (LinearLayout) findViewById(R.id.lv_mine_setting);

        tv_wait_pay_count= (TextView) findViewById(R.id.tv_wait_pay_count);
        tv_wait_goods_count= (TextView) findViewById(R.id.tv_wait_goods_count);
        tv_wait_accept_goods_count= (TextView) findViewById(R.id.tv_wait_accept_goods_count);
        tv_after_sale_count= (TextView) findViewById(R.id.tv_after_sale_count);
        lv_user = (LinearLayout) findViewById(R.id.lv_user);
        lv_user.setOnClickListener(this);
        pullToRefreshLayout= (PullToRefreshLayout) findViewById(R.id.pullrefresh);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user_all_order: //所有 订单

                break;
            case R.id.lv_wait_pay:  //待付款

                break;
            case R.id.lv_wait_goods:   //待发货

                break;
            case R.id.lv_wait_accept_goods:   //待收货

                break;
            case R.id.lv_after_sale:   //售后

                break;
            case R.id.lv_mine_keep:   //宝贝收藏

                break;
            case R.id.lv_mine_step:   //我的足迹

                break;
            case R.id.lv_mine_shop_car:  //购物车
            addFragment(new ShopCartFragment(1));
                break;
            case R.id.lv_mine_scores:   //我的积分
                Intent intent =new Intent(this,GoldActivity.class);
                startActivity(intent);
                break;
            case R.id.lv_mine_server:   //消息

                break;
            case R.id.lv_mine_suggest:  //我的建议
                addFragment(new SuggestFragment());
                break;
            case R.id.lv_mine_setting:  //设置
                addFragment(new SettingFragment());
                break;
            case R.id.lv_mine_content_keep:  //内容收藏

                break;
            case R.id.iv_back_button:
                onBackPressed();
                break;
            case R.id.lv_user:
               addFragment( new PersonFragment());
            break;
        }
    }

    //个人中心fragment跳转
    public void addFragment( Fragment fragment) {
      Class <?> tag=fragment.getClass();

        try {
            mFragmentManager.beginTransaction().add(R.id.container, fragment,tag.getSimpleName()).addToBackStack(fragment.getTag()).commit();
        } catch (Exception e) {
            e.printStackTrace();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.container,
                    fragment, tag.getSimpleName())
                    .addToBackStack(tag.getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.i("onActivityResult---mine");
        EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,requestCode+"",data));
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void OnBackPressed(){
        super.onBackPressed();

    }
}
