package com.chinayiz.chinayzy.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.ForgotPresenter;
import com.chinayiz.chinayzy.utils.TimeUntils;

/**
 * 忘记密码
 */

public class ForgotActivity extends BaseActivity<ForgotPresenter> implements View.OnClickListener,Handler.Callback {

    private static final int MSG_NUM=5;
    private EditText et_forgot_input_phone;
    private ImageView iv__register_lock;
    private EditText et_forgot_input_message;
    private TextView tv_forgot_sendmessage,tv_forgot_submit,tv_forgot_pact;
    private View v_register_line;
    private EditText et_forgot_input_password;
    private EditText et_forgot_input_newpassword;
    private Handler handler;
    private int num;

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
        initView();
        handler=new Handler(this);
    }

    private void initView() {
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

    private void submit() {
        // validate
        String phone = et_forgot_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = et_forgot_input_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_forgot_input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String newpassword = et_forgot_input_newpassword.getText().toString().trim();
        if (TextUtils.isEmpty(newpassword)) {
            Toast.makeText(this, "再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forgot_pact:
                break;
            case R.id.tv_forgot_submit:
                submit();
                break;
            case R.id.tv_forgot_sendmessage:
                TimeUntils timeUntils=new TimeUntils(handler);
                 timeUntils.RunTimer();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what==MSG_NUM){
            num=msg.arg1;
            if (num==0){
                tv_forgot_sendmessage.setText("发送验证码");
                tv_forgot_sendmessage.setClickable(true);
            }else {
                tv_forgot_sendmessage.setText(num+"后重新获取");
                tv_forgot_sendmessage.setClickable(false);
            }
        }
        return false;
    }

    @Override
    public Activity getActivity() {
        return null;
    }
}
