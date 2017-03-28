package com.chinayiz.chinayzy.ui.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/13 10:28
 * Class PrestoreFragment  积分充值
 */

public class PrestoreFragment extends AbsFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private EditText edit_moneys;
    private Button bt_inPut;
    private RadioGroup mRadioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestore, container, false);
        initView(view);
        return view;
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }


    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    public void initView(View view) {
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setTextColor(Color.BLACK);
        tv_actionbar_title.setText("充值");
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);

        edit_moneys = (EditText) view.findViewById(R.id.edit_moneys);
        mRadioGroup=((RadioGroup)view.findViewById(R.id.rg_payType));
        mRadioGroup.setOnCheckedChangeListener(this);
        ((RadioButton)mRadioGroup.findViewById(R.id.rb_alipay)).setChecked(true);
        bt_inPut = (Button) view.findViewById(R.id.bt_inPut);
        bt_inPut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_button:{
                getActivity().onBackPressed();
                break;
            }
            case R.id.bt_inPut:{
                if (TextUtils.isEmpty(edit_moneys.getText().toString())){
                    BaseActivity.showToast(getActivity(),"输入正确金额");
                    return;
                }
                mRequestUtils.getWxPayOrder("1",edit_moneys.getText().toString(),"");

                Logger.i("确认充值");

                break;
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(getActivity(), "未知错误，请重试");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.rb_weicpay:{
                Logger.i("微信支付");
                break;
            }
            case R.id.rb_alipay:{
                Logger.i("支付宝支付");
                break;
            }
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    private void submit() {
        String moneys = edit_moneys.getText().toString().trim();
        if (TextUtils.isEmpty(moneys)) {
            return;
        }

    }
}
