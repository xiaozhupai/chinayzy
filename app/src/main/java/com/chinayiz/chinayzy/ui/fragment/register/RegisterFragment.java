package com.chinayiz.chinayzy.ui.fragment.register;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
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
import com.chinayiz.chinayzy.views.CheckImageView;
import com.orhanobut.logger.Logger;

/**
 * 注册
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class RegisterFragment extends BaseFragment<RegisterPresenter> implements View.OnClickListener, SoftKeyboardStateHelper.SoftKeyboardStateListener {


    public EditText et_register_phone,et_register_wechat;
    public TextView tv_register_lock;
    public EditText et_register_message;
    public TextView tv_register_sendmessage;
    public View v_register_line;
    public EditText et_register_password,et_register_recommendcard;
    public TextView tv_register_submit;
    public TextView tv_member;
    public CheckImageView civ_check;


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
        et_register_recommendcard= (EditText) view.findViewById(R.id.et_register_recommendcard);
        et_register_wechat= (EditText) view.findViewById(R.id.et_register_wechat);
//        et_register_card= (EditText) view.findViewById(R.id.et_register_card);
//        et_register_truename= (EditText) view.findViewById(R.id.et_register_truename);
        tv_member= (TextView) view.findViewById(R.id.tv_member);
        civ_check= (CheckImageView) view.findViewById(R.id.civ_check);

        tv_register_submit.setOnClickListener(this);
        tv_register_sendmessage.setOnClickListener(this);
        tv_member.setOnClickListener(this);
        civ_check.setOnClickListener(this);
        tv_member.setText(Html.fromHtml("同意 <font color='#ff3952'>《用户协议》</font>"));
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
            case R.id.tv_member:
                mActivity.addFragment(new MemberProtocolFragment());
                break;
            case R.id.civ_check:
                if (civ_check.isCheck){
                    civ_check.setCheck(false);
                }else {
                    civ_check.setCheck(true);
                }
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
