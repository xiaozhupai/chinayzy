package com.chinayiz.chinayzy.ui.fragment.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.RegisterPresenter;
import com.chinayiz.chinayzy.utils.SoftKeyboardStateHelper;
import com.orhanobut.logger.Logger;

/**
 * 注册
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<RegisterPresenter> implements View.OnClickListener, SoftKeyboardStateHelper.SoftKeyboardStateListener {


    public EditText et_register_phone;
    public TextView tv_register_lock;
    public EditText et_register_message;
    public TextView tv_register_sendmessage;
    public View v_register_line;
    public EditText et_register_password;
    public TextView tv_register_submit;

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("注册");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_register,null);
        et_register_phone = (EditText)view.findViewById(R.id.et_register_phone);
        tv_register_lock = (TextView) view.findViewById(R.id.tv_register_lock);
        et_register_message = (EditText) view.findViewById(R.id.et_register_message);
        tv_register_sendmessage = (TextView) view.findViewById(R.id.tv_register_sendmessage);
        v_register_line = (View) view.findViewById(R.id.v_register_line);
        et_register_password = (EditText)view.findViewById(R.id.et_register_password);
        tv_register_submit = (TextView)view.findViewById(R.id.tv_register_submit);
        tv_register_submit.setOnClickListener(this);
        tv_register_sendmessage.setOnClickListener(this);

        SoftKeyboardStateHelper softKeyboardStateHelper=new SoftKeyboardStateHelper(view);
        softKeyboardStateHelper.addSoftKeyboardStateListener(this);
        return view;

    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter();
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
            case R.id.tv_register_submit:
                mPresenter.submit();

                break;
            case R.id.tv_register_sendmessage:
              mPresenter.sendMessage();
                break;
        }
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        Logger.i("键盘打开了");
    }

    @Override
    public void onSoftKeyboardClosed() {
        Logger.i("键盘关闭了");
    }
}
