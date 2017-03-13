package  com.chinayiz.chinayzy.ui.activity;
import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.LoginPresenter;

/**
 * 注册登录
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener{
    public ImageView ivlogo;
    public TextView tv_left_login;
    public View v_left_line;
    public TextView tv_right_register;
    public View v_right_line;
    public EditText ev_login_input_phone;
    public ImageView iv_lock;
    public EditText et_login_input_password;
    public TextView tv_forgot;
    public View v_line;
    public ImageView iv_qq;
    public ImageView iv_wechat;
    public ImageView iv_weibo;
    public EditText et_register_input_phone;
    public ImageView iv__register_lock;
    public EditText et_register_input_message;
    public TextView tv_register_sendmessage;
    public View v_register_line;
    public EditText et_register_input_password;
    public LinearLayout lv_login,lv_register;
    public TextView tv_login_submit,tv_register_submit;
    public ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);
        setStatuBarColor(LoginActivity.this, Color.parseColor("#eb5c70"));
        initView();
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
            case R.id.iv_qq:  //QQ
               mPresenter.toQQ();
                break;
            case R.id.iv_wechat:  //微信
              mPresenter.toWechat();
                break;
            case R.id.iv_weibo:  //微博
                mPresenter.toWeibo();
                break;
            case R.id.tv_left_login:   //登录UI
                mPresenter.showLeft();
                break;
            case R.id.tv_right_register:    //注册UI
                mPresenter.showRight();
                break;
            case R.id.tv_forgot:   //忘记密码
               mPresenter.toForgot();
                break;
            case R.id.tv_login_submit:    //登录提交
              mPresenter.login();
                break;
            case R.id.tv_register_submit:   //注册提交
                mPresenter.register();
                break;
            case R.id.tv_register_sendmessage: //发送验证码
              mPresenter.sendMessage();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }



    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        ivlogo = (ImageView) findViewById(R.id.ivlogo);
        tv_left_login = (TextView) findViewById(R.id.tv_left_login);
        v_left_line = (View) findViewById(R.id.v_left_line);
        tv_right_register = (TextView) findViewById(R.id.tv_right_register);
        v_right_line = (View) findViewById(R.id.v_right_line);
        ev_login_input_phone = (EditText) findViewById(R.id.ev_login_input_phone);
        iv_lock = (ImageView) findViewById(R.id.iv_lock);
        et_login_input_password = (EditText) findViewById(R.id.et_login_input_password);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        v_line = (View) findViewById(R.id.v_line);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_wechat = (ImageView) findViewById(R.id.iv_wechat);
        iv_weibo = (ImageView) findViewById(R.id.iv_weibo);
        et_register_input_phone = (EditText) findViewById(R.id.et_register_input_phone);
        iv__register_lock = (ImageView) findViewById(R.id.iv__register_lock);
        et_register_input_message = (EditText) findViewById(R.id.et_register_input_message);
        tv_register_sendmessage = (TextView) findViewById(R.id.tv_register_sendmessage);
        v_register_line = (View) findViewById(R.id.v_register_line);
        et_register_input_password = (EditText) findViewById(R.id.et_register_input_password);
        lv_login= (LinearLayout) findViewById(R.id.lv_login);
        lv_register= (LinearLayout) findViewById(R.id.lv_register);
        tv_login_submit= (TextView) findViewById(R.id.tv_login_submit);
        tv_register_submit= (TextView) findViewById(R.id.tv_register_submit);
        ivlogo.setOnClickListener(this);
        tv_left_login.setOnClickListener(this);
        tv_right_register.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_wechat.setOnClickListener(this);
        iv_weibo.setOnClickListener(this);
        tv_login_submit.setOnClickListener(this);
        tv_register_submit.setOnClickListener(this);
        tv_register_sendmessage.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
