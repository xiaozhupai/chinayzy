package com.chinayiz.chinayzy.ui.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.BaseMessage;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.net.Commons;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/10 16:34
 * Class ResuestTakeFragment  积分提现
 */

public class ResuestTakeFragment extends AbsFragment implements View.OnClickListener {
    public final static String SEND_CODE = "ResuestTakeFragment";
    public final static String SEND_TIME = "ResuestTak";
    public final static String MSG="0";
    private EditText edit_alpayId;
    private EditText edit_money;
    private EditText edit_verifyNumb;
    private EditText edit_phoneNumb;
    private Button btn_sendMSG;
    private Button bt_affirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_take, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setTextColor(Color.BLACK);
        tv_actionbar_title.setText("提现");
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);
        edit_alpayId = (EditText) view.findViewById(R.id.edit_alpayId);
        edit_money = (EditText) view.findViewById(R.id.edit_money);
        edit_verifyNumb = (EditText) view.findViewById(R.id.edit_verifyNumb);
        edit_phoneNumb= (EditText) view.findViewById(R.id.edit_phoneNumb);
        btn_sendMSG = (Button) view.findViewById(R.id.btn_sendMSG);
        bt_affirm = (Button) view.findViewById(R.id.bt_affirm);

        btn_sendMSG.setOnClickListener(this);
        iv_back_button.setOnClickListener(this);
        bt_affirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button:
                getActivity().onBackPressed();
                break;
            case R.id.btn_sendMSG:
                Logger.i("点击获取验证码");
                String phone = edit_phoneNumb.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    BaseActivity.showToast(getActivity(), "手机号码不允许为空");
                    return;
                }
                if (phone.length()!=11){
                    BaseActivity.showToast(getActivity(), "手机号码错误");
                    return;
                }
                mRequestUtils.getVerifyCode(phone);
                btn_sendMSG.setClickable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventMessage message=new EventMessage(BaseMessage.NET_EVENT,SEND_TIME,"");
                        for (int i=60;i>-1;i--){
                            message.setData(String.valueOf(i));
                            try {
                                Thread.sleep(1000);
                            } catch (Exception  e) {
                                e.printStackTrace();
                            }
                            EventBus.getDefault().post(message);
                        }
                    }
                }).start();
                break;
            case R.id.bt_affirm:
                submit();
                break;
        }
    }

    private void submit() {
        String alpayId = edit_alpayId.getText().toString().trim();
        if (TextUtils.isEmpty(alpayId)) {
            BaseActivity.showToast(getActivity(), "账号不能为空");
            return;
        }
        String money = edit_money.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            BaseActivity.showToast(getActivity(), "金额不能为空");
            return;
        }
        String verifyNumb = edit_verifyNumb.getText().toString().trim();
        if (TextUtils.isEmpty(verifyNumb)) {
            BaseActivity.showToast(getActivity(), "验证码错误");
            return;
        }
        String phone = edit_phoneNumb.getText().toString().trim();
        if (TextUtils.isEmpty(alpayId)) {
            BaseActivity.showToast(getActivity(), "手机号码不允许为空");
            return;
        }
        mRequestUtils.requestTake(phone,money,verifyNumb,alpayId,"1");
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case ResuestTakeFragment.SEND_CODE:{
                BaseActivity.showToast(getActivity(),"获取验证码成功");
            }
            case ResuestTakeFragment.SEND_TIME:{
                if (MSG.equals(message.getData())){
                    btn_sendMSG.setText("获取验证码");
                    btn_sendMSG.setClickable(true);
                }else {
                    btn_sendMSG.setText(message.getData().toString()+"秒后再获取");
                }
                break;
            }
           case Commons.GET_GOLD:{
               ResponseModel model= (ResponseModel) message.getData();
               if ("100".equals(model.getCode())){

               }else {
                  BaseActivity.showToast(getActivity(),model.getMsg());
               }
                break;
            }
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

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
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }
}
