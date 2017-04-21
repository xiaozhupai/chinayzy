package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.LoginPresenter;
import com.chinayiz.chinayzy.widget.LoadlingDialog;

/**
 * 注册登录
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener {


    public ImageView mIvBackButton;
    public TextView mTvActionbarTitle;
    public ImageView mIvMoreButton;
    public CheckBox mCbEditButton;
    public ImageView mIvShopcart;
    public RelativeLayout mRlActionBar;
    public EditText mEvLoginInputPhone;
    public TextView mTvLock;
    public EditText mEtLoginInputPassword;
    public ImageView mIvForgot;
    public View mVLine;
    public TextView mTvLoginSubmit;
    public TextView mTvRegisterSubmit;
    public ImageView mIvQq;
    public ImageView mIvWechat;
    public ImageView mIvWeibo;
    public LinearLayout mLvLogin;
    public LoadlingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);
        initView();
        setStatuBarColor(LoginActivity.this, Color.parseColor("#eb5c70"));

    }


    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_qq:  //QQ
//                mPresenter.toQQ();
//                break;
//            case R.id.iv_wechat:  //微信
//                mPresenter.toWechat();
//                break;
//            case R.id.iv_weibo:  //微博
//                mPresenter.toWeibo();
//                break;
            case R.id.iv_forgot:   //忘记密码
                mPresenter.toForgot();
                break;
            case R.id.tv_login_submit:    //登录提交
                mPresenter.login();
                break;
            case R.id.tv_register_submit:   //注册提交
                Skip.toMemberRuleFragment(getActivity());
                break;
            case R.id.iv_back_button:
                onBackPressed();
                break;
        }

    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void initView() {
        mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionbarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
        mIvMoreButton = (ImageView) findViewById(R.id.iv_more_button);
        mCbEditButton = (CheckBox) findViewById(R.id.cb_edit_button);
        mIvShopcart = (ImageView) findViewById(R.id.iv_shopcart);
        mRlActionBar = (RelativeLayout) findViewById(R.id.rl_ActionBar);
        mEvLoginInputPhone = (EditText) findViewById(R.id.ev_login_input_phone);
        mTvLock = (TextView) findViewById(R.id.tv_lock);
        mEtLoginInputPassword = (EditText) findViewById(R.id.et_login_input_password);
        mIvForgot = (ImageView) findViewById(R.id.iv_forgot);
        mVLine = (View) findViewById(R.id.v_line);
        mTvLoginSubmit = (TextView) findViewById(R.id.tv_login_submit);
        mTvRegisterSubmit = (TextView) findViewById(R.id.tv_register_submit);
//        mIvQq = (ImageView) findViewById(R.id.iv_qq);
//        mIvWechat = (ImageView) findViewById(R.id.iv_wechat);
//        mIvWeibo = (ImageView) findViewById(R.id.iv_weibo);
        mLvLogin = (LinearLayout) findViewById(R.id.lv_login);

        mIvBackButton.setOnClickListener(this);
        mIvMoreButton.setOnClickListener(this);
        mIvShopcart.setOnClickListener(this);
        mIvForgot.setOnClickListener(this);
        mTvLoginSubmit.setOnClickListener(this);
        mTvRegisterSubmit.setOnClickListener(this);
//        mIvQq.setOnClickListener(this);
//        mIvWechat.setOnClickListener(this);
//        mIvWeibo.setOnClickListener(this);

        mTvActionbarTitle.setText("登录");
        mIvMoreButton.setVisibility(View.GONE);
        mTvActionbarTitle.setTextColor(Color.parseColor("#1c1c1c"));
        mIvBackButton.setImageResource(R.mipmap.back_arrow);
//        mIvBackButton.setBackgroundColor(Color.parseColor("#f5f5f5"));
        mIvBackButton.setOnClickListener(this);
    }


}
