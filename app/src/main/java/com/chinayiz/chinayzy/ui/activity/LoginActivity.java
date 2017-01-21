package  com.chinayiz.chinayzy.ui.activity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.LoginPresenter;
import com.chinayiz.chinayzy.utils.TimeUntils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 注册登录
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, PlatformActionListener, Callback{
    private static final int MSG_SMSSDK_CALLBACK = 1;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR = 3;
    private static final int MSG_AUTH_COMPLETE = 4;
    private static final int MSG_NUM=5;
    private Handler handler;
    private ImageView ivlogo;
    private TextView tv_left_login;
    private View v_left_line;
    private TextView tv_right_register;
    private View v_right_line;
    private EditText ev_login_input_phone;
    private ImageView iv_lock;
    private EditText et_login_input_password;
    private TextView tv_forgot;
    private View v_line;
    private ImageView iv_qq;
    private ImageView iv_wechat;
    private ImageView iv_weibo;
    private EditText et_register_input_phone;
    private ImageView iv__register_lock;
    private EditText et_register_input_message;
    private TextView tv_register_sendmessage;
    private View v_register_line;
    private EditText et_register_input_password;
    private LinearLayout lv_login,lv_register;
    private TextView tv_login_submit,tv_register_submit;
    private int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);
        initView();
        handler = new Handler(this);
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
            case R.id.iv_qq:
                //qq
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                authorize(qq);
                break;
            case R.id.iv_wechat:
                //微信
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                authorize(wechat);
                break;
            case R.id.iv_weibo:
                //新浪微博
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                authorize(sina);
                break;

            case R.id.tv_left_login:   //登录UI
                showLeft();
                break;
            case R.id.tv_right_register:    //注册UI
                showRight();
                break;
            case R.id.tv_forgot:   //忘记密码
                Intent intent=new Intent(this,ForgotActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_submit:    //登录提交
                login();
                break;
            case R.id.tv_register_submit:   //注册提交
                register();
                break;
            case R.id.tv_register_sendmessage:
                TimeUntils timeUntils=new TimeUntils(handler);
                 timeUntils.RunTimer();
                break;

        }
    }

    private boolean isPhone(String str){
        if (str.length()==11){
            return true;
        }else {
            return false;
        }
    }

    private void register() {
        String phone = et_register_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = et_register_input_message.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_register_input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void login() {
        String phone = ev_login_input_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)  ) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_login_input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * 左边视图显示
     */
    private void showLeft(){
        ivlogo.setVisibility(View.VISIBLE);
        lv_login.setVisibility(View.VISIBLE);
        lv_register.setVisibility(View.GONE);
        tv_left_login.setTextColor(Color.WHITE);
        tv_right_register.setTextColor(Color.BLACK);
        v_left_line.setVisibility(View.VISIBLE);
        v_right_line.setVisibility(View.GONE);
    }
    /**
     * 右边视图显示
     */
    private void showRight(){
        lv_register.setVisibility(View.VISIBLE);
        ivlogo.setVisibility(View.INVISIBLE);
        lv_login.setVisibility(View.GONE);
        tv_left_login.setTextColor(Color.BLACK);
        tv_right_register.setTextColor(Color.WHITE);
        v_left_line.setVisibility(View.GONE);
        v_right_line.setVisibility(View.VISIBLE);
    }

    //执行授权,获取用户信息
    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    /**
     * 回调完成
     * @param platform
     * @param action
     * @param res
     */
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[]{platform.getName(), res};
            handler.sendMessage(msg);
        }
    }

    /**
     * 回调错误
     * @param platform
     * @param action
     * @param t
     */
    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        t.printStackTrace();
    }

    /**
     * 回调取消
     * @param platform
     * @param action
     */
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_CANCEL: {
                //取消授权
                Toast.makeText(this, "取消授权", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
                //授权失败
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                //授权成功
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                Object[] objs = (Object[]) msg.obj;
                String platform = (String) objs[0];
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
            }
            break;
            case MSG_NUM:{
               num=msg.arg1;
                if (num==0){
                    tv_register_sendmessage.setText("发送验证码");
                    tv_register_sendmessage.setClickable(true);
                }else {
                    tv_register_sendmessage.setText(num+"后重新获取");
                    tv_register_sendmessage.setClickable(false);
                }
            }
            break;
        }
        return false;
    }


    private void initView() {
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
    }


    @Override
    public Activity getActivity() {
        return this;
    }
}
