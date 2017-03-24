package com.chinayiz.chinayzy.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.ForgotPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotFragment extends BaseFragment<ForgotPresenter> implements View.OnClickListener {


    private EditText et_forgot_input_phone;
    private ImageView iv__register_lock;
    private EditText et_forgot_input_message;
    private TextView tv_forgot_sendmessage;
    private View v_register_line;
    private EditText et_forgot_input_password;
    private EditText et_forgot_input_newpassword;

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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_forgot, null);
        et_forgot_input_phone = (EditText) view.findViewById(R.id.et_forgot_input_phone);
        et_forgot_input_phone.setOnClickListener(this);
        et_forgot_input_message = (EditText) view.findViewById(R.id.et_forgot_input_message);
        et_forgot_input_message.setOnClickListener(this);
        tv_forgot_sendmessage = (TextView) view.findViewById(R.id.tv_forgot_sendmessage);
        tv_forgot_sendmessage.setOnClickListener(this);
        v_register_line = (View) view.findViewById(R.id.v_register_line);
        v_register_line.setOnClickListener(this);
        et_forgot_input_password = (EditText) view.findViewById(R.id.et_forgot_input_password);
        et_forgot_input_password.setOnClickListener(this);
        et_forgot_input_newpassword = (EditText) view.findViewById(R.id.et_forgot_input_newpassword);
        et_forgot_input_newpassword.setOnClickListener(this);
        return view;
    }

    @Override
    public ForgotPresenter initPresenter() {
        return new ForgotPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
        View view = View.inflate(getActivity(), R.layout.activity_forgot, null);

        initView(inflater,container,savedInstanceState);
        return view;
    }

//    private void submit() {
//        // validate
//        String phone = et_forgot_input_phone.getText().toString().trim();
//        if (TextUtils.isEmpty(phone)) {
//            Toast.makeText(getContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String message = et_forgot_input_message.getText().toString().trim();
//        if (TextUtils.isEmpty(message)) {
//            Toast.makeText(getContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String password = et_forgot_input_password.getText().toString().trim();
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(getContext(), "请输入6-12位密码", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String newpassword = et_forgot_input_newpassword.getText().toString().trim();
//        if (TextUtils.isEmpty(newpassword)) {
//            Toast.makeText(getContext(), "再次输入密码", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // TODO validate success, do something
//
//
//    }

    @Override
    public void onClick(View v) {

    }
}
