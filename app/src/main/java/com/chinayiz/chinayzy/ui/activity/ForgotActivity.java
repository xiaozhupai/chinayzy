package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.ForgotPresenter;

/**
 * 忘记密码
 */

public class ForgotActivity extends BaseActivity<ForgotPresenter> implements View.OnClickListener{
    public EditText et_forgot_input_phone;
    public ImageView iv__register_lock;
    public EditText et_forgot_input_message;
    public TextView tv_forgot_sendmessage,tv_forgot_submit,tv_forgot_pact;
    public View v_register_line;
    public EditText et_forgot_input_password;
    public EditText et_forgot_input_newpassword;

    @Override
    protected ForgotPresenter initPresenter() {
        return new ForgotPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        setStatuBarColor(ForgotActivity.this,Color.parseColor("#f5f5f5"));
        initView();
    }


    /**
     * 导航栏
     */
    @Override
    protected void initActionBar() {
        //actionbar
        mActionBar=findViewById(R.id.rl_ActionBar);
        mIvBackButton = (ImageView) findViewById(R.id.iv_back_button);
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_actionbar_title);
        mIvActionBarMore = (ImageView) findViewById(R.id.iv_more_button);
        mIvActionBarCart= (ImageView) findViewById(R.id.iv_shopcart);
        mCbActionBarEdit= (CheckBox) findViewById(R.id.cb_edit_button);
        mTvActionBarTitle.setText("找回密码");
        mIvActionBarMore.setVisibility(View.GONE);
        mTvActionBarTitle.setTextColor(Color.parseColor("#1c1c1c"));
        mIvBackButton.setImageResource(R.mipmap.back_arrow);
        mActionBar.setBackgroundColor(Color.parseColor("#f5f5f5"));
        mIvBackButton.setOnClickListener(this);
    }
    private void initView() {
        initActionBar();
        et_forgot_input_phone = (EditText) findViewById(R.id.et_forgot_input_phone);
        iv__register_lock = (ImageView) findViewById(R.id.iv__register_lock);
        et_forgot_input_message = (EditText) findViewById(R.id.et_forgot_input_message);
        tv_forgot_sendmessage = (TextView) findViewById(R.id.tv_forgot_sendmessage);
        v_register_line = (View) findViewById(R.id.v_register_line);
        et_forgot_input_password = (EditText) findViewById(R.id.et_forgot_input_password);
        et_forgot_input_newpassword = (EditText) findViewById(R.id.et_forgot_input_newpassword);
        tv_forgot_submit= (TextView) findViewById(R.id.tv_forgot_submit);
        tv_forgot_pact= (TextView) findViewById(R.id.tv_forgot_pact);
        tv_forgot_pact.setOnClickListener(this);
        tv_forgot_submit.setOnClickListener(this);
        tv_forgot_sendmessage.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forgot_pact:  //用户协议
                break;
            case R.id.tv_forgot_submit:  //忘记密码提交
                mPresenter.submit();
                break;
            case R.id.tv_forgot_sendmessage:   //发送验证码
                mPresenter.sendMessage();
                break;
            case R.id.iv_back_button:
                onBackPressed();
                break;
        }
    }


    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
